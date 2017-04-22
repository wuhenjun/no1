package com.shsxt.web;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;

import com.alibaba.fastjson.JSON;
import com.shsxt.model.PageInfo;
import com.shsxt.model.ResultInfo;
import com.shsxt.po.Notes;
import com.shsxt.po.PingLun;
import com.shsxt.po.PublicNote;
import com.shsxt.po.User;
import com.shsxt.service.UserService;
import com.shsxt.serviceimpl.UserServiceImpl;
import com.shsxt.util.MyStringUtil;
@WebServlet(name="UserServlet",urlPatterns="/user.do")
public class UserServlet extends HttpServlet{
	private UserService userservice;
	
	public UserServlet() {
		userservice = new UserServiceImpl();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//判断act，分发任务
		String act = req.getParameter("act");
		//分发任务
		if(null == act || act.equals("login")){
			login(req, resp);
			return;
		}
		if(act.equals("info")){
			req.setAttribute("change", "user/user.jsp");
			req.getRequestDispatcher("mainTemp.jsp").forward(req, resp);
			return;
		}
		if(act.equals("image")){
			getImage(req, resp);
			return;
		}
		if(act.equals("logout")){
			logout(req, resp);
			return ;
		}
		if(act.equals("checkNickName")){
			checkNickName(req, resp);
			return ;
		}
		if(act.equals("type")){
			typeManage(req, resp);
			return;
		}
		if(act.equals("checkTypeName")){
			checkTypeName(req, resp);
			return ;
		}
		if(act.equals("addtype")){
			addTypeNote(req, resp);
			return ;
		}
		if(act.equals("updatetypename")){
			updatetypename(req, resp);
			return;
		}
		if(act.equals("del")){
			deleteTypeNote(req, resp);
			return;
		}
		if(act.equals("zhuye")){
			querypublicnotes(req, resp);
			return;
		}
		if(act.equals("notelist")){
			queryAllNote(req, resp);
			return;
		}
		if(act.equals("queryriji")){
			queryrijiByNoteid(req, resp);
			return;
		}
		if(act.equals("delnote")){
			delnoteByNoteId(req, resp);
			return;
		}
		if(act.equals("queryNoteByDate")){
			queryNoteByDate(req, resp);
			return;
		}
		if(act.equals("queryNoTeTypeBytypename")){
			queryNoTeTypeBytypename(req, resp);
			return;
		}
		if(act.equals("querypublicriji")){
			querypublicrijiById(req, resp);
			return;
		}
		if(act.equals("savepinglun")){
			savepinglun(req, resp);
			return;
		}
		if(act.equals("searchKey")){
			searchKey(req, resp);
			return;
		}
	}
	
	protected void searchKey(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//查询公开日志
		String page = req.getParameter("pageNum");
		String val = req.getParameter("val");
		int pageNum = 0;
		//根据userId查询类别列表
		if(MyStringUtil.isNullOrEmpty(page)){
			pageNum = 1;
		}else{
			pageNum = Integer.parseInt(page);
		}
		PageInfo pi = userservice.searchKey(pageNum, val);
		List<PublicNote> list = pi.getPageMsg();
		for(PublicNote notes : list){
			Date date = notes.getPubtime();
			String s = notes.getStrpubtime();
			if(s == null){
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				notes.setStrpubtime(df.format(date));
			}
		}
		req.setAttribute("zhuyenote", pi);
		req.setAttribute("change", "zhuye.jsp");
		req.getRequestDispatcher("mainTemp.jsp").forward(req, resp);
		return;
	}
	
