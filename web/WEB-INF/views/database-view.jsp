<head>
<%@ include file="/WEB-INF/decorators/include/taglibs.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h2> My form</h2>
	<div id="main">
		<form:form method="post" modelAttribute="client" action="findClientById">
			<label>Id</label>
			<form:input path="id"></form:input>
			<input type="submit" value="Search" />
		</form:form>
	
	
<!-- 		<div> -->
<!-- 			<h2>Result</h2> -->
<%-- 			<p>Name: ${clientObject.name} }</p> --%>
<%-- 			<p>Age: ${clientObject.age} }</p> --%>
<%-- 			<p>Money: ${clientObject.money} }</p> --%>
<!-- 		</div> -->
	</div>
</body>
