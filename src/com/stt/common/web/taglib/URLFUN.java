package com.stt.common.web.taglib;

import java.io.UnsupportedEncodingException;

public class URLFUN {

	public static final String DEFAULT_ENC = "UTF-8";

	public static String decode(String text) {
		return decode(text, DEFAULT_ENC);
	}

	public static String decode(String text, String enc) {
		String value = text;
		try {
			value = java.net.URLDecoder.decode(text, enc);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return value;
	}
	
	public static Long randNum() {
		Long num=0L;
		int ss=(int)(Math.random()*10000);
		num=Long.parseLong(ss+"");
		return num;
	}
	
	
	

}
