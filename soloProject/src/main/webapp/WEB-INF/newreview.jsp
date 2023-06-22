<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="/css/style.css"/>
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
<title>New Review</title>
</head>
<body>
	<div class="nav d-flex justify-content-between align-items-center w-75 mx-auto mt-4">
		<h1 class="header">Review a Movie</h1>
		<img src="/images/Reel reviews.png">
	<div>
			<a class="link-danger" href="/dashboard">Home</a> |
			<a class="link-secondary" href="/logout">Logout</a>
		</div>
	</div>
	<div class="reviewForm d-flex justify-content-center mt-5 mb-4">
		<form:form action="/create" method="POST" modelAttribute="newReview">
				<form:label path="movieTitle">Movie Title:</form:label>
				<form:input class="input" path="movieTitle"/><br>
				<form:errors path="movieTitle" cssStyle="color: red;"/><br>
				<form:label path="reviewTitle">Review Title:</form:label>
				<form:input class="input" path="reviewTitle"/><br>
				<form:errors path="reviewTitle" cssStyle="color: red;"/><br>
				<form:label path="date" for="dateInput">Date:</form:label>
				<form:input class="text-center" type="date" id="dateInput" path="date"/><br>
				<form:errors path="date" cssStyle="color: red;"/><br>
				<form:label path="review" for="reviewInput">Review:</form:label><br>
				<form:textarea type="textarea" id="reviewInput" path="review" rows="10" cols="50"/>
				<form:errors path="review" cssStyle="color: red;"/><br>
				<div class="rating text-center">
				<p class="mt-3">Leave a Rating:</p><form:label path="rating" for="rateInput1">1<br>&nbsp;&nbsp;
					<form:radiobutton path="rating" value="1"/>&nbsp;&nbsp;
				</form:label>
				<form:label path="rating" for="rateInput2">2<br>&nbsp;&nbsp;
					<form:radiobutton path="rating" value="2"/>&nbsp;&nbsp;
				</form:label>
				<form:label path="rating" for="rateInput3">3<br>&nbsp;&nbsp;
					<form:radiobutton path="rating" value="3"/>&nbsp;&nbsp;
				</form:label>
				<form:label path="rating" for="rateInput4">4<br>&nbsp;&nbsp;
					<form:radiobutton path="rating" value="4"/>&nbsp;&nbsp;
				</form:label>
				<form:label path="rating" for="rateInput5">5<br>&nbsp;&nbsp;
					<form:radiobutton path="rating" value="5"/>&nbsp;&nbsp;
				</form:label>
				</div>
				<br>
				<input class="btn btn-danger" type="submit" value="Submit"/>
			</form:form> 
	</div>
</body>
</html>