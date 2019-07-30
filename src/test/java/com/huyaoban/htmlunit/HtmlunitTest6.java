package com.huyaoban.htmlunit;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Calendar;
import java.util.Scanner;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.DefaultCredentialsProvider;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.ImmediateRefreshHandler;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlCheckBoxInput;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlEmailInput;
import com.gargoylesoftware.htmlunit.html.HtmlImage;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import com.gargoylesoftware.htmlunit.util.Cookie;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class HtmlunitTest6 {

	@Test
	public void test1()
			throws InterruptedException, FailingHttpStatusCodeException, MalformedURLException, IOException {
		WebClient webClient = new WebClient(BrowserVersion.CHROME);

		webClient.getOptions().setJavaScriptEnabled(true);// 很重要，启用JS
		webClient.getOptions().setThrowExceptionOnScriptError(false);// 当JS执行出错的时候是否抛出异常, 这里选择不需要
		webClient.getOptions().setCssEnabled(false);// 是否启用CSS, 因为不需要展现页面, 所以不需要启用
		webClient.getOptions().setRedirectEnabled(true);
		webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);// 当HTTP的状态非200时是否抛出异常, 这里选择不需要
		webClient.getOptions().setTimeout(28 * 1000);
		webClient.getOptions().setUseInsecureSSL(true);
		webClient.getOptions().setSSLInsecureProtocol("TLSv1.2");
		webClient.getOptions().setDownloadImages(false);

		webClient.setRefreshHandler(new ImmediateRefreshHandler());
		webClient.setAjaxController(new NicelyResynchronizingAjaxController());// 很重要，设置支持AJAX
		webClient.waitForBackgroundJavaScript(29 * 1000);// 异步JS执行需要耗时,所以这里线程要阻塞30秒,等待异步JS执行结束
		webClient.setJavaScriptTimeout(30 * 1000);
		webClient.getCookieManager().setCookiesEnabled(true);
		DefaultCredentialsProvider provider = new DefaultCredentialsProvider();
		provider.addCredentials("cs.txt.guest.dev@hotmail.com", "suvalley2018");
		webClient.setCredentialsProvider(provider);
		webClient.addRequestHeader("user-agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.119 Safari/537.36");

		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, 12);
		webClient.getCookieManager()
				.addCookie(new Cookie(".amazon.com", "sid",
						"\"dy3NhllV8BnNxA7DRza8bw==|pdVDUvb2ft7dk7EyIM8HPZeY29tS6e8Bn8b8X7XAFkU=\"", "/",
						calendar.getTime(), true));
		webClient.getCookieManager().addCookie(new Cookie(".amazon.com", "at-main",
				"Atza|IwEBICyoECnkusTZ1v5gdadFp_jV4Wii3tGv9Fx82F4IeI2KDnsl5OswSm3yIjTsBrY1dqqWqnrLUP5cl_HpVGzrD09wAN9iFR3RjgOFR8OyiLYrIbLJxxW_rKLpQoWE2ikoHv_754z5G9DgwIQ7rUuZCnW3RLS6SIEYiji5jizV8wj_YjaCVSHJidbv087O4X7LvI-OAFJ-DAIni96B3MXtxQcywsK9U7XppPyV6z_lLSxNZXInW6OpbmiBlE7qYg0P2pb0BmmBiZ_kpXq0o8J59Zs99vnwmW77fOhyN78dTuQ0bJwg1261W4WON3f5YYeiW5bVsdLTiXcZixqyr2-hsZleHLH-N6B1bXC6-MMhxiSbnfcHXPoWKofiQw8iINL3rzdI7j39WCk-twSfVQi3Df4kmn5bSDkxjNA8x9uMbe1bBvQYJU76w1SZ-zwJ1jfXH5ElhlKLtL8W_G8ynKdb-DxpgGe-IOp0EhdJRYW4tsRAgybqpvGKBfn9c8DssFhCr18",
				"/", calendar.getTime(), true));
		webClient.getCookieManager()
				.addCookie(new Cookie("sellercentral.amazon.com", "csm-hit",
						"tb:8MXYR19YDTMTMGE56B3H+s-F7KGGHBX7SVRCJX7FCHJ|1564483191283&t:1564483191283&adb:adblk_no",
						"/", calendar.getTime(), true));
		webClient.getCookieManager()
				.addCookie(new Cookie(".amazon.com", "i18n-prefs", "USD", "/", calendar.getTime(), true));
		webClient.getCookieManager().addCookie(new Cookie(".amazon.com", "sess-at-main",
				"\"ysZZydwqAAFtjRf7DD3gsovuxsGIxkZaUCyYeNksVLo=\"", "/", calendar.getTime(), true));
		webClient.getCookieManager().addCookie(
				new Cookie(".amazon.com", "session-id", "145-5160206-4615244", "/", calendar.getTime(), true));
		webClient.getCookieManager()
				.addCookie(new Cookie(".amazon.com", "session-id-time", "2082787201l", "/", calendar.getTime(), true));
		webClient.getCookieManager().addCookie(new Cookie(".amazon.com", "session-token",
				"\"ym+o2m573mMG5HVawLTvPDz/BLmpgB8luEpTwgZI2ENcdu6X7YEQ+7Y/DtuIDIzA9jOwwRXO5BYym/ffCUrEcblswSKpnq2Jy50WSkBOl3eT5YqJlMz6G1ftDkYDTANQVRBCIQ/z9dXR4V+MyaTEzErwgohXHHXaL6LeDYhu2DbwY8q4+4jBOxE3A+ipSOyrlPB2M2igG7wipE6XEu7qVSrlS2oXUlMtWlkZMq+632o=\"",
				"/", calendar.getTime(), true));
		webClient.getCookieManager()
				.addCookie(new Cookie(".amazon.com", "sp-cdn", "\"L5Z9:CN\"", "/", calendar.getTime(), true));
		webClient.getCookieManager().addCookie(new Cookie(".amazon.com", "sst-main",
				"Sst1|PQFAqKHlywcxiJgzV5vtxD-FC_mLFjsdcg6MZozEz9l-eGhXMZCo8s00o-R4iAOzFhjHTetJ98N9xQyQ0GkOEY8xQHtz1LiI0CQE_dWqStVunZ71dfwplu2og7Juy8iP4vQ0DytXRQULIeT6BjAvB0OcObxI-oSTU41VuMkWV9k1OZ-aHNS0KhpJUMcAWn9CujsJPg-dH9f61AgtyvS2gH3S-NsDUD6fSwcW7sOYu-KZ3MS7R3In-cWDwkgiNCwk7pLdzkO_LxMgzcs_IbveGMc94GNko8yMrJBaM2O2oMgAg61G9XbDdn3QDTsrcH42rxhNGHzCc7K58Jt1PDHxj3zOEA",
				"/", calendar.getTime(), true));
		webClient.getCookieManager().addCookie(
				new Cookie(".amazon.com", "ubid-main", "131-6044410-5413616", "/", calendar.getTime(), true));
		webClient.getCookieManager().addCookie(new Cookie(".amazon.com", "x-main",
				"qOqiR5hcnliZFllPxM1ekQCTmKLGu5NS9CYcS1De69KxsaLyWIaLTWvB4pW5LzDh", "/", calendar.getTime(), true));
		webClient.getCookieManager()
				.addCookie(new Cookie(".amazon.com", "x-wl-uid",
						"1gemI3POPDKC3hcDATVVyerI2EHwNQBdnxpnUhrzLpTw1V8E1R0SjTZY0714PnxlWal9MWkgyvPk=", "/",
						calendar.getTime(), true));

		HtmlPage loginPage = webClient.getPage("https://sellercentral.amazon.com/gp/sign-in/sign-in.html");
		log.info("first title is {}", loginPage.getTitleText());
		HtmlEmailInput usernameText = (HtmlEmailInput) loginPage.getElementById("ap_email");
		usernameText.click();
		usernameText.type("cs.txt.guest.dev@hotmail.com");

		HtmlPasswordInput pwdEle = (HtmlPasswordInput) loginPage.getElementById("ap_password");
		pwdEle.click();
		pwdEle.type("suvalley2018");

		HtmlCheckBoxInput rememberMeEle = (HtmlCheckBoxInput) loginPage.getElementByName("rememberMe");
		if (!rememberMeEle.isChecked()) {
			rememberMeEle.setChecked(true);
		}

		HtmlSubmitInput loginBtn = (HtmlSubmitInput) loginPage.getElementById("signInSubmit");
		HtmlPage loginPageResult = loginBtn.click();

		log.info("login title is {}", loginPageResult.getTitleText());
		String loginXml = loginPageResult.asXml();
		if (loginXml.toLowerCase().contains("Messages")) {
			log.info("login success");
		}

		usernameText = null;
		usernameText = (HtmlEmailInput) loginPageResult.getElementById("ap_email");
		usernameText.click();
		log.info("email = {}", usernameText.getValueAttribute());
		// usernameText.type("cs.txt.guest.dev@hotmail.com");

		pwdEle = null;
		pwdEle = (HtmlPasswordInput) loginPageResult.getElementById("ap_password");
		pwdEle.click();
		log.info("password = {}", pwdEle.getValueAttribute());
		pwdEle.type("suvalley2018");

		rememberMeEle = null;
		rememberMeEle = (HtmlCheckBoxInput) loginPageResult.getElementByName("rememberMe");
		if (!rememberMeEle.isChecked()) {
			rememberMeEle.setChecked(true);
		}

		HtmlImage img_ele = null;
		HtmlElement divCaptcha_ele = (HtmlElement) loginPageResult.getElementById("auth-captcha-image");
		if (divCaptcha_ele.getElementsByTagName("img").size() > 0) {
			img_ele = (HtmlImage) divCaptcha_ele.getElementsByTagName("img").get(0);
		} else {
			img_ele = (HtmlImage) divCaptcha_ele;
		}
		String ap_captcha_url = img_ele.getAttribute("src");

		//
		Scanner sc = new Scanner(System.in);
		System.out.println("请输入验证码");
		String code = sc.nextLine();

		HtmlTextInput ap_captcha_guess = (HtmlTextInput) loginPageResult.getElementById("auth-captcha-guess");

		if (ap_captcha_guess == null) {
			ap_captcha_guess = (HtmlTextInput) loginPageResult.getElementById("auth-captcha-guess");
		}

		ap_captcha_guess.setValueAttribute(code);

		loginBtn = (HtmlSubmitInput) loginPageResult.getElementById("signInSubmit");
		loginPageResult = loginBtn.click();

		HtmlTextInput mobileCodeEle = (HtmlTextInput) loginPageResult.getElementById("auth-mfa-otpcode");
		if (mobileCodeEle != null) {
			System.out.println("请输入短信验证码");
			String mobileCode = sc.nextLine();
			mobileCodeEle.setValueAttribute(mobileCode);

			HtmlCheckBoxInput rememberMobileEle = (HtmlCheckBoxInput) loginPage
					.getElementById("auth-mfa-remember-device");
			if (rememberMobileEle != null) {
				rememberMobileEle.click();
			}

			loginBtn = (HtmlSubmitInput) loginPageResult.getElementById("auth-signin-button");
			loginPageResult = loginBtn.click();

		}

		log.info("login title is {}", loginPageResult.getTitleText());
		loginXml = null;
		loginXml = loginPageResult.asXml();
		if (loginXml.toLowerCase().contains("Messages")) {
			log.info("login success");
		}

		HtmlPage orderPage = webClient.getPage("https://sellercentral.amazon.com/orders-v3/order/701-1101743-1505052");
		String orderXml = orderPage.asXml();
		if (orderXml.contains("B07V3RYZPD")) {
			log.info("can fetch order");
		}

		usernameText = (HtmlEmailInput) orderPage.getElementById("ap_email");
		usernameText.click();
		usernameText.type("cs.txt.guest.dev@hotmail.com");
	}

}