	protected void savepinglun(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int publicnoteid = Integer.parseInt(req.getParameter("publicnoteid"));
		String content = req.getParameter("content");
		
		ResultInfo<User> ri = (ResultInfo<User>) req.getSession().getAttribute("userInfo");
		String nickname = ri.getResultMsg().getNickname();
		String pic = ri.getResultMsg().getPic();
		PingLun pl = new PingLun();
		pl.setPublicnoteid(publicnoteid);
		pl.setContent(content);
		pl.setNickname(nickname);
		pl.setPic(pic);
		
		//存储
		boolean flag = userservice.savepinglun(pl);
		if(flag){
			//查询分页信息
			//查询
			ResultInfo<PublicNote> ri3 = userservice.querypublicnoteById(publicnoteid);
			//查询
			if(ri3.getResultCode() == 2){
				req.setAttribute("note", ri3);
				req.setAttribute("change", "riji.jsp");
				
				//查询评论信息
				String page = req.getParameter("pageNum");
				int pageNum = 0;
				//根据userId查询类别列表
				if(MyStringUtil.isNullOrEmpty(page)){
					pageNum = 1;
				}else{
					pageNum = Integer.parseInt(page);
				}
				PageInfo pi = userservice.queryPLBypublicnotid(publicnoteid, pageNum);
				List<PingLun> list = pi.getPageMsg();
				for(PingLun notes : list){
					Date date = notes.getPubtime();
					String s = notes.getStrpubtime();
					if(s == null){
						DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						notes.setStrpubtime(df.format(date));
					}
				}
				req.setAttribute("pinglun", pi);
				
				req.getRequestDispatcher("mainTemp.jsp").forward(req, resp);
				return;
			}
		}else{
			return;
		}
			
	}
	
