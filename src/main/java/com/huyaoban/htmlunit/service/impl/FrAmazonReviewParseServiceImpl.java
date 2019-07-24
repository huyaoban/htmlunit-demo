package com.huyaoban.htmlunit.service.impl;

import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import com.google.common.base.Splitter;

import lombok.extern.slf4j.Slf4j;

@Service("frAmazonReviewParseService")
@Slf4j
public class FrAmazonReviewParseServiceImpl extends DefaultAmazonReviewParseServiceImpl {

	@Override
	public Integer parseHelpfulVotes(Element reviewInfoDiv) {
		Element helpfulVotes = reviewInfoDiv.selectFirst("span.cr-vote-text");
		if (helpfulVotes == null) {
			return 0;
		}

		log.info("parse helpful votes for review {}", reviewInfoDiv.attr("id"));

		String votesStr = helpfulVotes.text();
		String votes = Splitter.on(" ").trimResults().omitEmptyStrings().splitToList(votesStr).get(0);
		if ("UNE".equals(votes.toUpperCase())) {
			return 1;
		} else {
			return Integer.valueOf(votes);
		}
	}

	@Override
	public Integer parseTotalReviewCount(Element totalReviewCountElement) {
		String totalReviewCountStr = totalReviewCountElement.text();
		// 去掉千分位
		totalReviewCountStr = totalReviewCountStr.replace(" ", "");
		return Integer.valueOf(totalReviewCountStr);
	}

}
