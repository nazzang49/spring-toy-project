<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<!-- 간소화된 부트스트랩 -->
<link href="assets/css/signin.css" rel="stylesheet">
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css" rel="stylesheet">
<script src="assets/js/ie-emulation-modes-warning.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="assets/js/ie10-viewport-bug-workaround.js"></script>
<meta charset="UTF-8">
<title>Login</title>

<style type="text/css">
.form-signin {
	margin-top: 100px;
}
</style>

</head>
<body>
<!-- path -> webapp 하위 디렉토리부터 시작 -->
<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>
<div class="container">
	<form class="form-signin" action="/testArtifact/user/auth" method="post">
        <h2 class="form-signin-heading">Login</h2>
		<label for="inputEmail" class="sr-only">Email</label>
			<input type="text" id="inputEmail" name="user_email" class="form-control" placeholder="test@test.com" required autofocus>
		<label for="inputPassword" class="sr-only">Password</label>
			<input type="password" id="inputPassword" name="user_password" class="form-control" placeholder="Alphabets with numbers" required>
		<div class="checkbox">
			<label>
				<input type="checkbox" value="remember-me">Remember this account
			</label>
		</div>
		<button class="btn btn-lg btn-primary btn-block" type="submit">Login</button>
	</form>
</div>
</body>
</html>