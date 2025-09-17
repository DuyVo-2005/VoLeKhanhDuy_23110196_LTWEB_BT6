<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<div class="main">
	<form role="form"
		action="${pageContext.request.contextPath}/admin/category/add"
		method="post" enctype="multipart/form-data">
		<div class="form-group">
			<label>Category Name:</label> <input class="form-control"
				placeholder="please enter category name" name="name" required />
		</div>
		<div class="form-group">
		<label>User Name:</label>
		<select class="form-control" name="userId"
			required>
			<option value="">-- Choose user --</option>
			<c:forEach var="u" items="${userList}">
				<option value="${u.userId }">${u.userName}</option>
			</c:forEach>
		</select>
	</div>
		<div class="form-group">
			<label>Image</label> <input type="file" name="images" />
		</div>
		<button type="submit" class="btn btn-default">Add</button>
		<button type="reset" class="btn btn-primary">Cancel</button>
	</form>
</div>