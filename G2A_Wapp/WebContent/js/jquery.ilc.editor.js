// KMcC;) the editor 

var asutypePressed = false;
var currentEditorWidget = null;
var currentEditorIsChecking = false;
var currentEditorIsDirty = false;
var currentEditorBoldClick = null;

(function($) {
	// cleditor may be loaded dynamically only when used.
	if ($.cleditor == undefined)
		return;

	function onAddClick(e, data) {
			// Get the editor
			var editor = data.editor;
			// Insert some html into the document
			var html = '<span class="add start"></span>';
			var selectedHtml = editor.selectedHTML();
			if (selectedHtml)
				html += editor.selectedHTML();
			html += '<span class="add end"></span>';
			var marked = editor.selectedHTML(); 
			console.debug("MARKED: "+marked);
			editor.execCommand(data.command, html, false, data.button);
			editor.updateTextArea();
			//var stringToBeSent =  editor.$area.val();
			var stringToBeSent = html;
			console.debug("html " + stringToBeSent);
			// Hide the popup and set focus back to the editor
			$.ajax({
				async : false,
				dataType : "json",
				url : "/G2A_Wapp/editor/add.json",
				data : {html: stringToBeSent},
				cache : false
			}).done(function ( data ) {
				console.debug("done " + data);
				editor.$area.val(data);
				editor.updateFrame();
			});
			editor.focus();
	}


	function onMenuClick(selector, data, addText) {
		$(data.popup).find(selector).unbind("click").click(function(e) {
			// Get the editor
			var editor = data.editor;
			// Insert some html into the document
			var html = '<span class="' + $(this).attr('rel') + '">';
			var selectedHtml = editor.selectedHTML();
			if (selectedHtml)
				html += editor.selectedHTML();
			else if (addText)
				html += $(this).attr('rel').split(" ")[1];
			html += '</span> ';
			editor.execCommand(data.command, html, false, data.button);
			// Hide the popup and set focus back to the editor
			editor.hidePopups();
			editor.focus();
		});
	}

	function onGlossaryClick(e, data) {
		onMenuClick(".editorGlossaryPopupMenu > div", data, false);
	}

	function onNotesClick(e, data) {
		onMenuClick(".editorNotesPopupMenu > div", data, true);
	}

	function onSpecialClick(e, data) {
		$(data.popup).find(".specialMenu > option").unbind("click").click(
				function(e) {
					// Get the editor
					var editor = data.editor;
					// Insert some html into the document
					var selectedHtml = editor.selectedHTML();
					var html = selectedHtml ? selectedHtml : $(this).attr(
							'value');
					editor.execCommand(data.command, html + ' ', false,
							data.button);
					// Hide the popup and set focus back to the editor
					editor.hidePopups();
					editor.focus();
				});
	}

	function onCharsClick(e, data) {
		$(data.popup).find(".editorCharsPopupMenu > div").unbind("click")
				.click(function(e) {
					// Get the editor
					var editor = data.editor;
					// Insert some html into the document
					var selectedHtml = editor.selectedHTML();
					var html = selectedHtml ? selectedHtml : $(this).html();
					editor.execCommand(data.command, html, false, data.button);
					// Hide the popup and set focus back to the editor
					editor.hidePopups();
					editor.focus();
				});
	}

	// funzione chiamata quando si preme il tasto attiva/disattiva AS_U_TYPE
	function onAsutypeClick(e, data) {
		var editor = data.editor;
		editor.execCommand(data.command, " ", false, data.button);
		asutypePressed = !asutypePressed;
		asutypePressed ? $(data.button).css('background-image',
				'url(images/asutypeIconON.png)') : $(data.button).css(
				'background-image', 'url(images/asutypeIconOFF.png)');
		$(data.button).attr(
				"title",
				asutypePressed ? "Disattiva marcatura automatica del testo"
						: "Attiva marcatura automatica del testo");
		editor.focus();
	}

	var popupQuotes = {};

	// Configure editor by its preferences (custom buttons, menus, etc)
	function setup(json) {
		function buildMenu(klass, l, typeKlass, innerKlass) {
			var rv = '<div class="' + klass + '">';
			for ( var index in l) {
				var e = l[index];
				rv += '<div rel="' + typeKlass + " " + e.styleClassName
						+ '"><span ';
				if (innerKlass != undefined)
					rv += 'class="' + innerKlass + '" ';
				rv += 'style="background-color: ' + e.color + '" />'
						+ e.titleHtml + '</div>';
			}
			rv += "</div>";
			return rv;
		}
		function buildQuoteMenu(klass, l) {
			var rv = '<select size="5" class="' + klass + '">';
			for ( var index in l) {
				var e = l[index];
				rv += '<option value="' + e.descriptionHtml + '">'
						+ e.titleHtml + '</option>';
			}
			rv += "</select>";
			return rv;
		}
		$.cleditor.buttons.glossary = {
			name : "glossary",
			image : "glossaryIcon.png",
			title : "Marcatori di glossario",
			popupName : "glossary",
			popupClass : "cleditorPrompt",
			popupContent : buildMenu("editorGlossaryPopupMenu",
					json.references, 'reference', 'referencePicker'),
			command : "inserthtml",
			buttonClick : onGlossaryClick
		};
		$.cleditor.buttons.notes = {
			name : "notes",
			image : "notesIcon.png",
			title : "Note",
			popupName : "notes",
			popupClass : "cleditorPrompt",
			popupContent : buildMenu("editorNotesPopupMenu", json.notes,
					'note', 'notePicker'),
			command : "inserthtml",
			buttonClick : onNotesClick
		};

		$.cleditor.buttons.chars = {
			name : "chars",
			image : "specCharsIcon.png",
			title : "Caratteri Speciali",
			popupName : "chars",
			popupClass : "cleditorPrompt",
			popupContent : buildMenu("editorCharsPopupMenu", json.chars, ''),
			command : "inserthtml",
			buttonClick : onCharsClick
		};

		for ( var index in json.quotes)
			popupQuotes[index] = buildQuoteMenu("specialMenu",
					json.quotes[index]);
	}

	$.ajax({
		async : false,
		dataType : "json",
		url : "/Cophi_Wapp/editor/setup.json",
		success : setup
	});

	$.cleditor.buttons.add = {
			name : "add",
			css : {
				'background-image' : 'url(images/asutypeIconOFF.png)'
			},
			title : "Aggiungi token alla pericope",
			command : "inserthtml",
			popupName : "baiade",
			popupClass : "cleditorPrompt",
			popupContent : "",
			buttonClick : onAddClick
		};
	
	$.cleditor.buttons.Asutype = {
			name : "Asutype",
			css : {
				'background-image' : 'url(images/asutypeIconOFF.png)'
			},
			title : "Attiva marcatura automatica del testo",
			command : "inserthtml",
			popupName : "charsf",
			popupClass : "cleditorPrompt",
			popupContent : "",
			buttonClick : onAsutypeClick
		};

	$.cleditor.buttons.codleg = {
		name : "codleg",
		image : "CLIcon.png",
		title : "Codici legali",
		popupName : "codleg",
		popupClass : "cleditorPrompt",
		popupContent : popupQuotes[0],
		command : "inserthtml",
		buttonClick : onSpecialClick
	};
	$.cleditor.buttons.bibbia = {
		name : "bibbia",
		image : "BibbiaIcon.png",
		title : "Bibbia",
		popupName : "bibbia",
		popupClass : "cleditorPrompt",
		popupContent : popupQuotes[1],
		command : "inserthtml",
		buttonClick : onSpecialClick
	};
	$.cleditor.buttons.mishna = {
		name : "mishna",
		image : "MishnaIcon.png",
		title : "Mishnà",
		popupName : "mishna",
		popupClass : "cleditorPrompt",
		popupContent : popupQuotes[2],
		command : "inserthtml",
		buttonClick : onSpecialClick
	};
	$.cleditor.buttons.bavli = {
		name : "bavli",
		image : "TBIcon.png",
		title : "Talmud Bavli",
		popupName : "bavli",
		popupClass : "cleditorPrompt",
		popupContent : popupQuotes[3],
		command : "inserthtml",
		buttonClick : onSpecialClick
	};
	$.cleditor.buttons.tosefta = {
		name : "tosefta",
		image : "ToseftaIcon.png",
		title : "Toseftà",
		popupName : "tosefta",
		popupClass : "cleditorPrompt",
		popupContent : popupQuotes[4],
		command : "inserthtml",
		buttonClick : onSpecialClick
	};
	$.cleditor.buttons.yeru = {
		name : "yeru",
		image : "TYIcon.png",
		title : "Talmud Yerushalmi",
		popupName : "yeru",
		popupClass : "cleditorPrompt",
		popupContent : popupQuotes[5],
		command : "inserthtml",
		buttonClick : onSpecialClick
	};

	// Add the button to the default controls
	var controls = $.cleditor.defaultOptions.controls;
	controls += " | glossary notes | Asutype | chars | codleg bibbia mishna bavli tosefta yeru ";
	$.cleditor.defaultOptions.controls = controls;
})(jQuery);

