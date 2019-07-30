package com.huyaoban.htmlunit.factory;

import java.util.Calendar;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.ImmediateRefreshHandler;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlCheckBoxInput;
import com.gargoylesoftware.htmlunit.html.HtmlEmailInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.util.Cookie;

public class PooledWebClientFactory extends BasePooledObjectFactory<WebClient> {
	private static Logger logger = LoggerFactory.getLogger(PooledWebClientFactory.class);

	@Override
	public WebClient create() throws Exception {
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
		webClient.addRequestHeader("user-agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.119 Safari/537.36");

		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, 12);
		webClient.getCookieManager()
				.addCookie(new Cookie(".amazon.com", "sid",
						"\"dy3NhllV8BnNxA7DRza8bw==|pdVDUvb2ft7dk7EyIM8HPZeY29tS6e8Bn8b8X7XAFkU=\"", "/",
						calendar.getTime(), true));
		webClient.getCookieManager()
				.addCookie(new Cookie(".amazon.com", "at-main",
						"Atza|IwEBILnZpEKpHt9yVcYp3uZ7g0cillZUVDLPsaf3KImYEoC7xNhCkNRihyR1CNKX-4l_HJhSdWbk6Gjag2P4C-8NMiz_NB6P2F73wlVr-oxuACLSxUXrcEwLKqoFOtSFrpa3d2lApnnZ8VC3YFlgx0WE2IMQ6gvY30XlpV0-VIPELsLjYMIA772jKUvsQnYyfEyVR2lPiNjOYap-_2U367y1k_3a7Gvry_XU4OR4EE4BtMjULvw7PpOnkLcIR8TxMNNJjDfQ9e0mkRrG4TQJ-P7OHulEx0t_4yeXKQmTV-UydZYSd-lsdrqYKevnju7gExfUMzcQ0cAftQUSlDbjseDwCNOz2bDbRlUB4x7GDlVOthZXhwIfmSrn1bYiiDaSYBMw_KEXfmh-2v_2jUKLT79iy1BKt54ZOUb7lQi4jBLpXW11n6N8J4arUEf-uHDF2XXlVyJLgPZ0ypNN4lXdlH586UufBRuNL-ySny8XImZ8Vj1okg",
						"/",
						calendar.getTime(), true));
		webClient.getCookieManager()
				.addCookie(new Cookie("sellercentral.amazon.com", "csm-hit",
						"tb:KA7SM4G6DMP0JC6PXSF4+s-DTXEV63W4XM6YHJ5CVFA|1564474502573&t:1564474502573&adb:adblk_no",
						"/",
						calendar.getTime(), true));
		webClient.getCookieManager()
				.addCookie(new Cookie(".amazon.com", "i18n-prefs", "USD", "/",
						calendar.getTime(), true));
		webClient.getCookieManager()
				.addCookie(new Cookie(".amazon.com", "sess-at-main", "\"R80/loJsK1Top42Gv7brQqodJxvnhU8Aaw/iNGCYWxg=\"",
						"/",
						calendar.getTime(), true));
		webClient.getCookieManager()
				.addCookie(new Cookie(".amazon.com", "session-id", "145-5160206-4615244", "/",
						calendar.getTime(), true));
		webClient.getCookieManager()
				.addCookie(new Cookie(".amazon.com", "session-id-time", "2082787201l", "/",
						calendar.getTime(), true));
		webClient.getCookieManager()
				.addCookie(new Cookie(".amazon.com", "session-token",
						"\"Q4/S9GAq7hBwTduy+OmkfBtV46IUkeisU9Xy2U3cz/RL3bD3M/jl3n3kWeYplCDBqpk7vEWtlN+HhAm3yr5KVX4/EESP4U7QnbUXnoYCtqLiRhtBUCXEl4vHuIRtpe60TNy9QQY1fVE/LwyHHjCjcyQJeDSw8pNjuy5EB+jI/0pOgHA+Zhbp0uAMLw+ZByneHFMsm6n/b+DTBgdYUqaDmnnmP2YbjOf2xvxutAf/4G4=\"",
						"/",
						calendar.getTime(), true));
		webClient.getCookieManager()
				.addCookie(new Cookie(".amazon.com", "sp-cdn", "\"L5Z9:CN\"", "/",
						calendar.getTime(), true));
		webClient.getCookieManager()
				.addCookie(new Cookie(".amazon.com", "sst-main",
						"Sst1|PQEHlLXLSaE2kWNy8tLgsiFBC-8timsAT6UOAZMZOIYdTeIZMC5Co8wfOBzF42McD-klgwc2ZSe4F6ku1atGMZ2u4k2aGbZ3WHvxkeo_96YEOLR6GWQl0z1Az7gVVuABY95Lsf82B47voROjyUOl8lkmmLYiv12Oguh-X3KFUypaKSJKCiuPi99x7b3uj2I7GULBeqJqFQlsW5sKQ00FtJ-LWvzaeAn6hFLgJiAOhgRkgZW-fH01ywgxK1E9miAvLtk7i78QboaVaib06z7rUJZDqOldMMe84M7RAv3z9aEYcGurbPN8T7429eIS_uE-1uUcJ-1QpvuyRUK3wtq3cORknA",
						"/",
						calendar.getTime(), true));
		webClient.getCookieManager()
				.addCookie(new Cookie(".amazon.com", "ubid-main", "131-6044410-5413616", "/",
						calendar.getTime(), true));
		webClient.getCookieManager()
				.addCookie(new Cookie(".amazon.com", "x-main",
						"\"ambnGGMnh8fqwR?LzGTCHkhzOE0OHlyxXQE38UxdqH?5NlVgY?ZZtSfy4eENR?SY\"", "/",
						calendar.getTime(), true));
		webClient.getCookieManager()
				.addCookie(new Cookie(".amazon.com", "x-wl-uid",
						"1gemI3POPDKC3hcDATVVyerI2EHwNQBdnxpnUhrzLpTw1V8E1R0SjTZY0714PnxlWal9MWkgyvPk=", "/",
						calendar.getTime(), true));

