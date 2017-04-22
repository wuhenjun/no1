package com.shsxt.web;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.shsxt.model.ResultInfo;
import com.shsxt.po.User;
import com.shsxt.service.UserService;
import com.shsxt.serviceimpl.UserServiceImpl;
import com.shsxt.util.MyStringUtil;

@WebServlet(name="userUpdate",urlPatterns="/userupdate.do")
public class UserUpdate extends HttpServlet {
	private UserService userservice;
	
	public UserUpdate() {
		userservice = new UserServiceImpl();
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//获取session
		HttpSession session = req.getSession();
		//拷贝session中的值
		ResultInfo ri = (ResultInfo) session.getAttribute("userInfo");
		User user = new User();
		user.setUserid(((User)(ri.getResultMsg())).getUserid());
		
		//准备路径
		String serverPath=this.getServletContext().getRealPath("/WEB-INF");
		//文件的上传
		//1、上传组件  创建组件对象 
		DiskFileItemFactory factory = new DiskFileItemFactory();
		//2、最大缓存   切分大小  提高效率
		factory.setSizeThreshold(5*1024);  
		//3、临时文件目录    
		factory.setRepository(new File(serverPath+"/temp"));
		//4、根据组件获取的上传对象  解析头信息
		ServletFileUpload upload = new ServletFileUpload(factory); 
		//5、设定字符集
		upload.setHeaderEncoding("utf-8");
		//6、设定文件的大小
		upload.setSizeMax(30*1024*1024);
		//7、获取所有的条目列表
		try {
			//内部解析头信息
			List<FileItem> items=upload.parseRequest(req);
			//遍历容器
			Iterator<FileItem> it =items.iterator();
			while(it.hasNext()){
				FileItem item =it.next();
				//8、分析 普通的文本内容 还是文件
				if(item.isFormField()){ //普通表单
					if(item.getFieldName().equals("nick")){
						String value =item.getString("utf-8"); 
						user.setNickname(value);
					}else if(item.getFieldName().equals("mood")){
						String value =item.getString("utf-8"); 
						user.setMood(value);
					}
				}else if(!MyStringUtil.isNullOrEmpty(item.getName())){ //文件
					//准备路径
					String path =serverPath+"/upload";	
					String imageName = user.getUserid()+"-"+item.getName();
					File dest=new File(path+"/"+imageName); 
					try {
						item.write(dest);
						user.setPic(imageName);
					} catch (Exception e) {
						e.printStackTrace();
					}		
				}
			}
		} catch (FileUploadException e) {
		} 
		
		//进行更新操作，操作成功，修改session的值
		User result = userservice.updateMsg(user);
		if(result == null){
			req.setAttribute("error01", "修改失败！");
			req.getRequestDispatcher("user.do?act=info").forward(req, resp);
			return ;
		}else{
			ri.setResultMsg(result);
			req.setAttribute("change","zhuye.jsp");
			req.getRequestDispatcher("mainTemp.jsp").forward(req, resp);
			return ;
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}
}
