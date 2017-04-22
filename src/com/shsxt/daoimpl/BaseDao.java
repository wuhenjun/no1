package com.shsxt.daoimpl;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.shsxt.model.PageInfo;
import com.shsxt.util.JDBCUtil;
import com.shsxt.util.MyStringUtil;

/**
 * 这个接口可以与数据库进行交互。进行一系列的增删改查操作
 * @author Administrator
 *
 */
public class BaseDao {
	
	private Connection conn;  //连接对象
	
	private PreparedStatement pstmt; //通道对象
	
	private ResultSet rs;  //结果集对象
	
	/**
	 * 根据sql语句以及限制参数param 查询指定的记录条数并返回
	 * @param sql
	 * @param param
	 * @return
	 */
	public Object queryCount(String sql,Object[] param){
		//获取连接
		conn = JDBCUtil.getConn();
		try {
			//建立通道
			pstmt = conn.prepareStatement(sql);
			//遍历参数
			if(param != null && param.length != 0){
				for(int i=0;i<param.length;i++){
					pstmt.setObject(i+1, param[i]);
				}
			}
			//执行查询语句，返回结果集
			rs = pstmt.executeQuery();
			//创建结果对象
			Object obj = null;
			//遍历结果集，获取结果
			while(rs.next()){
				obj = rs.getObject(1);
			}
			return obj;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			//释放资源
			JDBCUtil.closeConn(conn, pstmt, rs);
		}
		return null;
	}
	
	/**
	 * 根据sql语句以及 指定参数param 和vo类型clz 查询多条匹配的记录，将之存放在list中并返回
	 * @param sql
	 * @param param
	 * @param clz
	 * @return
	 */
	public List queryDatas(String sql,Object[] param,Class clz){
		//获取连接
		conn = JDBCUtil.getConn();
		//创建装数据的容器
		List list = null;
		try {
			//获取通道
			pstmt = conn.prepareStatement(sql);
			//遍历参数
			if(param != null && param.length != 0){
				for(int i=0;i<param.length;i++){
					pstmt.setObject(i+1, param[i]);
				}
			}
			//执行sql，获取结果集
			rs = pstmt.executeQuery();
			//获取元数据
			ResultSetMetaData rsmd = rs.getMetaData();
			//创建容器对象
			list = new ArrayList<>();
			//遍历结果集，将数据放入指定对象，并将对象存入容器
			while(rs.next()){
				try {
					//创建对象盛装数据
					Object obj = clz.newInstance();
					//遍历每一个字段
					for(int j=0;j<rsmd.getColumnCount();j++){
						//获取对应字段的名称
						String columnName = rsmd.getColumnLabel(j+1);
						//获取对应属性
						Field field = clz.getDeclaredField(columnName);
						//根据属性获取对应方法
						Method method = clz.getDeclaredMethod("set"+MyStringUtil.firstChar2Upper(field.getName()), field.getType());
						//判定每一个字段的类型，执行方法
						if("bigdecimal".equals(rsmd.getColumnTypeName(j+1).toLowerCase())){
							method.invoke(obj, rs.getBigDecimal(j+1).doubleValue());
						}else{
							method.invoke(obj, rs.getObject(j+1));
						}
					}
					list.add(obj);
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (NoSuchFieldException e) {
					e.printStackTrace();
				} catch (SecurityException e) {
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			//释放资源
			JDBCUtil.closeConn(conn, pstmt, rs);
		}
		return list;
	}
	
	/**
	 * 根据sql语句以及 指定参数param 和vo类型clz 查询指定的一条记录并返回
	 * @param sql
	 * @param param
	 * @param clz
	 * @return
	 */
	public Object queryData(String sql,Object[] param,Class clz){
		//利用多条件查询多条数据
		List list = queryDatas(sql, param, clz);
		//判断返回结果，获取集合中的唯一一条数据
		return (list == null || list.size() == 0) ? null : list.get(0);
	}
	
	
	
	/**
	 * 根据sql语句以及指定参数param 对指定表格进行增删改的操作,返回指定结果
	 * @param sql
	 * @param param
	 * @return
	 */
	/*public Object updateGetData(String sql,Object[] param,Class clz){
		//执行增删改操作
		int value = updateData(sql, param);
		//如果操作成功，获取其值
		if(value == 1){
			Object obj = queryData(sql, param, clz);
			return obj;
		}
		return null;
	}*/
	/**
	 * 根据sql语句以及指定参数param 对指定表格进行增删改的操作
	 * @param sql
	 * @param param
	 * @return
	 */
	public int updateData(String sql,Object[] param){
		//获取连接
		conn = JDBCUtil.getConn();
		try {
			//建立通道
			pstmt = conn.prepareStatement(sql);
			//遍历参数
			if(param != null && param.length != 0){
				for(int i=0;i<param.length;i++){
					pstmt.setObject(i+1, param[i]);
				}
			}
			//执行查询语句
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			//释放资源
			JDBCUtil.closeConn(conn, pstmt, rs);
		}
		return -1;
	}
	
	/**
	 * 多条sql语句操作数据，事务的提交与回滚处理
	 * @param map
	 * @return
	 */
	public int updateData(Map<String,Object[]> map){
		//获取连接
		conn = JDBCUtil.getConn();
		//设置结果码
		int value = -1;
		//获取map中的信息
		Set<Entry<String,Object[]>> set = map.entrySet();
		try {
			//设置事务不自顶提交
			conn.setAutoCommit(false);
			//遍历信息
			for(Entry<String,Object[]> entry : set){
				//获取sql语句
				String sql = entry.getKey();
				//获取指定条件的参数数组
				Object[] param = entry.getValue();
				//建立通道
				pstmt = conn.prepareStatement(sql);
				//遍历参数
				if(param != null && param.length != 0){
					for(int i=0;i<param.length;i++){
						pstmt.setObject(i+1, param[i]);
					}
				}
				//执行查询语句
				value = pstmt.executeUpdate();
			}
			//提交事务
			conn.commit();
			//设置默认提交
			conn.setAutoCommit(true);
		} catch (SQLException e1) {
			try {
				//有异常，事务回滚
				value = -1;
				conn.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			e1.printStackTrace();
		} finally{
			//释放资源
			JDBCUtil.closeConn(conn, pstmt, rs);
		}
		return value;
	}
	/**
	 * 根据sql语句以及指定参数param 和当前页码，查询指定的页数信息
	 * @param sql
	 * @param param
	 * @return
	 */
	public PageInfo queryDataByPageNum(String sqlCount,String sqlPageNumMsg,Object[] param,Class clz,int pageNum){
		//获取总记录数
		long totalMsg = 0;
		if(queryCount(sqlCount, param) != null){
			totalMsg = (long) queryCount(sqlCount, param);
		}
		//获取当前页面的信息,首先将分页参数加入参数数组，并且进行分页sql语句加入
		//1、加入分页
		sqlPageNumMsg = sqlPageNumMsg + " limit ?,?";
		
		//2、获取分页的起始索引，即分页的第一个参数
		int startPageNum = (pageNum-1)*PageInfo.MsgCount;
		//3、获取分页的指定参数
		Object[] newParam = new Object[param.length+2];
		System.arraycopy(param, 0, newParam, 0, param.length);
		newParam[newParam.length-2] = startPageNum;
		newParam[newParam.length-1] = PageInfo.MsgCount;
		//执行分页查询
		List list = queryDatas(sqlPageNumMsg, newParam, clz);
		//创建pageInfo对象，将查询到的结果存储到此对象里
		PageInfo pf = new PageInfo(totalMsg, list, pageNum);
		return pf;
	}
}
