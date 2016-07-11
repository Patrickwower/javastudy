package com.dengyuecang.www.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpUtil {
	public static String sendPost(String url, Map<String, String> params,
			Map<String, String> headers) {
		CloseableHttpClient httpClient = null;
		try {
			httpClient = HttpClients.createDefault();

			HttpPost httpPost = new HttpPost(url);
			if (headers != null) {
				Set<String> keys = headers.keySet();
				for (Iterator<String> i = keys.iterator(); i.hasNext();) {
					String key = (String) i.next();
					httpPost.addHeader(key, (String) headers.get(key));
				}
			}
			List<NameValuePair> nvps = new ArrayList();

			Set<String> keys = params.keySet();
			for (String key : keys) {
				nvps.add(new BasicNameValuePair(key, (String) params.get(key)));
			}
			httpPost.setEntity(new UrlEncodedFormEntity(nvps, Consts.UTF_8));

			HttpResponse response = httpClient.execute(httpPost);

			HttpEntity entity = response.getEntity();

			return EntityUtils.toString(entity, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "error";
	}
}
