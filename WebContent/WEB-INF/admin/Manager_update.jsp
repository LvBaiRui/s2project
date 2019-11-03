
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>后台管理 - 管理员信息修改</title>
<jsp:include page="_head_link.jsp"></jsp:include>


</head>

<body>
	<div id="wrapper">

		<jsp:include page="_ctx_header.jsp"></jsp:include>


		<!--/. NAV TOP  -->

		<jsp:include page="_ctx_nav.jsp"></jsp:include>

	<div id="page-wrapper">
			<div id="page-inner">


				<jsp:include page="_body_start.jsp"></jsp:include>
			<ol class="breadcrumb">
					<li><a >Home</a></li>
					<li><a >管理员管理</a></li>
					<li class="active">修改管理员信息</li>
				</ol>

					
					<div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        
                        <div class="">
                            <div class="row">
                                <div class="col-lg-12">
                                    <form role="form" action="Manager" method="Post">
                                     <input type="hidden" name="oper" value="updateDeal">
                        			 <input type="hidden" id="id" name="id" value="${bean.managerId}">
                                      <div class="form-group row">
				                        <label class="col-sm-3 form-control-label"></label>
				                      </div>
			                          <div class="form-group row">
			                          <label class="col-sm-3 form-control-label">管理员名称：</label>
			                          <div class="col-sm-9">
			                            <input id="userName" type="text" placeholder="请输入管理员名称"  name="userName"  value="${userName!=null?userName:bean.userName}" class="form-control form-control-warning">
			                            <small class="form-text"></small>
			                          </div>
			                          </div>
			                          <div class="form-group row">
			                          <label class="col-sm-3 form-control-label">密码：</label>
			                          <div class="col-sm-9">
			                            <input id="userPass" type="password" name="userPass"  value="" class="form-control form-control-warning">
			                            <small class="form-text"></small>
			                          </div>
			                          </div>
			                          
			                          <div class="form-group row">
			                          <label class="col-sm-3 form-control-label">确认密码：</label>
			                          <div class="col-sm-9">
			                            <input id="userPass2" type="password" name="userPass2"  value="" class="form-control form-control-warning">
			                            <small class="form-text"></small>
			                          </div>
			                          </div>
			                  
			                          <div style="color:red;font-weight:bold">${msg}</div>
			                        <div class="form-group row">       
			                          <div class="col-sm-9 offset-sm-3">
			                            <input type="submit" value="提交" class="btn btn-primary">
			                            <input type="reset" value="重置" class="btn btn-primary">
			                            <input type="button" value="返回列表" class="btn btn-primary"  onclick="location.href='Manager?oper=list';">
			                          </div>
			                        </div>           
                      			</form>
                                       
					    
                                    
                                </div>
                                <!-- /.col-lg-6 (nested) -->
                            </div>
                            <!-- /.row (nested) -->
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>

<p style="text-align:center;">Copyright &copy; 2019.Company name as 守夜冠军</p>


			</div>
			<!-- /. PAGE INNER  -->
		</div>
		<!-- /. PAGE WRAPPER  -->
	</div>

	<jsp:include page="_body_end.jsp"></jsp:include>

</body>

</html>