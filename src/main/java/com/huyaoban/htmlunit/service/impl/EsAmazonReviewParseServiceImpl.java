package com.huyaoban.htmlunit.service.impl;

import java.util.Locale;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import com.google.common.base.Splitter;

import lombok.extern.slf4j.Slf4j;

@Service("esAmazonReviewParseService")
@Slf4j
public class EsAmazonReviewParseServiceImpl extends DefaultAmazonReviewParseServiceImpl {

	@Override
	public void afterPropertiesSet() throws Exception {
		// 12 de enero de 2018
		dateTimeFormatter = DateTimeFormat.forPattern("d MMM yyyy").withLocale(new Locale("es", "ES"));
	}

	@Override
	public Integer parseHelpfulVotes(Element reviewInfoDiv) {
		Element helpfulVotes = reviewInfoDiv.selectFirst("span.cr-vote-text");
		if (helpfulVotes == null) {
			return 0;
		}

		log.info("parse helpful votes for review {}", reviewInfoDiv.attr("id"));

		String votesStr = helpfulVotes.text();
		String votes = Splitter.on(" ").trimResults().omitEmptyStrings().splitToList(votesStr).get(1);
		if ("UNA".equals(votes.toUpperCase())) {
			return 1;
		} else {
			return Integer.valueOf(votes);
		}
	}

	@Override
	public Integer parseTotalReviewCount(Element totalReviewCountElement) {
		String totalReviewCountStr = totalReviewCountElement.text();
		// 去掉千分位
		totalReviewCountStr = totalReviewCountStr.replace(".", "");
		return Integer.valueOf(totalReviewCountStr);
	}

	@Override
	public String parseReviewDate(Element reviewInfoDiv) {
		// 12 de enero de 2018
		Element reviewDateElement = reviewInfoDiv.selectFirst("span.review-date");
		LocalDate reviewDate = LocalDate.parse(reviewDateElement.text().replace(" de ", " "), dateTimeFormatter);

		return reviewDate.toString();
	}

}
