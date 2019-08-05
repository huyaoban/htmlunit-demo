package com.huyaoban.htmlunit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.huyaoban.htmlunit.service.VerificationCodeService;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class HtmlunitTest7 {
	@Autowired
	private VerificationCodeService verificationCodeService;

	@Test
	public void test1() {
		String code = verificationCodeService.recognize(
				"https://opfcaptcha-prod.s3.amazonaws.com/b3399fc4725942d1b8ee08c8d0b73631.jpg?AWSAccessKeyId=AKIA5WBBRBBBS72QVIWQ&Expires=1564978621&Signature=MvR6vTPOEposqw2t0JbqlLPBbog%3D");
		log.info("recognized code is {}", code);
	}

}
