function resizeText(multiplier) {

    if (document.body.style.fontSize === "") {
        document.body.style.fontSize = "1.0em";
    }
    document.body.style.fontSize = parseFloat(document.body.style.fontSize) + (multiplier * 0.2) + "em";
}

function increaseText() {

    if (document.body.style.fontSize === "") {
        document.body.style.fontSize = "1.0em";
    }
    if (parseFloat(document.body.style.fontSize) < 2) {
        document.body.style.fontSize = parseFloat(document.body.style.fontSize) + 0.2 + "em";
    }
}

function decreaseText() {

    if (document.body.style.fontSize === "") {
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
function increaseGreek() {
    var fontSize = $('.greek').css('font-size');
    if (parseFloat(fontSize) < 40)
        $(".greek").css('font-size', parseFloat(fontSize) * 1.2);
}
function decreaseGreek() {
    var fontSize = $('.greek').css('font-size');
    if (parseFloat(fontSize) > 10)
        $(".greek").css('font-size', parseFloat(fontSize) * 0.8);
}
function resetGreek() {
    $(".greek").css('font-size', "1.4em");
}

function increaseArabic() {
    var fontSize = $('.arabic').css('font-size');
    alert(parseFloat(fontSize));
    if (parseFloat(fontSize) < 40)
        $(".arabic").css('font-size', parseFloat(fontSize) * 1.2);
}
function decreaseArabic() {
    var fontSize = $('.arabic').css('font-size');
    if (parseFloat(fontSize) > 10)
        $(".arabic").css('font-size', parseFloat(fontSize) * 0.8);
}

function resetArabic() {
    $(".arabic").css('font-size', "1.5em");
}
//END NON USATE
function g2aTest2() {
    var obj = document.getElementById("parallelViewFormId:dataTableId");
    style = window.getComputedStyle(obj);
    size = style.getPropertyValue("font-size");
    obj.style.fontSize = parseFloat(size) + 0.2 + "em";
    alert("de " + obj.style.fontSize);

    //alert("(" + obj + ") (" + obj.style + ") (" + document.getElementById("parallelViewFormId:dataTableId:3:j_idt49").style+")");
    //obj.style.fontSize=parseFloat(obj.style.fontSize) + 0.2 + "em";
}

function canvasDim() {
    var height = Math.round($(document.getElementById('tabViewId:tab1')).height()) - 200; // - $('#southLayoutUnit').height();
    var width = Math.round($(document.getElementById('tabViewId:tab1')).width()) - 10; // 

    console.log("Bobbe " + height);


    document.getElementById('tabViewId:reportViewForm:canvasHeight').value = height;
    document.getElementById('tabViewId:reportViewForm:canvasWidth').value = width;
////
//    console.log("getCanvasDim() canvas heigth " + $(document.getElementById('tabViewId:reportViewForm:canvasHeight')).val());
//    console.log("getCanvasDim() canvas width  " + $(document.getElementById('tabViewId:reportViewForm:canvasWidth')).val());

}

function setCanvasHeight() {
    var height = $(document.getElementById('tabViewId:tab1')).height() - 200; // - $('#southLayoutUnit').height();
    //var height = $('#centerLayoutUnit').height(); // - $('#southLayoutUnit').height();
    console.log("setCanvasHeight() heigth canvasHeight " + height);
    $(document.getElementById('tabViewId:reportViewForm:mynetwork')).css('height', height);
    $(document.getElementById('tabViewId:reportViewForm:graphRow')).css('height', height + 100);

    // $('canvas').css('height', height);
    // alert($('canvas').height());
}

function setHeight() {
    // var height = $(document).height() - $('body').offset().top;
    var heightCenter = $('#centerLayoutUnit').height(); // - $('#southLayoutUnit').height();
    //var heightCenter = $(document.getElementById('tabViewId:tab1')).height() 
    //console.log("setHeight() centerLayout " + heightCenter);

    $(document.getElementById('tabViewId:tab1')).css('height', heightCenter - 100);
    //$(document.getElementById('tabViewId:reportViewForm:genera_pg')).css('height', height - 100);
    //var heightGraphRow = $(document.getElementById('tabViewId:tab1')).height(); // - $('#southLayoutUnit').height();
    var heightToRemove = $(document.getElementById('tabViewId:reportViewForm:global_pg')).height();
    //console.log("setHeight() heightToRemove " + heightToRemove);
    var heightGraphRow = heightCenter - 260; // - heightToRemove;
    //console.log("setHeight() graphRow " + heightGraphRow);

    //ridimensionamento della tabella dei risultati in Search
    // console.log((document.getElementById('searchResultFormId:results_dt').childNodes[2]).style.height = heightGraphRow - 120);
    if ((document.getElementById('searchResultFormId:results_dt')) !== null) {
        console.log((document.getElementById('searchResultFormId:results_dt')));
        $(document.getElementById('searchResultFormId:results_dt').childNodes[2]).height(heightGraphRow - 120);
    }
    console.log($(document.getElementById('parallelViewFormId:dataTableId').childNodes));
    $(document.getElementById('parallelViewFormId:dataTableId').childNodes[2]).height(heightCenter - 120);

    $(document.getElementById('tabViewId:reportViewForm:graphRow')).css('height', heightGraphRow);
}
;

//window.addEventListener('resize', setHeight);
//window.onresize = setHeight;
//window.onload = setHeight;
//document.change = setHeight;

function resizedw() {
    // Haven't resized in 100ms!
    //alert("after resize");
    setHeight();
}

var doit;

function resizeCanvas() {
    clearTimeout(doit);
    doit = setTimeout(setCanvasHeight(), 100);

}

window.onresize = function () {
    clearTimeout(doit);
    doit = setTimeout(resizedw, 500);
};
document.onmouseover = function () {
    clearTimeout(doit);
    doit = setTimeout(resizedw, 100);
    //  doit = setTimeout(resizeCanvas, 100);

    //alert();
};

window.onload = function () {
    clearTimeout(doit);
    doit = setTimeout(resizedw(), 100);

};
