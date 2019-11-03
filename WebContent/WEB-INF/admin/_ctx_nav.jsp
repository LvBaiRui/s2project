<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <nav class="navbar-default navbar-side" role="navigation">
		<div id="sideNav" href=""><i class="fa fa-caret-right"></i></div>
            <div class="sidebar-collapse">
                <ul class="nav" id="main-menu">

 					<li>
                        <a  href="Main"><i class="fa fa-dashboard"></i> 主页面</a>
                    </li>
                    <li>
                        <a  href="Manager?oper=list"><i class="fa fa-dashboard"></i> 管理员</a>
                    </li>
					<li>
                        <a href="User?oper=list"><i class="fa fa-bar-chart-o"></i> 用户</a>
                    </li>
                    <li>
                        <a href="Item?oper=list"><i class="fa fa-bar-chart-o"></i> 商品分类</a>
                    </li>
                    <li>
                        <a href="Product?oper=list"><i class="fa fa-qrcode"></i> 商品</a>
                    </li>
                    
                    <li>
                        <a href="Order?oper=list"><i class="fa fa-table"></i> 订单</a>
                    </li>


                </ul>

            </div>

        </nav>
       