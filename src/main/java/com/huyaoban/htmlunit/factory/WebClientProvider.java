package com.huyaoban.htmlunit.factory;

import com.gargoylesoftware.htmlunit.WebClient;

public interface WebClientProvider {
	WebClient getWebClient();

	void returnWebClient(WebClient client);
}
