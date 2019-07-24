package com.example.demo.util;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

public class ApiUtils {
	
	public static String callAPItoStr (String url, Map<String, String> headers, Map<String, String> params) {
		CloseableHttpClient hc = HttpClientBuilder.create().build();
		String result = null;
		try {
			if (params != null && !params.isEmpty()) {
				for (String key : params.keySet()) {
					if (url.lastIndexOf("?") > -1) {
						url = url + "&" + key + "=" + URLEncoder.encode(params.get(key), "UTF-8");
					} else {
						url = url + "?" + key + "=" + URLEncoder.encode(params.get(key), "UTF-8");
					}
				}
			}
			
			HttpGet get = new HttpGet(url);
			if (headers != null && !headers.isEmpty()) {
				for (String key : headers.keySet()) {
					get.addHeader(key, headers.get(key));
				}
			}
			
			HttpResponse hs = hc.execute(get);
			result = EntityUtils.toString(hs.getEntity(), "UTF-8");
		} catch (Exception e) {
		} finally {
			try {
				hc.close();
			} catch (IOException e) {
			}
		}
		return result;
	}
}
