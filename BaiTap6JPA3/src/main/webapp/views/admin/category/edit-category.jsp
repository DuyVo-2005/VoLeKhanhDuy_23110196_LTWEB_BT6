<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:url value="/admin/user/edit" var="edit"></c:url>
	<form role="form" action="${edit}" method="post"
		enctype="multipart/form-data">
		<input name="userid" value="${user.userId }" hidden="">
		<div class="form-group">
			<label>Username:</label> <input type="text" class="form-control"
				value="${user.userName }" name="username" required"/>
		</div>
		<div class="form-group">
			<label>Role Name:</label> <input type="text" class="form-control"
				value="${user.role.roleName }" name="roleName" required/>
		</div>
		<div class="form-group">
			<label>Email:</label> <input type="text" class="form-control"
				value="${user.email }" name="email" required/>
		</div>
		<div class="form-group">
			<label>Fullname:</label> <input type="text" class="form-control"
				value="${user.fullname }" name="fullname" />
		</div>
		<div class="form-group">
			<c:url value="/image?fname=${user.imageLink}" var="imgUrl"></c:url>
			<img class="img-responsive" width="100px" src="${imgUrl}" alt="">
			<label>Ảnh đại diện</label> <input type="file" name="image"
				value="${category.images }" />
		</div>
		<div class="form-group">
			<label>Password:</label> <input type="text" class="form-control"
				value="${user.password }" name="paswword" required/>
		</div>
		<div class="form-group">
			<label>Phone:</label> <input type="text" class="form-control"
				value="${user.phone }" name="phone" />
		</div>
		<button type="submit" class="btn btn-default">Edit</button>
		<button type="reset" class="btn btn-primary">Reset</button>
	</form>
</body>
</html>