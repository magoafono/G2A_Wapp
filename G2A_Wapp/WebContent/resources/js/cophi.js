function resizeText(multiplier) {

	if (document.body.style.fontSize == "") {
		document.body.style.fontSize = "1.0em";
	}
	document.body.style.fontSize = parseFloat(document.body.style.fontSize) + (multiplier * 0.2) + "em";
}

function increaseText() {

	if (document.body.style.fontSize == "") {
		document.body.style.fontSize = "1.0em";
	}
	if (parseFloat(document.body.style.fontSize) < 2) {
		document.body.style.fontSize = parseFloat(document.body.style.fontSize) + 0.2 + "em";
	}
}

function decreaseText() {

	if (document.body.style.fontSize == "") {
		document.body.style.fontSize = "1.0em";
	}
	if (parseFloat(document.body.style.fontSize) > 0.5) {

		document.body.style.fontSize = parseFloat(document.body.style.fontSize) - 0.2 + "em";
	}
}

function resetSizeText(multiplier) {

	document.body.style.fontSize = "1.0em";
}

//NON USATE 
function increaseGreek () {
	var fontSize = $('.greek').css('font-size');
	if ( parseFloat(fontSize) < 40)
		$(".greek").css('font-size', parseFloat(fontSize) * 1.2);
}
function decreaseGreek () {
	var fontSize = $('.greek').css('font-size');
	if ( parseFloat(fontSize) >10)
		$(".greek").css('font-size', parseFloat(fontSize) * 0.8);
}
function resetGreek () {
	$(".greek").css('font-size', "1.4em");
}

function increaseArabic () {
	var fontSize = $('.arabic').css('font-size');
	alert(parseFloat(fontSize));
	if ( parseFloat(fontSize) < 40)
		$(".arabic").css('font-size', parseFloat(fontSize) * 1.2);
}
function decreaseArabic () {
	var fontSize = $('.arabic').css('font-size');
	if ( parseFloat(fontSize) >10)
		$(".arabic").css('font-size', parseFloat(fontSize) * 0.8);
}

function resetArabic () {
	$(".arabic").css('font-size', "1.5em");
}
//END NON USATE
function cophiTest2() {
	var obj = document.getElementById("parallelViewFormId:dataTableId");
	style = window.getComputedStyle(obj);
	size = style.getPropertyValue("font-size");
	obj.style.fontSize = parseFloat(size) + 0.2 + "em";
	alert("de " + obj.style.fontSize);

	//alert("(" + obj + ") (" + obj.style + ") (" + document.getElementById("parallelViewFormId:dataTableId:3:j_idt49").style+")");
	//obj.style.fontSize=parseFloat(obj.style.fontSize) + 0.2 + "em";
}