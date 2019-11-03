package edu.prj.ui.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.prj.bean.Manager;
import edu.prj.ui.UIConst;

/**
 * Servlet implementation class BaseServlet
 */
public abstract class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;



	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BaseServlet() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	protected String checkLoginAndIsAdmin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		javax.servlet.http.HttpSession session = request.getSession();
		String defaultURL = request.getContextPath() + "/admin/Login";
		String toURL = null;
		Object obj = session.getAttribute(UIConst.FG_LOGINUSER_KEY);
		if (obj == null) {
			toURL = defaultURL;
			return toURL;
		}

		// 如果登录，则判断是否是管理员
//		Manager member = (Manager) obj;
//		if (!member.getLoginName().equalsIgnoreCase("admin")) {
//			// 如果不是管理员，则删除会话中的登录用户信息，之后，转发到登录页面
//			session.removeAttribute(UIConst.BG_LOGINUSER_KEY);
//			toURL = defaultURL;
//			return toURL;
//		}
		return toURL;

	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
                          