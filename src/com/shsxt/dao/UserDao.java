package com.shsxt.dao;

import java.util.List;

import com.shsxt.model.PageInfo;
import com.shsxt.model.ResultInfo;
import com.shsxt.po.NoteType;
import com.shsxt.po.Notes;
import com.shsxt.po.PingLun;
import com.shsxt.po.PublicNote;
import com.shsxt.po.User;

/**
 * 规范一些与t_user表进行交互的方法
 * @author Administrator
 *
 */
public interface UserDao {
	
	/**
	 * 根据传进来的user参数查询是否存在这一纪录，并返回查到的数据包装成user返回
	 * @param user
	 * @return
	 */
	public User queryByUser(User user);
	
	/**
	 * 根据传进来的user对象，修改对应用户的信息
	 * @param user
	 * @return
	 */
	public User updateUserMsg(User user);
	
	/**
	 * 查看昵称是否可用
	 * @param nick
	 * @return
	 */
	public boolean checkNickName(String nick);
	
	/**
	 * 根据用户ID查询指定类别信息
	 * @param userid
	 * @return
	 */
	public PageInfo queryNoteTypeByUserId(int userid,int pageNum);
	
	/**
	 * 根据类型名查询是否存在
	 * @param typename
	 * @return
	 */
	public boolean checkTypeName(int userid,String typename);
	
	/**
	 * 根据用户ID和类别名新建一条类别记录
	 * @param userid
	 * @param typename
	 * @return
	 */
	public boolean updateTypeNote(int userid,String typename);
	
	/**
	 * 根据用户类型别更新信息
	 */
	public boolean updateTypeName(int typeid,String typename);
	
	/**
	 * 根据typeId删除信息
	 * @param typeid
	 * @return
	 */
	public boolean deleteTypeNoteByTypeid(int typeid);
	
	/**
	 * 根据userid查询noteType的所有信息
	 * @param userid
	 * @return
	 */
	public List<NoteType> queryNoteType(int userid);
	
	/**
	 * 增加日记
	 * @param notes
	 * @return
	 */
	public boolean addnote(Notes notes);
	
	/**
	 * 根据userid查询全部日志
	 * @param userid
	 * @return
	 */
	public PageInfo queryAllNote(int userid,int pageNum);
	
	/**
	 * 查询NOtes
	 * @param noteid
	 * @return
	 */
	public Notes queryNotesByNotesid(int noteid);
	
	/**
	 * 根据noteid删除日志
	 * @param noteid
	 * @return
	 */
	public boolean delnoteByNoteId(int noteid);
	
	/**
	 * 根据userid查询日志信息，按年份分类
	 * @param userid
	 * @return
	 */
	public PageInfo queryNoteByDate(int userid);
	
	/**
	 * 根据发布时间以及页码查询分页信息
	 * @param pubtime
	 * @param pageNum
	 * @return
	 */
	public PageInfo queryNoteByStringDate(int userid,String pubtime,int pageNum);
	
	/**
	 * 根据用户ID查询信息
	 * @param userid
	 * @return
	 */
	public PageInfo queryNoteTypeByUserid(int userid);
	
	/**
	 * 根据用户ID查询指定类别信息
	 * @param userid
	 * @return
	 */
	public PageInfo queryNoTeTypeBytypename(int userid,String typename,int pageNum);
	
	/**
	 * 想公用日志表增加信息
	 * @param note
	 * @return
	 */
	public boolean addPublicNotes(Notes note);
	
	/**
	 * 分页查询公开日志
	 * @param pageNum
	 * @return
	 */
	public PageInfo querypublicnotes(int pageNum);
	
	/**
	 * 根据公用日志ID查询日志
	 * @param publicnoteid
	 * @return
	 */
	public PublicNote querypublicnoteById(int publicnoteid);
	
	/**
	 * 根据公共日志ID查找评论
	 * @param publicnoteid
	 * @return
	 */
	public PageInfo queryPLBypublicnotid(int publicnoteid,int pageNum);
	
	/**
	 * 存储评论
	 * @param pl
	 * @return
	 */
	public boolean savepinglun(PingLun pl);
	
	/**
	 * 根据关键字查询公开日志
	 * @param pageNum
	 * @param val
	 * @return
	 */
	public PageInfo searchKey(int pageNum,String val);
}
