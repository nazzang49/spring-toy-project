<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>This is header-template page.</title>
</head>
<body>

	<nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">Digging Club</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
          <ul class="nav navbar-nav">
          	<!-- isAuthenticated = true -> can see this list -->
          	
            <li class="active"><a href="/testArtifact/index">Home</a></li>
            <li><a href="#about">Board</a></li>
            <sec:authorize access="isAuthenticated()">
            	<li><a href="/testArtifact/user/mypage">MyPage</a></li>
            	<li><a href="/testArtifact/user/logout">Logout</a></li>
            </sec:authorize>
            <sec:authorize access="!isAuthenticated()">
            	<li><a href="#contact">Login</a></li>
            	<li><a href="#contact">Join</a></li>
            </sec:authorize>
            <li><a href="#contact">Notice</a></li>
          </ul>
        </div>
      </div>
    </nav>

</body>
</html>