function isControlCode(code) {
	return code >= 33 && code <= 47;
}

function isWhiteSpace(aChar) {
	return " !\t\r\n\"()=?'^[]{}*+@#,.;:|\\".indexOf(aChar) >= 0;
}

function _enableRtl(editorWidget) {
	console.debug("Attivo rtl");
	$(editorWidget.editor.$frame[0].contentWindow.document).find('body').css(
			'direction', 'rtl');
}

function enableRtl(editorWidget, timeout) {
	console.debug("renderizzo l'editor");
	if (!editorWidget.render()) {
		console.debug("Attendo un po' " + timeout);
		setTimeout(function() {
			enableRtl(editorWidget, 50);
		}, timeout);
	} else {
		_enableRtl(editorWidget);
	}
}

function installAsYouType(editorWidget, remoteCommand, timeout, rtl) {
	console.debug("renderizzo l'editor");
	if (!editorWidget.render()) {
		// if (editorWidget.editor == undefined || editorWidget.editor.$frame ==
		// undefined) {
		console.debug("Attendo un po' " + timeout);
		setTimeout(function() {
			installAsYouType(editorWidget, remoteCommand, 50, rtl);
		}, timeout);
	} else {
		currentEditorWidget = editorWidget;
		console.debug("sto installando keypress su frame: "
				+ editorWidget.editor.$frame);
		asutypePressed = false;
		// console.debug("Inoltre ho un editorWidget = " + editorWidget);
		// console.debug("Pertanto, il mio editor e': " + editorWidget.editor);
		if (editorWidget.editor.$frame[0].contentWindow) {
			// XXX Patch for ticket #52
			var currentHtml = $(
					editorWidget.editor.$frame[0].contentWindow.document).find(
					'body').html();
			currentEditorWidget.editor.execCommand('selectAll', null, null,
					null).execCommand('insertHtml', currentHtml + "&nbsp;",
					null, null);
			// XXX End of Patch for ticket #52
			editorWidget.editor.focus();
			if (rtl)
				_enableRtl(editorWidget);
			$(editorWidget.editor.$frame[0].contentWindow.document)
					// XXX Patch for ticket #50
					.bind(
							"keyup",
							function(event) {
								if (!asutypePressed) {
									var currentHtml = $(
											editorWidget.editor.$frame[0].contentWindow.document)
											.find('body').html();
									var found = currentHtml
											.search(/<span [^<]*>\ *<\/span>/);
									if (found >= 0) {
										currentHtml = currentHtml.replace(
												/<span [^<]*>\ *<\/span>/g, "");
										currentEditorWidget.editor
												.execCommand('selectAll', null,
														null, null)
												.execCommand('insertHtml',
														currentHtml, null, null);
										setEditorCaretPosition(editorWidget,
												found);
									}
								}
							})
					// XXX End of Patch for ticket #50
					.bind(
							"keypress",
							function(event) {
								if (asutypePressed) {
									var charCode = event.keyCode ? event.keyCode
											: event.charCode;
									key = String.fromCharCode(charCode);
									if (!currentEditorIsChecking) {
										if (!isControlCode(charCode)
												&& isWhiteSpace(key)) {
											currentEditorWidget = editorWidget;
											editorWidget.editor
													.updateTextArea();
											currentEditorIsChecking = true;
											remoteCommand([ {
												name : 'description',
												value : editorWidget.editor.$area
														.val()
														+ key
											} ]);
										}
									} else
										currentEditorIsDirty = currentEditorIsDirty
												|| !isWhiteSpace(key);
								} else {
								}
							});
		}
	}
}

