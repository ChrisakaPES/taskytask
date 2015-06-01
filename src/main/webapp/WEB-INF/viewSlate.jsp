<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Tasks</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/slateStyle.css">
</head>
<body>
	<main>
	<nav class="sidebar">
		<section class="userImage">
			<img class="userPic" src="assets/defaultPic.png">
		</section>
		<section class="userInfo">
			<p class="username">
				<a href="#">Username</a>
			</p>
		</section>
	</nav>
	<section class="taskSection">
		<a class="addSlateBtn" href="#">Add New Task</a>
		<p class="slateName">Tasks</p>
	</section>
	</main>
</body>
</html>
