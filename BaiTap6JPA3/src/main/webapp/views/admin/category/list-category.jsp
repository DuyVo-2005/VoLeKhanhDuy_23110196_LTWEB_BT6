<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<div class="main">
	<table border="1" cellpadding="5" cellspacing="0">
		<thead>
			<tr>
				<th>STT</th>
				<th>Ảnh</th>
				<th>Tên danh mục</th>
				<th>Hành động</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${cateList}" var="cate" varStatus="STT">
				<tr>
					<td>${STT.index + 1}</td>
					<c:url value='/image' var="imgUrl">
						<c:param name="fname" value="${cate.images}" />
						<c:param name="t" value="${System.currentTimeMillis()}" />
					</c:url>
					<td><img height="150" width="200" src="${imgUrl}" /></td>
					<td>${cate.categoryName}</td>
					<td><a
						href="<c:url value='/admin/category/edit?id=${cate.categoryId}'/>">Sửa</a>
						| <a
						href="<c:url value='/admin/category/delete?id=${cate.categoryId}'/>">Xóa</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<button type="button"
		onclick="location.href='<c:url value='/admin/category/add' />'"
		class="btn btn-primary">Thêm</button>
	<button type="button"
		onclick="location.href='<c:url value='/admin/video/home' />'"
		class="btn btn-primary">Tab Video</button>
	<button type="button"
		onclick="location.href='<c:url value='/admin/user/home' />'"
		class="btn btn-primary">Tab User</button>
</div>