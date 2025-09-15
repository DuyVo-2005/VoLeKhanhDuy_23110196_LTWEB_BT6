<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đăng nhập</title>
</head>
<body>
	<form action="/BaiTap6JPA3/login" method="post">
		<c:if test="${alert!=null}">
			<h3 class="alert alert-danger">${alert}</h3>
		</c:if>
		
		<div class="container">
			<label for="uname">
				<b>Username</b>
				<input type="text" placeholder="Enter username" name="uname" required>
			</label>
			<label for="psw"> 
				<b>Password</b>
				<input type="text" placeholder="Enter password" name="psw" required>
			</label>
			<button type="submit">Login</button>
			<label>
				<input type="checkbox" checked="checked" name="remember">Remember me		
			</label>
		</div>
	</form>
</body>
</html>