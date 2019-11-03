package edu.prj.ui.admin;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.google.gson.Gson;
import com.liuvei.common.PagerItem;
import com.liuvei.common.RspResult;
import com.liuvei.common.SysFun;

import edu.prj.bean.Item;
import edu.prj.bean.Product;
import edu.prj.service.ItemService;
import edu.prj.service.ProductService;
import edu.prj.service.impl.ItemServiceImpl;
import edu.prj.service.impl.ProductServiceImpl;

/**
 * Servlet implementation class ProductServlet
 */
@WebServlet("/admin/Product")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ProductService productService = new ProductServiceImpl();
	ItemService itemService = new ItemServiceImpl();
	private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProductServlet() {
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
		// 从request里获取session对象和application对象
		javax.servlet.http.HttpSession session = request.getSession();
		javax.servlet.ServletContext application = request.getServletContext();
		// 调用继承的方式来获取config对象
		javax.servlet.ServletConfig config = getServletConfig();
		// 从response对象里获取out对象————response.getWriter()之前，要先设置页面的编码
		java.io.PrintWriter out = response.getWriter();

//		// 创建工厂
//		DiskFileItemFactory dfif = new DiskFileItemFactory();
//
//		// 使用工厂创建解析器对象
//		ServletFileUpload fileUpload = new ServletFileUpload(dfif);

//		try {
//			// 使用解析器对象解析request，得到FileItem列表
//			List<FileItem> list = fileUpload.parseRequest(request);
//
//			// 遍历所有表单项
//			for (FileItem fileItem : list) {
//				// 如果当前表单项为普通表单项
//				if (fileItem.isFormField()) {
//					// 获取当前表单项的字段名称
//					String fieldName = fileItem.getFieldName();
//
//					String value = fileItem.getString("utf-8");
//					System.out.println(value);
//				} else {
//					// 如果当前表单项不是普通表单项，说明就是文件字段
//					String name = fileItem.getName();// 获取上传文件的名称
//					// 如果上传的文件名称为空，即没有指定上传文件
//					if (name == null || name.isEmpty()) {
//						continue;
//					}
//					// 获取真实路径，对应${项目目录}/products/1，当然，这个目录必须存在，不存在，则马上创建
//					String savepath = this.getServletContext().getRealPath("/products/1");
//
//					java.io.File dir = new java.io.File(savepath);
//
//					if (!dir.exists()) {
//						dir.mkdirs();// 如果上传的目录不存在，则马上创建
//					}
//
//					// 生成uuid
//					String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
//					String newname = uuid + "_" + name;// 新的文件名称为uuid+下划线+原始名称
//
//					// 通过1目录和文件名称来创建File对象
//					java.io.File file = new java.io.File(savepath, newname);
//
//					// 把上传文件保存到指定位置
//					fileItem.write(file);
//					// 打印上传文件的物理根目录
//					response.getWriter().print("上传物理根目录：" + dir.getAbsolutePath() + "<br/>");
//					// 打印上传文件的名称
//					response.getWriter().print("上传文件的名称：" + name + "<br/>");
//					// 打印上传文件的大小
//					response.getWriter().print("上传文件的大小：" + fileItem.getSize() + "<br/>");
//					// 打印上传文件的类型
//					response.getWriter().print("上传文件的类型：" + fileItem.getContentType() + "<br/>");
//				}
//			}
//		} catch (Exception e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}

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
			try {
				insertDeal(request, response);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // 模板
			break;
		case "update":
			updateView(request, response);// 模板
			break;
		case "updatedeal":
			try {
				updateDeal(request, response);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // 模板
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
		List<Product> vDataList = null;
		// vDataList=questionService.list();

		// 步骤1
		PagerItem pagerItem = new PagerItem();
		pagerItem.parsePageNum(request.getParameter(pagerItem.getParamPageNum()));
		pagerItem.parsePageSize(request.getParameter(pagerItem.getParamPageSize()));

		// 步骤2
		Long rowCount = productService.count();
		pagerItem.changeRowCount(rowCount);
		vDataList = productService.pager(pagerItem.getPageNum(), pagerItem.getPageSize());
		pagerItem.changeUrl(SysFun.generalUrl(request.getRequestURI(), request.getQueryString()));

		// 步骤3：
		for(Product item :vDataList) {
		System.out.println("11111111111"+item.getImage());
		}
		request.setAttribute("pagerItem", pagerItem);
		request.setAttribute("DataList", vDataList);

		String toPage = "/WEB-INF/admin/Product_list.jsp";
		request.getRequestDispatcher(toPage).forward(request, response);

	}

	protected void listDeal(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<Product> vDataList = null;
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
			Long rowCount = productService.count();
			pagerItem.changeRowCount(rowCount);
			vDataList = productService.pager(pagerItem.getPageNum(), pagerItem.getPageSize());
			pagerItem.changeUrl(SysFun.generalUrl(request.getRequestURI(), request.getQueryString()));
		} else {
			Long rowCount = productService.countByName(name);
			pagerItem.changeRowCount(rowCount);
			vDataList = productService.pagerByName(name, pagerItem.getPageNum(), pagerItem.getPageSize());
			pagerItem.changeUrl(SysFun.generalUrl(request.getRequestURI(), request.getQueryString()));
		}

		// 步骤3：
		request.setAttribute("pagerItem", pagerItem);
		request.setAttribute("DataList", vDataList);

		String toPage = "/WEB-INF/admin/Product_list.jsp";
		request.getRequestDispatcher(toPage).forward(request, response);

	}

	protected void insertView(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<Item> vDataList = null;

		PagerItem pagerItem = new PagerItem();
		pagerItem.parsePageNum(request.getParameter(pagerItem.getParamPageNum()));
		pagerItem.parsePageSize(request.getParameter(pagerItem.getParamPageSize()));

		// 步骤2
		Long rowCount = itemService.count();
		pagerItem.changeRowCount(rowCount);
		vDataList = itemService.pager(pagerItem.getPageNum(), pagerItem.getPageSize());
		pagerItem.changeUrl(SysFun.generalUrl(request.getRequestURI(), request.getQueryString()));

		// 步骤3：
		request.setAttribute("pagerItem", pagerItem);
		request.setAttribute("DataList", vDataList);

		String toPage = "/WEB-INF/admin/Product_insert.jsp";
		request.getRequestDispatcher(toPage).forward(request, response);

	}

	protected void insertDeal(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ParseException {

		// Servlet的doXXX方法中的编码方式
		// 设置请求对象的编码方式
		request.setCharacterEncoding("utf-8");
		// 设置响应对象的编码方式
		response.setCharacterEncoding("utf-8");
		// 设置响应的内容类型为tvext/html
		response.setContentType("text/html;charset=utf-8");
		javax.servlet.http.HttpSession session = request.getSession();
		javax.servlet.ServletContext application = request.getServletContext();
		// 调用继承的方式来获取config对象
		javax.servlet.ServletConfig config = getServletConfig();
		// 从response对象里获取out对象————response.getWriter()之前，要先设置页面的编码
		java.io.PrintWriter out = response.getWriter();

		// -------------------------------------------------------------------

		// 取用户列表，并放到作用域
		List<Product> productList = productService.list();
		request.setAttribute("productList", productList);

		List<Item> vDataList = null;

		PagerItem pagerItem = new PagerItem();
		pagerItem.parsePageNum(request.getParameter(pagerItem.getParamPageNum()));
		pagerItem.parsePageSize(request.getParameter(pagerItem.getParamPageSize()));

		// 步骤2
		Long rowCount = itemService.count();
		pagerItem.changeRowCount(rowCount);
		vDataList = itemService.pager(pagerItem.getPageNum(), pagerItem.getPageSize());
		pagerItem.changeUrl(SysFun.generalUrl(request.getRequestURI(), request.getQueryString()));

		// 步骤3：
		request.setAttribute("pagerItem", pagerItem);
		request.setAttribute("DataList", vDataList);

		String toPage = "/WEB-INF/admin/Product_insert.jsp";

		String proName = SysFun.dealString(request.getParameter("proName"));
		String proPrice = SysFun.dealString(request.getParameter("proPrice"));
		String marketPrice = SysFun.dealString(request.getParameter("marketPrice"));
		String productInfo = SysFun.dealString(request.getParameter("productInfo"));
		String image = SysFun.dealString(request.getParameter("image"));
		String isHot = SysFun.dealString(request.getParameter("isHot"));
//		String dateTime = SysFun.dealString(request.getParameter("dateTime"));
		String itemId = SysFun.dealString(request.getParameter("itemId"));

		request.setAttribute("proName", proName);
		request.setAttribute("proPrice", proPrice);
		request.setAttribute("marketPrice", marketPrice);
		request.setAttribute("productInfo", productInfo);
		request.setAttribute("image", image);
		request.setAttribute("isHot", isHot);
//		request.setAttribute("dateTime", dateTime);
		request.setAttribute("itemId", itemId);
		if (SysFun.isNullOrEmpty(proName)) {
			request.setAttribute("msg", "商品名不能为空");
			request.getRequestDispatcher(toPage).forward(request, response);
			return;
		}
		if (SysFun.isNullOrEmpty(proPrice)) {
			request.setAttribute("msg", "商品价格不能为空");
			request.getRequestDispatcher(toPage).forward(request, response);
			return;
		}
		Product tmpBean = productService.loadByName(proName);
		if (tmpBean != null) {
			request.setAttribute("msg", "该商品已存在");
			request.getRequestDispatcher(toPage).forward(request, response);
			return;
		}
		if (SysFun.isNullOrEmpty(marketPrice)) {
			request.setAttribute("msg", "市场价格不能为空");
			request.getRequestDispatcher(toPage).forward(request, response);
			return;
		}

		if (SysFun.isNullOrEmpty(productInfo)) {
			request.setAttribute("msg", "请填写商品描述");
			request.getRequestDispatcher(toPage).forward(request, response);
			return;
		}
		if (SysFun.isNullOrEmpty(image)) {
			request.setAttribute("msg", "请上传商品图片");
			request.getRequestDispatcher(toPage).forward(request, response);
			return;
		}
		if (SysFun.isNullOrEmpty(isHot)) {
			request.setAttribute("msg", "请选择是否热销");
			request.getRequestDispatcher(toPage).forward(request, response);
			return;
		}

		if (SysFun.isNullOrEmpty(itemId)) {
			request.setAttribute("msg", "请选择商品类别");
			request.getRequestDispatcher(toPage).forward(request, response);
			return;
		}

		Product bean = new Product();
		bean.setProName(proName);
		bean.setProPrice(Double.parseDouble(proPrice));
		bean.setMarketPrice(Double.parseDouble(marketPrice));
		bean.setProductInfo(productInfo);
		bean.setImage(image);
		bean.setIsHot(Long.parseLong(isHot));

		// 获取当前日期
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		bean.setDateTime(simpleDateFormat.parse(dateFormat.format(date)));
		bean.setItemId(Long.parseLong(itemId));

		Long result = 0L;
		String msg = "";
		result = productService.insert(bean);

		if (result > 0) {
			out.println("<script>alert('添加成功.');location.href='Product?oper=list';</script>");
			return;
		} else {
			System.out.println(msg);
			msg = msg.replace("\'", "");
			request.setAttribute("msg", "添加失败！" + msg);
			request.getRequestDispatcher(toPage).forward(request, response);
		}

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

		List<Product> productList = productService.list();
		request.setAttribute("productList", productList);

		List<Item> vDataList = null;

		PagerItem pagerItem = new PagerItem();
		pagerItem.parsePageNum(request.getParameter(pagerItem.getParamPageNum()));
		pagerItem.parsePageSize(request.getParameter(pagerItem.getParamPageSize()));

		// 步骤2
		Long rowCount = itemService.count();
		pagerItem.changeRowCount(rowCount);
		vDataList = itemService.pager(pagerItem.getPageNum(), pagerItem.getPageSize());
		pagerItem.changeUrl(SysFun.generalUrl(request.getRequestURI(), request.getQueryString()));

		// 步骤3：
		request.setAttribute("pagerItem", pagerItem);
		request.setAttribute("DataList", vDataList);

		String id = request.getParameter("id");

		// 为空性判断
		if (id == null || id.isEmpty()) {
			out.print("<script>alert('id不能为空.');location.href='Product?oper=list';</script>");
			return;
		}

		// 主键值的合法性
		Long vId = 0L;
		try {
			vId = Long.parseLong(id);
		} catch (Exception e) {
			out.print("<script>alert('id不是数字.');location.href='Product?oper=list';</script>");
			return;
		}

		// 从数据库获取数据
		Product bean = null;
		bean = productService.load(vId);
		if (bean == null) {
			out.print("<script>alert('数据不存在.');location.href='Product?oper=list';</script>");
			return;
		}

		// 回显准备
		request.setAttribute("bean", bean);

		String toPage = "/WEB-INF/admin/Product_update.jsp";
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
			throws ServletException, IOException, ParseException {

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

		List<Product> productList = productService.list();
		request.setAttribute("productList", productList);

		List<Item> vDataList = null;

		PagerItem pagerItem = new PagerItem();
		pagerItem.parsePageNum(request.getParameter(pagerItem.getParamPageNum()));
		pagerItem.parsePageSize(request.getParameter(pagerItem.getParamPageSize()));

		// 步骤2
		Long rowCount = itemService.count();
		pagerItem.changeRowCount(rowCount);
		vDataList = itemService.pager(pagerItem.getPageNum(), pagerItem.getPageSize());
		pagerItem.changeUrl(SysFun.generalUrl(request.getRequestURI(), request.getQueryString()));

		// 步骤3：
		request.setAttribute("pagerItem", pagerItem);
		request.setAttribute("DataList", vDataList);

		String url = "Product?oper=list";
		String productId_str = SysFun.dealString(request.getParameter("id"));
		String proName = SysFun.dealString(request.getParameter("proName"));
		String proPrice = SysFun.dealString(request.getParameter("proPrice"));
		String marketPrice = SysFun.dealString(request.getParameter("marketPrice"));
		String productInfo = SysFun.dealString(request.getParameter("productInfo"));
		String image = SysFun.dealString(request.getParameter("image"));
		String isHot = SysFun.dealString(request.getParameter("isHot"));
//		String dateTime = SysFun.dealString(request.getParameter("dateTime"));
		String itemId = SysFun.dealString(request.getParameter("itemId"));

		Long vId = 0L;
		try {
			vId = Long.parseLong(productId_str);
		} catch (Exception e) {
			System.out.println(vId);
			out.print("<script>alert('id不是数字.');location.href='Product?oper=list';</script>");
			return;
		}

		request.setAttribute("id", vId);
		request.setAttribute("proName", proName);
		request.setAttribute("proPrice", proPrice);
		request.setAttribute("marketPrice", marketPrice);
		request.setAttribute("productInfo", productInfo);
		request.setAttribute("image", image);
		request.setAttribute("isHot", isHot);
//		request.setAttribute("dateTime", dateTime);
		request.setAttribute("itemId", itemId);

		if (SysFun.isNullOrEmpty(productId_str)) {
			out.print(alertGo("id不能为空。", url));
			return;
		}
		Long productId = Long.parseLong(productId_str);
		String toPage = "/WEB-INF/admin/Product_update.jsp";

		if (SysFun.isNullOrEmpty(proName)) {
			request.setAttribute("msg", "商品名称不能为空");
			request.getRequestDispatcher(toPage).forward(request, response);
			return;
		}

		Product tmpBean = productService.loadByName(proName);

		if (tmpBean != null && tmpBean.getProductId() != productId) {
			request.setAttribute("msg", "商品已存在");
			request.getRequestDispatcher(url).forward(request, response);
			return;
		}

		if (SysFun.isNullOrEmpty(proPrice)) {
			request.setAttribute("msg", "商品价格不能为空");
			request.getRequestDispatcher(toPage).forward(request, response);
			return;
		}

		if (SysFun.isNullOrEmpty(marketPrice)) {
			request.setAttribute("msg", "市场价格不能为空");
			request.getRequestDispatcher(toPage).forward(request, response);
			return;
		}
		if (SysFun.isNullOrEmpty(productInfo)) {
			request.setAttribute("msg", "商品描述不能为空");
			request.getRequestDispatcher(toPage).forward(request, response);
			return;
		}
		if (SysFun.isNullOrEmpty(image)) {
			request.setAttribute("msg", "商品图片不能为空");
			request.getRequestDispatcher(toPage).forward(request, response);
			return;
		}
		if (SysFun.isNullOrEmpty(isHot)) {
			request.setAttribute("msg", "请选择是否热销");
			request.getRequestDispatcher(toPage).forward(request, response);
			return;
		}
		if (SysFun.isNullOrEmpty(itemId)) {
			request.setAttribute("msg", "请选择商品类别");
			request.getRequestDispatcher(toPage).forward(request, response);
			return;
		}

		Product bean = productService.load(productId);

		bean.setProName(proName);
		bean.setProPrice(Double.parseDouble(proPrice));
		bean.setMarketPrice(Double.parseDouble(marketPrice));
		bean.setProductInfo(productInfo);
		bean.setImage(image);
		bean.setIsHot(Long.parseLong(isHot));
		// 获取当前日期
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		bean.setDateTime(simpleDateFormat.parse(dateFormat.format(date)));
		bean.setItemId(Long.parseLong(itemId));

		Long result = 0L;
		String msg = "";

		result = productService.update(bean);

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

			Long result = productService.delete(iId);
			if (result > 0) {
				rspResult.resetOk();
				out.print(gson.toJson(rspResult));// 不要使用println()
				return;
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
			Product bean = productService.load(iId);
			if (bean != null) {
				// 使用对象来回显页面

				Long itId = bean.getItemId();
				Item tmpBean = itemService.load(itId);

				String iName = tmpBean.getItemName();
				request.setAttribute("iName", iName);

				request.setAttribute("bean", bean);

				// 数据存在才转发到页面
				String toPage = "/WEB-INF/admin/Product_detail.jsp";
				request.getRequestDispatcher(toPage).forward(request, response);
				return;
			}
		}

		out.println("<script>");
		out.println("alert('数据不存在')");
		out.println("location.href='Product?oper=list';");
		out.println("</script>");
	}

}
