package com.huyaoban.htmlunit.service;

import org.jsoup.nodes.Element;

import com.huyaoban.htmlunit.model.AmazonReview;

public interface AmazonReviewParseService {

	AmazonReview parseReviewInfo(String asin, Element reviewInfoDiv);

}
