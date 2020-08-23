<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corona Kit-New User(user)</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<hr>
	<form action="user?action=showproducts" method="POST">
		<div>
			<label>Person Name</label> <input type="text" name="pname" required />
		</div>
		<br>
		<div>
			<label>Email</label> <input type="text" name="pemail" required />
		</div>
		<br>
		<div>
			<label>Contact Number</label> <input type="text" name="pcontact" required />
		</div>
		<br>
		<div>
			<input type="submit" value="submit" />
		</div>
	</form>
	<hr>	
	<jsp:include page="footer.jsp"/>
</body>
</html>