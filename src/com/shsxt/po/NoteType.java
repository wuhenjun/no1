package com.shsxt.po;
/**
 * 类别管理类
 * @author Administrator
 *
 */
public class NoteType {
	private int typeid;
	
	private String typename;
	
	private int userid;
	
	public NoteType() {
		// TODO Auto-generated constructor stub
	}

	public int getTypeid() {
		return typeid;
	}

	public void setTypeid(int typeid) {
		this.typeid = typeid;
	}

	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}
}
