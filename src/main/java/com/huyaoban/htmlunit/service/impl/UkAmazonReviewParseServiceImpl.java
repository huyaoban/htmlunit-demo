package com.huyaoban.htmlunit.service.impl;

import java.util.Locale;

import org.joda.time.format.DateTimeFormat;
import org.springframework.stereotype.Service;

@Service("ukAmazonReviewParseService")
public class UkAmazonReviewParseServiceImpl extends DefaultAmazonReviewParseServiceImpl {

	@Override
	public void afterPropertiesSet() throws Exception {
		// 5 March 2018
		dateTimeFormatter = DateTimeFormat.forPattern("d MMM yyyy").withLocale(Locale.UK);
	}

}
