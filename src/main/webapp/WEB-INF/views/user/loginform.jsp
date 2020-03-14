<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<!-- 간소화된 부트스트랩 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>
<!-- path -> webapp 하위 디렉토리부터 시작 -->
<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>
<div class="test">
	<div class="background">
		<div class="main">
			<div class="container">
				<form class="form-signin" action="/testArtifact/user/login" method="post">
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
			<!-- /container -->
		</div>
		<!-- /main -->
	</div>
</div>
<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>
</body>
</html>