<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>

	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>会员登录</title>
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

		</nav>

		<div class="container">
			<div class="row">

				<div style="margin:0 auto; margin-top:10px;width:950px;">
					<strong>My order</strong>
					<table class="table table-bordered">
					<c:forEach items="${page.list}" var="o">
						<tbody>
							<tr class="success">
								<th colspan="5">
									order number:${o.oid}
									total amount:£${o.total}
									<c:if test="${o.state == 1}">
										<a href="${pageContext.request.contextPath}/OrderServlet?method=findOrderByOid&oid=${o.oid}">pay</a>
									</c:if>
									<c:if test="${o.state == 2}">
										<a href="">unshipped</a>
									</c:if>
									<c:if test="${o.state == 3}">
										<a href="">sign for</a>
									</c:if>
									<c:if test="${o.state == 4}">
										<a href="">finish</a>
									</c:if>
								</th>
							</tr>
							<tr class="warning">
								<th>picture</th>
								<th>products</th>
								<th>price</th>
								<th>quantity</th>
								<th>total</th>
							</tr>
						<c:forEach items="${o.list}" var="item">
							<tr class="active">
								<td width="60" width="40%">
									<input type="hidden" name="id" value="22">
									<img src="${pageContext.request.contextPath}/${item.product.pimage}" width="70" height="60">
								</td>
								<td width="30%">
									<a target="_blank">${item.product.pname}</a>
								</td>
								<td width="20%">
									£${item.product.shop_price}
								</td>
								<td width="10%">
									${item.quantity}
								</td>
								<td width="15%">
									<span class="subtotal">£${item.total}</span>
								</td>
							</tr>
						</c:forEach>
						</tbody>
					</c:forEach>
					</table>
					<%@include file="pageFile.jsp"%>
				</div>
			</div>

		</div>

		<%@include file="footer.jsp"%>
	</body>

</html>