	protected void querypublicrijiById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int publicnoteid = Integer.parseInt(req.getParameter("publicnoteid"));
		//查询
		ResultInfo<PublicNote> ri = userservice.querypublicnoteById(publicnoteid);
		//查询
		if(ri.getResultCode() == 2){
			req.setAttribute("note", ri);
			req.setAttribute("change", "riji.jsp");
			
			//查询评论信息
			String page = req.getParameter("pageNum");
			int pageNum = 0;
			//根据userId查询类别列表
			if(MyStringUtil.isNullOrEmpty(page)){
				pageNum = 1;
			}else{
				pageNum = Integer.parseInt(page);
			}
			PageInfo pi = userservice.queryPLBypublicnotid(publicnoteid, pageNum);
			List<PingLun> list = pi.getPageMsg();
			for(PingLun notes : list){
				Date date = notes.getPubtime();
				String s = notes.getStrpubtime();
				if(s == null){
					DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					notes.setStrpubtime(df.format(date));
				}
			}
			req.setAttribute("pinglun", pi);
			
			req.getRequestDispatcher("mainTemp.jsp").forward(req, resp);
			return;
		}else{
			return;
		}
	}
	
	protected void querypublicnotes(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//查询公开日志
		String page = req.getParameter("pageNum");
		int pageNum = 0;
		//根据userId查询类别列表
		if(MyStringUtil.isNullOrEmpty(page)){
			pageNum = 1;
		}else{
			pageNum = Integer.parseInt(page);
		}
		PageInfo pi = userservice.querypublicnotes(pageNum);
		List<PublicNote> list = pi.getPageMsg();
		for(PublicNote notes : list){
			Date date = notes.getPubtime();
			String s = notes.getStrpubtime();
			if(s == null){
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				notes.setStrpubtime(df.format(date));
			}
		}
		req.getSession().setAttribute("zhuyenote", pi);
		req.setAttribute("change", "zhuye.jsp");
		req.getRequestDispatcher("mainTemp.jsp").forward(req, resp);
		return;
	}
	
	protected void queryNoTeTypeBytypename(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//获取userId
		ResultInfo<User> ri = (ResultInfo<User>) req.getSession().getAttribute("userInfo");
		int userid = ri.getResultMsg().getUserid();
		String typename = req.getParameter("typename");
		String page = req.getParameter("pageNum");
		int pageNum = 0;
		//根据userId查询类别列表
		if(MyStringUtil.isNullOrEmpty(page)){
			pageNum = 1;
		}else{
			pageNum = Integer.parseInt(page);
		}
		PageInfo pi = userservice.queryNoTeTypeBytypename(userid,typename, pageNum);
		List<Notes> list = pi.getPageMsg();
		for(Notes notes : list){
			Date date = notes.getPubtime();
			String s = notes.getStrpubtime();
			if(s == null){
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				notes.setStrpubtime(df.format(date));
			}
		}
		queryyunji(req, resp);
		queryleixing(req, resp);
		req.setAttribute("notes", pi);
		req.setAttribute("change", "notelist.jsp");
		req.getRequestDispatcher("mainTemp.jsp").forward(req, resp);
		return;
		
	}
	
	protected void queryyunji(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//获取userId
		ResultInfo<User> ri = (ResultInfo<User>) req.getSession().getAttribute("userInfo");
		int userid = ri.getResultMsg().getUserid();
		
		PageInfo pi = userservice.queryNoteByDate(userid);
		
		req.getSession().setAttribute("yunjicundang",pi );
	}
	protected void queryleixing(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//获取userId
		ResultInfo<User> ri = (ResultInfo<User>) req.getSession().getAttribute("userInfo");
		int userid = ri.getResultMsg().getUserid();
		
		PageInfo pi = userservice.queryNoteTypeByUserid(userid);
		
		req.getSession().setAttribute("yunjileixing",pi );
	}
	/**
	 * 根据发布日期查询日志
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void queryNoteByDate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String page = req.getParameter("pageNum");
		String pubtime = req.getParameter("notedate");
		ResultInfo<User> ri = (ResultInfo<User>) req.getSession().getAttribute("userInfo");
		int userid = ri.getResultMsg().getUserid();
		int pageNum = 0;
		//根据userId查询类别列表
		if(MyStringUtil.isNullOrEmpty(page)){
			pageNum = 1;
		}else{
			pageNum = Integer.parseInt(page);
		}
		//查询日志
		PageInfo pi = userservice.queryNoteByStringDate(userid,pubtime, pageNum);
		List<Notes> list = pi.getPageMsg();
		for(Notes notes : list){
			Date date = notes.getPubtime();
			String s = notes.getStrpubtime();
			if(s == null){
				DateFormat df = new SimpleDateFormat("yyyy年MM月");
				notes.setStrpubtime(df.format(date));
			}
		}
		queryyunji(req, resp);
		queryleixing(req, resp);
		//处理日期的输出问题
		req.setAttribute("notes", pi);
		req.setAttribute("change", "notelist.jsp");
		req.getRequestDispatcher("mainTemp.jsp").forward(req, resp);
		return;
	}
	/**
	 * 删除日志
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void delnoteByNoteId(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int noteid = Integer.parseInt(req.getParameter("noteid"));
		//传递给后台处理
		boolean flag = userservice.delnoteByNoteId(noteid);
		if(flag){
			resp.getWriter().print(1);
			return;
		}else{
			resp.getWriter().print(-1);
			return;
		}
		
	}
	/**
	 * 根据noteid查询日记
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void queryrijiByNoteid(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int noteid = Integer.parseInt(req.getParameter("noteid"));
		//查询
		ResultInfo<Notes> ri = userservice.queryNotesByNotesid(noteid);
		if(ri.getResultCode() == 1){
			req.setAttribute("note", ri);
			req.setAttribute("change", "riji.jsp");
			
			//查询评论信息
			/*String page = req.getParameter("pageNum");
			int pageNum = 0;
			//根据userId查询类别列表
			if(MyStringUtil.isNullOrEmpty(page)){
				pageNum = 1;
			}else{
				pageNum = Integer.parseInt(page);
			}
			//获取userId
			ResultInfo<User> ri = (ResultInfo<User>) req.getSession().getAttribute("userInfo");
			int userid = ri.getResultMsg().getUserid();
			
			String 
			
			PageInfo pi = userservice.queryPLBypublicnotid(publicnoteid, pageNum);
			req.setAttribute("pinglun", pi);*/
			
			req.getRequestDispatcher("mainTemp.jsp").forward(req, resp);
			return;
		}else{
			return;
		}
	}
	/**
	 * 根据userid查询全部日志
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 * @throws ParseException 
	 */
	protected void queryAllNote(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		//获取userId
		ResultInfo<User> ri = (ResultInfo<User>) req.getSession().getAttribute("userInfo");
		int userid = ri.getResultMsg().getUserid();
		
		String page = req.getParameter("pageNum");
		int pageNum = 0;
		//根据userId查询类别列表
		if(MyStringUtil.isNullOrEmpty(page)){
			pageNum = 1;
		}else{
			pageNum = Integer.parseInt(page);
		}
		//查询日志
		PageInfo pi = userservice.queryAllNote(userid, pageNum);
		//处理日期的输出问题
		List<Notes> list = pi.getPageMsg();
		for(Notes notes : list){
			Date date = notes.getPubtime();
			String s = notes.getStrpubtime();
			if(s == null){
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				notes.setStrpubtime(df.format(date));
			}
		}
		queryyunji(req, resp);
		queryleixing(req, resp);
		req.setAttribute("notes", pi);
		req.setAttribute("change", "notelist.jsp");
		req.getRequestDispatcher("mainTemp.jsp").forward(req, resp);
		return;
	}
	/**
	 * 根据typeID删除类别信息
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void deleteTypeNote(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//获取typeid
		int typeid = Integer.parseInt(req.getParameter("tid"));
		//删除信息
		boolean flag = userservice.deleteTypeNoteByTypeid(typeid);
		if(flag){
			//返回成功标志
			resp.getWriter().print(1);
			return;
		}else{
			resp.getWriter().print(-1);
			return;
		}
	}
	/**
	 * 根据typeid跟typename修改类别信息
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void updatetypename(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//获取修改后的值
		String typename = req.getParameter("tname");
		//获取userId
		int typeid = Integer.parseInt(req.getParameter("typeid"));
		//传递给service处理
		boolean flag = userservice.updateTypeName(typeid, typename);
		if(flag){
			resp.getWriter().print(1);
			return;
		}else{
			return;
		}
	}
	
	/**
	 * 更新类别信息，并且查询最新的类别信息
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void addTypeNote(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//获取typeName
		String typename = req.getParameter("type_name");
		//获取userId
		ResultInfo<User> ri = (ResultInfo<User>) req.getSession().getAttribute("userInfo");
		int userid = ri.getResultMsg().getUserid();
		//传递给service层进行处理
		boolean flag = userservice.updateTypeNote(userid, typename);
		if(flag){ //更新成功,进行查询，默认页码为1
			//获取更新之后的数据
			PageInfo pi = userservice.queryNoteTypeByUserId(userid, 1);
			req.setAttribute("pageinfo", pi);
			req.setAttribute("change", "notetype/type.jsp");
			req.getRequestDispatcher("mainTemp.jsp").forward(req, resp);
			return;
		}else{//调至出错界面
			return;
		}
	}
	
	/**
	 * 检查类型名是否唯一
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void checkTypeName(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//获取类型名
		String typename = req.getParameter("typename");
		ResultInfo<User> ri1 = (ResultInfo<User>) req.getSession().getAttribute("userInfo");
		int userid = ri1.getResultMsg().getUserid();
		//创建结果对象
		ResultInfo ri = new ResultInfo<>();
		//如果为空，则返回结果码-1
		if(MyStringUtil.isNullOrEmpty(typename)){
			ri.setResultCode(-1);
			ri.setResultStringMsg("类别名不能为空！");
			//获取JSON字符串
			String str = JSON.toJSONString(ri);
			resp.getWriter().print(str);
			return ;
		}
		//查询数据库，看是否有存在
		boolean flag = userservice.checkTypeName(userid,typename);
		if(flag){ //可用
			ri.setResultCode(1);
			//获取JSON字符串
			String str = JSON.toJSONString(ri);
			resp.getWriter().print(str);
			return ;
		}else{
			ri.setResultCode(-1);
			ri.setResultStringMsg("类别名已存在！");
			//获取JSON字符串
			String str = JSON.toJSONString(ri);
			resp.getWriter().print(str);
			return ;
		}
	}
	
	/**
	 * 执行类别管理方法，将数据携带到前台
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	public void typeManage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		//获取session中的信息,以及用户ID
		HttpSession session = req.getSession();
		ResultInfo<User> result = (ResultInfo<User>) session.getAttribute("userInfo");
		int userid = result.getResultMsg().getUserid();
		//获取分页信息
		String page = req.getParameter("pageNum");
		int pageNum = 0;
		//根据userId查询类别列表
		if(MyStringUtil.isNullOrEmpty(page)){
			pageNum = 1;
		}else{
			pageNum = Integer.parseInt(page);
		}
		//将参数传递给service层处理
		PageInfo pi = userservice.queryNoteTypeByUserId(userid, pageNum);
		//将信息存放至request中，并且加入静态页面
		queryyunji(req, resp);
		req.setAttribute("pageinfo", pi);
		req.setAttribute("change", "notetype/type.jsp");
		//请求转发至给定页面
		req.getRequestDispatcher("mainTemp.jsp").forward(req, resp);
		return ;
	}
	
	/**
	 * 分发到登录模块处理
	 * @throws IOException 
	 * @throws ServletException 
	 */
	public void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		//获取用户传过来的uname以及pwd
		String uname = req.getParameter("uname");
		String pwd = req.getParameter("upwd");
		
		String yzm = req.getParameter("yzm");
		
		//传递给service层处理业务
		ResultInfo<User> ri = userservice.login(uname, pwd);
		//获取session中的验证码
		String sessionyzm = (String) req.getSession().getAttribute("yzm");
		if(null != yzm){
			if(!yzm.equals(sessionyzm)){
				//转发回登录界面,并返回提示信息
				req.setAttribute("msg", "验证码错误");
				req.getRequestDispatcher("login.jsp").forward(req, resp);
				return ;
			}
		}
		//判断结果
		if(ri.getResultCode() == -1){
			//转发回登录界面,并返回提示信息
			req.setAttribute("msg", ri.getResultStringMsg());
			req.getRequestDispatcher("login.jsp").forward(req, resp);
			return ;
		}else{
			//登录到主界面
			//加cookie
			String his = req.getParameter("his");
			if(!MyStringUtil.isNullOrEmpty(his)){
				Cookie cookie = new Cookie("user",uname+"-"+pwd);
				cookie.setMaxAge(1*60*60*24);
				cookie.setPath(req.getContextPath());
				resp.addCookie(cookie);
			}
			//根据用户ID查询日志信息
			int userid = ri.getResultMsg().getUserid();
			PageInfo pitype = userservice.queryNoteTypeByUserid(userid);
			PageInfo pi = userservice.queryNoteByDate(userid);
			req.getSession().setAttribute("yunjileixing",pitype );
			req.getSession().setAttribute("yunjicundang",pi );
			req.getSession().setAttribute("username",uname );
			req.getSession().setAttribute("userpwd",pwd );
			req.getSession().setAttribute("userInfo", ri);
			PageInfo pi2 = userservice.querypublicnotes(1);
			List<PublicNote> list = pi2.getPageMsg();
			for(PublicNote notes : list){
				Date date = notes.getPubtime();
				String s = notes.getStrpubtime();
				if(s == null){
					DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					notes.setStrpubtime(df.format(date));
				}
			}
			req.getSession().setAttribute("zhuyenote", pi2);
			req.setAttribute("change", "zhuye.jsp");
			req.getRequestDispatcher("mainTemp.jsp").forward(req, resp);
			return ;
		}
		
	}
	
	/**
	 * 请求图片
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	public void getImage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		//获取请求图片name
		String imgName = req.getParameter("img");
		String lastName = imgName.substring(imgName.lastIndexOf(".")+1);
		resp.setContentType("image/"+lastName);
		//获取全限定名
		String path = req.getServletContext().getRealPath("/WEB-INF/upload/"+imgName);
		System.out.println(path);
		OutputStream os = resp.getOutputStream();
		//进行文件拷贝
		FileUtils.copyFile(new File(path), os);
		
		return ;
	}
	
	/**
	 * 退出管理
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	public void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		//清除session
		req.getSession().removeAttribute("userInfo");
		req.getSession().removeAttribute("username");
		req.getSession().removeAttribute("userpwd");
		//清除cookie
		Cookie[] c = req.getCookies();
		if(c != null){
			for(Cookie cookie : c){
				cookie.setMaxAge(0);
				resp.addCookie(cookie);
			}
		}
		//返回到登录界面
		resp.sendRedirect("login.jsp");
		return ;
	}
	
	public void checkNickName(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String nick = req.getParameter("nick");
		if(MyStringUtil.isNullOrEmpty(nick)){
			resp.getWriter().print("昵称不能为空！");
			return;
		}
		ResultInfo<User> ri = (ResultInfo<User>) req.getSession().getAttribute("userInfo");
		User user = ri.getResultMsg();
		if(nick.equals(user.getNickname())){
			resp.getWriter().print("");
			return;
		}
		//判断昵称是否唯一
		boolean flag = userservice.checkNickName(nick);
		if(flag){
			resp.getWriter().print("✔");
			return;
		}else{
			resp.getWriter().print("昵称被占用！");
			return;
		}
	}
}
