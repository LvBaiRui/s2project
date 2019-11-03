
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
					<span style="color:red">修改密码</span>&nbsp;&nbsp;USER INFO
					</h3>
						
					</div>
 <form id="registerForm" action="User" method="post" >
                    <input type="hidden" name="oper" value="update3"/>
                    <input type="hidden" name="id" value="${user.userId}"/>
						<table>
							<tbody><tr>
								<th>
									<span class="requiredField">*</span>请输入原密码:
								</th>
								<td>
								<input type="password" id="userPass0" class="text" maxlength="20" placeholder="请输入原密码" name="userPass0" value="${userPass0}"/>
								</td>
							</tr>
							
							<tr>
								<th>
									<span class="requiredField">*</span>:请输入新密码
								</th>
								<td>
									<input type="password" id="userPass1" class="text" maxlength="200" placeholder="请输入新密码"  name="userPass1" value="${userPass1}"/>
								</td>
							</tr>
									<tr>
										<th>
											<span class="requiredField">*</span>:请输入确认密码
										</th>
										<td>
												<input type="password"  class="text" maxlength="200" placeholder="请输入确认密码" name="userPass2" value="${userPass2}"/>
										</td>
									</tr>

						
							
							
									<tr >
									<th >
									
											
										</th>
										
										<td>
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
			<div style="margin-top: 50px;"></div>
			<hr />
			
		</div>
			
			
			<jsp:include page="_body_end.jsp"></jsp:include>
		</div>
		<div class="container footer">

			<jsp:include page="_ctx_footer.jsp"></jsp:include>

		</div>
		
		
		</div>
</body>

</html>