package com.huyaoban.htmlunit;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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

	@Test
	public void test1() throws FailingHttpStatusCodeException, IOException {
		WebClient client = webClientProvider.getWebClient();

		WebRequest req = new WebRequest(new URL(
				"https://sellercentral.amazon.com/messaging/api/orders/111-7121530-7757804/contactBuyer?marketplaceId=ATVPDKIKX0DER&buyerId=&customerType=&isReturn=false&documentReferrer="));
		req.setHttpMethod(HttpMethod.POST);
		req.setAdditionalHeader("Content-Type", "application/json");
		// 发送亚马逊站内信要带上这个请求头
		req.setAdditionalHeader("X-Requested-With", "A01596255MPE0V36MRSF");

		req.setRequestBody(
				"{\"uploadingFiles\":[],\"failedFiles\":[],\"failedFilesTooLarge\":false,\"topicId\":\"6\",\"rawMessageBody\":\"99999foweufoewflajfjsofuew77777\",\"attachments\":[]}");

		Page amazonMsgPage = client.getPage(req);
		WebResponse resp = amazonMsgPage.getWebResponse();
		log.info("{}", resp.getContentAsString(Charset.forName("UTF-8")));

		webClientProvider.returnWebClient(client);
	}

}
