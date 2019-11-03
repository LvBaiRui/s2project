package edu.prj.ui.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.liuvei.common.SysFun;

import edu.prj.bean.*;
import edu.prj.service.*;
import edu.prj.service.impl.*;
import edu.prj.ui.UIConst;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(urlPatterns = { "/admin/Login" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ManagerService managerService = new ManagerServiceImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// Servlet的doXXX方法中的编码方式和6个标准对象

				// Servlet的doXXX方法中的编码方式
				// 设置请求对象的编码方式
				request.setCharacterEncoding("utf-8");
				// 设置响应对象的编码方式
				response.setCharacterEncoding("utf-8");
				// 设置响应的内容类型为text/html
				response.setContentType("text/html;charset=utf-8");

				// Servlet的doXXX方法中的6个标准对象
				// 从request里获取session对象和application对象
				javax.servlet.http.HttpSession session = request.getSession();
				javax.servlet.ServletContext application = request.getServletContext();
				// 调用继承的方式来获取config对象
				javax.servlet.ServletConfig config = getServletConfig();
				// 从response对象里获取out对象————response.getWriter()之前，要先设置页面的编码
				java.io.PrintWriter out = response.getWriter();
				
				//-------------------------------------------------------------------

				String oper = request.getParameter("oper");
				if (oper == null) {
					oper = "";
				} else {
					oper = oper.trim().toLowerCase();
				}

				// 根据不同的操作类型，调用不同的处理方法
				switch (oper) {
				case "loginview":
					loginView(request, response);// 登录页面
					break;
				case "logindeal":
					loginDeal(request, response);// 登录处理
					break;
				case "logoutdeal":
					logoutDeal(request, response);// 注销处理
					break;
				default:
					loginView(request, response);// 默认
					break;
				}
	}

	protected void loginView(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String toPage = "/WEB-INF/admin/login.jsp";
		request.getRequestDispatcher(toPage).forward(request, response);

	}

	protected void loginDeal(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// Servlet的doXXX方法中的编码方式
		// 设置请求对象的编码方式
		request.setCharacterEncoding("utf-8");
		// 设置响应对象的编码方式
		response.setCharacterEncoding("utf-8");
		// 设置响应的内容类型为text/html
		response.setContentType("text/html;charset=utf-8");

		// Servlet的doXXX方法中的6个标准对象
		// 从request里获取session对象和application对象
		javax.servlet.http.HttpSession session = request.getSession();
		javax.servlet.ServletContext application = request.getServletContext();
		// 调用继承的方式来获取config对象
		javax.servlet.ServletConfig config = getServletConfig();
		// 从response对象里获取out对象————response.getWriter()之前，要先设置页面的编码
		java.io.PrintWriter out = response.getWriter();
		//-------------------------------------------------------------------

		String userName = request.getParameter("userName");
		String userPass = request.getParameter("userPass");
		request.setAttribute("userName", userName);// 回显准备
		String toPage = "/WEB-INF/admin/login.jsp";
		if (SysFun.isNullOrEmpty(userName)) {
			request.setAttribute("msg", "账号不能为空");
			request.getRequestDispatcher(toPage).forward(request, response);
			return;
		}
		if (SysFun.isNullOrEmpty(userPass)) {
			request.setAttribute("msg", "密码不能为空");
			request.getRequestDispatcher(toPage).forward(request, response);
			return;

		}
		Manager bean = null;
		bean = managerService.loadByName(userName);
		if (bean == null) {
			request.setAttribute("msg", "账号不存在。");
			request.getRequestDispatcher(toPage).forward(request, response);
			return;
		}
		System.out.println(bean.getUserPass());
		System.out.println(userPass);
		if (!bean.getUserPass().equals(userPass)) {
			request.setAttribute("msg", "密码错误。");
			request.getRequestDispatcher(toPage).forward(request, response);
			return;
		}

		
		
		// 登录成功，将当前登录账号保存到当前会话
		session.setAttribute(UIConst.FG_LOGINUSER_KEY, bean);
		Cookie cookie=new Cookie("userName",java.net.URLEncoder.encode(userName,"UTF-8"));
		response.addCookie(cookie);
		response.sendRedirect("Main");

	}

	protected void logoutDeal(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// Servlet的doXXX方法中的6个标准对象
		// 从request里获取session对象和application对象
		javax.servlet.http.HttpSession session = request.getSession();
		javax.servlet.ServletContext application = request.getServletContext();
		// 调用继承的方式来获取config对象
		javax.servlet.ServletConfig config = getServletConfig();
		// 从response对象里获取out对象————response.getWriter()之前，要先设置页面的编码
		java.io.PrintWriter out = response.getWriter();
		
		
		//移除会话数据
		//session.removeAttribute(UIConst.FG_LOGINUSER_KEY);
		//设置当前会话失效，重置会话id
		session.invalidate();
		//跳转到登录页面
		String toURL=request.getContextPath()+"/admin/Login";
		
		response.sendRedirect(toURL);
		
	}
	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
