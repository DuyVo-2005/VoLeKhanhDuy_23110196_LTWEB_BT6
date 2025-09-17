<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<div class="main">
	<form role="form"
		action="${pageContext.request.contextPath}/admin/video/add"
		method="post" enctype="multipart/form-data">
		<div class="form-group">
			<label>Tên user:</label>
			<select class="form-control" name="userId" required>
				<option value="">-- Choose user --</option>
				<c:forEach var="u" items="${users}">
					<option value="${u.userId }">${u.userName}</option>
				</c:forEach>
			</select>
		</div>
		<div class="form-group">
			<label>Video:</label> <input type="file" name="video" />
		</div>
		<button type="submit" class="btn btn-default">Thêm</button>
		<button type="reset" class="btn btn-primary">Hủy</button>
	</form>
</div>