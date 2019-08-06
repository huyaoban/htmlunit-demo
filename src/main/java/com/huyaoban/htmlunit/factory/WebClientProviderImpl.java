package com.huyaoban.htmlunit.factory;

import org.apache.commons.pool2.impl.GenericObjectPool;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gargoylesoftware.htmlunit.WebClient;

@Service
public class WebClientProviderImpl implements WebClientProvider, InitializingBean {

	@Autowired
	private PooledWebClientFactory factory;

	private GenericObjectPool<WebClient> webClientPool;

	@Override
	public void afterPropertiesSet() throws Exception {
		webClientPool = new GenericObjectPool<WebClient>(factory);

		webClientPool.setTestOnBorrow(true);
		webClientPool.setMaxWaitMillis(5000);
		webClientPool.setBlockWhenExhausted(true);
		webClientPool.setMaxTotal(40);
	}

	@Override
	public WebClient getWebClient() {
		try {
			return webClientPool.borrowObject();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("获取WebClient出现异常", e);
		}
	}

	@Override
	public void returnWebClient(WebClient client) {
		webClientPool.returnObject(client);
	}

}
