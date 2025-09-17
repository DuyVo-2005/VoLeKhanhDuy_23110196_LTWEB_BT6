<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<%-- <!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>Login</title>

<!-- Custom fonts for this template-->
<link
	href="${pageContext.request.contextPath}/templates/login/vendor/fontawesome-free/css/all.min.css"
	rel="stylesheet" type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">

<!-- Custom styles for this template-->
<link
	href="${pageContext.request.contextPath}/templates/login/css/sb-admin-2.min.css"
	rel="stylesheet">
</head>

<body class="bg-gradient-primary">

	<div class="container">

		<!-- Outer Row -->
		<div class="row justify-content-center">

			<div class="col-xl-10 col-lg-12 col-md-9">

				<div class="card o-hidden border-0 shadow-lg my-5">
					<div class="card-body p-0">
						<!-- Nested Row within Card Body -->
						<div class="row">
							<div class="col-lg-6 d-none d-lg-block bg-login-image"></div>
							<div class="col-lg-6">
								<div class="p-5">
									<div class="text-center">
										<h1 class="h4 text-gray-900 mb-4">Welcome Back!</h1>
									</div>
									<form action="${pageContext.request.contextPath}/login"
										method="post" class="user">
										<div class="form-group">
											<input type="text" class="form-control form-control-user"
												id="uname" name="uname" placeholder="Enter User Name"
												required>
										</div>
										<div class="form-group">
											<input type="password" class="form-control form-control-user"
												id="psw" name="psw" placeholder="Enter Password" required>
										</div>
										<div class="form-group">
											<div class="custom-control custom-checkbox small">
												<input type="checkbox" class="custom-control-input"
													id="remember" name="remember"> <label
													class="custom-control-label" for="remember">Remember
													Me</label>
											</div>
										</div>
										<button type="submit"
											class="btn btn-primary btn-user btn-block">Login</button>
										<hr>

									</form>
								</div>
							</div>
						</div>
					</div>
				</div>

			</div>

		</div>

	</div>

	<!-- Bootstrap core JavaScript-->
	<script
		src="${pageContext.request.contextPath}/templates/login/vendor/jquery/jquery.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/templates/login/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Core plugin JavaScript-->
	<script
		src="${pageContext.request.contextPath}/templates/login/vendor/jquery-easing/jquery.easing.min.js"></script>

	<!-- Custom scripts for all pages-->
	<script
		src="${pageContext.request.contextPath}/templates/login/js/sb-admin-2.min.js"></script>

</body>
</html>--%>

<body>
	<form action="/BaiTap6JPA3/login" method="post">
		<c:if test="${alert!=null}">
			<h3 class="alert alert-danger">${alert}</h3>
		</c:if>

		<div class="container">
			<label for="uname"> <b>Username</b> <input type="text"
				placeholder="Enter username" name="uname" required>
			</label> <label for="psw"> <b>Password</b> <input type="text"
				placeholder="Enter password" name="psw" required>
			</label>
			<button type="submit">Login</button>
			<label> <input type="checkbox" checked="checked"
				name="remember">Remember me
			</label>
		</div>
	</form>


</body>