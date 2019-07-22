package com.huyaoban.htmlunit.util;

import java.util.Calendar;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.ImmediateRefreshHandler;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.util.Cookie;

public final class WebClientUtil {

	public static WebClient buildWebClient() {
		WebClient webClient = new WebClient(BrowserVersion.CHROME);

		webClient.getOptions().setJavaScriptEnabled(true);// 很重要，启用JS
		webClient.getOptions().setThrowExceptionOnScriptError(false);// 当JS执行出错的时候是否抛出异常, 这里选择不需要
		webClient.getOptions().setCssEnabled(false);// 是否启用CSS, 因为不需要展现页面, 所以不需要启用
		webClient.getOptions().setRedirectEnabled(true);
		webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);// 当HTTP的状态非200时是否抛出异常, 这里选择不需要
		webClient.getOptions().setTimeout(28 * 1000);
		webClient.getOptions().setUseInsecureSSL(true);
		webClient.getOptions().setSSLInsecureProtocol("TLSv1.2");

		webClient.setRefreshHandler(new ImmediateRefreshHandler());
		webClient.setAjaxController(new NicelyResynchronizingAjaxController());// 很重要，设置支持AJAX
		webClient.waitForBackgroundJavaScript(29 * 1000);// 异步JS执行需要耗时,所以这里线程要阻塞30秒,等待异步JS执行结束
		webClient.setJavaScriptTimeout(30 * 1000);
		webClient.getCookieManager().setCookiesEnabled(true);

		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, 12);
		Cookie cookie1 = new Cookie(".amazon.com", "aws-priv", "eyJ2IjoxLCJzdCI6MX0=", "/", calendar.getTime(), false);
		Cookie cookie2 = new Cookie(".amazon.com", "regStatus", "pre-register", "/", calendar.getTime(), false);
		Cookie cookie3 = new Cookie(".amazon.com", "s_cc", "true", "/", calendar.getTime(), false);
		Cookie cookie4 = new Cookie(".amazon.com", "s_dslv", "1563436137044", "/", calendar.getTime(), false);
		Cookie cookie5 = new Cookie(".amazon.com", "s_fid", "243F6EC3DAB3976F-248791C8AB6604E9", "/",
				calendar.getTime(), false);
		Cookie cookie6 = new Cookie(".amazon.com", "s_nr", "1563436137041-Repeat", "/", calendar.getTime(), false);
		Cookie cookie7 = new Cookie(".amazon.com", "s_sq", "%5B%5BB%5D%5D", "/", calendar.getTime(), false);
		Cookie cookie8 = new Cookie(".amazon.com", "s_vn", "1594280031196%26vn%3D3", "/", calendar.getTime(), false);
		Cookie cookie9 = new Cookie(".amazon.com", "s_vnum", "1995436130310%26vn%3D1", "/", calendar.getTime(), false);
		Cookie cookie10 = new Cookie(".amazon.com", "session-id", "147-8546285-9430237", "/", calendar.getTime(),
				false);
		Cookie cookie11 = new Cookie(".amazon.com", "session-id-time", "2082787201l", "/", calendar.getTime(), false);
		Cookie cookie12 = new Cookie(".amazon.com", "session-token",
				"m86w+8UgcMqA5/pQH6cPLc2iFzoUBxIyVyfnG1aEU9xKoeDtphz6q9VIEwRjqM3OdpJ7gH38bdm1ySI+hiOC94WVW85yS55hVPAY1V6DzW06DzLdBKcohRkQBCedCnn/YTm/y+8/latljV2rth+HwOAyaZZesbbzL+OGYPMA8GVu8+i+YWXPno91CJzISLk2",
				"/", calendar.getTime(), false);
		Cookie cookie13 = new Cookie(".amazon.com", "skin", "noskin", "/", calendar.getTime(), false);
		Cookie cookie14 = new Cookie(".amazon.com", "sp-cdn", "\"L5Z9:CN\"", "/", calendar.getTime(), true);
		Cookie cookie15 = new Cookie(".amazon.com", "ubid-main", "131-5027863-1294407", "/", calendar.getTime(), false);
		Cookie cookie16 = new Cookie(".amazon.com", "x-wl-uid",
				"1s91+J6ij/clqCxS0ruOqhGLfYdQ8qmdgWkzZPAZO1aXnZjUG8pDvhx0H0rgKtQ1evrLtoKfQ1Wc=", "/",
				calendar.getTime(),
				false);
		Cookie cookie17 = new Cookie(".amazon.com", "i18n-prefs", "USD", "/", calendar.getTime(), false);
		Cookie cookie18 = new Cookie("www.amazon.com", "csm-hit",
				"tb:YEG4ZP0WGTWGRE4J253Q+s-7JFP5247G082SWGRHZA5|1563500671555&t:1563500671555&adb:adblk_no", "/",
				calendar.getTime(), false);
		webClient.getCookieManager().addCookie(cookie1);
		webClient.getCookieManager().addCookie(cookie2);
		webClient.getCookieManager().addCookie(cookie3);
		webClient.getCookieManager().addCookie(cookie4);
		webClient.getCookieManager().addCookie(cookie5);
		webClient.getCookieManager().addCookie(cookie6);
		webClient.getCookieManager().addCookie(cookie6);
		webClient.getCookieManager().addCookie(cookie7);
		webClient.getCookieManager().addCookie(cookie8);
		webClient.getCookieManager().addCookie(cookie9);
		webClient.getCookieManager().addCookie(cookie10);
		webClient.getCookieManager().addCookie(cookie11);
		webClient.getCookieManager().addCookie(cookie12);
		webClient.getCookieManager().addCookie(cookie13);
		webClient.getCookieManager().addCookie(cookie14);
		webClient.getCookieManager().addCookie(cookie15);
		webClient.getCookieManager().addCookie(cookie16);
		webClient.getCookieManager().addCookie(cookie17);
		webClient.getCookieManager().addCookie(cookie18);

		return webClient;
	}

}
