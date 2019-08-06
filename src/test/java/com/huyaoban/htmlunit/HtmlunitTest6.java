package com.huyaoban.htmlunit;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Calendar;
import java.util.Scanner;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.ImmediateRefreshHandler;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlCheckBoxInput;
import com.gargoylesoftware.htmlunit.html.HtmlEmailInput;
import com.gargoylesoftware.htmlunit.html.HtmlImage;
import com.gargoylesoftware.htmlunit.html.HtmlListItem;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTelInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import com.gargoylesoftware.htmlunit.util.Cookie;
import com.huyaoban.htmlunit.service.VerificationCodeService;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class HtmlunitTest6 {
	@Autowired
	private VerificationCodeService verificationCodeService;

	@Test
	public void test1()
			throws InterruptedException, FailingHttpStatusCodeException, MalformedURLException, IOException {
		WebClient webClient = new WebClient(BrowserVersion.CHROME);

		webClient.getOptions().setJavaScriptEnabled(false);// 很重要，启用JS
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

		// 跳过短信验证码的cookie
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, 12);
		webClient.getCookieManager()
				.addCookie(new Cookie(".amazon.com", "sid",
						"\"wxLbQZBEs0OksaLb1kGsmg==|AmE5pwX+q8BHWyFMrt13jlaUXwraypRfHrQy2+YF75o=\"", "/",
						calendar.getTime(), true));
		webClient.getCookieManager()
				.addCookie(new Cookie("sellercentral.amazon.com", "sid",
						"\"wxLbQZBEs0OksaLb1kGsmg==|AmE5pwX+q8BHWyFMrt13jlaUXwraypRfHrQy2+YF75o=\"", "/",
						calendar.getTime(), true));

		String account = "cs.txt.guest.dev@hotmail.com";
		String password = "suvalley2018";

		// 登陆结果标识
		boolean success = false;
		while (!success) {
			// 登陆主页
			HtmlPage loginPage = webClient.getPage("https://sellercentral.amazon.com/gp/sign-in/sign-in.html");

			// 账号
			HtmlEmailInput usernameElement = (HtmlEmailInput) loginPage.getElementById("ap_email");
			usernameElement.click();
			usernameElement.type(account);

			// 密码
			HtmlPasswordInput passwordElement = (HtmlPasswordInput) loginPage.getElementById("ap_password");
			passwordElement.click();
			passwordElement.type(password);

			// 记住我
			HtmlCheckBoxInput rememberMeElement = (HtmlCheckBoxInput) loginPage.getElementByName("rememberMe");
			if (!rememberMeElement.isChecked()) {
				rememberMeElement.setChecked(true);
			}

			// 登陆按钮
			HtmlSubmitInput loginBtn = (HtmlSubmitInput) loginPage.getElementById("signInSubmit");
			HtmlPage loginResultPage = loginBtn.click();

			// 校验登陆结果
			if (isLoginSuccess(loginResultPage)) {
				log.info("{} login success", account);
				success = true;
				break;
			}

			// 短信验证码
			HtmlTelInput mobileCodeElement = (HtmlTelInput) loginResultPage.getElementById("auth-mfa-otpcode");
			if (mobileCodeElement != null) {
				System.out.println("请输入短信验证码");
				Scanner sc = new Scanner(System.in);
				String mobileCode = sc.nextLine();
				mobileCodeElement.setValueAttribute(mobileCode);

				// 记住设备
				HtmlCheckBoxInput mobileRememberMeElement = (HtmlCheckBoxInput) loginResultPage
						.getElementById("auth-mfa-remember-device");
				if (!mobileRememberMeElement.isChecked()) {
					mobileRememberMeElement.setChecked(true);
				}

				// 登陆按钮
				loginBtn = (HtmlSubmitInput) loginResultPage.getElementById("auth-signin-button");
				loginResultPage = loginBtn.click();

				// 校验登陆结果
				if (isLoginSuccess(loginResultPage)) {
					log.info("{} login success", account);
					success = true;
					break;
				}
			}

			// 图片验证码
			HtmlImage captchaElement = (HtmlImage) loginResultPage.getElementById("auth-captcha-image");
			if (captchaElement != null) {
				// 图片链接
				String captchaUrl = captchaElement.getAttribute("src");
				// 调用接口识别图片验证码
				String code = verificationCodeService.recognize(captchaUrl);
				log.info("recognized verifcation code is {}, image url {}", code, captchaUrl);

				// 输入图片验证码
				HtmlTextInput captchaText = (HtmlTextInput) loginResultPage.getElementById("auth-captcha-guess");
				captchaText.setValueAttribute(code);

				// 密码
				passwordElement = (HtmlPasswordInput) loginResultPage.getElementById("ap_password");
				passwordElement.click();
				passwordElement.type(password);

				// 登陆按钮
				loginBtn = (HtmlSubmitInput) loginResultPage.getElementById("signInSubmit");
				loginResultPage = loginBtn.click();

				// 校验登陆结果
				if (isLoginSuccess(loginResultPage)) {
					log.info("{} login success", account);
					success = true;
					break;
				}
			}
		}
	}

	private boolean isLoginSuccess(HtmlPage loginResultPage) {
		HtmlListItem settingsElement = (HtmlListItem) loginResultPage.getElementById("sc-quicklink-settings");
		if (settingsElement != null) {
			return true;
		}

		HtmlListItem messagesElement = (HtmlListItem) loginResultPage.getElementById("sc-quicklink-messages");
		if (messagesElement != null) {
			return true;
		}

		return false;
	}
}
