package ru.arturgspb.jwebdav.auth;

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.ParseException;

public class OAuth implements AuthType {
	private String type;
	private String token;

	public OAuth(String type, String token) {
		this.type = type;
		this.token = token;
	}

	@Override
	public String getHeaderValue() {
		return type + " " + token;
	}
}
