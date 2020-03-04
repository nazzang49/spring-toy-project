<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>팀원 목록 호출</title>
<style type="text/css">
#teammate-list {
	border: 1px solid;
}
</style>
</head>
<body>

	<h1>This is get-teammate-list page for spring project.</h1>
	
	<table id="teammate-list">
	<thead>
	<tr>
		<th>groupware_id</th>
		<th>groupware_password</th>
		<th>phone_number</th>
		<th>main_role</th>
		<th>present_task</th>
	</tr>
	</thead>
	<tbody>
		<c:forEach var="teammate" items="${teammateList }">
			<tr>
				<td>${teammate.groupware_id }</td>
				<td>${teammate.groupware_password }</td>
				<td>${teammate.phone_number }</td>
				<td>${teammate.main_role }</td>
				<td>${teammate.present_task }</td>
			</tr>
		</c:forEach>
	</tbody>
	</table>
</body>
</html>