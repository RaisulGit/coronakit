<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corona Kit-All Products(Admin)</title>
</head>
<body>
	<jsp:include page="header.jsp" />
	<hr />
<div align="right"><a href="admin?action=logout">LOGOUT</a></div>
	<c:choose>
		<c:when test="${product == null || product.isEmpty() }">
			<p>
				No Products Found Try <a href="newProduct">adding</a> one
			</p>
		</c:when>
		<c:otherwise>
			<table border="1" cellspacing="4px" cellpadding="4px">

				<tr>
					<th>ProductID</th>
					<th>Product Name</th>
					<th>Product Cost</th>
					<th>Product Description</th>
				</tr>
				<c:forEach items="${product}" var="product">
					<tr>
						<td>${product.id}</td>
						<td>${product.productName}</td>
						<td>${product.cost}</td>
						<td>${product.productDescription}</td>
						<td><a href="admin?action=deleteproduct=${product.id}">DELETE</a>
							<span>|</span> <a href="admin?action=editproduct=${product.id}">EDIT</a>
						</td>
					</tr>
				</c:forEach>
				<tr>
					<!-- <jsp:include page="newproduct.jsp" />-->
				</tr>
				<tr>
					<td><a href="admin?action=newproduct">INSERT NEW PRODUCT</a></td>
				</tr>
			</table>
		</c:otherwise>
	</c:choose>

	<hr />
	<jsp:include page="footer.jsp" />
</body>
</html>