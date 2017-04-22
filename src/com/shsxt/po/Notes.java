package com.shsxt.po;

import java.util.Date;

/**
 * 日记类
 * @author Administrator
 *
 */
public class Notes {
	private int noteid;
	
	private String title;
	
	private int typeid;
	
	private String content;
	
	private Date pubtime;
	
	private String typename;
	
	private String strpubtime;
	
	private int userid;
	
	private long count ;
	
	public Notes() {
		// TODO Auto-generated constructor stub
	}
	
	public String getStrpubtime() {
		return strpubtime;
	}

	

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public void setStrpubtime(String strpubtime) {
		this.strpubtime = strpubtime;
	}



	public int getNoteid() {
		return noteid;
	}

	public void setNoteid(int noteid) {
		this.noteid = noteid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getTypeid() {
		return typeid;
	}

	public void setTypeid(int typeid) {
		this.typeid = typeid;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getPubtime() {
		return pubtime;
	}

	public void setPubtime(Date pubtime) {
		this.pubtime = pubtime;
	}

	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}
	
}
