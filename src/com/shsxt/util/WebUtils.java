package com.shsxt.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.tomcat.util.codec.binary.Base64;

public class WebUtils {
	/**
	 * 编写字符串加密
	 */
	public static String encode(String msg){
		try {
			//1、获取消息摘要
			MessageDigest digest =MessageDigest.getInstance("md5");
			//2、使用Base64加密
			return Base64.encodeBase64String(digest.digest(msg.getBytes()));			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}		
		return null;
		
	}
	
	/**
	 *判断空问题
	 */
	public static boolean isNull(String str){
		return null==str ||str.trim().equals("");
	}
}
