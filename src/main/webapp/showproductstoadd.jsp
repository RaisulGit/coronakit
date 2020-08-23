<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corona Kit-All Products(user)</title>
</head>
<body>
	<jsp:include page="header.jsp" />
	<hr />

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
					<th>Quantity</th>
				</tr>
				<c:forEach items="${product}" var="product">
					<tr>
						<td>${product.id}</td>
						<td>${product.productName}</td>
						<td>${product.cost}</td>
						<td>${product.productDescription}</td>
						<td> <select name="cars" id="cars">
								<option value="1">1</option>
								<option value="2">2</option>
								<option value="3">3</option>
								<option value="4">4</option>
						</select></td>
						<td><a href="user?action=addnewitem">ADD PRODUCT</a>						
					</tr>
				</c:forEach>
				<tr>
					<!--<jsp:include page="newproduct.jsp" />-->
				</tr>
			</table>
		</c:otherwise>
	</c:choose>

	<hr />
	<jsp:include page="footer.jsp" />
</body>
</html>