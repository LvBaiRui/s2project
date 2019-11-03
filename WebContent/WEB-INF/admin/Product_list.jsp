
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
					<li><a href="Main">Home</a></li>
					<li><a href="#">商品管理</a></li>
					<li class="active">Product</li>
				</ol>

				<!-- Advanced Tables -->
				<div class="panel panel-default">
					<div class="panel-heading"></div>
					<div class="panel-heading"></div>

					<form action="Product" method='POST' id='listform'>
						<input type="hidden" name="oper" value="listDeal" /> <span
							class="panel-heading">关键字：</span> <input type='search'
							name='searchName' id='searchName' placeholder="请输入要搜索的关键字"
							value="${searchName}" /> <input type='submit' value='搜索'
							class="btn btn-primary" /> <input type='button'
							class="btn btn-warning"
							onclick="javascript:location.href='Product?oper=list'" value='清空' />

					</form>


					<div class="panel-body">
						<button onclick="javascript:location.href='Product?oper=insert'"
							class="btn btn-success">添加</button>
						&nbsp;&nbsp;
						<button onclick='javascript:datadel()' class="btn btn-success">批量删除</button>
						&nbsp;&nbsp;
						<div class="table-responsive">

							<table class="table table-striped table-bordered table-hover"
								id="dataList">
								<thead>
									<tr>
										<th><input type='checkbox' id="checkall" value='' /></th>
										<th>序号</th>
										<th>商品Id</th>
										<th>商品名称</th>
										<th>商品价格</th>
										<th>市场价格</th>
										<th>商品描述</th>
										<th>商品图片</th>
										<th>是否热销</th>
										<th>日期</th>
										<th>类别</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody>

									<c:forEach var="bean" items="${DataList}" varStatus="status">
										<tr>
											<td><input type='checkbox' id="line${bean.productId}"
												value='${bean.productId}' /></td>
											<td>${status.count}</td>
											<td>${bean.productId}</td>
											<td>${fn:substring(bean.proName,0,6)}...</td>
											<td>${bean.proPrice}</td>
											<td>${bean.marketPrice}</td>
											<td>${fn:substring(bean.productInfo,0,6)}...</td>
											<td>${bean.image}</td>
											<td>${bean.isHot==0?"不热销":"热销"}</td>
											<td>${bean.dateTime}</td>
											<td>${bean.itemId}</td>
											<td><a href="javascript:item_del(this,${bean.productId});">删除</a>&nbsp;&nbsp;
												<a href="Product?oper=update&id=${bean.productId}">修改</a>&nbsp;&nbsp;
												<a href="Product?oper=detail&id=${bean.productId}">查看</a></td>
										</tr>
									</c:forEach>


								</tbody>
							</table>
							<jsp:include page="__pager.jsp" flush="true" />

						</div>

					</div>

				</div>
				<!--End Advanced Tables -->

				<p style="text-align: center;">Copyright &copy; 2019.Company
					name as 守夜冠军</p>

			</div>
			<!-- /. PAGE INNER  -->
		</div>
		<!-- /. PAGE WRAPPER  -->
	</div>

	<jsp:include page="_body_end.jsp"></jsp:include>

	<script>
		function item_del(obj, id) {

			if (confirm("确认要删除吗？")) {
				$.ajax({
					type : 'POST',
					url : 'Product?oper=deleteDeal&id=' + id,
					//dataType : 'json'
					success : function(data) {
						var result = JSON.parse(data);
						if (result.code == 1) {
							$("#line" + id).parents("tr").remove();
							alert("已删除！");
						} else {
							alert("删除失败！");
						}
						location.href=changeUrlArg(location.href,"t" ,new Date().getTime())
	    				location.reload();
					},
					error : function(data) {

					},
				});
			}
			;
		}

		function datadel() {
			if (confirm("真的要删除吗？")) {

				var num = 0; // 记录,删除成功的行数
				var total = 0;// 记录,要删除的行数
				var obj = null;// 记录当前对象
				// [其它已选中框列表]，排除全选框
				var $otherCheckedList = $("#dataList input:checkbox:checked:not('#checkall')");

				// 迭代所有已选中框
				$otherCheckedList.each(function() {
					obj = this;
					var id = $(this).val();
					//alert(id);
					if (id != null && id != "" && id != "0") {
						total++;// 总记录
						$.ajax({
							type : 'POST',
							url : "Product",
							async : false,// 要使用同步
							data : {
								"oper" : "deleteDeal",
								"id" : id
							},
							success : function(data) {
								var result = JSON.parse(data);
								if (result.code == 1) {
									num++;// layer.msg('已删除!',{icon:1,time:2000});
									$("#line" + id).parents("tr").remove();
								} else {
									alert('删除失败!');
								}
								location.href=changeUrlArg(location.href,"t" ,new Date().getTime())
			    				location.reload();
							},
							error : function(data) {
								alert('Error!');

							},
						});
					}
				});

				alert('要删除' + total + '行记录, 成功删除' + num + '行记录。');
				// location.reload();
			}
		}
	</script>



	<script>
		$(function() {
			//全选与反选
			$("#dataList #checkall")
					.change(
							function() {
								var $otherCheckList = $("#dataList input:checkbox:not('#checkall')");
								var checkAllState = $("#checkall").prop(
										"checked");
								$otherCheckList.prop("checked", checkAllState);

							});

			$("#nav_teacher").addClass("active");

		});
	</script>

</body>

</html>