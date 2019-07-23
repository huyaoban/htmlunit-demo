package com.huyaoban.htmlunit.service.impl;

import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import com.google.common.base.Splitter;

import lombok.extern.slf4j.Slf4j;

@Service("itAmazonReviewParseService")
@Slf4j
public class ItAmazonReviewParseServiceImpl extends DefaultAmazonReviewParseServiceImpl {

	@Override
	public Integer parseHelpfulVotes(Element reviewInfoDiv) {
		Element helpfulVotes = reviewInfoDiv.selectFirst("span.cr-vote-text");
		if (helpfulVotes == null) {
			return 0;
		}

		log.info("parse helpful votes for review {}", reviewInfoDiv.attr("id"));

		String votesStr = helpfulVotes.text();
		String votes = Splitter.on(" ").trimResults().omitEmptyStrings().splitToList(votesStr).get(0);
		if ("UNA".equals(votes.toUpperCase())) {
			return 1;
		} else {
			return Integer.valueOf(votes);
		}
	}

}
