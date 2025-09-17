<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
		<%@ include file="/commons/manager/header.jsp"%>
	</div>
	<div>
		<sitemesh:write property="body" />
	</div>
	<div>
		<%@ include file="/commons/manager/footer.jsp"%>
	</div>
</body>
</html>