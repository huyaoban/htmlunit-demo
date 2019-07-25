package com.huyaoban.htmlunit.util;

import java.util.Calendar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.skyscreamer.jsonassert.JSONParser;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.ImmediateRefreshHandler;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.util.Cookie;
import com.huyaoban.htmlunit.model.CountryInfo;


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
		webClient.getOptions().setDownloadImages(false);

		webClient.setRefreshHandler(new ImmediateRefreshHandler());
		webClient.setAjaxController(new NicelyResynchronizingAjaxController());// 很重要，设置支持AJAX
		webClient.waitForBackgroundJavaScript(29 * 1000);// 异步JS执行需要耗时,所以这里线程要阻塞30秒,等待异步JS执行结束
		webClient.setJavaScriptTimeout(30 * 1000);
		webClient.getCookieManager().setCookiesEnabled(true);

		return webClient;
	}

	public static WebClient buildWebClient(CountryInfo countryInfo) throws JSONException {
		WebClient client = buildWebClient();

		client.addRequestHeader(":authority", countryInfo.getAuthority());
		client.addRequestHeader(":method", "GET");
		client.addRequestHeader(":scheme", "https");
		client.addRequestHeader("accept",
				"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
		client.addRequestHeader("accept-encoding", "gzip, deflate, br");
		client.addRequestHeader("accept-language", "zh-CN,zh;q=0.9,en;q=0.8");
		client.addRequestHeader("cache-control", "no-cache");
		client.addRequestHeader("pragma", "no-cache");
		client.addRequestHeader("upgrade-insecure-requests", "1");
		client.addRequestHeader("user-agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.119 Safari/537.36");
		
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, 12);

		JSONArray cookieArray = (JSONArray) JSONParser.parseJSON(countryInfo.getCookies());
		for (int i = 0; i < cookieArray.length(); i++) {
			JSONObject cookieObj = cookieArray.getJSONObject(i);
			Cookie cookie = new Cookie(cookieObj.getString("domain"), cookieObj.getString("name"),
					cookieObj.getString("value"), cookieObj.getString("path"), calendar.getTime(),
					cookieObj.getBoolean("secure"));
			client.getCookieManager().addCookie(cookie);
		}

		return client;
	}

}
