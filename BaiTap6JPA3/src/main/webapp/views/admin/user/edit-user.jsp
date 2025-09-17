<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>

<div class="main">
	<c:url value="/admin/user/edit" var="edit"></c:url>
	<form role="form" action="${edit}" method="post"
		enctype="multipart/form-data">
		<input name="id" value="${user.userId }" hidden="">
		<div class="form-group">
			<label>Username:</label>
			<input type="text" class="form-control"
				value="${user.userName }" name="username" required/>
		</div>
		<div class="form-group">
			<label>Role Name:</label>
			<select class="form-control" name="roleId" required>
				<c:forEach var="r" items="${roleList}">
					<option value="${r.roleId}" <c:if test="${user.role.roleId == r.roleId }">selected</c:if>>
						${r.roleName}
					</option>
				</c:forEach>
			</select>
		</div>
		<div class="form-group">
			<label>Email:</label> <input type="text" class="form-control"
				value="${user.email }" name="email" required />
		</div>
		<div class="form-group">
		
			<label>Fullname:</label> <input type="text" class="form-control"
				value="${user.fullname }" name="fullname" />
		</div>
		<div class="form-group">
			<c:url value="/image?fname=${user.imageLink}" var="imgUrl"></c:url>
			<img class="img-responsive" width="100px" src="${imgUrl}" alt="">
			<label>User Image</label> <input type="file" name="image"
				value="${user.imageLink }" />
		</div>
		<div class="form-group">
			<label>Password:</label> <input type="text" class="form-control"
				value="${user.password }" name="password" required />
		</div>
		<div class="form-group">
			<label>Phone:</label> <input type="text" class="form-control"
				value="${user.phone }" name="phone" />
		</div>
		<button type="submit" class="btn btn-default">Edit</button>
		<button type="reset" class="btn btn-primary">Reset</button>
	</form>
</div>