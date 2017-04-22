package com.shsxt.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shsxt.util.WebUtils;

/**
 * Servlet Filter implementation class FilterServlet
 */
@WebFilter("*.do")
public class FilterServlet implements Filter {

    /**
     * Default constructor. 
     */
    public FilterServlet() {
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
		String url = req.getRequestURI();
		//获取session
		HttpSession session = req.getSession();
		//判断是否存在session
		if(session.getAttribute("userInfo") != null){
			//放行
			chain.doFilter(request, response);
			return ;
		}
		//3、退出不用拦截
		if(url.contains("user")){
			//获取 act
			String act =req.getParameter("act");
			if(!WebUtils.isNull(act)&& (act.equals("logout")||act.equals("login"))){
				chain.doFilter(req, res); //放行
				return ;
			}
		}
		if(url.contains("yzm.do")){
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
