package com.huyaoban.htmlunit.service.impl;

import java.net.URL;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.huyaoban.htmlunit.config.RabbitmqConfiguration;
import com.huyaoban.htmlunit.model.AmazonReview;
import com.huyaoban.htmlunit.model.AmazonReviewPageInfo;
import com.huyaoban.htmlunit.model.AsinReviewInfo;
import com.huyaoban.htmlunit.model.ReviewInfo;
import com.huyaoban.htmlunit.service.AmazonReviewCaptureService;
import com.huyaoban.htmlunit.service.AmazonReviewParseService;
import com.huyaoban.htmlunit.util.WebClientUtil;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AmazonReviewCaptureServiceImpl implements AmazonReviewCaptureService {
	private String url = "https://www.amazon.com/product-reviews/B074QLZBRL/ref=cm_cr_getr_d_paging_btm_next_1?ie=UTF8&reviewerType=all_reviews&pageNumber=1&pageSize=10";
	private String asin = "B074QLZBRL";

	@Autowired
	private AmazonReviewParseService amazonReviewParseService;

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Override
	public void captureAmazonReview(String asin) throws Exception {
		WebClient webClient = WebClientUtil.buildWebClient();
		
		WebRequest request = new WebRequest(new URL(url));
		Map<String, String> headerMap = new HashMap<String, String>();
		headerMap.put(":authority", "www.amazon.com");
		headerMap.put(":method", "GET");
		// headerMap.put(":path",
		// "/RAVPower-Charger-Charging-Compatible-Nintendo/product-reviews/B074QLZBRL/ref=cm_cr_dp_d_show_all_top?ie=UTF8&reviewerType=all_reviews");
		headerMap.put(":scheme", "https");
		headerMap.put("accept",
				"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
		headerMap.put("accept-encoding", "gzip, deflate, br");
		headerMap.put("accept-language", "zh-CN,zh;q=0.9,en;q=0.8");
		headerMap.put("cache-control", "no-cache");
		headerMap.put("pragma", "no-cache");
		// headerMap.put("referer",
		// "https://www.amazon.com/RAVPower-Charger-Charging-Compatible-Nintendo/dp/B074QLZBRL/ref=cm_cr_srp_d_product_top?ie=UTF8");
		headerMap.put("upgrade-insecure-requests", "1");
		headerMap.put("user-agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.119 Safari/537.36");
		request.setAdditionalHeaders(headerMap);

		HtmlPage page = webClient.getPage(request);

		Boolean hasNextPage = Boolean.TRUE;
		do {
			parseResult(page.asXml());
			HtmlElement pages = page.getHtmlElementById("cm_cr-pagination_bar");
			DomNodeList<HtmlElement> domNodeList = pages.getElementsByTagName("li");
			HtmlElement next = domNodeList.get(1);
			String nextClass = next.getAttribute("class");
			if (nextClass.contains("a-disabled")) {
				hasNextPage = Boolean.FALSE;
			} else {
				DomElement nextPage = next.getLastElementChild();
				page = nextPage.click();
				// webClient.waitForBackgroundJavaScript(29 * 1000);
			}
		} while (hasNextPage);
	}

	private void parseResult(String resultXml) {
		Document doc = Jsoup.parse(resultXml, "https://www.amazon.com/");
		Element reviewList = doc.getElementById("cm_cr-review_list");
		Elements reviews = reviewList.getElementsByAttributeValue("data-hook", "review");
		for (Element review : reviews) {
			AmazonReview amazonReview = amazonReviewParseService.parseReviewInfo(asin, review);
			log.info("{}", amazonReview);
		}
	}

	@Override
	public void captureAmazonReview(AmazonReviewPageInfo reviewPageInfo) throws Exception {
		WebClient webClient = WebClientUtil.buildWebClient();
		WebRequest request = new WebRequest(new URL(reviewPageInfo.getReviewPageUrl()));
		Map<String, String> headerMap = new HashMap<String, String>();
		headerMap.put(":authority", "www.amazon.com");
		headerMap.put(":method", "GET");
		// headerMap.put(":path",
		// "/RAVPower-Charger-Charging-Compatible-Nintendo/product-reviews/B074QLZBRL/ref=cm_cr_dp_d_show_all_top?ie=UTF8&reviewerType=all_reviews");
		headerMap.put(":scheme", "https");
		headerMap.put("accept",
				"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
		headerMap.put("accept-encoding", "gzip, deflate, br");
		headerMap.put("accept-language", "zh-CN,zh;q=0.9,en;q=0.8");
		headerMap.put("cache-control", "no-cache");
		headerMap.put("pragma", "no-cache");
		// headerMap.put("referer",
		// "https://www.amazon.com/RAVPower-Charger-Charging-Compatible-Nintendo/dp/B074QLZBRL/ref=cm_cr_srp_d_product_top?ie=UTF8");
		headerMap.put("upgrade-insecure-requests", "1");
		headerMap.put("user-agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.119 Safari/537.36");
		request.setAdditionalHeaders(headerMap);

		HtmlPage page = webClient.getPage(request);

		parseResult(page.asXml());
	}

	@Override
	public void captureAmazonReviewPageInfo(AsinReviewInfo asinReviewInfo) throws Exception {
		WebClient webClient = WebClientUtil.buildWebClient();
		WebRequest request = new WebRequest(new URL(asinReviewInfo.getReviewUrl()));
		Map<String, String> headerMap = new HashMap<String, String>();
		headerMap.put(":authority", "www.amazon.com");
		headerMap.put(":method", "GET");
		// headerMap.put(":path",
		// "/RAVPower-Charger-Charging-Compatible-Nintendo/product-reviews/B074QLZBRL/ref=cm_cr_dp_d_show_all_top?ie=UTF8&reviewerType=all_reviews");
		headerMap.put(":scheme", "https");
		headerMap.put("accept",
				"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
		headerMap.put("accept-encoding", "gzip, deflate, br");
		headerMap.put("accept-language", "zh-CN,zh;q=0.9,en;q=0.8");
		headerMap.put("cache-control", "no-cache");
		headerMap.put("pragma", "no-cache");
		// headerMap.put("referer",
		// "https://www.amazon.com/RAVPower-Charger-Charging-Compatible-Nintendo/dp/B074QLZBRL/ref=cm_cr_srp_d_product_top?ie=UTF8");
		headerMap.put("upgrade-insecure-requests", "1");
		headerMap.put("user-agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.119 Safari/537.36");
		request.setAdditionalHeaders(headerMap);

		HtmlPage page = webClient.getPage(request);
		Document doc = Jsoup.parse(page.asXml(), "https://www.amazon.com/");
		Element totalReviewCountEle = doc.selectFirst("span.totalReviewCount");
		String totalReviewCountStr = totalReviewCountEle.text();
		totalReviewCountStr = totalReviewCountStr.replace(",", "");
		Integer totalReviewCount = Integer.valueOf(totalReviewCountStr);
		Integer pageCount = totalReviewCount / asinReviewInfo.getPageSize() + 1;
		for (int pageNo = 1; pageNo <= pageCount; pageNo++) {
			String reviewPageUrl = MessageFormat.format(asinReviewInfo.getReviewUrlTmplt(), String.valueOf(pageNo),
					asinReviewInfo.getPageSize());

			AmazonReviewPageInfo reviewPageInfo = new AmazonReviewPageInfo(asinReviewInfo.getAsin(), pageNo,
					reviewPageUrl);
			rabbitTemplate.convertAndSend(RabbitmqConfiguration.REVIEW_PAGE_INFO_QUEUE_ROUTING_KEY, reviewPageInfo);
		}
	}

	@Override
	public void captureSingleReview(ReviewInfo reviewInfo) throws Exception {
		WebClient webClient = WebClientUtil.buildWebClient();
		WebRequest request = new WebRequest(new URL(reviewInfo.getReviewLink()));
		Map<String, String> headerMap = new HashMap<String, String>();
		headerMap.put(":authority", "www.amazon.com");
		headerMap.put(":method", "GET");
		// 爬取单个review时需要带上:path参数
		headerMap.put(":path", "/gp/customer-reviews/R1MTRY6JPU9J3A/ref=cm_cr_arp_d_rvw_ttl?ie=UTF8&ASIN=B074QLZBRL");
		headerMap.put(":scheme", "https");
		headerMap.put("accept",
				"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
		headerMap.put("accept-encoding", "gzip, deflate, br");
		headerMap.put("accept-language", "zh-CN,zh;q=0.9,en;q=0.8");
		headerMap.put("cache-control", "no-cache");
		headerMap.put("pragma", "no-cache");
		// headerMap.put("referer",
		// "https://www.amazon.com/RAVPower-Charger-Charging-Compatible-Nintendo/dp/B074QLZBRL/ref=cm_cr_srp_d_product_top?ie=UTF8");
		headerMap.put("upgrade-insecure-requests", "1");
		headerMap.put("user-agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.119 Safari/537.36");
		request.setAdditionalHeaders(headerMap);

		HtmlPage page = webClient.getPage(request);
		parseResult(page.asXml());
	}

}
