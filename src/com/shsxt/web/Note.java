package com.shsxt.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shsxt.model.ResultInfo;
import com.shsxt.po.NoteType;
import com.shsxt.po.Notes;
import com.shsxt.po.User;
import com.shsxt.service.UserService;
import com.shsxt.serviceimpl.UserServiceImpl;
import com.shsxt.util.MyStringUtil;

/**
 * Servlet implementation class Note
 */
@WebServlet(name = "note", urlPatterns = { "/note.do" })
public class Note extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UserService userservice;
	
	public Note() {
		userservice = new UserServiceImpl();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String act = request.getParameter("act");
		if(MyStringUtil.isNullOrEmpty(act) || act.equals("save")){
			queryTypeNote(request, response);
			return;
		}
		if(act.equals("savenote")){
			addnote(request, response);
			return;
		}
		
	}
	
	/**
	 * 增加日记
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void addnote(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取类别ID
		int typeid = Integer.parseInt(request.getParameter("typeId"));
		//获取title
		String title = request.getParameter("title");
		//获取内容
		String content = request.getParameter("content");
		
		Notes notes = new Notes();
		notes.setTitle(title);
		notes.setTypeid(typeid);
		notes.setContent(content);
		//传递给service处理
		boolean flag = userservice.addnote(notes);
		if(flag){
			//判断是否对外公开
			String pb = request.getParameter("public");
			if( pb != null && pb.equals("yes")){
				//获取userId
				ResultInfo<User> ri = (ResultInfo<User>) request.getSession().getAttribute("userInfo");
				int userid = ri.getResultMsg().getUserid();
				notes.setUserid(userid);
				//存储到公共日志表
				userservice.addPublicNotes(notes);
			}
			//request.setAttribute("change", "success.jsp");
			request.getRequestDispatcher("user.do?act=notelist").forward(request, response);
			return ;
		}else{
			return;
		}
	}
	/**
	 * 查询类别信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void queryTypeNote(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取userId
		ResultInfo<User> ri = (ResultInfo<User>) request.getSession().getAttribute("userInfo");
		int userid = ri.getResultMsg().getUserid();
		//查询信息
		ResultInfo<List<NoteType>> typeInfo = userservice.queryNoteType(userid);
		
		request.setAttribute("typeInfo", typeInfo);
		request.setAttribute("change", "note/save.jsp");
		request.getRequestDispatcher("mainTemp.jsp").forward(request, response);;
		return ;
	}

}
