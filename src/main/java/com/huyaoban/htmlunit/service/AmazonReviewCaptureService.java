package com.huyaoban.htmlunit.service;

import com.huyaoban.htmlunit.model.AsinInfo;
import com.huyaoban.htmlunit.model.AsinReviewPageInfo;
import com.huyaoban.htmlunit.model.ReviewInfo;

public interface AmazonReviewCaptureService {

	void captureReviewForAsin(AsinInfo asinInfo) throws Exception;

	void captureReviewByPage(AsinReviewPageInfo asinReviewPageInfo) throws Exception;

	void captureSingleReview(ReviewInfo reviewInfo) throws Exception;

	void checkReviewStatus(ReviewInfo reviewInfo) throws Exception;

}