		HtmlPage loginPage = webClient.getPage("https://sellercentral.amazon.com/gp/sign-in/sign-in.html");
		logger.info("first title is {}", loginPage.getTitleText());
		HtmlEmailInput usernameText = (HtmlEmailInput) loginPage.getElementById("ap_email");
		usernameText.click();
		usernameText.type("cs.txt.guest.dev@hotmail.com ");

		HtmlPasswordInput pwdEle = (HtmlPasswordInput) loginPage.getElementById("ap_password");
		pwdEle.click();
		pwdEle.type("suvalley2018");

		HtmlCheckBoxInput rememberMeEle = (HtmlCheckBoxInput) loginPage.getElementByName("rememberMe");
		if (!rememberMeEle.isChecked()) {
			rememberMeEle.setChecked(true);
		}

		HtmlSubmitInput loginBtn = (HtmlSubmitInput) loginPage.getElementById("signInSubmit");
		HtmlPage loginPageResult = loginBtn.click();

		logger.info("login title is {}", loginPageResult.getTitleText());
		String loginXml = loginPageResult.asXml();
		if (loginXml.toLowerCase().contains("Messages")) {
			logger.info("login success");
		}

		return webClient;
	}

	@Override
	public PooledObject<WebClient> wrap(WebClient obj) {
		return new DefaultPooledObject<>(obj);
	}

	@Override
	public void destroyObject(PooledObject<WebClient> p) throws Exception {
		WebClient client = p.getObject();
		client.close();
		super.destroyObject(p);
	}

	@Override
	public boolean validateObject(PooledObject<WebClient> p) {

		return super.validateObject(p);
	}

	@Override
	public void activateObject(PooledObject<WebClient> p) throws Exception {
		super.activateObject(p);
	}

}
