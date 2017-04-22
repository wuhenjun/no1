package com.shsxt.serviceimpl;




import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shsxt.dao.UserDao;
import com.shsxt.daoimpl.UserDaoImpl;
import com.shsxt.model.PageInfo;
import com.shsxt.model.ResultInfo;
import com.shsxt.po.NoteType;
import com.shsxt.po.Notes;
import com.shsxt.po.PingLun;
import com.shsxt.po.PublicNote;
import com.shsxt.po.User;
import com.shsxt.service.UserService;
import com.shsxt.util.MyStringUtil;
import com.shsxt.util.WebUtils;

public class UserServiceImpl implements UserService {
	private UserDao userdao = new UserDaoImpl();
	 private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	@Override
	public ResultInfo<User> login(String uname, String pwd) {
		/**
		 * 1、创建接收信息的对象
		 * 2、判断用户名或者密码是否为空，如果为空，返回信息
		 * 3、判断用户名与密码是否匹配
		 */
		ResultInfo<User> ri = new ResultInfo<User>();
		//判断用户名是否为空
		if(MyStringUtil.isNullOrEmpty(uname)){
			//设置失败信息
			ri.setResultCode(-1);
			ri.setResultStringMsg("用户名不能为空！");
			return ri;
		}
		//判断密码是否为空
		if(MyStringUtil.isNullOrEmpty(pwd)){
			//设置失败信息
			ri.setResultCode(-1);
			ri.setResultStringMsg("密码不能为空！");
			return ri;
		}
		//将信息保存为一个User对象
		//将密码加密
		pwd = WebUtils.encode(pwd);
		User u = new User(uname, pwd);
		//传递给dao层处理，获取返回结果
		User user = userdao.queryByUser(u);
		//判断返回结果
		if(user == null){
			//设置失败信息
			ri.setResultCode(-1);
			ri.setResultStringMsg("用户名或密码错误！");
			return ri;
		}else{
			//设置成功信息
			logger.info("{}进来了", user.getUsername());
			
			ri.setResultCode(1);
			ri.setResultMsg(user);
			return ri;
		}
	}
	@Override
	public User updateMsg(User user) {
		return userdao.updateUserMsg(user);
	}
	@Override
	public boolean checkNickName(String nick) {
		return userdao.checkNickName(nick);
	}
	@Override
	public PageInfo queryNoteTypeByUserId(int userid, int pageNum) {
		return userdao.queryNoteTypeByUserId(userid, pageNum);
	}
	@Override
	public boolean checkTypeName(int userid,String typename) {
		return userdao.checkTypeName(userid,typename);
	}
	@Override
	public boolean updateTypeNote(int userid, String typename) {
		return userdao.updateTypeNote(userid, typename);
	}
	@Override
	public boolean updateTypeName(int typeid, String typename) {
		return userdao.updateTypeName(typeid, typename);
	}
	@Override
	public boolean deleteTypeNoteByTypeid(int typeid) {
		return userdao.deleteTypeNoteByTypeid(typeid);
	}
	@Override
	public ResultInfo<List<NoteType>> queryNoteType(int userid) {
		ResultInfo<List<NoteType>> ri = new ResultInfo<>();
		List<NoteType> list = userdao.queryNoteType(userid);
		if(list != null){
			if(list.size() == 0){
				ri.setResultCode(-1);
			}else{
				ri.setResultCode(1);
				ri.setResultMsg(list);
			}
		}
		return ri;
	}
	@Override
	public boolean addnote(Notes notes) {
		return userdao.addnote(notes);
	}
	@Override
	public PageInfo queryAllNote(int userid, int pageNum) {
		return userdao.queryAllNote(userid, pageNum);
	}
	@Override
	public ResultInfo<Notes> queryNotesByNotesid(int noteid) {
		ResultInfo<Notes> ri = new ResultInfo<>();
		Notes notes = userdao.queryNotesByNotesid(noteid);
		if(notes != null){
			ri.setResultCode(1);
			ri.setResultMsg(notes);
		}else{
			ri.setResultCode(-1);
		}
		return ri;
	}
	@Override
	public boolean delnoteByNoteId(int noteid) {
		return userdao.delnoteByNoteId(noteid);
	}
	@Override
	public PageInfo queryNoteByDate(int userid) {
		return userdao.queryNoteByDate(userid);
	}
	@Override
	public PageInfo queryNoteByStringDate(int userid,String pubtime, int pageNum) {
		return userdao.queryNoteByStringDate(userid,pubtime, pageNum);
	}
	@Override
	public PageInfo queryNoteTypeByUserid(int userid) {
		return userdao.queryNoteTypeByUserid(userid);
	}
	@Override
	public PageInfo queryNoTeTypeBytypename(int userid,String typename, int pageNum) {
		return userdao.queryNoTeTypeBytypename(userid,typename, pageNum);
	}
	@Override
	public boolean addPublicNotes(Notes note) {
		return userdao.addPublicNotes(note);
	}
	@Override
	public PageInfo querypublicnotes(int pageNum) {
		return userdao.querypublicnotes(pageNum);
	}
	@Override
	public ResultInfo<PublicNote> querypublicnoteById(int publicnoteid) {
		ResultInfo<PublicNote> ri = new ResultInfo<>();
		PublicNote notes = userdao.querypublicnoteById(publicnoteid);
		if(notes != null){
			ri.setResultCode(2);
			ri.setResultMsg(notes);
		}else{
			ri.setResultCode(-1);
		}
		return ri;
	}
	@Override
	public PageInfo queryPLBypublicnotid(int publicnoteid,int pageNum){
		return userdao.queryPLBypublicnotid(publicnoteid, pageNum);
	}
	@Override
	public boolean savepinglun(PingLun pl) {
		return userdao.savepinglun(pl);
	}
	@Override
	public PageInfo searchKey(int pageNum, String val) {
		return userdao.searchKey(pageNum, val);
	}

}
