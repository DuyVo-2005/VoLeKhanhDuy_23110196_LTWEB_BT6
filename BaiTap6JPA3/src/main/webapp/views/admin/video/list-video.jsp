<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<div class="main">
	<form action="${pageContext.request.contextPath}/admin/video/home" method="get" class="form-inline mb-3">
		<input type="text" name="keyword" placeholder="Search video by user name"
			class="form-control mr-2" value="${keyword}">
		<button type="submit" class="btn btn-primary">Search</button>
	</form>

	<table border="1" cellpadding="5" cellspacing="0">
		<thead>
			<tr>
				<th>STT</th>
				<th>Username</th>
				<th>Video</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${videoList}" var="video" varStatus="STT">
				<tr>
					<td>${STT.index + 1}</td>
					<td>${video.user.userName}</td>
					<td><video width="640" height="360" controls>
							<source
								src="${pageContext.request.contextPath}/image?fname=${video.videoLink}"
								type="video/mp4">
						</video></td>
					<td><a
						href="<c:url value='/admin/video/edit?id=${video.videoId}'/>">Sửa</a>
						| <a
						href="<c:url value='/admin/video/delete?id=${video.videoId}'/>">Xóa</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<button type="button"
		onclick="location.href='<c:url value='/admin/video/add' />'"
		class="btn btn-primary">Add Video</button>
	<button type="button"
		onclick="location.href='<c:url value='/admin/category/home' />'"
		class="btn btn-primary">Tab Category</button>
	<button type="button"
		onclick="location.href='<c:url value='/admin/user/home' />'"
		class="btn btn-primary">Tab User</button>
</div>