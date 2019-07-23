package com.huyaoban.htmlunit.service.impl;

import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import com.google.common.base.Splitter;
import com.huyaoban.htmlunit.model.AmazonReview;
import com.huyaoban.htmlunit.service.AmazonReviewParseService;

import lombok.extern.slf4j.Slf4j;

@Service("defaultAmazonReviewParseService")
@Slf4j
public class DefaultAmazonReviewParseServiceImpl implements AmazonReviewParseService {

	@Override
	public AmazonReview parseReviewInfo(String asin, Element reviewInfoDiv) {
		AmazonReview.Builder builder = new AmazonReview.Builder();
		builder.withAsin(asin).withAmazonReviewId(parseAmazonReviewId(reviewInfoDiv))
				.withCustomerName(parseCustomerName(reviewInfoDiv))
				.withCustomerProfileLink(parseCustomerProfileLink(reviewInfoDiv))
				.withCurrentStar(parseCurrentStar(reviewInfoDiv)).withTitle(parseTitle(reviewInfoDiv))
				.withReviewDate(parseReviewDate(reviewInfoDiv))
				.withVerifiedPurchase(parseVerifiedPurchase(reviewInfoDiv)).withContent(parseContent(reviewInfoDiv))
				.withContentHtml(parseContentHtml(reviewInfoDiv)).withHelpfulVotes(parseHelpfulVotes(reviewInfoDiv))
				.withReviewLink(parseReviewLink(reviewInfoDiv));
		return builder.build();
	}

	@Override
	public String parseAmazonReviewId(Element reviewInfoDiv) {
		return reviewInfoDiv.attr("id");
	}

	@Override
	public String parseCustomerName(Element reviewInfoDiv) {
		Element customerName = reviewInfoDiv.getElementsByClass("a-profile-name").first();
		return customerName.text();
	}

	@Override
	public String parseCustomerProfileLink(Element reviewInfoDiv) {
		Element profile = reviewInfoDiv.selectFirst("a[class=a-profile]");
		return profile.attr("abs:href");
	}

	@Override
	public String parseCurrentStar(Element reviewInfoDiv) {
		Element currentStar = reviewInfoDiv.getElementsByClass("review-rating").first();
		return currentStar.text();
	}

	@Override
	public String parseTitle(Element reviewInfoDiv) {
		Element title = reviewInfoDiv.selectFirst("a.review-title-content");
		return title.text();
	}

	@Override
	public String parseReviewDate(Element reviewInfoDiv) {
		Element reviewDate = reviewInfoDiv.selectFirst("span.review-date");
		return reviewDate.text();
	}

	@Override
	public Boolean parseVerifiedPurchase(Element reviewInfoDiv) {
		Element verifiedPurchase = reviewInfoDiv
				.getElementsByAttributeValueContaining("data-reviews-state-param", "avp_only_reviews").first();
		return verifiedPurchase == null ? Boolean.FALSE : Boolean.TRUE;
	}

	@Override
	public String parseContent(Element reviewInfoDiv) {
		Element content = reviewInfoDiv.selectFirst("span.review-text-content");
		return content.text();
	}

	@Override
	public String parseContentHtml(Element reviewInfoDiv) {
		Element content = reviewInfoDiv.selectFirst("span.review-text-content");
		return content.html();
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
		if ("ONE".equals(votes.toUpperCase())) {
			return 1;
		} else {
			return Integer.valueOf(votes);
		}
	}

	@Override
	public String parseReviewLink(Element reviewInfoDiv) {
		Element title = reviewInfoDiv.selectFirst("a.review-title-content");
		return title.attr("abs:href");
	}

}
