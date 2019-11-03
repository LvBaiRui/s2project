 <%@ page language="java" contentType="text/html; charset=UTF-8" 
    pageEncoding="UTF-8"%> 
    <script src="../static/js/jquery-3.4.1.min.js"></script>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
    <link href="../static/store/css/cart.css" rel="stylesheet" type="text/css">

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
<div style="border: 1px solid #e4e4e4;width:930px;margin-bottom:10px;margin:0 auto;padding:10px;margin-bottom:10px;margin-top:10px;">
			<a href="Main"  style="color:blue;font-weight:bolder;letter-spacing:2px; ">返回首页&nbsp;&nbsp;&gt;</a>
		</div>
		<div class="span24">
			<div class="step step1">
				<h2 style="color:purple">购物车：</h2>
			</div>
			<c:if test="${not empty login}">
				
				
					<div style="color: red; font-family: 'Times New Roman', Times, serif">
						<h1>${login}</h1>
					</div>

			</c:if>
			<c:if test="${empty login}">
				<c:if test="${empty cart || empty cart.cartItems }">
					<h3 style="color: red;">购物车~毛~都没有</h3>
					<hr/>
					<br />
				</c:if>
				<c:if test="${not empty cart.cartItems}">

				<table>
					<tbody><tr>
						<th>图片</th>
						<th>商品</th>
						<th>价格</th>
						<th>数量</th>
						<th>小计</th>
						<th>操作</th>
					</tr>
					
						<c:forEach items="${cart.cartItems}" var="bean">
							<tr>
							<input type='hidden' id="line${bean.product.productId}"  value='${bean.product.productId}' />
								<td width="60">
									<input type="hidden" name="id" value="22">
									<img src="${pageContext.request.contextPath}/products/1/${bean.product.image}">
								</td>
								<td>
									<a target="_blank">${bean.product.proName}</a>
								</td>
								<td>
									￥${bean.product.proPrice}
								</td>
								<td class="quantity" width="60">
									${bean.count}
								</td>
								<td width="140">
									<span class="subtotal">￥${bean.subtotal}</span>
								</td>
								<td>
									<a href="javascript:item_del(this,${bean.product.productId});" class="delete">删除</a>
								</td>
							</tr>
							
						</c:forEach>
				</tbody></table>
				<dl id="giftItems" class="hidden" style="display: none;">
				</dl>
				<div class="total">		
					商品金额: <strong id="effectivePrice">￥${cart.total}元</strong>
				</div>
				<div class="bottom">
					<a href="Cart?oper=clear" id="clear" class="clear">清空购物车</a>
					<a href="Order?oper=insert" id="submit" class="submit">提交订单</a>
				</div>
				
				</c:if>
			</c:if>
			
		</div>

</div>

<div class="container index">

		
		<jsp:include page="_body_end.jsp"></jsp:include>
	</div>
<div class="container footer">
	
	<jsp:include page="_ctx_footer.jsp"></jsp:include>
	
</div>

 <script>
      
      function item_del(obj,id){
    	  if(confirm("确认要删除吗？")){
    		  
    		  location.href="${pageContext.request.contextPath}/store/Cart?oper=delete&id="+id;
    		 /*  $.ajax({
     			 type : 'POST',
     			 url : 'Cart?oper=delete&id='+id,
     			success : function(data){
     					$("#line" + id).parents("tr").remove();
     					alert("已删除！");
     			},
     			error:function(data){
     				
     			},
     		  }); */
    	  }
      }
    	
    	  
 </script>
</body></html>