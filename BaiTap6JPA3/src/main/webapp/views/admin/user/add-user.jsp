<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<form role="form"
	action="${pageContext.request.contextPath}/admin/user/add"
	method="post" enctype="multipart/form-data">
	<div class="form-group">
		<label>Role name:</label>
		<select class="form-control" name="roleId"
			required>
			<option value="">-- Choose role --</option>
			<c:forEach var="r" items="${roleList}">
				<option value="${r.roleId }">${r.roleName}</option>
			</c:forEach>
		</select>
	</div>
	<div class="form-group">
		<label>User name:</label> <input class="form-control"
			placeholder="please enter user name" name="username" required />
	</div>
	<div class="form-group">
		<label>Email</label> <input class="form-control"
			placeholder="please enter email" name="email" required />
	</div>
	<div class="form-group">
		<label>Full name:</label> <input class="form-control"
			placeholder="please enter full name" name="fullname" />
	</div>
	<div class="form-group">
		<label>Image:</label> <input type="file" name="images" />
	</div>
	<div class="form-group">
		<label>Password:</label> <input class="form-control"
			placeholder="please enter password" name="password" required />
	</div>
	<div class="form-group">
		<label>Phone</label> <input class="form-control"
			placeholder="please enter phone" name="phone" />
	</div>
	<button type="submit" class="btn btn-default">Add</button>
	<button type="reset" class="btn btn-primary">Cancel</button>
</form>
