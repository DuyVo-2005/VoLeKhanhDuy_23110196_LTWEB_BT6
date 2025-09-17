<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>

<div class="main">
	<c:url value="/admin/video/edit" var="edit"></c:url>
	<form role="form" action="${edit}" method="post"
		enctype="multipart/form-data">
		<input name="videoId" value="${video.videoId }" hidden="">
		<div class="form-group">
			<label>Tên user:</label>
			<select class="form-control" name="userId" required>
				<option value="${video.user.userName}"></option>
				<c:forEach var="u" items="${users}">
					<option value="${u.userId }">${u.userName}</option>
				</c:forEach>
			</select>
		</div>
		<div class="form-group">
			<c:url value="/image?fname=${video.videoLink}" var="imgUrl"></c:url>
			<img class="img-responsive" width="100px" src="${imgUrl}" alt="">
			<label>Video:</label> <input type="file" name="video"
				value="${video.videoLink }" />
		</div>
		<button type="submit" class="btn btn-default">Edit</button>
		<button type="reset" class="btn btn-primary">Reset</button>
	</form>
</div>