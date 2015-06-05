<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<html>

<head>
<title>Server Error</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/styles/errorStyle.css">
</head>

<body>
	<main class="serverError">
	<div class="error">
		<img src="${pageContext.request.contextPath}/resources/explode.gif">
		<p>The Server has encountered an internal error.</p>
		<p>We are attempting to resolve the problem.</p>
	</div>
	</main>
</body>

</html>
