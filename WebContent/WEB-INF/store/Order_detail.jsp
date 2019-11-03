 <%@ page language="java" contentType="text/html; charset=UTF-8" 
    pageEncoding="UTF-8"%> 
    <script src="../static/js/jquery-3.4.1.min.js"></script>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
    <link href="../static/store/css/cart.css" rel="stylesheet" type="text/css">
    <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
					
					<li  class="current"></li>
					<li  >订单详情</li>
				</ul>
			</div>
	
		
				<table>
					<tbody>
					<tr>
						<th colspan="2">订单编号：${order.orderId}</th>
						<th colspan="1">订单状态：
							<c:if test="${order.orderStatus == 0 }">未付款</c:if>
							<c:if test="${order.orderStatus == 1 }">已付款</c:if>
							<c:if test="${order.orderStatus == 2 }">确认收货</c:if>
							<c:if test="${order.orderStatus == 3 }">已完成</c:if>
							
							</th>
						<th colspan="2">时间:<fmt:formatDate value="${order.orderTime }" pattern="yyyy-MM-dd HH:mm:ss"/></th>
					</tr>
					
					<tr>
						<th>图片</th>
						<th>商品</th>
						<th>价格</th>
						<th>数量</th>
						<th>小计</th>
					</tr>
						<c:forEach items="${order.items}" var="tmpBean">
						<tr>
							<td width="60">
								<input type="hidden" name="id" value="22"/>
								<img src="${pageContext.request.contextPath}/products/1/${tmpBean.product.image}"/>
							</td>
							<td>
								<a target="_blank"><s:property value="product.pname"/>${tmpBean.product.proName}</a>
							</td>
							<td>
								￥${tmpBean.product.proPrice}
							</td>
							<td class="quantity" width="60">
								${tmpBean.count}
							</td>
							<td width="140">
								<span class="subtotal">￥${tmpBean.total}</span>
							</td>
						</tr>
						
						</c:forEach>
					
				</tbody>
			</table>
				<dl id="giftItems" class="hidden" style="display: none;">
				</dl>
				<div class="total">
					<em id="promotion"></em>
					商品金额: <strong id="effectivePrice">￥${order.orderPrice}元</strong>
				</div>
			<form id="orderForm" action="Order" method="post">
				<input type="hidden" name="oper" value="update"/>
				<input type="hidden" id="id" name="id" value="${order.orderId}">
				
				<div class="span24">
					<p>
					
							收货地址：<input type="text" value="" style="width:350px" placeholder="请输入收货地址" id="orderAddress" name="orderAddress" value="${orderAddress}"/>
								<br />
						
							联系方式：<input type="text"value="" style="width:150px" placeholder="请输入电话号码" id="orderPhone" name="orderPhone" value="${orderPhone}"/>
							<br/>
							<div style="color:red;">
								${msg}
							</div>
							
						</p>
						<hr />
						<p>
							<h3 style="color:purple">请选择支付方式：</h3>
							
							<br/>
							<div style="margin-left:30px;">
								<div>
							<input type="radio" name="pd_FrpId" value="ABC-NET-B2C"/>支付宝
							<img src="../static/store/bank_img/zfb.png" align="middle"/>
							</div>
							
							<br/>
							<div>
							<input type="radio" name="pd_FrpId" value="ABC-NET-B2C"/>微信&nbsp;&nbsp;&nbsp;
							<img src="../static/store/bank_img/wx.png" align="middle"/>
							</div>
							
							<br/>
							<div>
							<input type="radio" name="pd_FrpId" value="ABC-NET-B2C"/>银行卡
							<img src="../static/store/bank_img/abc.bmp" align="middle"/>
							</div>
							
							</div>
							
							
						</p>
						<hr />
						<p style="text-align:right">
							<a href="javascript:document.getElementById('orderForm').submit();">
								<img src="../static/store/images/finalbutton.gif" width="204" height="51" border="0" />
							</a>
						</p>
				</div>
			</form>
		</div>
		
	</div>
<div class="container footer">
	
	<jsp:include page="_ctx_footer.jsp"></jsp:include>
	
</div>



</body></html>