package com.huyaoban.htmlunit.service;

import org.jsoup.nodes.Element;

import com.huyaoban.htmlunit.model.AmazonReview;

public interface AmazonReviewParseService {

	AmazonReview parseReviewInfo(String asin, Element reviewInfoDiv);

	Integer parseTotalReviewCount(Element totalReviewCountElement);

	String parseAmazonReviewId(Element reviewInfoDiv);

	String parseCustomerName(Element reviewInfoDiv);

	String parseCustomerProfileLink(Element reviewInfoDiv);

	String parseCurrentStar(Element reviewInfoDiv);

	String parseTitle(Element reviewInfoDiv);

	String parseReviewDate(Element reviewInfoDiv);

	Boolean parseVerifiedPurchase(Element reviewInfoDiv);

	String parseContent(Element reviewInfoDiv);

	String parseContentHtml(Element reviewInfoDiv);

	Integer parseHelpfulVotes(Element reviewInfoDiv);

	String parseReviewLink(Element reviewInfoDiv);

}
