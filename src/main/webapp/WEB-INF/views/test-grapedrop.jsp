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
<title>test-grapedrop</title>

<link rel="stylesheet" href="http://localhost:8080/testArtifact/assets/css/test-grapedrop.css" type="text/css"/>
<link rel="stylesheet" href="http://localhost:8080/testArtifact/assets/css/test-base.css" type="text/css"/>

</head>
<body>

	<h1 id="i2cn">Insert your header text here
</h1>
<div class="gdp-row" id="i1kj">
  <div class="cell" id="i69j">
  </div>
  <div class="cell" id="inak">
    <div id="ipsi">
      <form method="post" data-redirect="" class="form" id="iamu">
        <div class="form-group">
          <label class="label">Name</label>
          <input placeholder="Type here your name" name="firstname" class="input" id="i5vau"/>
        </div>
        <div class="form-group">
          <label class="label">Email</label>
          <input type="email" placeholder="Type here your email" name="email" class="input"/>
        </div>
        <div class="form-group">
          <label class="label">Options</label>
          <select name="options" class="select"><option value="1">Option 1</option><option value="2">Option 2</option><option value="3">Option 3</option></select>
        </div>
        <div class="form-group">
          <label class="label">Gender</label>
          <input type="radio" name="gender" value="M" class="radio"/>
          <label class="radio-label">Male</label>
          <input type="radio" name="gender" value="F" class="radio"/>
          <label class="radio-label">Female</label>
        </div>
        <div class="form-group">
          <label class="label">Message</label>
          <textarea name="message" class="textarea"></textarea>
        </div>
        <div class="form-group">
          <button type="submit" class="button">Send</button>
        </div>
        <div data-form-state="success" class="state-success" id="iezsv">Thanks! We received your request
        </div>
        <div data-form-state="error" class="state-error" id="i6i3f">An error occurred on processing your request, try again!
        </div>
      </form>
    </div>
  </div>
  <div class="cell" id="iln3">
  </div>
</div>
</body>
</html>