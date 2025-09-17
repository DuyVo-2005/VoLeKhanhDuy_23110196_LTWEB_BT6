<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>

<div class="main">
	<c:url value="/admin/video/edit" var="edit"></c:url>
	<form role="form" action="${edit}" method="post"
		enctype="multipart/form-data">
		<input type="hidden" name="id" value="${video.videoId }">
		<div class="form-group">
			<label>Username:</label>
			<select class="form-control" name="userId" required>
				<c:forEach var="u" items="${userList}">
					<option value="${u.userId }" <c:if test="${video.user.userName == u.userName }">selected</c:if>>
					${u.userName}</option>
				</c:forEach>
			</select>
		</div>
		<div class="form-group">
			<c:url value="/image?fname=${video.videoLink}" var="imgUrl"></c:url>
			<img class="img-responsive" width="100px" src="${imgUrl}" alt="">
			<label>Video:</label> <input type="file" name="videoLink"
				value="${video.videoLink }" />
		</div>
		<button type="submit" class="btn btn-default">Edit</button>
		<button type="reset" class="btn btn-primary">Reset</button>
	</form>
</div>