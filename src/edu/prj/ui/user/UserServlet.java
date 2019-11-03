package edu.prj.ui.user;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.liuvei.common.SysFun;

import edu.prj.bean.User;
import edu.prj.service.UserService;
import edu.prj.service.impl.UserServiceImpl;
import edu.prj.ui.UIConst;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet(urlPatterns = { "/store/User" })
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserService userService = new UserServiceImpl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserServlet() {
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

		// -------------------------------------------------------------------

		String oper = request.getParameter("oper");
		if (oper == null) {
			oper = "";
		} else {
			oper = oper.trim().toLowerCase();
		}

		// 根据不同的操作类型，调用不同的处理方法
		switch (oper) {
		case "indexview":
			indexView(request, response);
			break;

		case "loginview":
			loginView(request, response);// 登录页面
			break;
		case "logindeal":
			loginDeal(request, response);// 登录处理
			break;
		case "registview":
			registView(request, response);// 登录页面
			break;
		case "registdeal":
			registDeal(request, response);// 登录处理
			break;
			
		case "detail":
			detailView(request, response);// 登录页面
			break;
			
		case "update":
			updateDeal(request, response);// 模板
			break;
			
		case "update2":
			updatePassView(request, response);// 模板
			break;
			
		case "update3":
			update2Deal(request, response);// 模板
			break;

		case "logoutdeal":
			logoutDeal(request, response);// 注销处理
			break;
		default:
			indexView(request, response);// 默认
			break;
		}
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

	protected void indexView(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String toPage = "/WEB-INF/store/index.jsp";
		request.getRequestDispatcher(toPage).forward(request, response);

	}

	protected void loginView(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String toPage = "/WEB-INF/store/login.jsp";
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
		// -------------------------------------------------------------------

		String userName = request.getParameter("userName");
		String userPass = request.getParameter("userPass");
		request.setAttribute("userName", userName);// 回显准备
		String toPage = "/WEB-INF/store/login.jsp";
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
		User bean = null;
		bean = userService.loadByName(userName);
		if (bean == null) {
			request.setAttribute("msg", "账号不存在。");
			request.getRequestDispatcher(toPage).forward(request, response);
			return;
		}
		if (!bean.getUserPass().equals(userPass)) {
			request.setAttribute("msg", "密码错误。");
			request.getRequestDispatcher(toPage).forward(request, response);
			return;
		}
		System.out.println(bean.getStatus());
		if (bean.getStatus() == 0) {
			request.setAttribute("msg", "请先联系管理员进行激活，再登录。");
			request.getRequestDispatcher(toPage).forward(request, response);
			return;
		}

		// 登录成功，将当前登录账号保存到当前会话
		request.getSession().setAttribute("user", bean);
		Cookie cookie = new Cookie("userName", java.net.URLEncoder.encode(userName, "UTF-8"));
		response.addCookie(cookie);
		response.sendRedirect("Main");

	}

	protected void registView(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String toPage = "/WEB-INF/store/regist.jsp";
		request.getRequestDispatcher(toPage).forward(request, response);

	}

	protected void registDeal(HttpServletRequest request, HttpServletResponse response)
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
		// -------------------------------------------------------------------

//		// 取用户列表，并放到作用域
		List<User> userList = userService.list();
		request.setAttribute("userList", userList);

		String toPage = "/WEB-INF/store/regist.jsp";

		String userName = SysFun.dealString(request.getParameter("userName"));
		String userPass = SysFun.dealString(request.getParameter("userPass"));
		String userPass2 = SysFun.dealString(request.getParameter("userPass2"));
		String name = SysFun.dealString(request.getParameter("name"));
		String email = SysFun.dealString(request.getParameter("email"));

		String address = SysFun.dealString(request.getParameter("address"));

		request.setAttribute("userName", userName);
		request.setAttribute("userPass", userPass);
		request.setAttribute("userPass2", userPass2);
		request.setAttribute("name", name);
		request.setAttribute("email", email);
		request.setAttribute("address", address);

		if (SysFun.isNullOrEmpty(userName)) {
			request.setAttribute("msg", "用户名不能为空");
			request.getRequestDispatcher(toPage).forward(request, response);
			return;
		}
		if (SysFun.isNullOrEmpty(userPass)) {
			request.setAttribute("msg", "密码不能为空");
			request.getRequestDispatcher(toPage).forward(request, response);
			return;
		}
		if (!userPass.equals(userPass2)) {
			request.setAttribute("msg", "两次密码不相同");
			request.getRequestDispatcher(toPage).forward(request, response);
			return;
		}

		User tmpBean = userService.loadByName(userName);
		if (tmpBean != null) {
			request.setAttribute("msg", "该用户已存在");
			request.getRequestDispatcher(toPage).forward(request, response);
			return;
		}

		if (SysFun.isNullOrEmpty(email)) {
			request.setAttribute("msg", "邮箱不能为空");
			request.getRequestDispatcher(toPage).forward(request, response);
			return;
		}
		boolean isOk=false;
		isOk=isEmail(email);
		if(!isOk) {
			request.setAttribute("msg", "邮箱格式不正确。");
			request.getRequestDispatcher(toPage).forward(request, response);
			return;
		}
		


		if (SysFun.isNullOrEmpty(name)) {
			request.setAttribute("msg", "姓名不能为空");
			request.getRequestDispatcher(toPage).forward(request, response);
			return;
		}

		User bean = new User();
		bean.setUserName(userName);
		bean.setUserPass(userPass2);
		bean.setName(name);
		bean.setEmail(email);
		bean.setAddress(address);
		bean.setStatus(0L);
		int rand = (int) ((Math.random() * 9 + 1) * 1000);
		bean.setCode(Integer.toString(rand));

		Long result = 0L;
		String msg = "";
		result = userService.insert(bean);

		if (result > 0) {
			out.println("<script>alert('注册成功，请联系管理员进行激活.');location.href='Main?oper=list';</script>");
			return;
		} else {
			System.out.println(msg);
			msg = msg.replace("\'", "");
			request.setAttribute("msg", "添加失败！" + msg);
			request.getRequestDispatcher(toPage).forward(request, response);
		}

	}
	
	protected void detailView(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		java.io.PrintWriter out = response.getWriter();

		// -------------------------------------------------------------------

		// 取得主键，再根据主键，获取记录
		String vId = request.getParameter("id");
		if (!SysFun.isNullOrEmpty(vId)) {
			Long iId = SysFun.parseLong(vId);
			User bean = userService.load(iId);
			if (bean != null) {
				// 使用对象来回显页面
				request.setAttribute("bean", bean);

				// 数据存在才转发到页面
				String toPage = "/WEB-INF/store/User_detail.jsp";
				request.getRequestDispatcher(toPage).forward(request, response);
				return;
			}
		}

		out.println("<script>");
		out.println("alert('数据不存在')");
		out.println("location.href='User?oper=list';");
		out.println("</script>");
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

		// 移除会话数据
		// session.removeAttribute(UIConst.FG_LOGINUSER_KEY);
		// 设置当前会话失效，重置会话id
		session.invalidate();
		// 跳转到登录页面
		String toURL = request.getContextPath() + "/store/Main";

		response.sendRedirect(toURL);

	}
	
	
	protected void updateDeal(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		javax.servlet.http.HttpSession session = request.getSession();
		javax.servlet.ServletContext application = request.getServletContext();
		// 调用继承的方式来获取config对象
		javax.servlet.ServletConfig config = getServletConfig();
		// 从response对象里获取out对象————response.getWriter()之前，要先设置页面的编码
		java.io.PrintWriter out = response.getWriter();

		// -------------------------------------------------------------------

		List<User> userList = userService.list();
		request.setAttribute("userList", userList);

		String url = "User?oper=list";
		String id_str = SysFun.dealString(request.getParameter("id"));
		String name = SysFun.dealString(request.getParameter("name"));
		String email = SysFun.dealString(request.getParameter("email"));
		String address = SysFun.dealString(request.getParameter("address"));

		request.setAttribute("name", name);
		request.setAttribute("email", email);
		request.setAttribute("address", address);
		

		if (SysFun.isNullOrEmpty(id_str)) {
			out.print(alertGo("id不能为空。", url));
			return;
		}
		Long id = Long.parseLong(id_str);
		String toPage = "/WEB-INF/store/User_detail.jsp";


		
		if (SysFun.isNullOrEmpty(name)) {
			request.setAttribute("msg", "真实姓名不能为空");
			request.getRequestDispatcher(toPage).forward(request, response);
			return;
		}

		
		User bean = userService.load(id);
		
		bean.setName(name);
		bean.setEmail(email);
		bean.setAddress(address);
		

		Long result = 0L;
		String msg = "";

		result = userService.update(bean);

		if (result > 0) {
			
			request.getSession().setAttribute("user", bean);
			
			out.print(alertGo("修改成功", url));
			return;
		} else {
			System.out.println(msg);
			msg = msg.replace("\'", "");
			request.setAttribute("msg", "修改失败");
			// request.getRequestDispatcher(toPage).forward(request, response);
			detailView(request, response);
			return;
		}

	}
	
	private StringBuilder alertGo(String msg, String url) {
		StringBuilder sb = new StringBuilder();
		sb.append("<script>");
		sb.append("alert('" + msg + "');");
		sb.append("location.href='" + url + "';");
		sb.append("</script>");
		return sb;
	}
	
	protected void updatePassView(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		javax.servlet.http.HttpSession session = request.getSession();
		javax.servlet.ServletContext application = request.getServletContext();
		// 调用继承的方式来获取config对象
		javax.servlet.ServletConfig config = getServletConfig();
		// 从response对象里获取out对象————response.getWriter()之前，要先设置页面的编码
		java.io.PrintWriter out = response.getWriter();

		// -------------------------------------------------------------------

		List<User> userList = userService.list();
		request.setAttribute("userList", userList);

		String id = request.getParameter("id");

		// 为空性判断
		if (id == null || id.isEmpty()) {
			out.print("<script>alert('id不能为空.');location.href='User?oper=detail';</script>");
			return;
		}

		// 主键值的合法性
		Long vId = 0L;
		try {
			vId = Long.parseLong(id);
		} catch (Exception e) {
			out.print("<script>alert('id不是数字.');location.href='User?oper=detail';</script>");
			return;
		}

		// 从数据库获取数据
		User bean = null;
		bean = userService.load(vId);
		if (bean == null) {
			out.print("<script>alert('数据不存在.');location.href='User?oper=detail';</script>");
			return;
		}

		// 回显准备
		request.setAttribute("bean", bean);

		String toPage = "/WEB-INF/store/User_update2.jsp";
		request.getRequestDispatcher(toPage).forward(request, response);

	}
	
	protected void update2Deal(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		javax.servlet.http.HttpSession session = request.getSession();
		javax.servlet.ServletContext application = request.getServletContext();
		// 调用继承的方式来获取config对象
		javax.servlet.ServletConfig config = getServletConfig();
		// 从response对象里获取out对象————response.getWriter()之前，要先设置页面的编码
		java.io.PrintWriter out = response.getWriter();
		
		List<User> userList = userService.list();
		request.setAttribute("userList", userList);

		String url = "User?oper=list";
		String id_str = SysFun.dealString(request.getParameter("id"));
		String userPass0 = SysFun.dealString(request.getParameter("userPass0"));
		String userPass1 = SysFun.dealString(request.getParameter("userPass1"));
		String userPass2 = SysFun.dealString(request.getParameter("userPass2"));
		System.out.println(userPass0);
		
		System.out.println(userPass1);
		
		System.out.println(userPass2);

		request.setAttribute("userPass0", userPass0);
		request.setAttribute("userPass1", userPass1);
		request.setAttribute("userPass2", userPass2);

		if (SysFun.isNullOrEmpty(id_str)) {
			out.print(alertGo("id不能为空。", url));
			return;
		}
		Long id = Long.parseLong(id_str);
		String toPage = "/WEB-INF/store/User_update2.jsp";
		User bean = userService.load(id);
		
		System.out.println(bean.getUserPass());

		
		if (SysFun.isNullOrEmpty(userPass0)) {
			request.setAttribute("msg", "原密码不能为空");
			request.getRequestDispatcher(toPage).forward(request, response);
			return;
		}
		
		if(!userPass0.equals(bean.getUserPass())) {
			request.setAttribute("msg", "原密码不正确");
			System.out.println("原密码不正确");
			request.getRequestDispatcher(toPage).forward(request, response);
			return;
		}
		
		if (SysFun.isNullOrEmpty(userPass1)) {
			request.setAttribute("msg", "新密码不能为空");
			request.getRequestDispatcher(toPage).forward(request, response);
			return;
		}
		if (SysFun.isNullOrEmpty(userPass2)) {
			request.setAttribute("msg", "确认密码不能为空");
			request.getRequestDispatcher(toPage).forward(request, response);
			return;
		}

		if(!userPass1.equals(userPass2)) {
			request.setAttribute("msg", "两次密码不相同");
			request.getRequestDispatcher(toPage).forward(request, response);
			return;
		}
		

		bean.setUserPass(userPass2);
		

		Long result = 0L;
		String msg = "";

		result = userService.update3(bean);

		if (result > 0) {
			
			request.getSession().setAttribute("user", bean);
			
			out.print(alertGo("修改成功", url));
			return;
		} else {
			System.out.println(msg);
			msg = msg.replace("\'", "");
			request.setAttribute("msg", "修改失败");
			// request.getRequestDispatcher(toPage).forward(request, response);
			detailView(request, response);
			return;
		}
		
		
	}

	

	public static boolean isEmail(String email) {
		String regExp = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		Pattern p = Pattern.compile(regExp);
		Matcher m = p.matcher(email);
		return m.find();
	}
		
}
