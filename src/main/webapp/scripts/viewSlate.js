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