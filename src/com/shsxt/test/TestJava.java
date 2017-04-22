package com.shsxt.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.shsxt.daoimpl.UserDaoImpl;
import com.shsxt.model.PageInfo;
import com.shsxt.po.User;

public class TestJava {

	@Test
	public void test() {
		UserDaoImpl udi = new UserDaoImpl();
		User user = new User();
		user.setUserid(2);
		user.setNickname("杨老师");
		user.setMood("棒棒哒！O(∩_∩)O");
		user.setPic("a.jpg");
		User flag = udi.updateUserMsg(user);
		System.out.println(flag);
	}
	@Test
	public void tes1t() {
		UserDaoImpl udi = new UserDaoImpl();
		PageInfo pi = udi.queryAllNote(1, 1);
		System.out.println(pi.getTotalMsg());
	}
}
