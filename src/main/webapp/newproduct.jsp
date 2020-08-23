<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corona Kit-Add New Product(Admin)</title>
</head>
<body>
	<jsp:include page="header.jsp" />
	<hr />

	<form action="admin?action=insertproduct" method="POST">
		<table cellspacing="5px" cellpadding="5px">
			<tr>
				<th>ProductID</th>
				<th>Product Name</th>
				<th>Product Cost</th>
				<th>Product Description</th>
			</tr>
			<tr>
				<td><input type="text" name="pid" id="pid" /></td>
				<td><input type="text" name="pname" id="pname" /></td>
				<td><input type="text" name="pcost" id="pcost" /></td>
				<td><input type="text" name="pdescription" id="pdescription" /></td>
				<td><input type="submit" value="ADD NEW PRODUCT">
				<td>
			</tr>
		</table>
	</form>
	<hr />
	<jsp:include page="footer.jsp" />
</body>
</html>