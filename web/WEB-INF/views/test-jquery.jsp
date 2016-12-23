<%@ include file="/WEB-INF/decorators/include/taglibs.jsp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<script src="${contextPath}/resources/js/jquery.min.js"></script>

<script>
	$(document).ready(function(){
		// Load =============================
		$("button.bt-load").click(function(){
			$(".show-load").load("${contextPath}/testAjax/pages/testLoadPage h2");
			
		});

		// Get =============================
		$("button.bt-get").click(function(){
			var pathname = window.location.pathname; // Returns path only
			var url = window.location.href;     // Returns full URL
			$.get("./pages/getForm", 
			{
				name: $(".get-name").val(),
				age: $(".get-age").val()
			}).done(function(data, status){
				$(".my-result").html(data);
				$(".my-path").html(pathname);
				$(".my-url").html(url);
			});
// 			,
// 			function(data, status){
// 				if(status == success) {
// 					$(".show-get").html(data);
// 				}
// 			})
			
		});

		// Post =============================
		$("button.bt-post").click(function(){
			$(".show-post").load("./testLoadPage h2");
			
		});

		$("button.test-location").click(function(){
			window.location = "${contextPath}/myStringAgain";
			
		});
	})
</script>
</head>
<body>
	<c:out value="test jstl 222"></c:out>
	<h2>This is Ajax with load</h2>
	<button class="bt-load">Click here to load</button>
	<div class="show-load"></div>
	<br/><br/>
	<h2>This is Ajax with get</h2>
	<button class="bt-get">Click here to get</button>
	<div class="show-get">
		Name: <input type="text" class="get-name" name="getName" /><br/>
		Age: <input type="text" class="get-age" name="getAge" /><br/>
		Result: <p class="my-result"></p><br/>
		Path only: <p class="my-path"></p><br/>
		Full url: <p class="my-url"></p>
		<button class="test-location">Move</button>
	</div>
	<br/><br/>
	<h2>This is Ajax with post</h2>
		Name: <input type="text" class="post-name" name="getName" /><br/>
		Age: <input type="text" class="post-age" name="getAge" /><br/>
		Result: <p class="my-result"></p><br/>
	<button class="bt-post">Click here to post</button>
	<div class="show-post"></div>
</body>
</html>