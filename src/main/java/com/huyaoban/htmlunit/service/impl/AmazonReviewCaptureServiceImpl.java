package com.huyaoban.htmlunit.service.impl;

import java.text.MessageFormat;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.google.common.collect.Lists;
import com.huyaoban.htmlunit.config.RabbitmqConfiguration;
import com.huyaoban.htmlunit.model.AmazonReview;
import com.huyaoban.htmlunit.model.AsinInfo;
import com.huyaoban.htmlunit.model.AsinReviewPageInfo;
import com.huyaoban.htmlunit.model.CountryInfo;
import com.huyaoban.htmlunit.model.ReviewInfo;
import com.huyaoban.htmlunit.service.AmazonReviewCaptureService;
import com.huyaoban.htmlunit.service.AmazonReviewParseService;
import com.huyaoban.htmlunit.util.CountryInfoUtil;
import com.huyaoban.htmlunit.util.WebClientUtil;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AmazonReviewCaptureServiceImpl implements AmazonReviewCaptureService {
	@Autowired
	private ApplicationContext context;

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@RabbitListener(queues = RabbitmqConfiguration.ASIN_INFO_QUEUE, containerFactory = "asinInfoContainerFactory")
	@Override
	public void captureReviewForAsin(AsinInfo asinInfo) throws Exception {
		CountryInfo countryInfo = CountryInfoUtil.getCountryInfo(asinInfo.getCountryCode().toUpperCase());
		WebClient webClient = WebClientUtil.buildWebClient(countryInfo);

		String firstPageLink = MessageFormat.format(countryInfo.getReviewPageLinkTemplate(), asinInfo.getAsin(), 1,
				countryInfo.getPageSize());

		HtmlPage firstPage = webClient.getPage(firstPageLink);
		Document doc = Jsoup.parse(firstPage.asXml(), countryInfo.getBaseUrl());

		// 获取review总数，并计算页数
		Element totalReviewCountEle = doc.selectFirst("span.totalReviewCount");
		AmazonReviewParseService amazonReviewParseService = (AmazonReviewParseService) context
				.getBean(countryInfo.getReviewParserService());
		Integer totalReviewCount = amazonReviewParseService.parseTotalReviewCount(totalReviewCountEle);
		Integer pageCount = totalReviewCount / countryInfo.getPageSize() + 1;

		for (int pageNo = 1; pageNo <= pageCount; pageNo++) {
			String reviewPageLink = MessageFormat.format(countryInfo.getReviewPageLinkTemplate(), asinInfo.getAsin(),
					String.valueOf(pageNo), countryInfo.getPageSize());

			AsinReviewPageInfo asinReviewPageInfo = new AsinReviewPageInfo(asinInfo.getAsin(),
					countryInfo.getCountryCode(), pageNo, reviewPageLink);
			rabbitTemplate.convertAndSend(RabbitmqConfiguration.ASIN_REVIEW_PAGE_INFO_QUEUE_KEY, asinReviewPageInfo);
		}

		webClient.close();
	}

	@RabbitListener(queues = RabbitmqConfiguration.ASIN_REVIEW_PAGE_INFO_QUEUE, containerFactory = "asinReviewPageInfoContainerFactory")
	@Override
	public void captureReviewByPage(AsinReviewPageInfo asinReviewPageInfo) throws Exception {
		CountryInfo countryInfo = CountryInfoUtil.getCountryInfo(asinReviewPageInfo.getCountryCode().toUpperCase());
		WebClient webClient = WebClientUtil.buildWebClient(countryInfo);

		HtmlPage reviewPage = webClient.getPage(asinReviewPageInfo.getReviewPageLink());
		parseReviewFromHtml(countryInfo, asinReviewPageInfo, reviewPage);

		webClient.close();
	}

	@RabbitListener(queues = RabbitmqConfiguration.REVIEW_INFO_QUEUE, containerFactory = "reviewInfoContainerFactory")
	@Override
	public void captureSingleReview(ReviewInfo reviewInfo) throws Exception {
		CountryInfo countryInfo = CountryInfoUtil.getCountryInfo(reviewInfo.getCountryCode().toUpperCase());
		WebClient webClient = WebClientUtil.buildWebClient(countryInfo);

		String reviewPath = MessageFormat.format(countryInfo.getReviewPathTemplate(), reviewInfo.getReviewId(),
				reviewInfo.getAsin());
		webClient.addRequestHeader(":path", reviewPath);

		HtmlPage page = webClient.getPage(reviewInfo.getReviewLink());
		parseReviewFromHtml(countryInfo, reviewInfo, page);

		webClient.close();
	}

	@RabbitListener(queues = RabbitmqConfiguration.REVIEW_INFO_CHECK_DELETE_QUEUE, containerFactory = "reviewInfoCheckDeleteContainerFactory")
	@Override
	public void checkReviewStatus(ReviewInfo reviewInfo) throws Exception {
		CountryInfo countryInfo = CountryInfoUtil.getCountryInfo(reviewInfo.getCountryCode().toUpperCase());
		WebClient webClient = WebClientUtil.buildWebClient(countryInfo);

		String reviewPath = MessageFormat.format(countryInfo.getReviewPathTemplate(), reviewInfo.getReviewId(),
				reviewInfo.getAsin());
		webClient.addRequestHeader(":path", reviewPath);

		HtmlPage page = webClient.getPage(reviewInfo.getReviewLink());
		parseReviewFromHtml(countryInfo, reviewInfo, page);

		webClient.close();
	}

	private List<AmazonReview> parseReviewFromHtml(CountryInfo countryInfo, AsinReviewPageInfo asinReviewPageInfo,
			HtmlPage page) {
		Document doc = Jsoup.parse(page.asXml(), countryInfo.getBaseUrl());
		Element reviewListElement = doc.getElementById("cm_cr-review_list");
		Elements reviewElements = reviewListElement.getElementsByAttributeValue("data-hook", "review");
		List<AmazonReview> reviewList = Lists.newArrayList();

		AmazonReviewParseService amazonReviewParseService = (AmazonReviewParseService) context
				.getBean(countryInfo.getReviewParserService());
		for (Element reviewElement : reviewElements) {
			AmazonReview amazonReview = amazonReviewParseService.parseReviewInfo(asinReviewPageInfo.getAsin(),
					reviewElement);
			reviewList.add(amazonReview);
			log.info("{}", amazonReview);
		}

		return reviewList;
	}

	private AmazonReview parseReviewFromHtml(CountryInfo countryInfo, ReviewInfo reviewInfo, HtmlPage page) {
		Document doc = Jsoup.parse(page.asXml(), countryInfo.getBaseUrl());
		Element reviewList = doc.getElementById("cm_cr-review_list");
		Elements reviews = reviewList.getElementsByAttributeValue("data-hook", "review");

		AmazonReviewParseService amazonReviewParseService = (AmazonReviewParseService) context
				.getBean(countryInfo.getReviewParserService());
		AmazonReview amazonReview = amazonReviewParseService.parseReviewInfo(reviewInfo.getAsin(), reviews.first());
		log.info("{}", amazonReview);

		return amazonReview;
	}

}
