package com.huyaoban.htmlunit.service;

import com.huyaoban.htmlunit.model.AmazonReviewPageInfo;
import com.huyaoban.htmlunit.model.AsinReviewInfo;
import com.huyaoban.htmlunit.model.ReviewInfo;

public interface AamazonReviewMessageService {

	void processAsinInfo(AsinReviewInfo asinInfo) throws Exception;

	void processAmazonReviewMessage(AmazonReviewPageInfo pageInfo) throws Exception;

	void captureSingleReview(ReviewInfo reviewInfo) throws Exception;
}
