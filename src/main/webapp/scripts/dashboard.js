/**
 * 
 */
//Validating Empty Field
Console.log("dashboard.js Loaded");

function check_empty() {
	if (document.getElementById('nameInput').value == "" || document.getElementById('dateInput').value == "" 
			|| document.getElementById('descriptionInput').value == "") {
	alert("Fill All Fields !");
	} else {
	document.getElementById('slateCreationForm').submit();
	alert("Form Submitted Successfully...");
	}
}
function check_empty_for_update() {
	if (document.getElementById('nameInput').value == "" || document.getElementById('dateInput').value == "" 
			|| document.getElementById('descriptionInput').value == "") {
	alert("Fill All Fields !");
	} else {
	document.getElementById('slateUpdateForm').submit();
	}
}
//Function To Display Popup
function div_show() {
	document.getElementById('backgroundSection').style.display = "block";
}
//Function to Hide Popup
function div_hide(){
	document.getElementById('backgroundSection').style.display = "none";
}