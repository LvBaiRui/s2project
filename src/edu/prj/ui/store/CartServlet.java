package edu.prj.ui.store;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.prj.bean.Cart;
import edu.prj.bean.CartItem;
import edu.prj.bean.Product;
import edu.prj.bean.User;
import edu.prj.service.ProductService;
import edu.prj.service.impl.ProductServiceImpl;

/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/store/Cart")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       ProductService productService=new ProductServiceImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		case "list":
			listView(request, response);// 
			break;
		case "update":
			updateDeal(request, response);
			break;
		case "delete":
			deleteDeal(request, response);
			break;
		case "clear":
			clearDeal(request, response);
			break;
		default:
			listView(request, response);// 默认
			break;
		}
		
		
	}
	
	protected void listView(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		User user=(User) request.getSession().getAttribute("user");
		if(user == null){
			//未登录 提示
			request.setAttribute("login", "未登录，请先登录!");
			String toPage="/WEB-INF/store/cart.jsp";
			request.getRequestDispatcher(toPage).forward(request, response);
			return;
		}
		
		String toPage = "/WEB-INF/store/cart.jsp";
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	protected void updateDeal(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			User user=(User) request.getSession().getAttribute("user");
			if(user == null){
				//未登录 提示
				request.setAttribute("login", "请先登录!");
				String toPage="/WEB-INF/store/cart.jsp";
				request.getRequestDispatcher(toPage).forward(request, response);
				return;
			}
			
			
		String pid = request.getParameter("productId");
		int count = Integer.parseInt(request.getParameter("count"));
		
		//2.封装cartitem
		//调用service获取product
		Long id=Long.parseLong(pid);
		Product bean=productService.load(id);
		
		//创建cartitem
		CartItem cartItem = new CartItem(bean, count);
		
		//3.将cartitem加入购物车
		//获取购物车
		Cart cart=getCart(request);
		
		cart.add2cart(cartItem);
		
		
		String toPage="/WEB-INF/store/cart.jsp";
		request.getRequestDispatcher(toPage).forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "加入购物车失败");
		
		}
		}
	
	protected void deleteDeal(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pid = request.getParameter("id");
		System.out.println(pid);
		//2.获取购物车 执行移除
		getCart(request).removeFromCart(pid);
		
		String toPage="/WEB-INF/store/cart.jsp";
		request.getRequestDispatcher(toPage).forward(request, response);
		

		
	}
	

	protected void clearDeal(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getCart(request).clearCart();
		
		String toPage="/WEB-INF/store/cart.jsp";
		request.getRequestDispatcher(toPage).forward(request, response);
		
	}
	
	
}
