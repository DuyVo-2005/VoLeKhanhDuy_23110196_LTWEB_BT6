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
	<table border="1" cellpadding="5" cellspacing="0">
		<thead>
			<tr>
				<th>STT</th>
				<th>User Name</th>
				<th>Role Name</th>
				<th>Email</th>
				<th>Full name</th>
				<th>Image</th>
				<th>Password</th>
				<th>Phone</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${userList}" var="user" varStatus="STT">
				<tr>
					<td>${STT.index + 1}</td>
					<td>${user.userName}</td>
					<td>${user.roleName}</td>
					<td>${user.email}</td>
					<td>${user.fullname}</td>
					<c:url value='/image' var="imgUrl">
						<c:param name="fname" value="${user.imageLink}" />
						<c:param name="t" value="${System.currentTimeMillis()}" />
					</c:url>
					<td><img height="150" width="200" src="${imgUrl}" /></td>
					<td>${user.password}</td>
					<td>${user.phone}</td>
					<td><a href="<c:url value='/admin/user/edit?id=${cate.userId}'/>">Sửa</a>
						| <a href="<c:url value='/admin/usery/delete?id=${cate.userId}'/>">Xóa</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<button type="button"
		onclick="location.href='<c:url value='/admin/user/add' />'"
		class="btn btn-primary">Thêm</button>
</body>
</html>