<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corona Kit-Edit Product(Admin)</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<hr/>
	<!-- Shall contain entry form for editing product detail -->
	<form action="admin?action=updateproduct" method="post">
		<div><label>Product ID</label></div> 
		<div><input type="text" name="pid" value="${product.id }"  required readonly="readonly"/></div>
		<br>
		<div><label>Product Name</label></div>  
		<div><input type="text" name="pname" value="${product.productName}" required /></div>
		<br>
		<div><label>Product Cost</label></div>
		<div><input type="text" name="pcost" value="${product.cost}" required /></div>
		<br>
		<div><label>Product Description</label></div>
		<div><input type="text" name="pdescription"	value="${product.productDescription }" required /></div>
		<br>
		<div><input type="submit" value="save"></div>
	</form>
<hr/>	
	<jsp:include page="footer.jsp"/>
</body>
</html>