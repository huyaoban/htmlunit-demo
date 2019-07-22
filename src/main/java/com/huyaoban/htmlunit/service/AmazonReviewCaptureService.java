package com.huyaoban.htmlunit.service;

import com.huyaoban.htmlunit.model.AmazonReviewPageInfo;
import com.huyaoban.htmlunit.model.AsinReviewInfo;
import com.huyaoban.htmlunit.model.ReviewInfo;

public interface AmazonReviewCaptureService {
	void captureAmazonReview(String asin) throws Exception;

	void captureAmazonReview(AmazonReviewPageInfo reviewPageInfo) throws Exception;

	void captureAmazonReviewPageInfo(AsinReviewInfo asinReviewInfo) throws Exception;

	void captureSingleReview(ReviewInfo reviewInfo) throws Exception;

}
