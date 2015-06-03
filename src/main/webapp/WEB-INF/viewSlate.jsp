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
				<form action="<c:url value="/slate/${ slateId }/create/task"></c:url>" id="taskCreationForm" method="post" name="createForm">
					<img id="closeCreate" src="${pageContext.request.contextPath }/resources/closeReg.png" onclick="create_div_hide()" />
					<h2>Create new task</h2>
					<hr>
					<input id="createSlateId" name="slateId" value="${ slateId }" type="hidden">
					<input id="createNameInput" name="name" placeholder="Name" type="text">
					<input id="createDateInput" name="dueDate" placeholder="Due Date" type="datetime-local">
					<textarea id="createDescriptionInput" name="description" placeholder="Description"></textarea>
					<a href="javascript:%20create_check_empty()" id="submit">Create</a>
				</form>
			</section>
		</section>
		<section id="backgroundUpdateSection" class="backSection">
			<section class="taskUpdatingForm" id="hiddenUpdateFormSection" onload="update_div_hide()">
				<form action="<c:url value="/slate/${ slateId }/update/task"></c:url>" id="taskUpdatingForm" method="post" name="updateForm">
					<img id="closeUpdate" src="${pageContext.request.contextPath }/resources/closeReg.png" onclick="update_div_hide()" />
					<h2>Update task</h2>
					<hr>
					<input id="updateSlateId" name="slateId" value="${ slateId }" type="hidden">
					<input id="updateNameInput" name="name" placeholder="Name" type="text">
					<input id="updateDateInput" name="dueDate" placeholder="Due Date" type="datetime-local">
					<textarea id="updateDescriptionInput" name="description" placeholder="Description"></textarea>
					<a href="javascript:%20update_check_empty()" id="submit">Update</a>
				</form>
			</section>
		</section>
		<a class="addSlateBtn" href="#" onclick="create_div_show()">Add New Task</a>
		<p class="slateName">Tasks</p>
		<c:forEach var="task" items="${ model.getTasks() }">
			<div class="taskWrapper">
				<p class="taskName">${ task.getTask_name() }</p>
				<c:out value="${ task.getTask_description() }"></c:out>
				<aside>Task Due: ${ task.getDeadline() }</aside>
				<!-- Link or Button to call popup updating form Should go here. -->
				<!-- Link or Button to delete task should go here. -->
				<form action="<c:url value="/slate/${ slateId }/delete/task"></c:url>" method="post">
					<input type="hidden" name="toBeDeletedId" value="${ task.getTask_id() }" />
					<input type="submit" value="Delete Task" />
				</form>
				
				
			</div>
		</c:forEach>
		
	</section>
	</main>
	<script type="text/javascript" src="<c:url value="/scripts/viewSlate.js"></c:url>"></script>
</body>
</html>
