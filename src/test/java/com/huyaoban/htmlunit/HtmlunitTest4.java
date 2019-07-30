package com.huyaoban.htmlunit;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.ImmediateRefreshHandler;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebWindow;
import com.gargoylesoftware.htmlunit.WebWindowEvent;
import com.gargoylesoftware.htmlunit.WebWindowListener;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class HtmlunitTest4 {

	private WebClient buildWebClient() {
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

		return webClient;
	}

	@Test
	public void test1() throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		String url = "https://ent.sina.com.cn/film/";
		WebClient webClient = buildWebClient();

		webClient.addWebWindowListener(new WebWindowListener() {
			@Override
			public void webWindowOpened(final WebWindowEvent event) {
				log.info("a new window opened");
				WebWindow window = event.getWebWindow();
				log.info("window name is {}", window.getName());
				WebClient client = window.getWebClient();
				List<WebWindow> windows = client.getWebWindows();
				log.info("total windows {}, after opened", windows.size());
			}

			@Override
			public void webWindowContentChanged(final WebWindowEvent event) {
				log.info("changed '" + ((HtmlPage) event.getNewPage()).getTitleText() + "'; ");
			}

			@Override
			public void webWindowClosed(final WebWindowEvent event) {
				log.info("a window closed");
				WebWindow window = event.getWebWindow();
				log.info("window name is {}", window.getName());
				WebClient client = window.getWebClient();
				List<WebWindow> windows = client.getWebWindows();
				log.info("total windows {}, after closed", windows.size());
			}
		});

		HtmlPage page = webClient.getPage(url);

		webClient.openWindow(new URL("http://www.baidu.com"), "baidu");
		List<WebWindow> windows = webClient.getWebWindows();
		log.info("total {} windows", windows.size());
		for (WebWindow win : windows) {
			log.info(" window name is {}", win.getName());
		}
	}

}
