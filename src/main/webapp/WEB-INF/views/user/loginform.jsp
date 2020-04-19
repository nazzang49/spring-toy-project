<%@page import="java.math.BigInteger"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="java.security.SecureRandom"%>
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

.form-signin input[type="text"],
			 input[type="password"],
			 button[type="submit"],
			 .checkbox,
			 .form-signin-heading {
	width: 50%;
	margin: 0 auto;
}

</style>

</head>
<body>


<%
	// for naver login
	String clientId = "qDHIWeRV3CiLwZE9XJ7p";
    String redirectURI = URLEncoder.encode("http://localhost:8080/testArtifact/index", "UTF-8");
    SecureRandom random = new SecureRandom();
    String state = new BigInteger(130, random).toString();
    
    String naverLoginApiUrl = "https://nid.naver.com/oauth2.0/authorize?response_type=code";
    naverLoginApiUrl += "&client_id="+clientId;
    naverLoginApiUrl += "&redirect_uri="+redirectURI;
    naverLoginApiUrl += "&state="+state;
    session.setAttribute("state", state);
%>

<!-- path -> webapp 하위 디렉토리부터 시작 -->
<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>
<div class="container">
	<form class="form-signin" action="/testArtifact/user/auth" method="post">
        <h2 class="form-signin-heading">Login</h2>
		<label for="inputEmail" class="sr-only">Email</label>
			<input type="text" id="inputEmail" name="user_email" class="form-control" placeholder="name@email.com" required autofocus>
		<label for="inputPassword" class="sr-only">Password</label>
			<input type="password" id="inputPassword" name="user_password" class="form-control" placeholder="6-20 alphabets with one special character or number" required>
		<div class="checkbox">
			<label>
				<input type="checkbox" value="remember-me">Remember this account
			</label>
		</div>
		<button class="btn btn-lg btn-primary btn-block" type="submit">Login</button>
		<div style="text-align: center; margin: 20px;">
			<a href="<%=naverLoginApiUrl %>">
				<img alt="naverLoginLogo" src="/testArtifact/assets/img/naver-login-logo.PNG" style="width: 150; height: 50px;">
			</a>
		</div>
	</form>
</div>
</body>
</html>