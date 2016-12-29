<head>
<%@ include file="/WEB-INF/decorators/include/taglibs.jsp"%>

<style>
	.error {
		color: #ff0000;
	}
	.errorblock{
		color: #000;
		background-color: #ffEEEE;
		border: 3px solid #ff0000;
		padding:8px;
		margin:16px;
	}

</style>

</head>
<body>
<div>
	<div id="main">
		<div>
			<h2>div 1 spring all errors</h2>
			<div path="*.errors" cssClass="errorblock" ></div>
		</div>
		<div>
			<h2>div 2 all errors</h2>
			<div id="*.errors" name="*.errors" class="errorblock" ></div>
		</div>
		<div>
			<h2>div 3 jstl all error</h2>
			<c:forEach items="${errors}" var="error">
				<%-- do want you want with ${error} --%>
				<c:out value="${error.defaultMessage}" />
			</c:forEach>
		</div>
		<div>
			<h2>div 4 jstl not loop</h2>
			<p id="errors" name="errors" class="error"></p>
			<h2>continue</h2>
			<c:out value="${errors.defaultMessage}" />
		</div>
		<div>
			<h2>div 5 Spring Form</h2>
			<form:form method="post"  commandName="product" action="addProduct">
				<label>Name</label>
				<form:input path="productName" type="text" />
				<form:errors path="productName" cssClass="error"/>
				<label>Quantity</label>
				<form:input path="productQuantity" type="text" />
				<form:errors path="productQuantity" cssClass="error"/>
				<td><input type="submit" value="Ok" /></td>
			</form:form>
		</div>
		<h2>Search</h2>
		<form action="productDetail" method="post">
		<table>
			<tr>
				<td>Search for Id: </td>
				<td><input type="text" name="id" value=""/></td>
				<td><input type="submit" value="Search" /></td>
			</tr>
		</table>
		</form>
		<h2>New product</h2>
		<form action="addProduct" method="post">
		<table>
			<tr>
				<td>Add new product: </td>
				<td><input type="text" name="productName" value=""/></td>
				<td name="productName.errors" id="productName.errors" class="error"></td>
				<td><input type="text" name="productQuantity" value=""/></td>
				<td name="productQuantity.errors" id="productQuantity.errors" class="error"></td>
				<td><input type="submit" value="Ok" /></td>
			</tr>
		</table>
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
	</div>
</div>
</body>	