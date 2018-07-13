ua' ban<head>
<%@ include file="/WEB-INF/decorators/include/taglibs.jsp"%>

<style>
	
	th, td { 
		width:20%;
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

		$("#productId").click(function(){
			
			$("#orderColumn").val("productId");
			$("#allProduct").submit();
		})

	})
	
// 		changeMonth: true,
// 		changeYear: true,
// 		showOn: 'button', 
// 		buttonImage: imagePath,
// 		buttonImageOnly: true
</script>
</head>
<body>
	<div>
		<div id="main">
			<div class="container">
				<h2>Table</h2>
				
				<form action="/pages/{pageNumber}" method="get" id="allProduct">
					<c:choose>
						<c:when test="${empty orderCurrentType}">
								<input type="hidden" name="orderType" id="orderType" value="${orderCurrentType}"/>
						</c:when>
						<c:otherwise>
								<input type="hidden" name="orderType" id="orderType" value="" />
						</c:otherwise>
					</c:choose>
				
					<input type="hidden" name="orderColumn" id="orderColumn" />
					<input type="hidden" name="pageNumber" id="pageNumber" />
					<table class="table">
						<thead class="thead-inverse">
							<tr>
								<th href="" name="productId" id="productId">#</th>
								<th href="">Name</th>
								<th href="">Quantity</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="productTable" items="${productTables}">
								<tr>
									<td>${productTable.productId}</td>
									<td>${productTable.productName}</td>
									<td>${productTable.productQuantity}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
								
				</form>
				
				<c:url var="firstUrl" value="/pages/1" />
				<c:url var="lastUrl" value="/pages/${productPage.totalPages}" />
				<c:url var="prevUrl" value="/pages/${currentIndex - 1}" />
				<c:url var="nextUrl" value="/pages/${currentIndex + 1}" />
	
				<div>
					<ul class="pagination">
						<c:choose>
							<c:when test="${currentIndex == 1}">
							<li class="disabled"><a href="#">&lt;&lt;</a></li>
							<li class="disabled"><a href="#">&lt;</a></li>
							</c:when>
							<c:otherwise>
							<li><a href="${firstUrl}">&lt;&lt;</a></li>
							<li><a href="${prevUrl}">&lt;</a></li>
							</c:otherwise>
						</c:choose>
						<c:forEach var="i" begin="${beginIndex}" end="${endIndex}">
							<c:url var="pageUrl" value="/pages/${i}" />
								<c:choose>
									<c:when test="${i == currentIndex}">
										<li class="active"><a href="${pageUrl}"><c:out value="${i}" /></a></li>
									</c:when>
									<c:otherwise>
										<li><a href="${pageUrl}"><c:out value="${i}" /></a></li>
									</c:otherwise>
								</c:choose>
						</c:forEach>
						<c:choose>
							<c:when test="${currentIndex == productPage.totalPages}">
							<li class="disabled"><a href="#">&gt;</a></li>
							<li class="disabled"><a href="#">&gt;&gt;</a></li>
							</c:when>
							<c:otherwise>
							<li><a href="${nextUrl}">&gt;</a></li>
							<li><a href="${lastUrl}">&gt;&gt;</a></li>
							</c:otherwise>
						</c:choose>
					</ul>
				</div>
				
				
			</div>
		</div>
	</div>
</body>	