package com.shsxt.daoimpl;

import java.util.ArrayList;
import java.util.List;

import com.shsxt.dao.UserDao;
import com.shsxt.model.PageInfo;
import com.shsxt.model.ResultInfo;
import com.shsxt.po.NoteType;
import com.shsxt.po.Notes;
import com.shsxt.po.PingLun;
import com.shsxt.po.PublicNote;
import com.shsxt.po.User;
import com.shsxt.util.MyStringUtil;

/**
 * 与t_user表进行交互的类
 * @author Administrator
 *
 */
public class UserDaoImpl extends BaseDao implements UserDao{

	@Override
	public User queryByUser(User user) {
		//创建sql
		String sql = "select userid,username,pwd,nickname,mood,pic,attenddate from t_user where username=? and pwd=?";
		//填好参数
		Object[] param = {user.getUsername(),user.getPwd()};
		//执行方法，获得返回值
		User u = (User) queryData(sql, param, User.class);
		return u;
	}

	@Override
	public User updateUserMsg(User user) {
		//创建sql
		StringBuilder sb = new StringBuilder("update t_user set");
		//创建装参数的容器
		List param = new ArrayList<>();
		//判断参数是否存在
		if(!MyStringUtil.isNullOrEmpty(user.getNickname())){
			sb.append(" nickname=?");
			param.add(user.getNickname());
		}
		if(!MyStringUtil.isNullOrEmpty(user.getMood())){
			if(!MyStringUtil.isNullOrEmpty(user.getNickname())){
				sb.append(",mood=?");
				param.add(user.getMood());
			}else{
				sb.append(" mood=?");
				param.add(user.getMood());
			}
		}
		if(!MyStringUtil.isNullOrEmpty(user.getPic())){
			if(MyStringUtil.isNullOrEmpty(user.getNickname()) && MyStringUtil.isNullOrEmpty(user.getMood())){
				sb.append(" pic=?");
				param.add(user.getPic());
			}else{
				sb.append(",pic=?");
				param.add(user.getPic());
			}
		}
		sb.append(" where userid="+user.getUserid());
		//执行sql返回结果
		int value = updateData(sb.toString(), param.toArray());
		if(value == 1){
			User u = (User) queryData("select userid,username,pwd,nickname,mood,pic,attenddate from t_user where userid=?", new Object[]{user.getUserid()}, User.class);
			return u;
		}
		return null;
	}