function setEditorCaretPosition(editorWidget, s) {
	console.debug("setEditorCaretPosition: " + s);
	var elem = editorWidget.editor.$frame[0];
	elem.focus();
	if (elem.selectionStart) {
		elem.selectionStart = s;
		elem.selectionEnd = s;
	} else if (document.selection) {
		var range = elem.createTextRange();
		range.collapse(true);
		range.moveStart('character', s);
		range.moveEnd('character', 0);
		range.select();
	}
}

function onEditorComplete(inplaceElement, editorWidget, remoteCommand, rtl) {
	console.debug("onEditorComplete: " + inplaceElement[0]);
	var events = $._data(inplaceElement[0], 'events');
	// check if already installed
	if (events != undefined && events.click != undefined) {
		console.debug("CLICK ALREADY INSTALLED!");
	} else {
		inplaceElement.click(function() {
			inplaceElement.unbind("click");
			installAsYouType(editorWidget, remoteCommand, 250, rtl);
		});
		editorUnescape(inplaceElement);
	}
}

function editorUnescape(startNode) {
	var editedTexts = startNode.find(".ui-inplace-display");
	editedTexts.each(function(index) {
		$(this).html($(this).text());
	});
	editedTexts = startNode.find(".ui-inplace-display-disabled");
	console.debug("EDITORUNESCAPE: " + editedTexts);
	editedTexts.each(function(index) {
		$(this).html($(this).text());
	});
}

