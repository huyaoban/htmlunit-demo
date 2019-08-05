package com.huyaoban.htmlunit.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.eclipse.jetty.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huyaoban.htmlunit.service.VerificationCodeService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class VerificationCodeServiceImpl implements VerificationCodeService {

	@Override
	public String recognize(String imageUrl) {
		try {
			CloseableHttpClient client = HttpClients.createDefault();
			HttpPost post = new HttpPost("https://v2-api.jsdama.com/upload");
			post.addHeader("Content-Type", "application/json");

			String postData = buildPostData(imageUrl);
			if (!StringUtils.isEmpty(postData)) {
				post.setEntity(new StringEntity(postData, "UTF-8"));
			}

			CloseableHttpResponse response = client.execute(post);
			int responseCode = response.getStatusLine().getStatusCode();
			HttpEntity entity = response.getEntity();
			if (responseCode == HttpStatus.OK_200 && entity != null) {
				String responseBody = EntityUtils.toString(entity, "UTF-8");
				log.info("response body is {}", responseBody);

				JSONObject jsonObj = JSON.parseObject(responseBody);
				if (jsonObj.containsKey("data")) {
					JSONObject dataObj = jsonObj.getJSONObject("data");
					if (dataObj.containsKey("recognition")) {
						return dataObj.getString("recognition");
					}
				}
			} else {
				throw new RuntimeException("识别图片验证码发生异常");
			}

			throw new RuntimeException("识别图片验证码发生异常");
		} catch (IOException ioe) {
			throw new RuntimeException("识别图片验证码发生异常");
		}
	}

	private String buildPostData(String url) throws IOException {
		Map<String, Object> paramMap = new HashMap<String, Object>();

		paramMap.put("softwareId", 14715);
		paramMap.put("softwareSecret", "W9MccftHpmdIFQUXZqBBxOUemnRYaFSsVqRjsCSI");
		paramMap.put("username", "tileo.xiao");
		paramMap.put("password", "__daydayup#2019");
		paramMap.put("captchaData", encodeImgageToBase64(url));
		paramMap.put("captchaType", 1001);
		paramMap.put("captchaMinLength", 0);
		paramMap.put("captchaMaxLength", 0);
		paramMap.put("workerTipsId", 0);

		return JSON.toJSONString(paramMap);
	}

	private String encodeImgageToBase64(String url) throws IOException {
		InputStream is = null;
		CloseableHttpClient client = HttpClients.createDefault();
		HttpGet method = new HttpGet(url);
		CloseableHttpResponse response = client.execute(method);
		int responseCode = response.getStatusLine().getStatusCode();
		HttpEntity entity = response.getEntity();
		if (responseCode == HttpStatus.OK_200 && entity != null) {
			is = entity.getContent();
		} else {
			throw new RuntimeException("识别图片验证码，拉取图片时发生异常");
		}

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len;
		while ((len = is.read(buffer)) != -1) {
			outputStream.write(buffer, 0, len);
		}
		is.close();

		return Base64.encodeBase64String(outputStream.toByteArray());
	}
}
