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
<title>Login Registration</title>
</head>
<body>
		<h1 class="header text-center mt-2">Welcome to <img class="mb-4" src="./images/Reel reviews.png"></h1>
		<hr class="hr">
	<div class="container">
		<div class="register">
			<h2 class="header">Register</h2>
			<form:form action="/register" method="POST" modelAttribute="newUser">
				<form:label path="userName">User Name:</form:label>
				<form:input class="input" path="userName"/>
				<form:errors path="userName" cssStyle="color: red;"/><br>
				<form:label path="email">Email:</form:label>
				<form:input class="input" path="email"/>
				<form:errors path="email" cssStyle="color: red;"/><br>
				<form:label path="password">Password:</form:label>
				<form:input type="password" path="password"/>
				<form:errors path="password" cssStyle="color: red;"/><br>
				<form:label path="confirm">Confirm Password:</form:label>
				<form:input type="password" path="confirm"/>
				<form:errors path="confirm" cssStyle="color: red;"/><br>
				<input class="btn btn-success" type="submit" value="Register"/>
			</form:form> 
		</div>
			<div class="login">
				<h2 class="header">Login</h2>
				<form:form action="/login" method="POST" modelAttribute="newLogin">
					<form:label path="email">Email:</form:label>
					<form:input type="input" path="email"/>
					<form:errors path="email" cssStyle="color: red;"/><br>
					<form:label path="password">Password:</form:label>
					<form:input type="password" path="password"/>
					<form:errors path="password" cssStyle="color: red;"/>
					<input class="btn btn-success" type="submit" value="Login"/>
				</form:form>
			</div>
	</div>
</body>
</html>