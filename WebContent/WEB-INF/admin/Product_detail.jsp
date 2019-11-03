
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>购物商城 - 后台商品管理</title>
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
					<li><a href="#">商品管理</a></li>
					<li class="active">商品详情</li>
				</ol>

				
				 <div class="row">
            
                    <!-- column -->
                    <div class="col-sm-12">
                        <div class="card">
                            <div class="card-block">
                           
              
                                <div class="table-responsive">
	<div class="col-xl-12 col-sm-12">
                  <div class="item d-flex align-items-center">
                 	 商品名称：${bean.proName}                 
                  </div>
                </div>
               <div class="col-xl-12 col-sm-12">
                  <div class="item d-flex align-items-center">
                 	 商品价格：${bean.proPrice}                 
                  </div>
                </div>
                  <div class="col-xl-12 col-sm-12">
                  <div class="item d-flex align-items-center">
                 	 市场价格：${bean.marketPrice}                 
                  </div>
                </div>
                <div class="col-xl-12 col-sm-12">
                  <div class="item d-flex align-items-center">
                 	 商品描述：${bean.productInfo}                 
                  </div>
                </div>
                </div>
                <div class="col-xl-12 col-sm-12">
                  <div class="item d-flex align-items-center">
                 	 商品图片：${bean.image}                 
                  </div>
                  <img alt="" src="${pageContext.request.contextPath }/products/1/${bean.image}"  width="170" height="170"  style="display: inline-block;">
                </div>
                <div class="col-xl-12 col-sm-12">
                  <div class="item d-flex align-items-center">
                 	 是否热销：${bean.isHot==0?"不热销":"热销"}                 
                  </div>
                </div>
                <div class="col-xl-12 col-sm-12">
                  <div class="item d-flex align-items-center">
                 	 日期：${bean.dateTime}                 
                  </div>
                </div>
                <div class="col-xl-12 col-sm-12">
                  <div class="item d-flex align-items-center">
                 	 商品类别：${iName}                 
                  </div>
                </div>
                <div class="col-xl-12 col-sm-12">
                  <div class="item d-flex align-items-center">
                  <input type="button" value="返回列表" class="btn btn-primary"  onclick="location.href='Product?oper=list';">
                   
                  </div>
                </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

				<jsp:include page="_ctx_footer.jsp"></jsp:include>

			</div>
			<!-- /. PAGE INNER  -->
		</div>
		<!-- /. PAGE WRAPPER  -->

	<jsp:include page="_body_end.jsp"></jsp:include>

</body>

</html>