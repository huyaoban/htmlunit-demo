package com.huyaoban.htmlunit.service.impl;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.joda.time.format.DateTimeFormat;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

@Service("jpAmazonReviewParseService")
public class JpAmazonReviewParseServiceImpl extends DefaultAmazonReviewParseServiceImpl {

	@Override
	public void afterPropertiesSet() throws Exception {
		// 2018年3月3日
		dateTimeFormatter = DateTimeFormat.forPattern("yyyy年MM月dd日").withLocale(Locale.JAPAN);
	}

	@Override
	public Integer parseHelpfulVotes(Element reviewInfoDiv) {
		Element helpfulVotes = reviewInfoDiv.selectFirst("span.cr-vote-text");
		if (helpfulVotes == null) {
			return 0;
		}

		String votesStr = helpfulVotes.text();

		String pattern = "^[0-9]*";
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(votesStr);
		if (m.find()) {
			String votes = m.group();
			return Integer.valueOf(votes);
		} else {
			return 0;
		}
	}

}
