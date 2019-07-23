package com.huyaoban.htmlunit.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.huyaoban.htmlunit.config.RabbitmqConfiguration;
import com.huyaoban.htmlunit.model.AsinInfo;
import com.huyaoban.htmlunit.model.ReviewInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/review")
@Api(value = "review抓取")
public class ReviewController {
	@Autowired
	private RabbitTemplate rabbitTemplate;

	@PostMapping("/capture-single-review")
	@ApiOperation(value = "抓取单个review")
	public void captureSingleReview(@RequestBody ReviewInfo reviewInfo) {
		rabbitTemplate.convertAndSend(RabbitmqConfiguration.REVIEW_EXCHANGE,
				RabbitmqConfiguration.REVIEW_INFO_QUEUE_KEY, reviewInfo);
	}

	@PostMapping("/capture-asin-reviews")
	@ApiOperation(value = "抓取ASIN的review")
	public void captureReviewForAsin(@RequestBody AsinInfo asinInfo) {
		rabbitTemplate.convertAndSend(RabbitmqConfiguration.REVIEW_EXCHANGE,
				RabbitmqConfiguration.ASIN_INFO_QUEUE_KEY, asinInfo);
	}

	@PostMapping("/check-review-status")
	@ApiOperation(value = "检查review是否删除")
	public void checkReviewStatus(@RequestBody ReviewInfo reviewInfo) {
		rabbitTemplate.convertAndSend(RabbitmqConfiguration.REVIEW_EXCHANGE,
				RabbitmqConfiguration.REVIEW_INFO_CHECK_DELETE_QUEUE_KEY, reviewInfo);
	}
}
