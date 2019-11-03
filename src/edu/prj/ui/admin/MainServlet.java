package edu.prj.ui.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.liuvei.common.SysFun;

import edu.prj.bean.Manager;
import edu.prj.ui.UIConst;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet(urlPatterns = { "/admin/Main" })
public class MainServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MainServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// Servlet的doXXX方法中的编码方式
		// Servlet的doXXX方法中的编码方式
		// 设置请求对象的编码方式
//		request.setCharacterEncoding("utf-8");
//		// 设置响应对象的编码方式
//		response.setCharacterEncoding("utf-8");
//		// 设置响应的内容类型为text/html
//		response.setContentType("text/html;charset=utf-8");

		// Servlet的doXXX方法中的6个标准对象
		// 从request里获取session对象和application对象
		javax.servlet.http.HttpSession session = request.getSession();
		javax.servlet.ServletContext application = request.getServletContext();
		// 调用继承的方式来获取config对象
		javax.servlet.ServletConfig config = getServletConfig();
		// 从response对象里获取out对象————response.getWriter()之前，要先设置页面的编码
		java.io.PrintWriter out = response.getWriter();

		// -------------------------------------------------------------------

		String oper = request.getParameter("oper");
		if (oper == null) {
			oper = "";
		} else {
			
			oper = oper.trim().toLowerCase();
		}

		// 根据不同的操作类型，调用不同的处理方法
		switch (oper) {
		case "mbview":
			mbView(request, response);// 模板
			break;
		default:
			mainView(request, response);// 默认
			break;
		}

	}

	protected void mainView(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String toPage = "/WEB-INF/admin/index.jsp";
		request.getRequestDispatcher(toPage).forward(request, response);
	}

	protected void mbView(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String toPage = "/WEB-INF/admin/mb.jsp";
		request.getRequestDispatcher(toPage).forward(request, response);
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
