
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
					<li><a href="#">Library</a></li>
					<li class="active">damin</li>
				</ol>

				<!-- Advanced Tables -->
				<div class="panel panel-default">
					<div class="panel-heading">表格</div>
					<div class="panel-body">
						<div class="table-responsive">
							<table class="table table-striped table-bordered table-hover"
								id="dataTables-example">
								<thead>
									<tr>
										<th>Rendering engine</th>
										<th>Browser</th>
										<th>Platform(s)</th>
										<th>Engine version</th>
										<th>CSS grade</th>
									</tr>
								</thead>
								<tbody>
									<tr class="odd gradeX">
										<td>Trident</td>
										<td>Internet Explorer 4.0</td>
										<td>Win 95+</td>
										<td class="center">4</td>
										<td class="center">X</td>
									</tr>

								</tbody>
							</table>
						</div>

					</div>
				</div>
				<!--End Advanced Tables -->










				<jsp:include page="_ctx_footer.jsp"></jsp:include>

			</div>
			<!-- /. PAGE INNER  -->
		</div>
		<!-- /. PAGE WRAPPER  -->
	</div>

	<jsp:include page="_body_end.jsp"></jsp:include>

</body>

</html>