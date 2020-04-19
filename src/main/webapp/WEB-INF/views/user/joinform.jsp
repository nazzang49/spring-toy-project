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
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<meta charset="UTF-8">
<title>Join</title>

<style type="text/css">
.form-signin {
	margin-top: 100px;
}

.form-signin input[type="text"],
			 input[type="password"],
			 input[type="email"],
			 .btn,
			 .form-signin-heading {
	width: 50%;
	margin: 0 auto;
}
</style>

<!-- frontend validation -->
<script type="text/javascript">
	
	var joinResult = '${joinResult}';
	alert(joinResult);
	if(joinResult != '' && joinResult == false) {
		alert('Join rejcted. Please, try again.');
	}
	
	// email check flag
	var checkDuplicationFlag = false;

	function checkValidation() {
		if(!checkDuplicationFlag) {
			alert("You need to check duplication.");
			return false;
		}
		
		// min 6, max 20 and at least including one special character or number
		var regexForPassword = /^(?=.*[a-zA-Z])((?=.*\d)|(?=.*\W)).{6,20}$/
		var regexForPhone = /^[0-9]{2,3}-[0-9]{3,4}-[0-9]{4}$/
		var regexForAddr = /^[ㄱ-ㅎ|가-힣|0-9|\*]+$/
		
		var password = document.getElementById("inputPassword");
		var checkPassword = document.getElementById("checkPassword");
		var phone = document.getElementById("inputPhone");
		var basicAddr = document.getElementById("inputAddr1");
		var detailAddr = document.getElementById("inputAddr2");
		
		if(!validFilter(regexForPassword, password, "[password] min 6, max 20, at least 1 special character or number")) {
        	return false;
		}
		
		alert(password.value)
		alert(checkPassword.value)
		
		if(password.value != checkPassword.value) {
        	alert("[password] passwords are different each other");
           	checkPassword.value = "";
           	checkPassword.focus();
        	return false;
       	}
		
		if(!validFilter(regexForPhone, phone, "[phone] only 000-0000-0000 pattern allowed")) {
        	return false;
		}
		
		if(basicAddr.value == '' | basicAddr.value == null) {
			return false;
		}
		
		if(!validFilter(regexForAddr, detailAddr, "[detail address] only korean and numbers allowed")) {
        	return false;
		}
	}
	
	// validation by regex, test function
	function validFilter(regex, component, message) {
		if(regex.test(component.value)) {
        	return true;
       	}
       	alert(message);
       	component.value = "";
       	component.focus();
       	return false;
   	}
	
	// daum API
	function findAddress() {
		var basicAddr = document.getElementById('inputAddr1');
		new daum.Postcode({
	        oncomplete: function(data) {
				basicAddr.value = data.address
	        }
	    }).open();
	}
	
	// check duplication
	function checkDuplication() {
		var email = document.getElementById("inputEmail");
		
		if(email.value == '' | email.value == null) {
			alert("[email] email is empty");
           	email.value = "";
           	email.focus();
        	return false;
		}
		
		// GET -> @RequestParam available
		// POST -> @RequestBody available, contentType, data necessary
		$.ajax({
			url: "/testArtifact/user/checkDuplication?user_email="+email.value,
			method: "GET",
			dataType: "json",
			success: function getResult(result) {
				if(result) {
					alert("This email is duplicate.");
					email.value = "";
					email.focus();
				} else {
					var useFlag = confirm("This email is available.");
					if(useFlag) {
						email.focus();
					} else {
						email.value = "";
						email.focus();
					}
				}
			},
			error: function errorProc(request, status, error) {
				alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
			}
		})
		
		// email check completed
		checkDuplicationFlag = true;
		
		var data = {
			user_email: email.value
		}
		
// 		$.ajax({
// 			url: "/testArtifact/user/checkDuplication?user_email="+email.value,
// 			method: "POST",
// 			data: JSON.stringify(data),
// 			dataType: "json",
// 			contentType: "application/json",
// 			success: function getResult(result) {
// 				alert(result);
// 			},
// 			error: function errorProc(request, status, error) {
// 				alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
// 			}
// 		})
	}
</script>

</head>
<body>
<!-- path -> webapp 하위 디렉토리부터 시작 -->
<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>
<div class="container">
	<form class="form-signin" name="joinform" action="/testArtifact/user/join" method="post" onsubmit="return checkValidation()">
        <h2 class="form-signin-heading">Join</h2>
		<label for="inputEmail" class="sr-only">Email</label>
			<input type="email" id="inputEmail" name="user_email" class="form-control" placeholder="name@email.com" required autofocus>
			<button class="btn btn-lg btn-info btn-block" type="button" onclick="checkDuplication()">Duplication Check</button>
		<label for="inputPassword" class="sr-only">Password</label>
			<input type="password" id="inputPassword" name="user_password" class="form-control" placeholder="6-20 alphabets with one special character or number" required>
		<label for="checkPassword" class="sr-only">Check Password</label>
			<input type="password" id="checkPassword" class="form-control" placeholder="check password" required>
		<label for="inputPhone" class="sr-only">Phone</label>
			<input type="text" id="inputPhone" name="user_phone" class="form-control" placeholder="000-0000-0000" required autofocus>
		<label for="inputAddr1" class="sr-only">Basic Address</label>
			<button class="btn btn-lg btn-info btn-block" type="button" onclick="findAddress()">Find Address</button>
			<input type="text" id="inputAddr1" name="user_addr1" class="form-control" placeholder="basic address" readonly>
		<label for="inputAddr2" class="sr-only">Detail Address</label>
			<input type="text" id="inputAddr2" name="user_addr2" class="form-control" placeholder="detail address" required autofocus>
		<button class="btn btn-lg btn-primary btn-block" type="submit">Join</button>
		<button class="btn btn-lg btn-primary btn-block" type="reset">Reset</button>
	</form>
</div>
</body>
</html>