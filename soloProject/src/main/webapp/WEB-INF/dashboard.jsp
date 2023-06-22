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
<title>Dashboard</title>
</head>
<body class="mb-5">
	<div class="nav d-flex justify-content-between align-items-baseline w-75 mx-auto mt-4">
		<h1 class="header">Hi, ${user.userName}</h1>
		<img src="./images/Reel reviews.png">
	<div>
			<a class="link-danger" href="/new/review">Review a Movie!</a> |
			<a class="link-secondary" href="/logout">Logout</a>
		</div>
	</div>
<div class="container d-flex align-items-top">
	<div class="releasing mx-auto w-35 mt-5 text-center">
		<h2 class="h2">Movies Releasing Soon!</h2>
		<div class="movie1 mt-4">
			<img src="./images/dialofdestiny.jpeg">
			<h4 class="mt-3">Indiana Jones and the Dial of Destiny</h4>
			<p>Release Date: 6/30/2023</p>
		</div>
		<hr>
		<div class="movie2 mt-4">
			<img src="./images/midr.jpg">
			<h4 class="mt-3">Mission: Impossible - Dead Reckoning</h4>
			<p>Release Date:7/12/2023</p>
		</div>
		<hr>
		<div class="movie3 mt-4">
			<img src="./images/oppenheimer.jpg">
			<h4 class="mt-3">Oppenheimer</h4>
			<p>Release Date: 7/21/2023</p>
		</div>
	</div>
	<div class="posts w-50 mx-auto mt-5 text-center">
		<c:forEach var="movie" items="${movieReviews}">
		<a class="h4 link-primary" href="/review/${movie.id}">${movie.reviewTitle}</a>
		<p>Movie: ${movie.movieTitle}</p>
		<p>Review By: ${movie.lead.userName}</p>
		<p>Posted on:<fmt:formatDate value="${movie.date}" pattern="MM/dd/yyyy"></fmt:formatDate></p>
		 <c:if test="${movie.lead.id == userId}">
			<a href="/edit/review/${movie.id}"><button class="btn btn-outline-light text-danger fw-bold">Edit</button></a>
		 </c:if>
		<p class="mt-2 text-warning"><i class="bi bi-hand-thumbs-up-fill">&nbsp;&nbsp;${movie.userLikes.size()} likes</i></p>
		<hr>
		</c:forEach>
	</div>
</div>
</body>
</html>