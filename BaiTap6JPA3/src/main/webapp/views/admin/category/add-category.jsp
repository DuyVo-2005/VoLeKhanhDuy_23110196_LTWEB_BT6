<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Category</title>
</head>
<body>
<form role="form" action="${pageContext.request.contextPath}/admin/category/add" method="post" enctype="multipart/form-data">
		<div class="form-group">
			<label>Tên danh mục:</label> <input class="form-control" placeholder="please enter category name" name="name" required/>
		</div>
		<div class="form-group">
			<label>Ảnh đại diện</label> <input type="file" name="images" />
		</div>
		<button type="submit" class="btn btn-default">Thêm</button>
		<button type="reset" class="btn btn-primary">Hủy</button>
	</form>
</body>
</html>