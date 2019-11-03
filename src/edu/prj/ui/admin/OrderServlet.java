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

import edu.prj.bean.Order;
import edu.prj.bean.OrderItem;
import edu.prj.service.OrderItemService;
import edu.prj.service.OrderService;
import edu.prj.service.impl.OrderItemServiceImpl;
import edu.prj.service.impl.OrderServiceImpl;

/**
 * Servlet implementation class OrderServlet
 */
@WebServlet(urlPatterns = { "/admin/Order" })
public class OrderServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	OrderService orderService = new OrderServiceImpl();
	OrderItemService orderItemService = new OrderItemServiceImpl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OrderServlet() {
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
		case "deletedeal":
			deleteDeal(request, response);// 模板
			break;

		case "detail":
			detailView(request, response);// 模板
			break;
		case "update":
			updateView(request, response);// 模板
			break;
		case "updatedeal":
			updateDeal(request, response);// 模板
			break;

		default:
			listView(request, response);// 默认
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

	protected void listView(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Order> vDataList = null;
		// vDataList=questionService.list();

		// 步骤1
		PagerItem pagerItem = new PagerItem();
		pagerItem.parsePageNum(request.getParameter(pagerItem.getParamPageNum()));
		pagerItem.parsePageSize(request.getParameter(pagerItem.getParamPageSize()));

		// 步骤2
		Long rowCount = orderService.count();
		pagerItem.changeRowCount(rowCount);
		vDataList = orderService.pager(pagerItem.getPageNum(), pagerItem.getPageSize());
		pagerItem.changeUrl(SysFun.generalUrl(request.getRequestURI(), request.getQueryString()));

		// 步骤3：
		request.setAttribute("pagerItem", pagerItem);
		request.setAttribute("DataList", vDataList);

		String toPage = "/WEB-INF/admin/Order_list.jsp";
		request.getRequestDispatcher(toPage).forward(request, response);

	}

	protected void listDeal(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<Order> vDataList = null;
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
			Long rowCount = orderService.count();
			pagerItem.changeRowCount(rowCount);
			vDataList = orderService.pager(pagerItem.getPageNum(), pagerItem.getPageSize());
			pagerItem.changeUrl(SysFun.generalUrl(request.getRequestURI(), request.getQueryString()));
		} else {
			Long rowCount = orderService.countByName(name);
			pagerItem.changeRowCount(rowCount);
			vDataList = orderService.pagerByName(name, pagerItem.getPageNum(), pagerItem.getPageSize());
			pagerItem.changeUrl(SysFun.generalUrl(request.getRequestURI(), request.getQueryString()));
		}

		// 步骤3：
		request.setAttribute("pagerItem", pagerItem);
		request.setAttribute("DataList", vDataList);

		String toPage = "/WEB-INF/admin/Order_list.jsp";
		request.getRequestDispatcher(toPage).forward(request, response);

	}

	protected void updateView(HttpServletRequest request, HttpServletResponse response)
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

		List<Order> orderList = orderService.list();
		request.setAttribute("orderList", orderList);

		String id = request.getParameter("id");

		// 为空性判断
		if (id == null || id.isEmpty()) {
			out.print("<script>alert('id不能为空.');location.href='Order?oper=list';</script>");
			return;
		}

		// 主键值的合法性
		Long vId = 0L;
		try {
			vId = Long.parseLong(id);
		} catch (Exception e) {
			out.print("<script>alert('id不是数字.');location.href='Order?oper=list';</script>");
			return;
		}

		// 从数据库获取数据
		Order bean = null;
		bean = orderService.load(vId);
		if (bean == null) {
			out.print("<script>alert('数据不存在.');location.href='Order?oper=list';</script>");
			return;
		}

		// 回显准备
		request.setAttribute("bean", bean);

		String toPage = "/WEB-INF/admin/Order_update.jsp";
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

	protected void updateDeal(HttpServletRequest request, HttpServletResponse response)
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

		List<Order> orderList = orderService.list();
		request.setAttribute("orderList", orderList);

		String url = "Order?oper=list";

		String id_str = SysFun.dealString(request.getParameter("id"));
		String name = SysFun.dealString(request.getParameter("name"));
		String phone = SysFun.dealString(request.getParameter("phone"));
		String address = SysFun.dealString(request.getParameter("address"));
		request.setAttribute("name", name);
		request.setAttribute("phone", phone);
		request.setAttribute("address", address);

		if (SysFun.isNullOrEmpty(id_str)) {
			out.print(alertGo("id不能为空。", url));
			return;
		}
		Long id = Long.parseLong(id_str);
		String toPage = "/WEB-INF/admin/Order_update.jsp";

		if (SysFun.isNullOrEmpty(phone)) {
			request.setAttribute("msg", "收货人姓名不能为空");
			request.getRequestDispatcher(toPage).forward(request, response);
			return;
		}
		if (SysFun.isNullOrEmpty(name)) {
			request.setAttribute("msg", "收货人地址不能为空");
			request.getRequestDispatcher(toPage).forward(request, response);
			return;
		}

		if (SysFun.isNullOrEmpty(name)) {
			request.setAttribute("msg", "收货人联系电话不能为空");
			request.getRequestDispatcher(toPage).forward(request, response);
			return;
		}

		if (phone.length() != 11) {
			request.setAttribute("msg", "请输入正确的联系电话");
			request.getRequestDispatcher(toPage).forward(request, response);
			return;
		}

		Long oid;
		try {
			oid = Long.parseLong(phone);

		} catch (Exception e) {
			request.setAttribute("msg", "请输入正确的联系电话");
			request.getRequestDispatcher(toPage).forward(request, response);
			return;
		}

		Order bean = orderService.load(id);

		bean.setName(name);
		bean.setOrderAddress(address);
		bean.setPhone(oid);

		Long result = 0L;
		String msg = "";

		result = orderService.update(bean);

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

	protected void deleteDeal(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		java.io.PrintWriter out = response.getWriter();

		RspResult rspResult = new RspResult();
		rspResult.resetNo();
		Gson gson = new Gson();

		// 取得主键，再根据主键，获取记录
		String vId = request.getParameter("id");
		if (!SysFun.isNullOrEmpty(vId)) {
			Long iId = SysFun.parseLong(vId);

			OrderItem tmpBean = orderItemService.loadById(iId);
			if (tmpBean != null) {
				Long result2 = orderItemService.deleteByOId(iId);
				if (result2 > 0) {
					Long result = orderService.delete(iId);
					if (result > 0) {
						rspResult.resetOk();
						out.print(gson.toJson(rspResult));// 不要使用println()
						return;
					}
				}
			}

		}
		out.println(gson.toJson(rspResult));

	}

	protected void detailView(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		java.io.PrintWriter out = response.getWriter();

		// -------------------------------------------------------------------

		// 取得主键，再根据主键，获取记录
		String vId = request.getParameter("id");
		if (!SysFun.isNullOrEmpty(vId)) {
			Long iId = SysFun.parseLong(vId);
			Order bean = orderService.load(iId);
			if (bean != null) {
				// 使用对象来回显页面
				request.setAttribute("bean", bean);

				// 数据存在才转发到页面
				String toPage = "/WEB-INF/admin/Order_detail.jsp";
				request.getRequestDispatcher(toPage).forward(request, response);
				return;
			}
		}

		out.println("<script>");
		out.println("alert('数据不存在')");
		out.println("location.href='Order?oper=list';");
		out.println("</script>");
	}

}
