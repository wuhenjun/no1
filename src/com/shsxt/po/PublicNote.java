package com.shsxt.po;

import java.util.Date;

public class PublicNote {
	private int publicnoteid;
	
	private int userid;
	
	private String title;
	
	private String content;
	
	private Date pubtime;
	
	private String nickname;
	
	private String strpubtime;
	public PublicNote() {
		// TODO Auto-generated constructor stub
	}
	

	public String getStrpubtime() {
		return strpubtime;
	}


	public void setStrpubtime(String strpubtime) {
		this.strpubtime = strpubtime;
	}


	public String getNickname() {
		return nickname;
	}



	public void setNickname(String nickname) {
		this.nickname = nickname;
	}



	public int getPublicnoteid() {
		return publicnoteid;
	}

	public void setPublicnoteid(int publicnoteid) {
		this.publicnoteid = publicnoteid;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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
}
