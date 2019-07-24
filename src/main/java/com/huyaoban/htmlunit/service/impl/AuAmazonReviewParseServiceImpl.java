package com.huyaoban.htmlunit.service.impl;

import java.util.Locale;

import org.joda.time.format.DateTimeFormat;
import org.springframework.stereotype.Service;

@Service("auAmazonReviewParseService")
public class AuAmazonReviewParseServiceImpl extends DefaultAmazonReviewParseServiceImpl {

	@Override
	public void afterPropertiesSet() throws Exception {
		// 11 May 2018
		dateTimeFormatter = DateTimeFormat.forPattern("d MMM yyyy").withLocale(Locale.ENGLISH);
	}

}
