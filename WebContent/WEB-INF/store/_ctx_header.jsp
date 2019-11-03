<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="st" uri="http://java.sun.com/jsp/jstl/stt" %> 
    <script src="../static/js/jquery-3.4.1.min.js"></script>
   <div class="span5">
		<div class="logo">
			<a href="#">
				<img src="../static/store/image/r___________renleipic_01/logo.gif" alt="网上商城"/>
			</a>
		</div>
	</div>
	<div class="span9">
<div class="headerAd">
	<img src="../static/store/image/header.jpg" width="320" height="50" alt="正品保障" title="正品保障"/>
</div>	</div>
	<div class="span10 last">
		<div class="topNav clearfix">
		<br/>
			<div>
			
			
			</div>
			<ul>
				<c:if test="${empty user }">
					<li id="headerLogin" class="headerLogin" style="display: list-item;">
					<a href="User?oper=loginview">登录</a>|
					</li>
					<li id="headerRegister" class="headerRegister" style="display: list-item;">
					<a href="User?oper=registview">注册</a>|
					</li>
							
				</c:if>
			   <c:if test="${not empty user }">
			    	<li style="color:red;font-weight:bolder;">${user.name }:你好!</li>
					<li><a href="Order?oper=list" style="color:purple;">我的订单</a></li>
					<li><a href="User?oper=logoutDeal" style="color:black;font-weight:bolder;">退出</a></li>
					<li><a href="User?oper=detail&id=${user.userId}" style="color:green;">个人信息</a></li>
				</c:if>
			
				<li id="headerLogout" class="headerLogout">
					<a>[退出]</a>|
				</li>
						
			<li>
			
				<div class="cart">
				<a  href="Cart">购物车</a>
				</div>
			</li>
						
			</ul>
	
			
		</div>
		
			
	</div>
	<div class="span24">
		<ul class="mainNav" id="c_ul">
				
					
					
		</ul>
	</div>
	
<script type="text/javascript">
	$(function(){
		$.ajax({
			type : 'get',
			url : 'Main?oper=find',
					
			success:function(data) {
				var obj=JSON.parse(data);
				for(var i in obj){
					$("#c_ul").append("<li >"+"<a href='Product?oper=pager&id="+obj[i].itemId+"'>"+obj[i].itemName+"</a>|</li>");
				}
				
			},
			error : function(data) {
				alert('Error!');

			},

		});
	})
	
	
</script>