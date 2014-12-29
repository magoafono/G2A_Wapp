/**
 * 
 */
function greekselection(){
	selection ("pericopesTextForm:greektextarea", "pericopesTextForm:greektext");
}

function arabicselection(){
	selection ("pericopesTextForm:arabictextarea", "pericopesTextForm:arabictext");
}
function selection (formTextArea, formText) {
	
	var element = document.getElementById(formTextArea);
	var value = element.value;
	var start = element.selectionStart;
	var end = element.selectionEnd;
	var select = value.substring(start,end);
	var targetElement = document.getElementById(formText);
	targetElement.value= select + " [" + start + "$" + end + ").";
	targetElement.innerHTML = select + " [" + start + "$" + end + ").";
	//alert(select);
}
