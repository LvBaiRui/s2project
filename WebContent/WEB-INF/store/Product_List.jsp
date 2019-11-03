 <%@ page language="java" contentType="text/html; charset=UTF-8" 
    pageEncoding="UTF-8"%> 
    <script src="../static/js/jquery-3.4.1.min.js"></script>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
    <link href="../static/store/css/product.css" rel="stylesheet" type="text/css">
    
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>购物商城</title>

<jsp:include page="_head_link.jsp"></jsp:include>


</head>
<body>

<div class="container header" >
	
	<jsp:include page="_ctx_header.jsp"></jsp:include>
	


</div>	

<div class="container index">
		
<div class="container productList">


		
		<div style="border: 1px solid #e4e4e4;width:930px;margin-bottom:10px;margin:0 auto;padding:10px;margin-bottom:10px;margin-top:10px;">
			<a href="Main" style="color:blue;font-weight:bolder;letter-spacing:2px; ">返回首页&nbsp;&nbsp;&gt;</a>
		</div>
				<div id="result" class="result table clearfix" id="dataList">
		<div style="border: 1px solid #e4e4e4;margin-bottom:10px;margin:0 auto;padding:10px;margin-bottom:10px;" >
				<ul>
				<c:forEach var="bean" items="${DataList}" varStatus="status">
		
				<li>
					<a href="Product?oper=detail&id=${bean.productId}">
						<img src="${pageContext.request.contextPath }/products/1/${bean.image}" width="170" height="170"  style="display: inline-block;"/>
						   
						<span style='color:green'>
						${fn:substring(bean.proName,0,10) }...
						</span>
						 
						<span class="price">
							商城价： ￥${bean.proPrice}/份
						</span>
						 
					</a>
					
					</li>
					</c:forEach>

			</ul>
		</div>
		</div>
	
	
		<jsp:include page="__pager.jsp" flush="true"/>
		
	</div>
	
	<div style="border: 0px solid #e4e4e4;width:930px;margin-bottom:10px;margin:0 auto;padding:10px;margin-bottom:10px;margin-top:10px;">

		</div>
		<jsp:include page="_body_end.jsp"></jsp:include>
	</div>
<div class="container footer">
	
	<jsp:include page="_ctx_footer.jsp"></jsp:include>

</div>
</body>
</html>