package com.shsxt.po;

import java.util.Date;

public class PingLun {
	private int pid ;
	private String nickname;
	private String pic;
	private String content;
	private Date pubtime;
	private String strpubtime;
	private int publicnoteid;
	
	public PingLun() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public int getPublicnoteid() {
		return publicnoteid;
	}



	public void setPublicnoteid(int publicnoteid) {
		this.publicnoteid = publicnoteid;
	}



	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
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

	public String getStrpubtime() {
		return strpubtime;
	}

	public void setStrpubtime(String strpubtime) {
		this.strpubtime = strpubtime;
	}
	
	
}
