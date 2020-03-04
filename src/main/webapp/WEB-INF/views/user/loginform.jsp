<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<!-- 간소화된 부트스트랩 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>
<div class="test">
	<div class="background">
		<div class="header">
			
		</div>
		<div class="main">
			<div class="container">
				<form class="form-signin" action="/testArtifact/user/login" method="post">
					<h2 class="form-signin-heading">Login</h2>
					<label for="inputEmail" class="sr-only">Email</label>
						<input type="text" id="inputEmail" name="email" class="form-control" placeholder="test@test.com" required autofocus>
					<label for="inputPassword" class="sr-only">Password</label>
						<input type="password" id="inputPassword" name="password" class="form-control" placeholder="Alphabets with numbers" required>
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
		<div class="footer">
			
		</div>
	</div>
</div>
</body>
</html>