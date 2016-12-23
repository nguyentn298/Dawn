<%@ include file="include/taglibs.jsp"%>
<%-- <%@ include file="/WEB-INF/decorators/include/taglibs.jsp"%> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html class="no-js" lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title><dec:title default="playground" /></title> global css goes
here...


<link href="${contextPath}/resources/css/style.css" rel="stylesheet" type="text/css" />

<script src="https://code.jquery.com/jquery-1.11.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/jquery.validation/1.15.0/jquery.validate.min.js"></script>
<script src="https://cdn.jsdelivr.net/jquery.validation/1.15.0/additional-methods.min.js"></script>
<sitemesh:write property='head' />
<!-- <link rel="stylesheet" href="https://jqueryvalidation.org/files/demo/site-demos.css"> -->
</head>
<body style="background-color:lightgray">
	<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
		<tr height="124">
			<td align="center">
				<%@ include file="include/header.jsp"%>
			</td>
		</tr>

		<tr>
			<td valign="top" align="center" colspan="2" class="">
				<table width="96%" height="95%" cellspacing="0" cellpadding="0" border="0" align="center" class="">
					<tbody>
						<tr>
							<td valign="middle">
								<sitemesh:write property='body'/>
							</td>
						</tr>
					</tbody>
				</table>
			</td>
		</tr>
		<tr>
			<td align="center"><jsp:include page="include/footer.jsp" /></td>
		</tr>
	</table>


	global javascript goes here...

</body>
</html>