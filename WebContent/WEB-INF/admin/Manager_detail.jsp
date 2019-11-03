
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>购物商城 - 后台管理</title>
<jsp:include page="_head_link.jsp"></jsp:include>


</head>

<body>
	<div id="wrapper">

		<jsp:include page="_ctx_header.jsp"></jsp:include>


		<!--/. NAV TOP  -->

		<jsp:include page="_ctx_nav.jsp"></jsp:include>

		<!-- /. NAV SIDE  -->

		<div id="page-wrapper">
			<div id="page-inner">


				<jsp:include page="_body_start.jsp"></jsp:include>
				<ol class="breadcrumb">
					<li><a href="#">Home</a></li>
					<li><a href="#">管理员管理</a></li>
					<li class="active">管理员信息详情</li>
				</ol>

				
				 <div class="row">
            
                    <!-- column -->
                    <div class="col-sm-12">
                        <div class="card">
                            <div class="card-block">
                           
              
                                <div class="table-responsive">
	<div class="col-xl-12 col-sm-12">
                  <div class="item d-flex align-items-center" style="margin:10px;">
                 	 管理员名称：${bean.userName}                 
                  </div>
                </div>
                <div class="col-xl-12 col-sm-12">
                  <div class="item d-flex align-items-center" style="margin:10px;">
                    管理员密码：${bean.userPass}
                   
                  </div>
                </div>
                  
                 <div class="col-xl-12 col-sm-12">
                  <div class="item d-flex align-items-center" style="margin:10px;">         
                   
                  
                </div>
                <div class="col-xl-12 col-sm-12" >
                  <div class="item d-flex align-items-center">
                  <input type="button" value="返回列表" class="btn btn-primary"  onclick="location.href='Manager?oper=list';">
                   
                  </div>
                </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <jsp:include page="_ctx_footer.jsp"></jsp:include>
                </div>


	
			
				

			</div>
			<!-- /. PAGE INNER  -->
		</div>
		<!-- /. PAGE WRAPPER  -->
		
	</div>

	<jsp:include page="_body_end.jsp"></jsp:include>

</body>

</html>