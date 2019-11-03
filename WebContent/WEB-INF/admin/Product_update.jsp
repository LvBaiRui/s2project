
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>后台管理 - 商品信息修改</title>
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
					<li><a >商品管理</a></li>
					<li class="active">修改商品信息</li>
				</ol>

					
					<div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        
                        <div class="">
                            <div class="row">
                                <div class="col-lg-12">
                                    <form role="form" action="Product" method="Post" >
                                     <input type="hidden" name="oper" value="updateDeal">
                        			 <input type="hidden" id="id" name="id" value="${bean.productId}">
                                      <div class="form-group row">
				                        <label class="col-sm-3 form-control-label"></label>
				                      </div>
			                          <div class="form-group row">
			                          <label class="col-sm-3 form-control-label">商品名称：</label>
			                          <div class="col-sm-9">
			                            <input id="proName" type="text" placeholder="请输入商品名称"  name="proName"  value="${proName!=null?proName:bean.proName}" class="form-control form-control-warning">
			                            <small class="form-text"></small>
			                          </div>
			                          </div>
			                           <div class="form-group row">
			                          <label class="col-sm-3 form-control-label">商品价格：</label>
			                          <div class="col-sm-9">
			                            <input id="proPrice" type="text" placeholder="请输入商品价格"  name="proPrice"  value="${proPrice!=null?proPrice:bean.proPrice}" class="form-control form-control-warning">
			                            <small class="form-text"></small>
			                          </div>
			                          </div>
			                           <div class="form-group row">
			                          <label class="col-sm-3 form-control-label">市场价格：</label>
			                          <div class="col-sm-9">
			                            <input id="marketPrice" type="text" placeholder="请输入市场价格"  name="marketPrice"  value="${marketPrice!=null?marketPrice:bean.marketPrice}" class="form-control form-control-warning">
			                            <small class="form-text"></small>
			                          </div>
			                          </div>
			                           <div class="form-group row">
			                          <label class="col-sm-3 form-control-label">商品描述：</label>
			                          <div class="col-sm-9">
			                            <input id="productInfo" type="text" placeholder="请输入商品描述  " name="productInfo"  value="${productInfo!=null?productInfo:bean.productInfo}" class="form-control form-control-warning">
			                            <small class="form-text"></small>
			                          </div>
			                          </div>
			                          <div class="form-group row">
			                          <label class="col-sm-3 form-control-label">商品图片：</label>
			                          <div class="col-sm-9">
			                            <input id="image" type="file" placeholder="请传入商品图片 " name="image"  value="${image!=null?image:bean.image}" class="form-control form-control-warning">
			                            <small class="form-text"></small>
			                          </div>
			                          </div>
			                           <div class="form-group row">
			                          <label class="col-sm-3 form-control-label">是否热销：</label>
			                          <div class="col-sm-9">
			                            <select name="isHot" id="isHot" class="form-control form-control-warning">
			                          						<option value="">请选择</option>
															<option value="0">不热销</option>
															<option value="1">热销</option>
														</select>
			                          </div>
			                          </div>
			                         <div class="form-group row">
			                          <label class="col-sm-3 form-control-label">商品类别：</label>
			                           <div class="col-sm-9">
			                          <select name="itemId" id="itemId" class="form-control form-control-warning">
			                          						<option value="">请选择</option>
			                          						 <c:forEach var="bean" items="${DataList}" varStatus="status">
			                          						<option value="${bean.itemId}">${bean.itemName}</option>
			                          						 </c:forEach>
														</select>
														
			                          </div>
			                          </div>
			                           
			                             <div style="color:red;font-weight:bold">${msg}</div>
			                        <div class="form-group row">       
			                          <div class="col-sm-9 offset-sm-3">
			                            <input type="submit" value="提交" class="btn btn-primary">
			                            <input type="reset" value="重置" class="btn btn-primary">
			                            <input type="button" value="返回列表" class="btn btn-primary"  onclick="location.href='Product?oper=list';">
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