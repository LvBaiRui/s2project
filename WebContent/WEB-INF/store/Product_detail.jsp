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
		

<div class="container productContent" >

		<div class="span18 last" >
		
		<div style="border: 1px solid #e4e4e4;width:16600px;margin-bottom:10px;margin:0 auto;padding:10px;margin-bottom:10px;">
					<a href="Main"  style="color:blue;font-weight:bolder;letter-spacing:2px; ">返回首页&nbsp;&nbsp;&gt;</a>
			
					<a>${bean.proName}</a>
				</div>
				
				
			
			<div class="productImage">
					<a title="" style="outline-style: none; text-decoration: none;" id="zoom" href="#">
						<div class="zoomPad">
							<img style="opacity: 1;" title="" class="medium" src="${pageContext.request.contextPath }/products/1/${bean.image}">
						</div>

					</a>
				
			</div>
			<div class="name">${bean.proName}</div>
			<div class="sn">
				<div>编号：${bean.productId}</div>
			</div>
			<div class="info">
				<dl>
					<dt>商场价:</dt>
					<dd>
						<strong>￥：${bean.proPrice}元/份</strong>
							参 考 价：
							<del>￥${bean.marketPrice}元/份</del>
					</dd>
				</dl>
					<dl>
						<dt>促销:</dt>
						<dd>
								<a target="_blank" title="限时抢购 (2014-07-30 ~ 2015-01-01)">限时抢购</a>
						</dd>
					</dl>
					<dl>
						<dt>    </dt>
						<dd>
							<span>    </span>
						</dd>
					</dl>
			</div>
				<div class="action">
					<form action="Cart" method="post">
					 <input type="hidden" name="oper" value="update" />
					  <input type="hidden" name="productId" value="${bean.productId}" />
						<dl class="quantity">
							<dt>购买数量:</dt>
							<dd>
								<input id="quantity" name="count" value="1" maxlength="4" onpaste="return false;" type="text" />
								<div>
									<span id="increase" class="increase">&nbsp;</span>
									<span id="decrease" class="decrease">&nbsp;</span>
								</div>
							</dd>
							<dd>
								件
							</dd>
						</dl>
					<div class="buy">
							<input id="addCart" class="addCart" value="加入购物车" type="submit" />
				
					</div>
					</form>
				</div>
			<div id="bar" class="bar" >
				<ul>
						<li id="introductionTab">
							<a href="#introduction">商品介绍</a>
						</li>
						
				</ul>
			</div>
				
				<div id="introduction" name="introduction" class="introduction" >
					<div class="title">
						<strong>商品介绍</strong>
					</div>
					<div>
						<img src="${pageContext.request.contextPath}/products/1/${bean.image}">
					</div>
					
					<div style="background-color:#d3d3d3;width:678px;padding:10px 10px;margin:10px 0 10px 0;" >
						<strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;详细信息：&nbsp;&nbsp;&nbsp;${bean.productInfo }</strong>
					</div>
				</div>

				
		</div>
		
	</div>

		<jsp:include page="_body_end.jsp"></jsp:include>
	</div>
<div class="container footer">
	
	<jsp:include page="_ctx_footer.jsp"></jsp:include>
	
</div>
</body></html>