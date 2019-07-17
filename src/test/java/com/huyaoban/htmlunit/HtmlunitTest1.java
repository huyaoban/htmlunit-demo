package com.huyaoban.htmlunit;

import java.io.IOException;
import java.net.MalformedURLException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.ImmediateRefreshHandler;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class HtmlunitTest1 {

	private WebClient buildWebClient() {
		WebClient webClient = new WebClient(BrowserVersion.CHROME);

		webClient.getOptions().setJavaScriptEnabled(true);// 很重要，启用JS
		webClient.getOptions().setThrowExceptionOnScriptError(false);// 当JS执行出错的时候是否抛出异常, 这里选择不需要
		webClient.getOptions().setCssEnabled(false);// 是否启用CSS, 因为不需要展现页面, 所以不需要启用
		webClient.getOptions().setRedirectEnabled(true);
		webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);// 当HTTP的状态非200时是否抛出异常, 这里选择不需要
		webClient.getOptions().setTimeout(30 * 1000);
		webClient.getOptions().setUseInsecureSSL(true);
		webClient.getOptions().setSSLInsecureProtocol("TLSv1.2");

		webClient.setRefreshHandler(new ImmediateRefreshHandler());
		webClient.setAjaxController(new NicelyResynchronizingAjaxController());// 很重要，设置支持AJAX
		webClient.waitForBackgroundJavaScript(30 * 1000);// 异步JS执行需要耗时,所以这里线程要阻塞30秒,等待异步JS执行结束
		webClient.setJavaScriptTimeout(30 * 1000);

		return webClient;
	}

	@Test
	public void test1() throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		String url = "https://ent.sina.com.cn/film/";
		WebClient webClient = buildWebClient();

		HtmlPage page = webClient.getPage(url);

		String pageXml = page.asXml();

		Document doc = Jsoup.parse(pageXml);
		// 票房排行榜
		Element board = doc.getElementsByClass("module_tab_cont").first();
		// 内地
		Element mainland = board.selectFirst("ul.module_item_singer.clearfix");
		Elements movieListEle = mainland.select("ul > li");
		for (Element movie : movieListEle) {
			Element rankEle = movie.selectFirst("span.num");
			String rank = rankEle.text();
			Element movieInfoEle = movie.selectFirst("a");
			String movieName = movieInfoEle.text();
			String movieLink = movieInfoEle.attr("href");
			log.info("{} {} {}", rank, movieName, movieLink);
		}
	}

	@Test
	public void test2() throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		String url = "https://www.baidu.com/";
		WebClient webClient = buildWebClient();

		HtmlPage page = webClient.getPage(url);

		HtmlForm form = page.getFormByName("f");
		HtmlInput kw = form.getInputByName("wd");
		HtmlSubmitInput submit = (HtmlSubmitInput) page.getElementById("su");
		
		kw.type("htmlunit");
		HtmlPage resultPage = submit.click();
		
		HtmlElement contentLeft = (HtmlElement)resultPage.getElementById("content_left");
		
		contentLeft.querySelectorAll("div.c-container");
	}
}
