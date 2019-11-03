package edu.prj.ui.admin;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;

import edu.prj.ui.UIConst;

/**
 * Servlet Filter implementation class Admin2LoginCheckFilter
 */
@WebFilter("/admin/*")
public class Admin2LoginCheckFilter implements Filter {
	
	FilterConfig filterConfig=null;
	
    /**
     * Default constructor. 
     */
    public Admin2LoginCheckFilter() {
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
	public void doFilter(ServletRequest req, ServletResponse rsp, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		HttpServletRequest request=(HttpServletRequest)req;
		HttpServletResponse response=(HttpServletResponse)rsp;
		// 从request里获取session对象和application对象
		javax.servlet.http.HttpSession session = request.getSession();
		javax.servlet.ServletContext application = request.getServletContext();
		// 调用继承的方式来获取config对象
		javax.servlet.FilterConfig config = filterConfig;
		// 从response对象里获取out对象————response.getWriter()之前，要先设置页面的编码
		java.io.PrintWriter out = response.getWriter();

		//取得本次访问的资源标识符
				String path=request.getRequestURI();
				//1)登录相关的不要检测，放行
				if(path.indexOf("login")>-1||path.indexOf("Login")>-1) {
					chain.doFilter(request, response);
					return;
				}
				
				//2)判断是否存在会话
				String defaultURL = request.getContextPath() + "/admin/Login";
				String toURL = null;
				//没有登录，则跳转到登录页面
				Object obj = session.getAttribute(UIConst.FG_LOGINUSER_KEY);
				if (obj == null) {
					toURL = defaultURL;
					response.sendRedirect(toURL);
					return;
				}
				//3)已登录则放行
				chain.doFilter(request, response);


	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		filterConfig=fConfig;
	}

}
