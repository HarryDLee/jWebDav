package ru.arturgspb.jwebdav;

import com.sun.net.httpserver.Headers;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import ru.arturgspb.jwebdav.auth.AuthType;

import java.io.IOException;

public class jWebDav {
	AuthType authType;
	AbstractHttpClient httpClient;

	public jWebDav(AuthType authType) {
		this.authType = authType;
		this.httpClient = new DefaultHttpClient();
	}

	public HttpResponse put(String url, byte[] data, String contentType) throws IOException {
		Headers headers = new Headers();
		if (contentType != null)
			headers.add(HttpHeaders.CONTENT_TYPE, contentType);
		return this.put(url, new ByteArrayEntity(data), headers);
	}

	public HttpResponse put(String url, HttpEntity entity, Headers headers) throws IOException {
		HttpPut put = new HttpPut(url);
		put.setEntity(entity);
		put.setHeader(HttpHeaders.EXPECT, "100-continue");
		return this.execute(put, headers);
	}

	public HttpResponse post(String url) throws IOException {
		HttpPost post = new HttpPost(url);
		return this.execute(post, null);
	}

	private HttpResponse execute(HttpRequestBase request, Headers headers) throws IOException {
		if (headers != null) {
			for (String key : headers.keySet()) {
				request.addHeader(key, headers.getFirst(key));
			}
		}
		request.setHeader(HttpHeaders.ACCEPT, "*/*");
		request.setHeader(HttpHeaders.AUTHORIZATION, authType.getHeaderValue());
		HttpResponse response = httpClient.execute(request);
		request.abort();
		httpClient.clearRequestInterceptors();
		return response;
	}
}
