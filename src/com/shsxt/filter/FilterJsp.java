package com.shsxt.filter;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.internal.ArrayComparisonFailure;

import com.shsxt.util.WebUtils;

/**
 * Servlet Filter implementation class FilterJsp
 */
@WebFilter("*.jsp")
public class FilterJsp implements Filter {

    /**
     * Default constructor. 
     */
    public FilterJsp() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		req.setCharacterEncoding("utf-8");
		res.setContentType("text/html;charset=utf-8");
		
		//获取session
		HttpSession session = req.getSession();
		//判断是否存在session
		if(session.getAttribute("userInfo") != null){
			//放行
			chain.doFilter(request, response);
			return ;
		}
		//判断是否有cookie值
		Cookie[] c = req.getCookies();
		if(c != null){
			//遍历cookie
			for(Cookie cookie :c){
				//获取数据
				String name = cookie.getName();
				if("user".equals(name)){
					String[] userMsg = cookie.getValue().split("-");
					//确保数组不会越界
					userMsg = Arrays.copyOf(userMsg, 2);
					String uname = userMsg[0];
					String pwd = userMsg[1];
					//重定向到服务器
					req.setAttribute("username",uname );
					req.setAttribute("userpwd",pwd );
					req.getRequestDispatcher("login.jsp?act=login").forward(req, res);;
					return ;
				}
			}
		}
		//2)、判断
		String url = req.getRequestURI();
		if(url.contains("statics/")||url.contains("login.jsp")){
			chain.doFilter(req, res); //放行
			return ;
		}
		//其他全部拦截
		res.sendRedirect("login.jsp");
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
