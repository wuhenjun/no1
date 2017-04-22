package com.shsxt.util;




public class MyStringUtil {
	
	/**
	 * 将字符串的首字母变大写
	 * @param str
	 * @return
	 */
	public static String firstChar2Upper(String str){
		return str.toUpperCase().charAt(0)+str.substring(1);
	}
	
	public static boolean isNullOrEmpty(String str){
		boolean flag=false;
		if(null==str||str.length()==0){
			flag=true;
			
		}
		return flag;	
	}
	
	
	
	
	
	
}
