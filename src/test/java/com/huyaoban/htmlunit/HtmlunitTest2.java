package com.huyaoban.htmlunit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.huyaoban.htmlunit.service.AmazonReviewCaptureService;

@RunWith(SpringRunner.class)
@SpringBootTest

public class HtmlunitTest2 {
	@Autowired
	private AmazonReviewCaptureService amazonReviewCaptureService;

	@Test
	public void test1() throws Exception {
		amazonReviewCaptureService.captureAmazonReview("B074QLZBRL");
	}

}
