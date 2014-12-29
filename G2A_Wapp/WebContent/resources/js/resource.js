// KMcC;) the resource helper 

function showHelp() {
	alert("Sono di aiuto!");
}

function handleChangePasswordRequest(xhr, status, args) {
	if (args.validationFailed) {
		$('#changeUserPasswordDialog').effect("shake", {
			times : 3
		}, 70);
	} else {
		cpDialog.hide();
	}
}

function flashNew() {
	var colorStart = "yellow";
	var colorEnd = "white";
	$('.new').stop().animate({
		backgroundColor : colorStart
	}, 0).animate({
		backgroundColor : colorEnd
	}, 0).delay(80).animate({
		backgroundColor : colorStart
	}, 0).delay(80).animate({
		backgroundColor : colorEnd
	}, 0).delay(80).animate({
		backgroundColor : colorStart
	}, 0).delay(80).animate({
		backgroundColor : colorEnd
	}, 0).delay(80).animate({
		backgroundColor : colorStart
	}, 0).animate({
		backgroundColor : colorEnd
	}, 250);
	setTimeout(function() {
		$('.new').removeAttr('style').removeClass('new').addClass('normal');
	}, 800);
}

var wrap = function(functionToWrap, before, after, thisObject) {
	return function() {
		var args = Array.prototype.slice.call(arguments), result;
		if (before)
			before.apply(thisObject || this, args);
		result = functionToWrap.apply(thisObject || this, args);
		if (after)
			after.apply(thisObject || this, args);
		return result;
	};
};

function onKeyPress(event, inplace) {
	var key = event.keyCode ? event.keyCode : event.charCode;
	switch (key) {
	case 13:
		inplace.save();
		return false;
	case 27:
		inplace.cancel();
		return false;
	}
	return true;
};

