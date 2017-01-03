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
<script>
	$(document).ready(function(){
		var date_input=$('input[name="productDateUpdated"]'); //our date input has the name "date"
// 		var container=$('.container form').length>0 ? $('.bootstrap-iso form').parent() : "body";
		var options={
		format: 'mm/dd/yyyy',
// 		format: 'yyyy-mm-dd hh:mm',
// 		dateFormat: 'yy-mm-dd',
// 		container: container,
		todayHighlight: true,
		autoclose: true,
// 		startDate: '-3d'
		};
		date_input.datepicker(options);
	})
</script>
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
			<div class="container">
				<h2>div 5 Spring Form</h2>
				<div class="col-md-6 col-sm-6 col-xs-12">
					<form:form method="post" modelAttribute="product" action="addProduct">
						<label>Name: </label>
						<form:input path="productName" type="text" />
						<form:errors path="productName" cssClass="error"/>
						
						<label>Quantity: </label>
						<form:input path="productQuantity" type="text" />
						<form:errors path="productQuantity" cssClass="error"/>
						
<!-- 						<label>Date: </label> -->
<%-- 						<form:input path="productDateUpdated" /> --%>
<%-- 						<form:errors path="productDateUpdated" cssClass="error"/> --%>
						
<!-- 						<input type="submit" value="Ok" /> -->
						
						<div class="form-group"> <!-- Date input -->
							<label class="control-label" for="productDateUpdated">Date</label>
							<form:input class="form-control" path="productDateUpdated" type="text"/>
							<form:errors path="productDateUpdated" cssClass="error"/>
						</div>
						<div class="form-group"> <!-- Submit button -->
							<button class="btn btn-primary " type="submit">Submit</button>
     					 </div>
					</form:form>
				</div>
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