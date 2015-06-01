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
		<section id="backgroundCreateSection" class="backSection">
			<section class="taskCreationForm" id="hiddenCreateFormSection" onload="create_div_hide()">
				<form action="create/task" id="taskCreationForm" method="post" name="createForm">
					<img id="closeCreate" src="${pageContext.request.ContextPath }/resources/closeReg.png" onclick="create_div_hide()" />
					<h2>Create new task</h2>
					<hr>
					<input id="nameInput" name="name" placeholder="Name" type="text">
					<input id="dateInput" name="dueDate" placeholder="Due Date" type="datetime-local">
					<textarea id="descriptionInput" name="description" placeholder="Description"></textarea>
					<a href="javascript:%20check_empty()" id="submit">Create</a>
				</form>
			</section>
		</section>
		<a class="addSlateBtn" href="#" onclick="create_div_show()">Add New Task</a>
		<p class="slateName">Tasks</p>
		
	</section>
	</main>
</body>
</html>
