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
        
        
        <section>
				<form action="${ context }/dashboard/update/${ model.getId() }" id="slateUpdateForm" method="post" name="form">
					<h2>Update Slate</h2>
					<hr>
					<input id="nameInput" name="name" placeholder="Name" value="${ model.getName() }"type="text">
					<input id="dateInput" name="dueDate" placeholder="Due Date" value="${ model.getDueDate().toString() }" type="text">
					<textarea id="descriptionInput" name="description" placeholder="Description">${ model.getDescription() }</textarea>
					<br/>
					<a href="javascript:%20check_empty_for_update()" id="submit">Submit Changes</a>
				</form>
		</section>
		
		
	    
    </div>
    <script src="${context}/scripts/dashboard.js" type="text/javascript"></script>
</body>
</html>