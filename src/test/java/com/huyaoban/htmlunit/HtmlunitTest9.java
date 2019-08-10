package com.huyaoban.htmlunit;

import java.io.IOException;
import java.net.MalformedURLException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.ImmediateRefreshHandler;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class HtmlunitTest9 {

	private WebClient buildWebClient() {
		WebClient webClient = new WebClient(BrowserVersion.CHROME);

		webClient.getOptions().setJavaScriptEnabled(false);// 很重要，启用JS
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

		return webClient;
	}

	@Test
	public void test1() throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		String url = "https://music.163.com/#/search/m/";
		WebClient webClient = buildWebClient();

		HtmlPage page = webClient.getPage(url);

		HtmlTextInput content = (HtmlTextInput) page.getElementById("m-search-input");
		content.click();
		content.type("解脱");
		
		HtmlDivision parent = (HtmlDivision)content.getParentNode();
		HtmlAnchor searchBtn = (HtmlAnchor)parent.querySelector("a[title=\"搜索\"]");
		HtmlPage searchResultPage = searchBtn.click();

		HtmlDivision songs = searchResultPage.querySelector("div.srchsongst");
		if (songs != null) {
			log.info("朝朝");
		}
	}

}
