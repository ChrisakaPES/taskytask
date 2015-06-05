/**
 * 
 */
function create_check_empty() {
	if(document.getElementById('createNameInput').value === "" || document.getElementById('createDateInput').value === ""
		|| document.getElementById('createDescriptionInput').value === "") {
		alert("Fill all of the fields!");
	} else {
		document.getElementById('taskCreationForm').submit();
		alert("Form Submitted Successfully!");
	}
}
function create_div_hide() {
	document.getElementById('backgroundCreateSection').style.display = "none";
}
function create_div_show() {
	document.getElementById('backgroundCreateSection').style.display = "block";
}

function update_check_empty() {
	if(document.getElementById('updateNameInput').value === "" || document.getElementById('updateDateInput').value === ""
		|| document.getElementById('updateDescriptionInput').value === "") {
		alert("Fill all of the fields!");
	} else {
		document.getElementById('taskUpdatingForm').submit();
		alert("Form Submitted Successfully!");
	}
}
function update_div_hide() {
	document.getElementById('backgroundUpdateSection').style.display = "none";
}
function update_div_show() {
	document.getElementById('backgroundUpdateSection').style.display = "block";
}

function div_hide(backgroundSection) {
	document.getElementById(backgroundSection).style.display = "none";

}
function div_show(backgroundSection) {
	document.getElementById(backgroundSection).style.display = "block";

}
function showUpdateWindow(taskId,taskName,taskDeadline,taskDescription) {
	document.getElementById('updateTaskId').value = taskId;
	document.getElementById('updateNameInput').value = taskName;
	document.getElementById('updateDateInput').value = taskDeadline;
	document.getElementById('updateDescriptionInput').value = taskDescription;
	div_show('backgroundUpdateSection');
}



div_hide("backgroundUpdateSection");
div_hide("backgroundCreateSection")