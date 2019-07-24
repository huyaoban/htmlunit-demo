package com.huyaoban.htmlunit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.huyaoban.htmlunit.model.AsinInfo;
import com.huyaoban.htmlunit.model.AsinReviewPageInfo;
import com.huyaoban.htmlunit.model.ReviewInfo;
import com.huyaoban.htmlunit.service.AmazonReviewCaptureService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HtmlunitTest2 {
	@Autowired
	private AmazonReviewCaptureService amazonReviewCaptureService;

	@Test
	public void test1() throws Exception {
		AsinInfo asinInfo = new AsinInfo("B074QLZBRL", "US");
		amazonReviewCaptureService.captureReviewForAsin(asinInfo);
	}

	@Test
	public void test2() throws Exception {
		AsinReviewPageInfo asinReviewPageInfo = new AsinReviewPageInfo("B074QLZBRL", "US", 2,
				"https://www.amazon.com/product-reviews/B074QLZBRL/ref=cm_cr_arp_d_paging_btm_next_2?ie=UTF8&reviewerType=all_reviews&pageNumber=2&pageSize=20");
		amazonReviewCaptureService.captureReviewByPage(asinReviewPageInfo);
	}

	@Test
	public void test3() throws Exception {
		ReviewInfo reviewInfo = new ReviewInfo("R3GRP25WKBEB60", "B00MXWFUQC", "us",
				"https://www.amazon.com/gp/customer-reviews/R3GRP25WKBEB60/ref=cm_cr_arp_d_rvw_ttl?ie=UTF8&ASIN=B00MXWFUQC");
		amazonReviewCaptureService.captureSingleReview(reviewInfo);
	}

}