	@Override
	public boolean checkNickName(String nick) {
		//准备sql
		String sql = "select userid,username,pwd,nickname,mood,pic,attenddate from t_user where nickname=?";
		//进行查询
		Object value = queryCount(sql, new Object[]{nick});
		if(value == null){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public PageInfo queryNoteTypeByUserId(int userid,int pageNum) {
		//创建sql，查询数量
		String sqlcount = "select count(*) from t_notetype where userid=?";
		//创建sql,查询内容
		String sql = "select typeid,typename,userid from t_notetype where userid=?";
		//执行查询，返回结果
		PageInfo.MsgCount = 5;
		PageInfo pi = queryDataByPageNum(sqlcount, sql, new Object[]{userid}, NoteType.class, pageNum);
		return pi;
	}

	@Override
	public boolean checkTypeName(int userid,String typename) {
		//准备sql
		String sql = "select typeid,typename,userid from t_notetype where userid=? and typename=?";
		//进行查询
		Object value = queryCount(sql, new Object[]{userid,typename});
		if(value == null){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean updateTypeNote(int userid, String typename) {
		//准备sql
		String sql = "insert into t_notetype(typename,userid) values(?,?)";
		//进行操作
		int value = updateData(sql, new Object[]{typename,userid});
		return value == 1 ? true : false;
		
	}

	@Override
	public boolean updateTypeName(int typeid, String typename) {
		//准备sql
		String sql = "update t_notetype set typename=? where typeid=?";
		//进行操作
		int value = updateData(sql, new Object[]{typename,typeid});
		return value == 1 ? true : false;
	}

	@Override
	public boolean deleteTypeNoteByTypeid(int typeid) {
		//准备sql
		String sql = "delete from t_notetype where typeid=?";
		//进行操作
		int value = updateData(sql, new Object[]{typeid});
		return value == 1 ? true : false;
	}

	@Override
	public List<NoteType> queryNoteType(int userid) {
		//准备sql
		String sql = "select typeid,typename,userid from t_notetype where userid=?";
		List<NoteType> list = queryDatas(sql, new Object[]{userid}, NoteType.class);
		return list;
	}

	@Override
	public boolean addnote(Notes notes) {
		String sql = "insert into t_note(title,typeid,content) value(?,?,?)";
		int value = updateData(sql, new Object[]{notes.getTitle(),notes.getTypeid(),notes.getContent()});
		return value == 1 ? true : false;
	}

	@Override
	public PageInfo queryAllNote(int userid,int pageNum) {
		String sqlcount = "select count(1) from t_note n join (select u.userid,nt.typeid,nt.typename from t_user u JOIN t_notetype nt on u.userid=nt.userid)m on n.typeid=m.typeid where userid =?";
		
		String sql = "select n.noteid as noteid,n.pubtime as pubtime,n.title as title,m.typename as typename from t_note n join (select u.userid,nt.typeid,nt.typename from t_user u JOIN t_notetype nt on u.userid=nt.userid)m on n.typeid=m.typeid where userid =? order by pubtime desc";
		PageInfo.MsgCount = 10;
		PageInfo pi = queryDataByPageNum(sqlcount, sql, new Object[]{userid}, Notes.class, pageNum);
		pi.setWhopage(1);
		return pi;
	}

	@Override
	public Notes queryNotesByNotesid(int noteid) {
		String sql = "select b.typename as typename,a.noteid as noteid,a.title as title,a.content as content,a.pubtime as pubtime from t_note a join t_notetype b on a.typeid=b.typeid where a.noteid = ?";
		Notes note = (Notes) queryData(sql, new Object[]{noteid}, Notes.class);
		return note;
	}

	@Override
	public boolean delnoteByNoteId(int noteid) {
		String sql = "delete from t_note where noteid=?";
		int value = updateData(sql, new Object[]{noteid});
		return value == 1 ? true : false;
	}

	@Override
	public PageInfo queryNoteByDate(int userid) {
		String sql = "select count(n.noteid) as count,DATE_FORMAT(n.pubtime, '%Y年%m月') as strpubtime,m.userid as userid from t_note n join (select u.userid,nt.typeid from t_user u join t_notetype nt on u.userid=nt.userid) m on n.typeid=m.typeid where userid =? GROUP BY DATE_FORMAT(pubtime, '%Y年%m月') ORDER BY pubtime";
		String sqlcount = "select count(*) from t_note n join (select u.userid,nt.typeid from t_user u join t_notetype nt on u.userid=nt.userid) m on n.typeid=m.typeid where userid = ? GROUP BY DATE_FORMAT(pubtime, '%Y年%m月') ORDER BY pubtime";
		PageInfo.MsgCount = 3;
		PageInfo pi = queryDataByPageNum(sqlcount, sql, new Object[]{userid}, Notes.class, 1);
		return pi;
	}

	@Override
	public PageInfo queryNoteByStringDate(int userid,String pubtime, int pageNum) {
		String sqlcount = "select count(1) from t_note n join (select u.userid,nt.typeid,nt.typename from t_user u JOIN t_notetype nt on u.userid=nt.userid)m on n.typeid=m.typeid where m.userid=? and DATE_FORMAT(n.pubtime, '%Y年%m月')=?";
		String sql = "select n.noteid as noteid,n.pubtime as pubtime,n.title as title,m.typename as typename from t_note n join (select u.userid,nt.typeid,nt.typename from t_user u JOIN t_notetype nt on u.userid=nt.userid)m on n.typeid=m.typeid where m.userid=? and DATE_FORMAT(n.pubtime, '%Y年%m月')=? order by n.pubtime desc";
		PageInfo.MsgCount = 10;
		PageInfo pi = queryDataByPageNum(sqlcount, sql, new Object[]{userid,pubtime}, Notes.class, pageNum);
		pi.setWhopage(2);
		return pi;
	}

	@Override
	public PageInfo queryNoteTypeByUserid(int userid) {
		String sqlcount = "select count(1) from t_note n join (select u.userid,nt.typeid,nt.typename from t_user u join t_notetype nt on u.userid=nt.userid)m on n.typeid=m.typeid where userid=? GROUP BY n.typeid";
		String sql = "select m.typeid as typeid,count(n.noteid) as count, m.typename as typename,m.userid as userid from t_note n join (select u.userid,nt.typeid,nt.typename from t_user u join t_notetype nt on u.userid=nt.userid)m on n.typeid=m.typeid where userid=? GROUP BY n.typeid order by noteid desc";
		PageInfo.MsgCount = 4;
		PageInfo pi = queryDataByPageNum(sqlcount, sql, new Object[]{userid}, Notes.class, 1);
		return pi;
	}

	@Override
	public PageInfo queryNoTeTypeBytypename(int userid,String typename, int pageNum) {
		String sqlcount = "select count(1) from t_note n join t_notetype nt on n.typeid=nt.typeid where typename=? and userid=?";
		String sql = "select noteid,typename,title,pubtime from t_note n join t_notetype nt on n.typeid=nt.typeid where typename=? and userid=? ORDER BY n.typeid desc";
		PageInfo.MsgCount = 10;
		PageInfo pi = queryDataByPageNum(sqlcount, sql, new Object[]{typename,userid}, Notes.class, pageNum);
		pi.setWhopage(3);
		return pi;
	}

	@Override
	public boolean addPublicNotes(Notes note) {
		String sql = "insert into t_publicnote(userid,title,content) values(?,?,?)";
		int value = updateData(sql, new Object[]{note.getUserid(),note.getTitle(),note.getContent()});
		return value == 1 ? true : false;
	}

	@Override
	public PageInfo querypublicnotes(int pageNum) {
		String sqlcount = "select count(1) from t_user u join t_publicnote p on u.userid=p.userid";
		String sql = "select publicnoteid,nickname,title,pubtime from t_user u join t_publicnote p on u.userid=p.userid ORDER BY pubtime DESC";
		PageInfo.MsgCount = 10;
		PageInfo ri = queryDataByPageNum(sqlcount, sql, new Object[]{}, PublicNote.class, pageNum);
		return ri;
	}

	@Override
	public PublicNote querypublicnoteById(int publicnoteid) {
		String sql = "select publicnoteid,nickname,title,pubtime,content from t_user u join t_publicnote p on u.userid=p.userid where publicnoteid=?";
		PublicNote pn = null;
		if(queryData(sql, new Object[]{publicnoteid},PublicNote.class) != null){
			pn = (PublicNote) queryData(sql, new Object[]{publicnoteid},PublicNote.class);
		}
		return pn;
	}

	@Override
	public PageInfo queryPLBypublicnotid(int publicnoteid,int pageNum) {
		String sqlcount = "select count(1) from t_pinglun where publicnoteid=?"; 
		String sql = "select pid,nickname,pic,content,pubtime from t_pinglun where publicnoteid=? order by pubtime desc";
		PageInfo.MsgCount = 10;
		PageInfo pi = queryDataByPageNum(sqlcount, sql, new Object[]{publicnoteid}, PingLun.class, pageNum);
		return pi;
		
	}

	@Override
	public boolean savepinglun(PingLun pl) {
		String sql = "insert into t_pinglun(nickname,pic,content,publicnoteid) value(?,?,?,?)";
		int value = updateData(sql, new Object[]{pl.getNickname(),pl.getPic(),pl.getContent(),pl.getPublicnoteid()});
		return value == 1 ? true : false;
	}

	@Override
	public PageInfo searchKey(int pageNum, String val) {
		String sqlcount = "select count(1) from t_user u join t_publicnote p on u.userid=p.userid where title LIKE '%"+val+"%'";
		String sql = "select publicnoteid,nickname,title,pubtime from t_user u join t_publicnote p on u.userid=p.userid where title LIKE '%"+val+"%' ORDER BY pubtime DESC";
		PageInfo.MsgCount = 10;
		PageInfo pi = queryDataByPageNum(sqlcount, sql, new Object[]{}, PublicNote.class, pageNum);
		return pi;
	}
	
}
