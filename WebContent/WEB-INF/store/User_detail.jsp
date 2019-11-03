
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script src="../static/js/jquery-3.4.1.min.js"></script>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>购物商城</title>

<jsp:include page="_head_link.jsp"></jsp:include>


</head>
<body>

	<div class="container header">

		<jsp:include page="_ctx_header.jsp"></jsp:include>



	</div>

	<div class="container index">
	
	
			<div style="border: 1px solid #e4e4e4;width:16600px;margin-bottom:10px;margin:0 auto;padding:10px;margin-bottom:10px;">
					<a href="Main"  style="color:blue;font-weight:bolder;letter-spacing:2px; ">返回首页&nbsp;&nbsp;&gt;</a>
			
					
				</div>


		<div class="span24">
			<div class="wrap">
				<div class="main clearfix">
					<div style="margin-bottom:30px;">
					<h3>
					<span style="color:red">个人信息</span>&nbsp;&nbsp;USER INFO
					</h3>
						
					</div>
					 <form id="registerForm" action="User" method="post" >
                    <input type="hidden" name="oper" value="update"/>
                    <input type="hidden" name="id" value="${user.userId}"/>
						<table>
							<tbody>
							
							<tr>
								<th>
									<span class="requiredField">*</span>E-mail:
								</th>
								<td>
									<input type="text" id="email" class="text" maxlength="200" placeholder="Email"  name="email" value="${user.email}"/>
								</td>
							</tr>
									<tr>
										<th>
											姓名:
										</th>
										<td>
												<input type="text"  class="text" maxlength="200" placeholder="请输入姓名" name="name" value="${user.name}"/>
										</td>
									</tr>
									
									
									
									<tr>
										<th>
											地址:
										</th>
										<td>
												<input type="text" class="text" maxlength="200"  name="address" value="${user.address}"/>
										</td>
									</tr>
									
									
									<tr >
									<th >
									
											
										</th>
										
										<td>
												 <input type="button" value="修改个人信息" class="updateInfo" />
												 <input type="button" value="修改密码" class="btn btn-primary"  onclick="location.href='User?oper=update2&id=${user.userId}';" />
												 <input type="submit" value="确认修改" />
										</td>
									</tr>
									
									
									

							<tr>
								<th>&nbsp;
									
								</th>
								<td style="color:red; font-weight:bold;">
									${msg}
								</td>
							</tr>
							
							
							
						</tbody></table>
						
					</form>
				</div>
			</div>
		</div>
			
			

			<div style="margin-top: 50px;"></div>
			<hr />

			<jsp:include page="_body_end.jsp"></jsp:include>
		</div>
		<div class="container footer">

			<jsp:include page="_ctx_footer.jsp"></jsp:include>

		</div>
		
		
		</div>
</body>

<script>
$("input").attr("readonly","readonly");
$(".updateInfo").click(function () {
	$("input").removeAttr("readonly");
	
	
})
</script>




</html>