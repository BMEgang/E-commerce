<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html>

	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>product information</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css" />
		<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript"></script>
		<!-- 引入自定义css文件 style.css -->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css"/>

		<style>
			body {
				margin-top: 20px;
				margin: 0 auto;
			}

			.carousel-inner .item img {
				width: 100%;
				height: 300px;
			}
		</style>
	</head>

	<body>


			<%@include file="header.jsp"%>


		<div class="container">
			<div class="row">
				<div style="border: 1px solid #e4e4e4;width:930px;margin-bottom:10px;margin:0 auto;padding:10px;margin-bottom:10px;">
					<a href="${pageContext.request.contextPath}/">main page&nbsp;&nbsp;&gt;</a>
				</div>

				<div style="margin:0 auto;width:950px;">
					<form id="myForm" method="post" action="${pageContext.request.contextPath}/CartServlet?method=addCartItemToCart">

					<div class="col-md-6">
						<img style="opacity: 1;width:400px;height:350px;" title="" class="medium" src="${pageContext.request.contextPath}/${product.pimage}">
					</div>

					<div class="col-md-6">
						<div><strong>${product.pname}</strong></div>
						<div style="border-bottom: 1px dotted #dddddd;width:350px;margin:10px 0 10px 0;">
							<div>number：${product.pid}</div>
						</div>

						<div style="margin:10px 0 10px 0;">shop price: <strong style="color:#ef0101;">&pound${product.shop_price}</strong> market price： <del>&pound;${product.market_price}</del>
						</div>

						<div style="margin:10px 0 10px 0;">on sale: <a target="_blank" title="limited time (2022-01-01 ~ 2022-01-30)" style="background-color: #f07373;">flash sale</a> </div>

						<div style="padding:10px;border:1px solid #e7dbb1;width:330px;margin:15px 0 10px 0;;background-color: #fffee6;">
							<div style="margin:5px 0 10px 0;">white</div>

							<div style="border-bottom: 1px solid #faeac7;margin-top:20px;padding-left: 10px;">quantity:
								<input id="quantity" name="quantity" value="1" maxlength="4" size="10" type="text"> </div>
								<input type="hidden" name="pid" value="${product.pid}">

							<div style="margin:20px 0 10px 0;;text-align: center;">
								<%--加入到购物车 --%>
								<a href="javascript:void(0)">
									<input id="btnId" style="background: url('${pageContext.request.contextPath}/img/product.gif') no-repeat scroll 0 -600px rgba(0, 0, 0, 0);height:36px;width:127px;" value="add basket" type="button">
								</a> &nbsp;store</div>
						</div>
					</div>
					</form>
				</div>
				<div class="clear"></div>
				<div style="width:950px;margin:0 auto;">
					<div style="background-color:#d3d3d3;width:930px;padding:10px 10px;margin:10px 0 10px 0;">
						<strong>products introduction</strong>
						<h3>${product.pdesc}</h3>
					</div>


			</div>
		</div>

		<%@include file="footer.jsp"%>

	</body>
	<script>
		$(function(){
			$("#btnId").click(function(){
				var formObj=document.getElementById("myForm");
				//formObj.action="/store_v5/CartServlet";
				//formObj.method="get";
				formObj.submit();
			});
		});
	</script>
</html>