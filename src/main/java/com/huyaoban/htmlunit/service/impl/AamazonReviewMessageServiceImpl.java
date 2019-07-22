package com.huyaoban.htmlunit.service.impl;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huyaoban.htmlunit.config.RabbitmqConfiguration;
import com.huyaoban.htmlunit.model.AmazonReviewPageInfo;
import com.huyaoban.htmlunit.model.AsinReviewInfo;
import com.huyaoban.htmlunit.model.ReviewInfo;
import com.huyaoban.htmlunit.service.AamazonReviewMessageService;
import com.huyaoban.htmlunit.service.AmazonReviewCaptureService;

@Service
public class AamazonReviewMessageServiceImpl implements AamazonReviewMessageService {
	@Autowired
	private AmazonReviewCaptureService amazonReviewCaptureService;

	@RabbitListener(queues = RabbitmqConfiguration.REVIEW_ASIN_INFO_QUEUE, containerFactory = "asinInfoContainerFactory")
	@Override
	public void processAsinInfo(AsinReviewInfo asinReviewInfo) throws Exception {
		amazonReviewCaptureService.captureAmazonReviewPageInfo(asinReviewInfo);
	}

	@RabbitListener(queues = RabbitmqConfiguration.REVIEW_PAGE_INFO_QUEUE, containerFactory = "reviewPageContainerFactory")
	@Override
	public void processAmazonReviewMessage(AmazonReviewPageInfo reviewPageInfo) throws Exception {
		amazonReviewCaptureService.captureAmazonReview(reviewPageInfo);
	}

	@RabbitListener(queues = RabbitmqConfiguration.REVIEW_SINGLE_QUEUE, containerFactory = "singleReviewContainerFactory")
	@Override
	public void captureSingleReview(ReviewInfo reviewInfo) throws Exception {
		amazonReviewCaptureService.captureSingleReview(reviewInfo);
	}

}
