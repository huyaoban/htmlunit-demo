package com.huyaoban.htmlunit.service.impl;

import java.util.Locale;

import org.joda.time.format.DateTimeFormat;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import com.google.common.base.Splitter;

import lombok.extern.slf4j.Slf4j;

@Service("deAmazonReviewParseService")
@Slf4j
public class DeAmazonReviewParseServiceImpl extends DefaultAmazonReviewParseServiceImpl {

	@Override
	public void afterPropertiesSet() throws Exception {
		// 21. November 2018
		dateTimeFormatter = DateTimeFormat.forPattern("dd. MMM yyyy").withLocale(Locale.GERMANY);
	}

	@Override
	public Integer parseHelpfulVotes(Element reviewInfoDiv) {
		Element helpfulVotes = reviewInfoDiv.selectFirst("span.cr-vote-text");
		if (helpfulVotes == null) {
			return 0;
		}

		log.info("parse helpful votes for review {}", reviewInfoDiv.attr("id"));

		String votesStr = helpfulVotes.text();
		String votes = Splitter.on(" ").trimResults().omitEmptyStrings().splitToList(votesStr).get(0);
		if ("EINE".equals(votes.toUpperCase())) {
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

}
