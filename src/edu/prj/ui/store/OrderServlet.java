package edu.prj.ui.store;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.liuvei.common.PagerItem;
import com.liuvei.common.SysFun;

import edu.prj.bean.Cart;
import edu.prj.bean.CartItem;
import edu.prj.bean.Order;
import edu.prj.bean.OrderItem;
import edu.prj.bean.Product;
import edu.prj.bean.User;
import edu.prj.service.OrderItemService;
import edu.prj.service.OrderService;
import edu.prj.service.ProductService;
import edu.prj.service.impl.OrderItemServiceImpl;
import edu.prj.service.impl.OrderServiceImpl;
import edu.prj.service.impl.ProductServiceImpl;
import edu.prj.ui.UIConst;

/**
 * Servlet implementation class OrderServlet
 */
@WebServlet("/store/Order")
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ProductService productService = new ProductServiceImpl();
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
			indexView(request, response);//
			break;
		case "list":
			listView(request, response);//
			break;
		case "insert":
			insertDeal(request, response);
			break;
		case "detail":
			detailView(request, response);
			break;
		case "update":
			updateDeal(request, response);
			break;
		case "pay":
			payView(request, response);
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

		Long isHot = 1L;
		List<Product> hotList = productService.pIsHot(isHot);
		request.setAttribute("hList", hotList);

		List<Product> newList = productService.pIsNew();
		request.setAttribute("nList", newList);

		String toPage = "/WEB-INF/store/index.jsp";
		request.getRequestDispatcher(toPage).forward(request, response);

	}

	protected void listView(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 1.从session中获取user
		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			// 未登录 提示
			request.setAttribute("login", "请先登录!");
			String toPage = "/WEB-INF/store/cart.jsp";
			request.getRequestDispatcher(toPage).forward(request, response);
			return;
		}

		List<Order> vDataList = null;
		List<Order> vDataList1 = null;
		// vDataList=questionService.list();

		// 步骤0
		Long iId = null;
		iId = user.getUserId();
		System.out.println(iId);
		// 步骤1
		PagerItem pagerItem = new PagerItem();
		pagerItem.parsePageNum(request.getParameter(pagerItem.getParamPageNum()));
		pagerItem.parsePageSize(request.getParameter(pagerItem.getParamPageSize()));

		// 步骤2
		Long rowCount = orderService.countById1(iId);
		System.out.println(rowCount);
		pagerItem.changeRowCount(rowCount);
		
		vDataList = orderService.pagerById(iId);
		vDataList1=orderService.pagerById2(iId, pagerItem.getPageNum(), pagerItem.getPageSize());
		pagerItem.changeUrl(SysFun.generalUrl(request.getRequestURI(), request.getQueryString()));

		// 步骤3：
		request.setAttribute("pagerItem", pagerItem);
		request.setAttribute("DataList", vDataList);
		request.setAttribute("DataList1", vDataList1);

		String toPage = "/WEB-INF/store/Order_list.jsp";
		request.getRequestDispatcher(toPage).forward(request, response);

	}
	
	protected void detailView(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		

		
	}
	
	protected void payView(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		java.io.PrintWriter out = response.getWriter();

		// -------------------------------------------------------------------

		//取得主键，再根据主键，获取记录
		String vId=request.getParameter("id");
		System.out.println(vId);
		
		if(!SysFun.isNullOrEmpty(vId)) {
			Long iId=SysFun.parseLong(vId);
			Order bean=orderService.load(iId);
			System.out.println(bean.getOrderPrice());
			if(bean !=null) {
				//使用对象来回显页面
				bean.setOrderStatus(1);
				
				Long result=orderService.update2(bean);
				System.out.println(result);
				if(result>0L) {
					request.setAttribute("bean", bean);
					//数据存在才转发到页面
					String toPage="/WEB-INF/store/pay.jsp";
					request.getRequestDispatcher(toPage).forward(request, response);
					return;
				}
			
			}
		}
		
		out.println("<script>");
		out.println("alert('数据不存在')");
		out.println("location.href='Order?oper=list';");
		out.println("</script>");
		
		
		String toPage = "/WEB-INF/store/pay.jsp";
		request.getRequestDispatcher(toPage).forward(request, response);


		
	}

	protected void insertDeal(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// -1.从session中获取user
		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			// 未登录 提示
			request.setAttribute("login", "请先登录!");
			String toPage = "/WEB-INF/store/cart.jsp";
			request.getRequestDispatcher(toPage).forward(request, response);
			return;
		}

		// 0.获取购物车
		Cart cart = (Cart) request.getSession().getAttribute("cart");

		Order order = new Order();

		int rand = (int) ((Math.random() * 9 + 1) * 1000);
		Long oid = Long.parseLong(rand + "");

		while (order.getOrderId() == oid) {
			rand = (int) ((Math.random() * 9 + 1) * 1000);
			oid = Long.parseLong(rand + "");
		}

		order.setOrderId(oid);

		System.out.println(order.getOrderId());

		order.setOrderPrice(cart.getTotal());
		System.out.println(order.getOrderPrice());

		order.setOrderTime(new Date());
		System.out.println(order.getOrderTime());

		order.setOrderStatus(UIConst.ORDER_WEIFUKUAN);
		System.out.println(order.getOrderStatus());

		order.setOrderAddress(null);
		order.setUser(user);
		order.setName(user.getName());
		System.out.println(order.getName());

		order.setUserId(user.getUserId());
		System.out.println(user.getUserId());

		orderService.insert(order);
		System.out.println(order.getOrderId());
		for (CartItem bean : cart.getCartItems()) {
			OrderItem oi = new OrderItem();

			oi.setCount(Long.parseLong(bean.getCount() + ""));

			oi.setTotal(bean.getSubtotal());

			oi.setProduct(bean.getProduct());
			oi.setProductId(bean.getProduct().getProductId());
			oi.setOrder(order);
			order.getItems().add(oi);
			orderItemService.insert(oi);
		}

		request.setAttribute("order", order);
		String toPage = "/WEB-INF/store/Order_detail.jsp";
		request.getRequestDispatcher(toPage).forward(request, response);

	}
	
	private Cart getCart(HttpServletRequest request) {
		// TODO Auto-generated method stub
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		if(cart == null){
			cart = new Cart();
			
			//将cart放入session中
			request.getSession().setAttribute("cart", cart);
		}
		return cart;
	}
	
	protected void updateDeal(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
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
		User user = (User) request.getSession().getAttribute("user");
		Cart cart = (Cart) request.getSession().getAttribute("cart");
				
		String orderId=request.getParameter("id");
		System.out.println(orderId);
			
		Order order=new Order();		
		Long id=Long.parseLong(orderId);
		order=orderService.load(id);
		
		for (CartItem bean : cart.getCartItems()) {
			OrderItem oi = new OrderItem();

			oi.setCount(Long.parseLong(bean.getCount() + ""));

			oi.setTotal(bean.getSubtotal());

			oi.setProduct(bean.getProduct());
			oi.setProductId(bean.getProduct().getProductId());
			oi.setOrder(order);
			order.getItems().add(oi);
			
		}
		System.out.println("金额："+order.getOrderPrice());
		request.setAttribute("order", order);
		
		
		String orderAddress=request.getParameter("orderAddress"); 
		String orderPhone=request.getParameter("orderPhone"); 
		request.setAttribute("orderAddress", orderAddress);
		request.setAttribute("orderPhone", orderPhone);
		request.setAttribute("id", orderId);

		
		String toPage = "/WEB-INF/store/Order_detail.jsp";
		if (SysFun.isNullOrEmpty(orderAddress)) {
			request.setAttribute("msg", "收货地址不能为空");
			request.getRequestDispatcher(toPage).forward(request, response);
			return;
		}
		
		if (SysFun.isNullOrEmpty(orderPhone)) {
			request.setAttribute("msg", "联系方式不能为空");
			request.getRequestDispatcher(toPage).forward(request, response);
			return;
		}
		if(orderPhone.length()!=11) {
			request.setAttribute("msg", "联系方式不正确");
			request.getRequestDispatcher(toPage).forward(request, response);
			return;
		}
		Long phone;
		try {
			phone = Long.parseLong(orderPhone);

		} catch (Exception e) {
			request.setAttribute("msg", "联系方式不正确，");
			request.getRequestDispatcher(toPage).forward(request, response);
			return;
		}
		
		Order bean=new Order();
		
		Long oid;
		try {
			oid = Long.parseLong(orderId);

		} catch (Exception e) {
			request.setAttribute("msg", "id不是数字，");
			request.getRequestDispatcher(toPage).forward(request, response);
			return;
		}
		
		bean=orderService.load(oid);
		if(bean==null) {
			request.setAttribute("msg", "不存在该订单，");
			request.getRequestDispatcher(toPage).forward(request, response);
			return;
		}
		System.out.println(bean.getOrderPrice());
		System.out.println(bean.getOrderTime());
		System.out.println(bean.getOrderStatus());
		System.out.println(user.getName());
		System.out.println(phone);
		System.out.println(orderAddress);
		System.out.println(bean.getUserId());
		System.out.println(bean.getOrderId());
		
		Order tmpBean=new Order();
		tmpBean.setOrderPrice(bean.getOrderPrice());
		tmpBean.setOrderTime(bean.getOrderTime());
		tmpBean.setOrderStatus(bean.getOrderStatus());
		tmpBean.setName(user.getName());
		tmpBean.setPhone(phone);
		tmpBean.setOrderAddress(orderAddress);
		tmpBean.setUserId(bean.getUserId());
		tmpBean.setOrderId(bean.getOrderId());
		
		Long result = 0L;
		String msg = "";
		result=orderService.update(tmpBean);
		String url = "Order?oper=list";
		if (result > 0) {
			out.print(alertGo("订单已确认", url));
			cart.clearCart();
			return;
		} else {
			System.out.println(msg);
			msg = msg.replace("\'", "");
			request.setAttribute("msg", "修改失败");
			request.getRequestDispatcher(toPage).forward(request, response);
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

}
