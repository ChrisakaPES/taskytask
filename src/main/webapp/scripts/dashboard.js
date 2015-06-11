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
	if (document.getElementById('nameInput').value === "" || document.getElementById('dateInput').value === "" 
			|| document.getElementById('descriptionInput').value === "") {
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
//Function To Display Popup
function div_show() {
	document.getElementById('backgroundSection').style.display = "block";
}
//Function to Hide Popup
function div_hide(){
	document.getElementById('backgroundSection').style.display = "none";
}
function div_hide(divId){
	document.getElementById(divId).style.display = "none";
}
function div_show(divId) {
	document.getElementById(divId).style.display = "block";
}

//div_hide("backgroundUpdateSection")