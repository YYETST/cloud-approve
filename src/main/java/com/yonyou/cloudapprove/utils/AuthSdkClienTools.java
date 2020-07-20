package com.yonyou.cloudapprove.utils;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.yonyou.apache.http.util.Asserts;
import com.yonyou.cloud.auth.sdk.client.AuthSDKClient;
import com.yonyou.cloud.auth.sdk.client.utils.codec.SignUtils;

/**
 * 
 * @author guanjpu
 *
 */
public class AuthSdkClienTools extends AuthSDKClient {

	private static final Logger LOGGER = LoggerFactory.getLogger(AuthSdkClienTools.class);
	private String accessKey;
	private String accessSecret;

	public AuthSdkClienTools(String accessKey, String accessSecret) {
		super(accessKey, accessSecret);
		this.accessKey = accessKey;
		this.accessSecret = accessSecret;
	}

	public String executePostJson(String reqUrl, Object body, Map<String, String> headerMap) {
		Asserts.notBlank(this.accessKey, "accessKey");
		Asserts.notBlank(this.accessSecret, "accessSecret");
		CloseableHttpClient httpclient = HttpClientBuilder.create().build();
		RequestConfig requestConfig = RequestConfig.custom()  
		        .setConnectTimeout(5000).setConnectionRequestTimeout(5000)  
		        .setSocketTimeout(5000).build();  
		HttpPost post = new HttpPost(reqUrl);
		post.setConfig(requestConfig);
		Map<String, List<String>> reqParams = new TreeMap();
		post.setHeader("Content-Type", ContentType.APPLICATION_JSON.toString());
		post.addHeader("YYCtoken", SignUtils.getToken(this.accessKey, this.accessSecret, reqUrl, reqParams));
		if (headerMap != null) {
			for (Map.Entry<String, String> entry : headerMap.entrySet()) {
				post.setHeader(entry.getKey(), entry.getValue());
			}
		}
		if (body != null) {
			StringEntity stringEntity = new StringEntity(JSON.toJSONString(body), ContentType.APPLICATION_JSON);
			post.setEntity(stringEntity);
		}
		try {
			CloseableHttpResponse response = httpclient.execute(post);
			HttpEntity entity = response.getEntity();
			String result = EntityUtils.toString(entity, "utf-8");

			System.out.println("发送开发者中心"+result);
			return result;
		} catch (Exception e) {
			LOGGER.error("", e);
			throw new RuntimeException("AuthSdkClientTools doPost失败." + e.getMessage(), e);
		} finally {
			if (null != post) {
				post.releaseConnection();
			}
		}
	}
}
