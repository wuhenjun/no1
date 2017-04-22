package com.shsxt.po;

import java.util.Date;

/**
 * 用户信息
 * @author Administrator
 *
 */
public class User {
	private int userid;
	
	private String username;
	
	private String pwd;
	
	private String nickname;
	
	private String mood;
	
	private String pic;
	
	private Date attenddate;
	
	public User() {
		// TODO Auto-generated constructor stub
	}

	public User(String username, String pwd) {
		super();
		this.username = username;
		this.pwd = pwd;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getMood() {
		return mood;
	}

	public void setMood(String mood) {
		this.mood = mood;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public Date getAttenddate() {
		return attenddate;
	}

	public void setAttenddate(Date attenddate) {
		this.attenddate = attenddate;
	}
	
}
