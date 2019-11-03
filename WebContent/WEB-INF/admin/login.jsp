<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="st" uri="http://java.sun.com/jsp/jstl/stt" %> 
 
<!DOCTYPE html>
<html lang="en" class="no-js">

    <head>

        <meta charset="utf-8">
        <title>Login</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">

        <link rel='stylesheet' href='http://fonts.googleapis.com/css?family=PT+Sans:400,700'>
        <link rel="stylesheet" href="../static/assets/css/reset.css">
        <link rel="stylesheet" href="../static/assets/css/supersized.css">
        <link rel="stylesheet" href="../static/assets/css/style.css">


    </head>

    <body style="background: url(../static/assets/img/backgrounds/1.jpg) no-repeat; background-size: cover; ">

        <div class="page-container">
            <h1>商城后台管理系统</h1>
            <form action="Login" method="post">
            <input type="hidden" name="oper" value="loginDeal" />
                <input placeholder="请输入账号" id="userName" name="userName" type="text"  value="${not empty userName?userName:(st:decode(cookie.userName.value))}" class="validate">
                <input placeholder="请输入密码" id="userPass" name="userPass" type="password" >
                
                <div>&nbsp;</div>
                <div style="color:red ">${msg}</div>

                <button type="submit">登录</button>
               <a href="#" >忘记密码?</a>
                
                
            </form>
            
        </div>

        <script src="../static/shop/assets/js/jquery-1.8.2.min.js"></script>
        <script src="assets/js/supersized.3.2.7.min.js"></script>
        <script src="../static/shop/assets/js/supersized-init.js"></script>
        <script src="../static/shop/assets/js/scripts.js"></script>

    </body>

</html>