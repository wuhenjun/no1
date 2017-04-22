package com.shsxt.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * 注册，产生连接对象，关闭连接
 * @author Administrator
 *
 */
public class JDBCUtil {
	private static Properties p;    //配置文件
	
	static {
		//初始化、加载配置文件
		p = new Properties();
		try {
			p.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("db.properties"));
			//注册
			Class.forName("com.mysql.jdbc.Driver");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 创建数据库连接对象
	 */
	public static Connection getConn(){
		Connection conn = null;
		//创建连接对象
		try {
			conn = DriverManager.getConnection(p.getProperty("mysqlURL"),
					p.getProperty("mysqlUname"),
					p.getProperty("mysqlPwd"));
			//将连接对象返回
			return conn;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 关闭数据库连接对象，以及连接通道，结果集通道
	 */
	public static void closeConn(Connection conn,Statement stmt,ResultSet rs){
		if(rs != null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(stmt != null){
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
