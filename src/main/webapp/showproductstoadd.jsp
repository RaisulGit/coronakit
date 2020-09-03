<%@page import="java.util.*"%>
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
			<p>No Products Found Try, contact asdmin</p>
		</c:when>
		<c:otherwise>
			<%
				Map<String,String> map1 = new HashMap<>();			
			%>
			<form action="user?action=addnewitem" method="post">
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
							<td><input name="id" value="${product.id}" /></td>
							<td><input name="productName" value="${product.productName}" /></td>
							<td><input name="cost" value="${product.cost}" /></td>
							<td><input name="productDescription" value="${product.productDescription}" /></td>
							<td><select id="itemcount" name="itemcount">
									<option value="0">0</option>
									<option value="1">1</option>
									<option value="2">2</option>
									<option value="3">3</option>
									<option value="4">4</option>
							</select></td>
							<%
								String id=request.getParameter("id");
								String icount=request.getParameter("itemcount");
								map1.put(id, icount);
							%>
						</tr>
					</c:forEach>
				</table>
				
				<input type="submit" value="SAVE DATA">
			</form>
		</c:otherwise>
	</c:choose>
	<hr />
	<jsp:include page="footer.jsp" />
	
</body>
</html>