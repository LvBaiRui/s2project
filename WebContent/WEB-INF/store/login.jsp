 <%@ page language="java" contentType="text/html; charset=UTF-8" 
    pageEncoding="UTF-8"%> 
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="st" uri="http://java.sun.com/jsp/jstl/stt" %>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
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




<div class="container login">
		<div class="span12">
<div class="ad" style="margin-top:2px;">

					<img src="../static/store/image/login.jpg" width="500" height="330" alt="会员登录" title="会员登录">
</div>		</div>
		<div class="span12 last" >
			<div class="wrap" >
				<div class="main" >
					<div class="title">
						<strong>会员登录</strong>USER LOGIN 
					</div>
					<form id="loginForm"  method="post" novalidate="novalidate" action="User">
                    <input type="hidden" name="oper" value="logindeal"/>
						<table >
							<tbody ><tr">
								<th>
										用户名:
								</th>
								<td>
									<input type="text" id="username" class="text" maxlength="20" placeholder="请输入账号" name="userName" value="${not empty userName?userName : st:decode(cookie.userName.value)}"/>
									
								</td>
							</tr>
							<tr >
								<th >
									密&nbsp;&nbsp;码:
								</th>
								<td>
									<input type="password" id="password" class="text" maxlength="20" autocomplete="off" placeholder="请输入密码" name="userPass" />
								</td>
							</tr>
								
							<tr>
								<th>&nbsp;
									
								</th>
								<td style="color:red; font-weight:bold;">
									${msg}
								</td>
							</tr>
							<tr>
								<th>
								&nbsp;
								</th>
								<td>
			
									<label>
										&nbsp;&nbsp;<a href=""# style="color:red;">忘记密码?</a>
									</label>
								</td>
							</tr>
							<tr>
								<th>&nbsp;
									
								</th>
								<td>
									<input type="submit" class="submit" value="登 录">
								</td>
							</tr>
							<tr class="register">
								<th>&nbsp;
									
								</th>
								<td>
									<dl>
										<dt>还没有注册账号？</dt>
										<dd>
											立即注册即可体验在线购物！
											<a href="./会员注册.htm">立即注册</a>
										</dd>
									</dl>
								</td>
							</tr>
						</tbody></table>
					</form>
				</div>
			</div>
		</div>
	</div>

<div style="margin-top:50px;">


</div>

<div class="container footer">
	
	<jsp:include page="_ctx_footer.jsp"></jsp:include>
	
</div>
</body></html>