function handleAsYouType(xhr, status, args) {
	var description = B64.decode(args.autotagResults);
	if (!currentEditorIsDirty) {
		currentEditorWidget.editor.execCommand('selectAll', null, null, null)
				.execCommand('insertHtml', description + "&nbsp;", null, null);
	}
	currentEditorIsChecking = false;
	currentEditorIsDirty = false;
}

function onEditorReady(inplaceId, editorWidget, doAsYouType, rtl) {
	var inplaceElement = document.getElementById(inplaceId);
	if (inplaceElement == null) {
		console.debug("inplaceElement non ancora pronto, attendo");
		setTimeout(function() {
			onEditorReady(inplaceId, editorWidget, doAsYouType);
		}, 50);
	} else {
		onEditorComplete($(inplaceElement), editorWidget, doAsYouType, rtl);
	}
}

$(document).ready(function() {
	// cleditor is loaded dynamically only if used in the page
	if ($.cleditor == undefined)
		return;
	// $.cleditor.defaultOptions.useCSS = true;
	$.cleditor.defaultOptions.docCSSFile = "/talmud/jquery.cleditor.css";
	// Patch names
	var buttons = {
		bold : 'Grassetto (traduzione letterale)',
		italic : 'Corsivo (trascrizione letterale)',
		removeformat : 'Pulisci marcature del testo selezionato',
		undo : 'Annulla modifica',
		redo : 'Ripeti modifica',
		cut : 'Taglia',
		copy : 'Copia',
		paste : 'Incolla',
		pastetext : 'Incolla come testo semplice'
	};
	for ( var buttonName in buttons) {
		$.cleditor.buttons[buttonName].title = buttons[buttonName];
	}

});
