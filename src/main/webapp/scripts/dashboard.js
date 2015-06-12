/**
 * 
 */
//Validating Empty Field
var Console = Console;
Console.log("dashboard.js Loaded");

function check_empty() {
	if (document.getElementById('nameInput').value === "" || document.getElementById('dateInput').value === "" 
			|| document.getElementById('descriptionInput').value === "") {
	alert("Fill All Fields !");
	} else {
	document.getElementById('slateCreationForm').submit();
	alert("Form Submitted Successfully...");
	}
}
function check_empty_for_update() {
	if (document.getElementById('updateNameInput').value === "" || document.getElementById('updateDateInput').value === "" 
			|| document.getElementById('updateDescriptionInput').value === "") {
	alert("Fill All Fields !");
	} else {
	document.getElementById('slateUpdateForm').submit();
	}
}
function showUpdateWindow(slateId,slateName,slateDeadline,slateDescription) {
	document.getElementById('updateSlateId').value = slateId;
	document.getElementById('updateNameInput').value = slateName;
	document.getElementById('updateDateInput').value = slateDeadline;
	document.getElementById('updateDescriptionInput').value = slateDescription;
	div_show('backgroundUpdateSection');
}
function div_hide(divId){
	document.getElementById(divId).style.display = "none";
}
function div_show(divId) {
	document.getElementById(divId).style.display = "block";
}
function compareDate(dueDate, slateId) {
	var currentDate = new Date();
	Console.log(dueDate);
	//var deadline = new Date(dueDate);
	document.getElementById("slate" + slateId).innerHtml = "affectedByJS";
	
}

//div_hide("backgroundUpdateSection")