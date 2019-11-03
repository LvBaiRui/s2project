package edu.prj.ui.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.liuvei.common.PagerItem;
import com.liuvei.common.RspResult;
import com.liuvei.common.SysFun;

import edu.prj.bean.Manager;
import edu.prj.service.ManagerService;
import edu.prj.service.impl.ManagerServiceImpl;

/**
 * Servlet implementation class ManagerServlet
 */
@WebServlet("/admin/Manager")
public class ManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	ManagerService managerService = new ManagerServiceImpl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ManagerServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Servlet的doXXX方法中的编码方式
		// 设置请求对象的编码方式
		request.setCharacterEncoding("utf-8");
		// 设置响应对象的编码方式
		response.setCharacterEncoding("utf-8");
		// 设置响应的内容类型为text/html
		response.setContentType("text/html;charset=utf-8");
		javax.servlet.http.HttpSession session = request.getSession();
		javax.servlet.ServletContext application = request.getServletContext();
		// 调用继承的方式来获取config对象
		javax.servlet.ServletConfig config = getServletConfig();
		// 从response对象里获取out对象————response.getWriter()之前，要先设置页面的编码
		java.io.PrintWriter out = response.getWriter();

		// -------------------------------------------------------------------

		// 取得操作类型
		String oper = request.getParameter("oper");
		if (oper == null) {
			oper = "";
		} else {
			oper = oper.trim().toLowerCase();
		}

		// 根据不同的操作类型，调用不同的处理方法
		switch (oper) {
		case "list":
			listView(request, response);// 模板
			break;
		case "listdeal":
			listDeal(request, response);// 模板
			break;
		case "insert":
			insertView(request, response);// 模板
			break;
		case "insertdeal":
			insertDeal(request, response);// 模板
			break;
		case "update":
			updateView(request, response);// 模板
			break;
		case "updatedeal":
			updateDeal(request, response);// 模板
			break;
		case "deletedeal":
			deleteDeal(request, response);// 模板
			break;
		case "detail":
			detailView(request, response);// 模板
			break;

		default:
			listView(request, response);// 默认
			break;
		}
	}

	private void listView(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Manager> vDataList = null;
		// vDataList=questionService.list();

		// 步骤1
		PagerItem pagerItem = new PagerItem();
		pagerItem.parsePageNum(request.getParameter(pagerItem.getParamPageNum()));
		pagerItem.parsePageSize(request.getParameter(pagerItem.getParamPageSize()));

		// 步骤2
		Long rowCount = managerService.count();
		pagerItem.changeRowCount(rowCount);
		vDataList = managerService.pager(pagerItem.getPageNum(), pagerItem.getPageSize());
		pagerItem.changeUrl(SysFun.generalUrl(request.getRequestURI(), request.getQueryString()));

		// 步骤3：
		request.setAttribute("pagerItem", pagerItem);
		request.setAttribute("DataList", vDataList);

		String toPage = "/WEB-INF/admin/Manager_list.jsp";
		request.getRequestDispatcher(toPage).forward(request, response);

	}
	
	private void listDeal(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Manager> vDataList = null;
		// vDataList=questionService.list();
		String name = request.getParameter("searchName");
		name = name == null ? "" : name;

		request.setAttribute("searchName", name);// 搜索关键字回显准备
		// 步骤1
		PagerItem pagerItem = new PagerItem();
		pagerItem.parsePageNum(request.getParameter(pagerItem.getParamPageNum()));
		pagerItem.parsePageSize(request.getParameter(pagerItem.getParamPageSize()));

		// 步骤2
		if (SysFun.isNullOrEmpty(name)) {
			Long rowCount = managerService.count();
			pagerItem.changeRowCount(rowCount);
			vDataList = managerService.pager(pagerItem.getPageNum(), pagerItem.getPageSize());
			pagerItem.changeUrl(SysFun.generalUrl(request.getRequestURI(), request.getQueryString()));
		} else {
			Long rowCount = managerService.countByName(name);
			pagerItem.changeRowCount(rowCount);
			vDataList = managerService.pagerByName(name, pagerItem.getPageNum(), pagerItem.getPageSize());
			pagerItem.changeUrl(SysFun.generalUrl(request.getRequestURI(), request.getQueryString()));
		}

		// 步骤3：
		request.setAttribute("pagerItem", pagerItem);
		request.setAttribute("DataList", vDataList);

		String toPage = "/WEB-INF/admin/Manager_list.jsp";
		request.getRequestDispatcher(toPage).forward(request, response);

	}
	
	private void insertView(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String toPage = "/WEB-INF/admin/Manager_insert.jsp";
		request.getRequestDispatcher(toPage).forward(request, response);

	}
	
	private void insertDeal(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// Servlet的doXXX方法中的编码方式
		// 设置请求对象的编码方式
		request.setCharacterEncoding("utf-8");
		// 设置响应对象的编码方式
		response.setCharacterEncoding("utf-8");
		// 设置响应的内容类型为text/html
		response.setContentType("text/html;charset=utf-8");
		javax.servlet.http.HttpSession session = request.getSession();
		javax.servlet.ServletContext application = request.getServletContext();
		// 调用继承的方式来获取config对象
		javax.servlet.ServletConfig config = getServletConfig();
		// 从response对象里获取out对象————response.getWriter()之前，要先设置页面的编码
		java.io.PrintWriter out = response.getWriter();

		// -------------------------------------------------------------------

		// 取用户列表，并放到作用域
		List<Manager> managerList = managerService.list();
		request.setAttribute("managerList", managerList);

		String toPage = "/WEB-INF/admin/Manager_insert.jsp";

		String userName = SysFun.dealString(request.getParameter("userName"));
		String userPass = SysFun.dealString(request.getParameter("userPass"));
		String userPass2 = SysFun.dealString(request.getParameter("userPass2"));

		request.setAttribute("userName", userName);
		request.setAttribute("userPass", userPass);
		request.setAttribute("userPass2", userPass2);

		if (SysFun.isNullOrEmpty(userName)) {
			request.setAttribute("msg", "管理员名称不能为空");
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

		Manager tmpBean = managerService.loadByName(userName);
		if (tmpBean != null) {
			request.setAttribute("msg", "该管理员已存在");
			request.getRequestDispatcher(toPage).forward(request, response);
			return;
		}

		Manager bean = new Manager();
		bean.setUserName(userName);
		bean.setUserPass(userPass2);

		Long result = 0L;
		String msg = "";
		result = managerService.insert(bean);

		if (result > 0) {
			out.println("<script>alert('添加成功.');location.href='Manager?oper=list';</script>");
			return;
		} else {
			System.out.println(msg);
			msg = msg.replace("\'", "");
			request.setAttribute("msg", "添加失败！" + msg);
			request.getRequestDispatcher(toPage).forward(request, response);
		}

	}

	private void updateView(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// Servlet的doXXX方法中的编码方式
		// 设置请求对象的编码方式
		request.setCharacterEncoding("utf-8");
		// 设置响应对象的编码方式
		response.setCharacterEncoding("utf-8");
		// 设置响应的内容类型为text/html
		response.setContentType("text/html;charset=utf-8");
		javax.servlet.http.HttpSession session = request.getSession();
		javax.servlet.ServletContext application = request.getServletContext();
		// 调用继承的方式来获取config对象
		javax.servlet.ServletConfig config = getServletConfig();
		// 从response对象里获取out对象————response.getWriter()之前，要先设置页面的编码
		java.io.PrintWriter out = response.getWriter();

		// -------------------------------------------------------------------

		List<Manager> managerList = managerService.list();
		request.setAttribute("managerList", managerList);

		String managerId = request.getParameter("managerId");

		// 为空性判断
		if (managerId == null || managerId.isEmpty()) {
			out.print("<script>alert('管理员Id不能为空.');location.href='Manager?oper=list';</script>");
			return;
		}

		// 主键值的合法性
		Long vId = 0L;
		try {
			vId = Long.parseLong(managerId);
		} catch (Exception e) {
			out.print("<script>alert('管理员Id不是数字.');location.href='Manager?oper=list';</script>");
			return;
		}

		// 从数据库获取数据
		Manager bean = null;
		bean = managerService.load(vId);
		if (bean == null) {
			out.print("<script>alert('数据不存在.');location.href='Manager?oper=list';</script>");
			return;
		}

		// 回显准备
		request.setAttribute("bean", bean);

		String toPage = "/WEB-INF/admin/Manager_update.jsp";
		request.getRequestDispatcher(toPage).forward(request, response);
	}
	
	private StringBuilder alertGo(String msg, String url) {
		StringBuilder sb = new StringBuilder();
		sb.append("<script>");
		sb.append("alert('" + msg + "');");
		sb.append("location.href='" + url + "';");
		sb.append("</script>");
		return sb;
	}
	
	private void updateDeal(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// Servlet的doXXX方法中的编码方式
		// 设置请求对象的编码方式
		request.setCharacterEncoding("utf-8");
		// 设置响应对象的编码方式
		response.setCharacterEncoding("utf-8");
		// 设置响应的内容类型为text/html
		response.setContentType("text/html;charset=utf-8");
		javax.servlet.http.HttpSession session = request.getSession();
		javax.servlet.ServletContext application = request.getServletContext();
		// 调用继承的方式来获取config对象
		javax.servlet.ServletConfig config = getServletConfig();
		// 从response对象里获取out对象————response.getWriter()之前，要先设置页面的编码
		java.io.PrintWriter out = response.getWriter();

		// -------------------------------------------------------------------

		List<Manager> managerList = managerService.list();
		request.setAttribute("managerList", managerList);

		String url = "Manager?oper=list";
		String managerId_str = SysFun.dealString(request.getParameter("id"));
		String userName = SysFun.dealString(request.getParameter("userName"));
		String userPass = SysFun.dealString(request.getParameter("userPass"));
		String userPass2 = SysFun.dealString(request.getParameter("userPass2"));

		request.setAttribute("userName", userName);
		request.setAttribute("userPass", userPass);
		request.setAttribute("userPass2", userPass2);

		if (SysFun.isNullOrEmpty(managerId_str)) {
			out.print(alertGo("管理员Id不能为空。", url));
			return;
		}
		Long managerId = Long.parseLong(managerId_str);
		String toPage = "/WEB-INF/admin/Manager_update.jsp";

		if (SysFun.isNullOrEmpty(userName)) {
			request.setAttribute("msg", "管理员名称不能为空");
			request.getRequestDispatcher(toPage).forward(request, response);
			return;
		}

		if (!userPass.equals(userPass2)) {
			request.setAttribute("msg", "两次密码不相同");
			request.getRequestDispatcher(toPage).forward(request, response);
			return;
		}

		Manager tmpBean = managerService.loadByName(userName);

		if (tmpBean != null && tmpBean.getManagerId() != managerId) {
			request.setAttribute("msg", "账号已存在");
			request.getRequestDispatcher(url).forward(request, response);
			return;
		}

		Manager bean = managerService.load(managerId);

		bean.setUserName(userName);

		if (!userPass.isEmpty()) {
			bean.setUserPass(userPass);
		}

		Long result = 0L;
		String msg = "";

		result = managerService.update(bean);

		if (result > 0) {
			out.print(alertGo("修改成功", url));
			return;
		} else {
			System.out.println(msg);
			msg = msg.replace("\'", "");
			request.setAttribute("msg", "修改失败");
			// request.getRequestDispatcher(toPage).forward(request, response);
			updateView(request, response);
			return;
		}
	}

	private void deleteDeal(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		java.io.PrintWriter out = response.getWriter();

		RspResult rspResult = new RspResult();
		rspResult.resetNo();
		Gson gson = new Gson();

		// 取得主键，再根据主键，获取记录
		String vId = request.getParameter("id");
		if (!SysFun.isNullOrEmpty(vId)) {
			Long iId = SysFun.parseLong(vId);

			Long result = managerService.delete(iId);
			if (result > 0) {
				rspResult.resetOk();
				out.print(gson.toJson(rspResult));// 不要使用println()
				return;
			}
		}
		out.println(gson.toJson(rspResult));
	}

	private void detailView(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		java.io.PrintWriter out = response.getWriter();

		// -------------------------------------------------------------------

		// 取得主键，再根据主键，获取记录
		String vId = request.getParameter("managerId");
		if (!SysFun.isNullOrEmpty(vId)) {
			Long iId = SysFun.parseLong(vId);
			Manager bean = managerService.load(iId);
			if (bean != null) {
				// 使用对象来回显页面
				request.setAttribute("bean", bean);

				// 数据存在才转发到页面
				String toPage = "/WEB-INF/admin/Manager_detail.jsp";
				request.getRequestDispatcher(toPage).forward(request, response);
				return;
			}
		}

		out.println("<script>");
		out.println("alert('数据不存在')");
		out.println("location.href='Manager?oper=list';");
		out.println("</script>");
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
