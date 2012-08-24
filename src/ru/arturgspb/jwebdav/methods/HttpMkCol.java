package ru.arturgspb.jwebdav.methods;

import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.protocol.HTTP;

import java.net.URI;

public class HttpMkCol extends HttpEntityEnclosingRequestBase {

	public static final String METHOD_NAME = "MKCOL";

	public HttpMkCol(String url)
	{
		this(URI.create(url));
	}

	public HttpMkCol(URI url)
	{
		this.setURI(url);
		this.setHeader(HttpHeaders.CONTENT_TYPE, "text/xml" + HTTP.CHARSET_PARAM + HTTP.UTF_8.toLowerCase());
	}

	@Override
	public String getMethod()
	{
		return METHOD_NAME;
	}
}
