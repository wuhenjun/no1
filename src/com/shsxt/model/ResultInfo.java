package com.shsxt.model;
/**
 * 结果信息类
 * @author Administrator
 *
 */
public class ResultInfo<T> {
	private int resultCode;  //结果信息码
	
	private String resultStringMsg;  //错误提示
	
	private T resultMsg;   //结果信息
	
	public ResultInfo() {
		// TODO Auto-generated constructor stub
	}

	public int getResultCode() {
		return resultCode;
	}

	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}

	public T getResultMsg() {
		return resultMsg;
	}

	public void setResultMsg(T resultMsg) {
		this.resultMsg = resultMsg;
	}

	public String getResultStringMsg() {
		return resultStringMsg;
	}

	public void setResultStringMsg(String resultStringMsg) {
		this.resultStringMsg = resultStringMsg;
	}
	
	
}
