<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>

	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>shopping busket</title>
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
			
			.container .row div {
				/* position:relative;
	 float:left; */
			}
			
			font {
				color: #3164af;
				font-size: 18px;
				font-weight: normal;
				padding: 0 10px;
			}
		</style>
	</head>

	<body>

		
			<%@include file="header.jsp"%>


		<div class="container">
			<c:if test="${empty cart.getCartItems()}">
				<div class="row">
					<div class="col-md-12">
						<h1>Go shopping!</h1>
					</div>
				</div>
			</c:if>

			<c:if test="${not empty cart.getCartItems()}">
				<div class="row">

					<div style="margin:0 auto; margin-top:10px;width:950px;">
						<strong style="font-size:16px;margin:5px 0;">order details</strong>
						<table class="table table-bordered">
							<tbody>
							<tr class="warning">
								<th>picture</th>
								<th>goods</th>
								<th>price</th>
								<th>quantity</th>
								<th>total</th>
								<th>operation</th>
							</tr>
						<c:forEach items="${cart.getCartItems()}" var="item">
							<tr class="active">
								<td width="60" width="40%">
									<input type="hidden" name="id" value="22">
									<img src="${pageContext.request.contextPath}/${item.product.pimage}" width="70" height="60">
								</td>
								<td width="30%">
									<a target="_blank"> ${item.product.pname}</a>
								</td>
								<td width="20%">
									£${item.product.shop_price}
								</td>
								<td width="10%">
									<input type="text" name="quantity" value="${item.num}" maxlength="4" size="10">
								</td>
								<td width="15%">
									<span class="subtotal">£${item.subTotal}</span>
								</td>
								<td>
									<a href="javascript:;" id="${item.product.pid}" class="delete">delete</a>
								</td>
							</tr>
						</c:forEach>
							</tbody>
						</table>
					</div>
				</div>

				<div style="margin-right:130px;">
					<div style="text-align:right;">
						<em style="color:#ff6600;">
							nice!&nbsp;&nbsp;
						</em> credit: <em style="color:#ff6600;">${cart.total}</em>&nbsp; amount: <strong style="color:#ff6600;">£${cart.total}</strong>
					</div>
					<div style="text-align:right;margin-top:10px;margin-bottom:10px;">
						<a href="${pageContext.request.contextPath}/CartServlet?method=clearCart" id="clear" class="clear">clear busket</a>
						<a href="${pageContext.request.contextPath}/OrderServlet?method=saveOrder">
								<%--提交表单 --%>
							<input type="submit" width="100" value="submit" name="submit" border="0" style="background: url('${pageContext.request.contextPath}/img/register.gif') no-repeat scroll 0 0 rgba(0, 0, 0, 0);
									height:35px;width:100px;color:white;">
						</a>
					</div>
				</div>
			</c:if>

		</div>

		<%@include file="footer.jsp"%>

	</body>
<script>
	$(function () {
		$(".delete").click(function () {
			if (confirm("are you sure?")){
				var pid = this.id;
				window.location.href="/CartServlet?method=removeCartItem&id="+pid;
			}
		})

	});
</script>
</html>