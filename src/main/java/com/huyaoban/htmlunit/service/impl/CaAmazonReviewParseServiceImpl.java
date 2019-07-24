package com.huyaoban.htmlunit.service.impl;

import java.util.Locale;

import org.joda.time.format.DateTimeFormat;
import org.springframework.stereotype.Service;

@Service("caAmazonReviewParseService")
public class CaAmazonReviewParseServiceImpl extends DefaultAmazonReviewParseServiceImpl {

	@Override
	public void afterPropertiesSet() throws Exception {
		// March 19, 2019
		dateTimeFormatter = DateTimeFormat.forPattern("MMM d, yyyy").withLocale(Locale.CANADA);
	}

}
