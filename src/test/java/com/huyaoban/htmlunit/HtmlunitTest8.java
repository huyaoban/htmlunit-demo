package com.huyaoban.htmlunit;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.HtmlUtils;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.HttpMethod;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.WebResponse;
import com.huyaoban.htmlunit.factory.WebClientProvider;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class HtmlunitTest8 {

	@Autowired
	private WebClientProvider webClientProvider;

	// @Test
	public void test1() throws FailingHttpStatusCodeException, IOException {
		WebClient client = webClientProvider.getWebClient();

		WebRequest req = new WebRequest(new URL(
				"https://sellercentral.amazon.com/messaging/api/orders/111-6299753-5976241/contactBuyer?marketplaceId=ATVPDKIKX0DER1&buyerId=&customerType=&isReturn=false&documentReferrer="));
		req.setHttpMethod(HttpMethod.POST);
		req.setAdditionalHeader("Content-Type", "application/json");
		// 发送亚马逊站内信要带上这个请求头
		req.setAdditionalHeader("X-Requested-With", "A01596255MPE0V36MRSF");

		// html特殊字符需要转义
		req.setRequestBody(
				"{\"attachments\":[],\"failedFiles\":[],\"failedFilesTooLarge\":false,\"rawMessageBody\":\"45&deg;\",\"topicId\":\"6\",\"uploadingFiles\":[]}");

		Page amazonMsgPage = client.getPage(req);
		WebResponse resp = amazonMsgPage.getWebResponse();
		log.info("{}", resp.getContentAsString(Charset.forName("UTF-8")));

		webClientProvider.returnWebClient(client);
	}

	@Test
	public void test2() {
		log.info("after escape {}", HtmlUtils.htmlEscape(
				"the heating pad can be washed in a normal wash at max 40?/104°F; do not bleach it; do not dry this pad in a tumble drier.\\n3. The heating pad will activate level 4 heating by default when powered in and automatically turn off in 30 minutes if not set up with a timer.\\n\\nAs a growing company, we hope that you will be willing to share your experience on Amazon! "));
	}

}
