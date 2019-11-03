package edu.prj.ui.store;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.liuvei.common.PagerItem;
import com.liuvei.common.SysFun;

import edu.prj.bean.Product;
import edu.prj.service.ProductService;
import edu.prj.service.impl.ProductServiceImpl;

/**
 * Servlet implementation class ProductServlet
 */
@WebServlet("/store/Product")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    ProductService productService=new ProductServiceImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductServlet() {
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
		case "indexview":
			indexView(request, response);// 登录页面
			break;
		case "detail":
			detailView(request, response);// 登录页面
			break;
		case "pager":
			pagerListView(request, response);// 登录页面
			break;

		default:
			indexView(request, response);// 默认
			break;
		}
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	
	protected void indexView(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String toPage = "/WEB-INF/store/index.jsp";
		request.getRequestDispatcher(toPage).forward(request, response);

	}
	
	
	protected void detailView(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		java.io.PrintWriter out = response.getWriter();

		// -------------------------------------------------------------------

		//取得主键，再根据主键，获取记录
		String vId=request.getParameter("id");
		if(!SysFun.isNullOrEmpty(vId)) {
			Long iId=SysFun.parseLong(vId);
			Product bean=productService.load(iId);
			if(bean !=null) {
				//使用对象来回显页面
				request.setAttribute("bean", bean);
				
				//数据存在才转发到页面
				String toPage="/WEB-INF/store/Product_detail.jsp";
				request.getRequestDispatcher(toPage).forward(request, response);
				return;
			}
		}
		
		out.println("<script>");
		out.println("alert('数据不存在')");
		out.println("location.href='Main?';");
		out.println("</script>");
	}
	protected void pagerListView(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		List<Product> vDataList = null;
		// vDataList=questionService.list();
		
		//步骤0
		String vId=request.getParameter("id");
		Long iId=null;
		if(!SysFun.isNullOrEmpty(vId)) {
			 iId=SysFun.parseLong(vId);
			
		}
		// 步骤1
		PagerItem pagerItem = new PagerItem();
		pagerItem.parsePageNum(request.getParameter(pagerItem.getParamPageNum()));
		pagerItem.parsePageSize(request.getParameter(pagerItem.getParamPageSize()));

		// 步骤2
			Long rowCount = productService.countById(iId);
			pagerItem.changeRowCount(rowCount);
			vDataList = productService.pagerByNum(iId,pagerItem.getPageNum(), pagerItem.getPageSize());
			pagerItem.changeUrl(SysFun.generalUrl(request.getRequestURI(), request.getQueryString()));

		// 步骤3：
		request.setAttribute("pagerItem", pagerItem);
		request.setAttribute("DataList", vDataList);

		String toPage = "/WEB-INF/store/Product_List.jsp";
		request.getRequestDispatcher(toPage).forward(request, response);

	}


}
