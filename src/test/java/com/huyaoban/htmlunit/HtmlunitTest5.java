package com.huyaoban.htmlunit;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.huyaoban.htmlunit.factory.WebClientProvider;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class HtmlunitTest5 {

	@Autowired
	private WebClientProvider webClientProvider;

	private volatile Set<Integer> ids = new HashSet<>();

	@Test
	public void test1() throws InterruptedException {
		ExecutorService service = Executors.newFixedThreadPool(30);
		List<WebClientTask> tasks = new ArrayList<WebClientTask>();
		for (int i = 1; i <= 30; i++) {
			tasks.add(new WebClientTask(i));
		}
		service.invokeAll(tasks);
		Assert.assertEquals(30, ids.size());
	}

	class WebClientTask implements Callable<Integer> {
		private Integer id;

		public WebClientTask(Integer id) {
			this.id = id;
		}

		@Override
		public Integer call() throws Exception {
			WebClient client = webClientProvider.getWebClient();
			HtmlPage page = client.getPage("http://www.baidu.com");
			Thread.sleep(10000);

			webClientProvider.returnWebClient(client);
			ids.add(id);
			return id;
		}

	}

}
