
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script src="../static/js/jquery-3.4.1.min.js"></script>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<link href="../static/store/css/cart.css" rel="stylesheet"
	type="text/css">

<!DOCTYPE html>    
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>购物商城</title>

<jsp:include page="_head_link.jsp"></jsp:include>
</head>
<body>
	<div class="container header">

		<jsp:include page="_ctx_header.jsp"></jsp:include>

	</div>

	<div class="container cart">

		<div class="span24">

			<div class="step step1">
				<ul>

					<li class="current"></li>
					<li><strong>我的订单</strong></li>
				</ul>
			</div>

	
			<table>
				<c:forEach var="bean" items="${DataList1}" varStatus="status" >
					
						<tbody>
							<tr>
								<th colspan="2" >订单编号:${bean.orderId }</th>
								<th colspan="1"><c:if test="${bean.orderStatus == 0 }">
										<a href="Order?oper=pay&id=${bean.orderId}">去付款</a>
									</c:if> <c:if test="${bean.orderStatus == 1 }">已付款</c:if> <c:if
										test="${bean.orderStatus == 2 }">确认收货</c:if> <c:if
										test="${bean.orderStatus == 3 }">已完成</c:if></th>
								<th colspan="2">金额:${bean.orderPrice }元</th>
							</tr>
							<tr>
								<th>图片</th>
								<th>商品</th>
								<th>价格</th>
								<th>数量</th>
								<th>小计</th>
							</tr>
							<c:forEach var="tt" items="${DataList}" varStatus="status">
													
							<c:if test="${bean.orderId==tt.orderId}">
								
							<tr >
								<td width="60" width="40%">
									<input type="hidden" name="id" value="22">
									<img src="${pageContext.request.contextPath}/products/1/${tt.image}" width="70" height="60">
								</td>
								<td width="30%">
								
									<a href="Product?oper=detail&id=${tt.productId}">${tt.proName}</a>
								</td>
								<td width="20%">
									￥${tt.proPrice}
								</td>
								<td width="10%">
									${tt.count}
								</td>
								<td width="15%">
									<span class="subtotal">￥${tt.total}</span>
								</td>
							</tr>
							
						</c:if>
						</c:forEach>
						
						
						
						
						</tbody>
				</c:forEach>

			</table>
		</div>
		<jsp:include page="__pager.jsp" flush="true" />
		
		<div style="border: 0px solid #e4e4e4;width:930px;margin-bottom:10px;margin:0 auto;padding:10px;margin-bottom:10px;margin-top:10px;">

		</div>
	</div>

	<div style="margin-top:50px;">
	
	</div>

	

	<div class="container footer">

		<jsp:include page="_ctx_footer.jsp"></jsp:include>

	</div>

</body>
</html>


