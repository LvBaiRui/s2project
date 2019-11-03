package edu.prj.ui.store;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import edu.prj.bean.Item;
import edu.prj.bean.Product;
import edu.prj.service.ItemService;
import edu.prj.service.ProductService;
import edu.prj.service.impl.ItemServiceImpl;
import edu.prj.service.impl.ProductServiceImpl;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet(urlPatterns = { "/store/Main" })
public class storeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ItemService itemService = new ItemServiceImpl();
	ProductService productService=new ProductServiceImpl();
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public storeServlet() {
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
		case "find":
			find(request, response);
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

		Long isHot=1L;
		List<Product> hotList=productService.pIsHot(isHot);
		request.setAttribute("hList", hotList);
		
		List<Product> newList=productService.pIsNew();
		request.setAttribute("nList", newList);

		
		String toPage = "/WEB-INF/store/index.jsp";
		request.getRequestDispatcher(toPage).forward(request, response);

	}

	protected void find(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		java.io.PrintWriter out = response.getWriter();
		List<Item> vDataList = null;

		vDataList = itemService.list();

		Gson gson = new Gson();
		out.print(gson.toJson(vDataList));
		System.out.println(gson.toJson(vDataList));

	}
	

}
