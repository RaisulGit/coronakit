<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corona Kit-My Kit(user)</title>
</head>
<body>
	<jsp:include page="header.jsp" />
	<hr />
	<c:choose>
		<c:when test="${abc == null || abc.isEmpty() }">
			<p>
				No Product Added to Kit <a href="newProduct">adding</a> one
			</p>
		</c:when>
		<c:otherwise>
		<form action="user?action=saveorder" method="post">
			<table border="1" cellspacing="4px" cellpadding="4px">

				<tr>
					<th>id</th>
					<th>coronaKitId</th>
					<th>productId</th>
					<th>quantity</th>
					<th>amount</th>
				</tr>
				<c:forEach items="${abc}" var="a">
					<tr>
						<td>${a.id}</td>
						<td>${a.coronaKitId}</td>
						<td>${a.productId}</td>
						<td>${a.amount}</td>
						<td>${a.quantity}</td>
					</tr>
				</c:forEach>
				<tr>
					<th></th>
					<th></th>
					<th></th>
					<th>Total Quantity:</th>
					<th>Total Amount:</th>
				</tr>
				<tr>
					<!--<jsp:include page="newproduct.jsp" />-->
				</tr>
			</table>
			<input type="submit" value="CONFIRM ORDER"/>
			</form>
		</c:otherwise>
	</c:choose>

	<hr />
	<jsp:include page="footer.jsp" />
</body>
</html>