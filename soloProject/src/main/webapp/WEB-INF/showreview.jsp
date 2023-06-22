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
<script class="alert" type="text/javascript">
	function likeMsg() {
		alert("Thanks for the like!");
	}
</script>
<title>Show Review</title>
</head>
<body>
	<div class="nav d-flex justify-content-between align-items-center w-75 mx-auto mt-4">
		<h1 class="header">Hi, ${movie.lead.userName}</h1>
		<img src="/images/Reel reviews.png">
	<div>
			<a class="link-danger" href="/dashboard">Home</a> |
			<a class="link-secondary" href="/logout">Logout</a>
		</div>
	</div>
	<div class="reviewCard border border-danger">
		 <div class="card-body text-center">
		    <h3 class="card-title text-danger">${movie.reviewTitle}</h3><br>
		   <h4 class="card-subtitle mb-2"> Movie: ${movie.movieTitle}</h4>
		   <h4 class="card-subtitle mb-2"> ${movie.lead.userName}'s rating: <span class='text-warning'>${movie.rating} stars</span></h4>
		    <p class="card-text mt-3">${movie.review}</p>
		    <a href="/like/${movie.id}" class="card-link" onClick="likeMsg()"><button class="btn btn-outline-warning">Like</button></a>
	  	</div>
	</div>
</body>
</html>