<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="productDetail" method="post">
		<tr>
			<td>Search for Id: </td>
			<td><input type="text" name="id" value=""/></td>
			<td><input type="submit" value="Search" /></td>
		</tr>
	
	</form>
<p>==============================================</p><br/>
<h4>My data</h4><br/>
<c:if test="${!empty productId}">
	<tr>
		<td>ID: ${productId}</td>
		<td>Name: ${productName}</td>
		<td>Quantity: ${productQuantity}</td>
	</tr>
</c:if>
</body>
</html>