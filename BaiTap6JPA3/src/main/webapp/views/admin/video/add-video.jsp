<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<form role="form"
	action="${pageContext.request.contextPath}/admin/video/add"
	method="post" enctype="multipart/form-data">
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
		<label>Video:</label> <input type="file" name="vdo" />
	</div>
	<button type="submit" class="btn btn-default">Add</button>
	<button type="reset" class="btn btn-primary">Cancel</button>
</form>