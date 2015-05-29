<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <title>Dashboard</title>
    <link rel="stylesheet" type="text/css" href="${context}/styles/dashStyle.css"/>
</head>
<body>
    <div id="main">
        <nav class="sidebar">
            <section class="userImage">
                <img class="userPic" src="${context}/resources/defaultPic.png"/>
            </section>
            <section class="userInfo">
                <p class="username"><a href="#">Username</a></p>
            </section>
        </nav>
        <section class="topBar">
            <div class="slateOptions">
                <p><a href="#" onclick="div_show()">Add New Slate</a></p>
                <p><a href="#">Edit Slate</a></p>
                <p><a href="#">Delete Slate</a></p>
            </div>
        </section>
        <!-- Attempt at hidden form may need to be moved. -->
        <section id="backgroundSection" class="backSection">
	        <section class="slateCreationForm" id="hiddenFormSection" onload="div_hide()">
					<form action="create" id="slateCreationForm" method="post" name="form">
						<img id="close" src="#" onclick ="div_hide()">
						<h2>Create new slate</h2>
						<hr>
						<input id="nameInput" name="name" placeholder="Name" type="text">
						<input id="dateInput" name="dueDate" placeholder="Due Date" type="text">
						<textarea id="descriptionInput" name="description" placeholder="Description"></textarea>
						<a href="javascript:%20check_empty()" id="submit">Send</a>
					</form>
			</section>
		</section>
		<section class="slateSection">
		
			<c:forEach var="slate" items="${model.getSlates()}">
				<div class="slateWrapper">
		            <p class="slateName">${ slate.getName() }</p>
		            <c:out value="${ slate.getDescription() }"/>
		            <aside>Task due: ${ slate.getDueDate().toString() }</aside>
		            <a href="update/${ slate.getId() }" >Edit Slate</a>
		            <form action="delete" method="post" name="deleteForm">
		            	<input type="hidden" name="toBeDeletedId" value="${ slate.getId() }"/>
		            	<input type="submit" value="Delete Slate"/>
		            </form>
		       </div>	
	    	</c:forEach>
	    </section>
	    
    </div>
    <script src="${context}/scripts/dashboard.js" type="text/javascript"></script>
</body>
</html>