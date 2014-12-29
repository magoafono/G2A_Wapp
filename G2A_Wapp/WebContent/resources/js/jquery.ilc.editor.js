// KMcC;) the editor 

var asutypePressed = false;
var currentEditorWidget = null;
var currentEditorIsChecking = false;
var currentEditorIsDirty = false;
var currentEditorBoldClick = null;

(function($) {
	// Define the hello button
	if ($.cleditor == undefined)
		return; // FIXME cleditor viene caricato dinamicamente solo se usato

	console.debug("CLEDITOR ATTIVATO!");

	function onGlossaryClick(e, data) {
		$(data.popup)
				.find(".editorGlossaryPopupMenu > div")
				.unbind("click")
				.bind(
						"click",
						function(e) {
							// Get the editor
							var editor = data.editor;
							// Insert some html into the document
							console.debug("*** HO: "
									+ $(this).find('span').attr('style'));
							var html = '<span class="tag '
									+ $(this).attr('rel') + '" style="'
									+ $(this).find('span').attr('style') + '">';
							html += editor.selectedHTML();
							html += '</span> ';
							editor.execCommand(data.command, html, false,
									data.button);
							// Hide the popup and set focus back to the editor
							editor.hidePopups();
							editor.focus();
						});
	}
	
	function onNotesClick(e, data) {
		$(data.popup).find(".editorNotesPopupMenu > div").unbind("click").bind(
				"click",
				function(e) {
					// Get the editor
					var editor = data.editor;
					// Insert some html into the document
					var selectedHtml = editor.selectedHTML();
					var html = '<span class="note ' + $(this).attr('rel')
							+ '">';
					html += selectedHtml ? selectedHtml : $(this).attr('rel');
					html += '</span> ';
					editor.execCommand(data.command, html, false, data.button);
					// Hide the popup and set focus back to the editor
					editor.hidePopups();
					editor.focus();
				});
	}

	function onSpecialClick(e, data) {
		$(data.popup).find(".specialMenu > option").unbind("click").bind(
				"click",
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
		$(data.popup).find(".editorCharsPopupMenu > div").unbind("click").bind(
				"click",
				function(e) {
					// Get the editor
					var editor = data.editor;
					// Insert some html into the document
					var selectedHtml = editor.selectedHTML();
					var html = selectedHtml ? selectedHtml : $(this)
							.attr('rel');
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
				asutypePressed ? "Disattiva auto marcatura del testo"
						: "Attiva auto marcatura del testo");
		editor.focus();
	}

	var popupGlossary = '<div class="editorGlossaryPopupMenu">';
	popupGlossary += '<div rel="c"><span class="noteTypePicker" style="background-color: lightgreen" />Concetto</div>';
	popupGlossary += '<div rel="e"><span class="noteTypePicker" style="background-color: yellow" />Idioma</div>';
	popupGlossary += '<div rel="l"><span class="noteTypePicker" style="background-color: orange" />Nota linguistica</div>';
	popupGlossary += '<div rel="p"><span class="noteTypePicker" style="background-color: cyan" />Persona</div>';
	popupGlossary += "</div>";

	var popupNotes = '<div class="editorNotesPopupMenu">';
	popupNotes += '<div rel="g"><span class="glossaryPicker" style="background-color: white" />Nota generica</div>';
	popupNotes += '<div rel="h"><span class="glossaryPicker" style="background-color: lightblue" />Halakha</div>';
	popupNotes += "</div>";

	var popupChars = '<div class="editorCharsPopupMenu">';
	popupChars += '<div rel="«">«</div>';
	popupChars += '<div rel="»">»</div>';
	popupChars += '<div rel="ṭ">ṭ</div>';
	popupChars += '<div rel="Ṭ">Ṭ</div>';
	popupChars += '<div rel="ʿ">ʿ</div>';
	popupChars += '<div rel="ś">ś</div>';
	popupChars += '<div rel="Ś">Ś</div>';
	popupChars += "</div>";

	var popupBibbia = '<select size = "4" class="specialMenu">';
	popupBibbia += '<option value="Gen.">Genesi</option>';
	popupBibbia += '<option value="Es.">Esodo</option>';
	popupBibbia += '<option value="Lev.">Levitico</option>';
	popupBibbia += '<option value="Num.">Numeri</option>';
	popupBibbia += '<option value="Deut.">Deuteronomio</option>';
	popupBibbia += '<option value="Gios.">Giosuè</option>';
	popupBibbia += '<option value="Giud.">Giudici</option>';
	popupBibbia += '<option value="1 Sam.">1 Samuele</option>';
	popupBibbia += '<option value="2 Sam.">2 Samuele</option>';
	popupBibbia += '<option value="1 Re">1 Re</option>';
	popupBibbia += '<option value="2 Re">2 Re</option>';
	popupBibbia += '<option value="Is.">Isaia</option>';
	popupBibbia += '<option value="Ger.">Geremia</option>';
	popupBibbia += '<option value="Ez.">Ezechiele</option>';
	popupBibbia += '<option value="Os.">Osea</option>';
	popupBibbia += '<option value="Gioe.">Gioele</option>';
	popupBibbia += '<option value="Am.">Amos</option>';
	popupBibbia += '<option value="Ov.">Ovadià</option>';
	popupBibbia += '<option value="Giona">Giona</option>';
	popupBibbia += '<option value="Mi.">Mikhà</option>';
	popupBibbia += '<option value="Na.">Nachum</option>';
	popupBibbia += '<option value="Chab.">Chabaquq</option>';
	popupBibbia += '<option value="Tze.">Tzefanyà</option>';
	popupBibbia += '<option value="Chag.">Chaggai</option>';
	popupBibbia += '<option value="Zac.">Zaccaria</option>';
	popupBibbia += '<option value="Mal.">Malachia</option>';
	popupBibbia += '<option value="Sal.">Salmi</option>';
	popupBibbia += '<option value="Pro.">Proverbi</option>';
	popupBibbia += '<option value="Giob.">Giobbe</option>';
	popupBibbia += '<option value="Can.">Cantico dei cantici</option>';
	popupBibbia += '<option value="Rut">Rut</option>';
	popupBibbia += '<option value="Ekhà">Ekhà/Lamentazioni</option>';
	popupBibbia += '<option value="Qo.">Qohelet/Ecclesiaste</option>';
	popupBibbia += '<option value="Est.">Ester</option>';
	popupBibbia += '<option value="Dan.">Daniele</option>';
	popupBibbia += '<option value="Ezra">Ezra</option>';
	popupBibbia += '<option value="Nech.">Nechemia</option>';
	popupBibbia += '<option value="1 Cr.">1 Cronache</option>';
	popupBibbia += '<option value="2 Cr.">2 Cronache</option>';
	popupBibbia += '</select>';

	var popupCodLeg = '<select size = "4" class="specialMenu">';
	popupCodLeg += "<option value=\"Maimonide, Mishnè Torà\">Maimonide, Mishnè Torà</option>";
	popupCodLeg += "<option value=\"Tur e Shulchan 'Arukh, Orach Chayim\">Tur e Shulchan 'Arukh, Orach Chayim</option>";
	popupCodLeg += "<option value=\"Tur e Shulchan 'Arukh, Yorè De'à\">Tur e Shulchan 'Arukh, Yorè De'à</option>";
	popupCodLeg += "<option value=\"Tur e Shulchan 'Arukh, Even Ha-'Ezer\">Tur e Shulchan 'Arukh, Even Ha-'Ezer</option>";
	popupCodLeg += "<option value=\"Tur e Shulchan 'Arukh, Choshen Mishpat\">Tur e Shulchan 'Arukh, Choshen Mishpat</option>";
	popupCodLeg += "</select>";

	var popupMishna = '<select size = "4" class="specialMenu">';
	popupMishna += '<option value="Mishnà, <em>‘Arakhìn</em>,">‘Arakhìn</option>';
	popupMishna += '<option value="Mishnà, <em>‘Avodà Zarà</em>,">‘Avodà Zarà</option>';
	popupMishna += '<option value="Mishnà, <em>‘Eduyòt</em>,">‘Eduyòt</option>';
	popupMishna += '<option value="Mishnà, <em>‘Eruvìn</em>,">‘Eruvìn</option>';
	popupMishna += '<option value="Mishnà, <em>‘Oqatzìn</em>,">‘Oqatzìn</option>';
	popupMishna += '<option value="Mishnà, <em>‘Orlà</em>,">‘Orlà</option>';
	popupMishna += '<option value="Mishnà, <em>Avòt</em>,">Avòt</option>';
	popupMishna += '<option value="Mishnà, <em>Bavà Batrà</em>,">Bavà Batrà</option>';
	popupMishna += '<option value="Mishnà, <em>Bavà Metzi‘à</em>,">Bavà Metzi‘à</option>';
	popupMishna += '<option value="Mishnà, <em>Bavà Qammà</em>,">Bavà Qammà</option>';
	popupMishna += '<option value="Mishnà, <em>Bekhoròt</em>,">Bekhoròt</option>';
	popupMishna += '<option value="Mishnà, <em>Berakhòt</em>,">Berakhòt</option>';
	popupMishna += '<option value="Mishnà, <em>Betzà</em>,">Betzà</option>';
	popupMishna += '<option value="Mishnà, <em>Bikkurìm</em>,">Bikkurìm</option>';
	popupMishna += '<option value="Mishnà, <em>Chaghigà</em>,">Chaghigà</option>';
	popupMishna += '<option value="Mishnà, <em>Challà</em>,">Challà</option>';
	popupMishna += '<option value="Mishnà, <em>Chullìn</em>,">Chullìn</option>';
	popupMishna += '<option value="Mishnà, <em>Demài</em>,">Demài</option>';
	popupMishna += '<option value="Mishnà, <em>Ghittìn</em>,">Ghittìn</option>';
	popupMishna += '<option value="Mishnà, <em>Horayòt</em>,">Horayòt</option>';
	popupMishna += '<option value="Mishnà, <em>Kelìm</em>,">Kelìm</option>';
	popupMishna += '<option value="Mishnà, <em>Keritòt</em>,">Keritòt</option>';
	popupMishna += '<option value="Mishnà, <em>Ketubòt</em>,">Ketubòt</option>';
	popupMishna += '<option value="Mishnà, <em>Kilàyim</em>,">Kilàyim</option>';
	popupMishna += '<option value="Mishnà, <em>Ma‘asèr Shenì</em>,">Ma‘asèr Shenì</option>';
	popupMishna += '<option value="Mishnà, <em>Ma‘aseròt</em>,">Ma‘aseròt</option>';
	popupMishna += '<option value="Mishnà, <em>Makhsirìm</em>,">Makhsirìm</option>';
	popupMishna += '<option value="Mishnà, <em>Makkòt</em>,">Makkòt</option>';
	popupMishna += '<option value="Mishnà, <em>Me‘ilà</em>,">Me‘ilà</option>';
	popupMishna += '<option value="Mishnà, <em>Meghillà</em>,">Meghillà</option>';
	popupMishna += '<option value="Mishnà, <em>Menachòt</em>,">Menachòt</option>';
	popupMishna += '<option value="Mishnà, <em>Middòt</em>,">Middòt</option>';
	popupMishna += '<option value="Mishnà, <em>Miqwa’òt</em>,">Miqwa’òt</option>';
	popupMishna += '<option value="Mishnà, <em>Mo‘èd Qatàn</em>,">Mo‘èd Qatàn</option>';
	popupMishna += '<option value="Mishnà, <em>Nazìr</em>,">Nazìr</option>';
	popupMishna += '<option value="Mishnà, <em>Nedarìm</em>,">Nedarìm</option>';
	popupMishna += '<option value="Mishnà, <em>Nega‘ìm</em>,">Nega‘ìm</option>';
	popupMishna += '<option value="Mishnà, <em>Niddà</em>,">Niddà</option>';
	popupMishna += '<option value="Mishnà, <em>Ohalòt</em>,">Ohalòt</option>';
	popupMishna += '<option value="Mishnà, <em>Parà</em>,">Parà</option>';
	popupMishna += '<option value="Mishnà, <em>Pe’à</em>,">Pe’à</option>';
	popupMishna += '<option value="Mishnà, <em>Pesachìm</em>,">Pesachìm</option>';
	popupMishna += '<option value="Mishnà, <em>Qiddushìn</em>,">Qiddushìn</option>';
	popupMishna += '<option value="Mishnà, <em>Qinnìm</em>,">Qinnìm</option>';
	popupMishna += '<option value="Mishnà, <em>Rosh ha-Shanà</em>,">Rosh ha-Shanà</option>';
	popupMishna += '<option value="Mishnà, <em>Sanhedrìn</em>,">Sanhedrìn</option>';
	popupMishna += '<option value="Mishnà, <em>Shabbàt</em>,">Shabbàt</option>';
	popupMishna += '<option value="Mishnà, <em>Sheqalìm</em>,">Sheqalìm</option>';
	popupMishna += '<option value="Mishnà, <em>Shevi‘ìt</em>,">Shevi‘ìt</option>';
	popupMishna += '<option value="Mishnà, <em>Shevu‘òt</em>,">Shevu‘òt</option>';
	popupMishna += '<option value="Mishnà, <em>Sotà</em>,">Sotà</option>';
	popupMishna += '<option value="Mishnà, <em>Sukkà</em>,">Sukkà</option>';
	popupMishna += '<option value="Mishnà, <em>Ta‘anìt</em>,">Ta‘anìt</option>';
	popupMishna += '<option value="Mishnà, <em>Tahoròt</em>,">Tahoròt</option>';
	popupMishna += '<option value="Mishnà, <em>Tamìd</em>,">Tamìd</option>';
	popupMishna += '<option value="Mishnà, <em>Temurà</em>,">Temurà</option>';
	popupMishna += '<option value="Mishnà, <em>Terumòt</em>,">Terumòt</option>';
	popupMishna += '<option value="Mishnà, <em>Tevùl Yom</em>,">Tevùl Yom</option>';
	popupMishna += '<option value="Mishnà, <em>Yadàyim</em>,">Yadàyim</option>';
	popupMishna += '<option value="Mishnà, <em>Yevamòt</em>,">Yevamòt</option>';
	popupMishna += '<option value="Mishnà, <em>Yomà</em>,">Yomà</option>';
	popupMishna += '<option value="Mishnà, <em>Zavìm</em>,">Zavìm</option>';
	popupMishna += '<option value="Mishnà, <em>Zevachìm</em>,">Zevachìm</option>';
	popupMishna += '</select>';

	var popupBavli = '<select size = "4" class="specialMenu">';
	popupBavli += '<option value="TB, <em>‘Arakhìn</em>,">‘Arakhìn</option>';
	popupBavli += '<option value="TB, <em>‘Avodà Zarà</em>,">‘Avodà Zarà</option>';
	popupBavli += '<option value="TB, <em>‘Eruvìn</em>,">‘Eruvìn</option>';
	popupBavli += '<option value="TB, <em>Bavà Batrà</em>,">Bavà Batrà</option>';
	popupBavli += '<option value="TB, <em>Bavà Metzi‘à</em>,">Bavà Metzi‘à</option>';
	popupBavli += '<option value="TB, <em>Bavà Qammà</em>,">Bavà Qammà</option>';
	popupBavli += '<option value="TB, <em>Bekhoròt</em>,">Bekhoròt</option>';
	popupBavli += '<option value="TB, <em>Berakhòt</em>,">Berakhòt</option>';
	popupBavli += '<option value="TB, <em>Betzà</em>,">Betzà</option>';
	popupBavli += '<option value="TB, <em>Chaghigà</em>,">Chaghigà</option>';
	popupBavli += '<option value="TB, <em>Chullìn</em>,">Chullìn</option>';
	popupBavli += '<option value="TB, <em>Ghittìn</em>,">Ghittìn</option>';
	popupBavli += '<option value="TB, <em>Horayòt</em>,">Horayòt</option>';
	popupBavli += '<option value="TB, <em>Keritòt</em>,">Keritòt</option>';
	popupBavli += '<option value="TB, <em>Ketubòt</em>,">Ketubòt</option>';
	popupBavli += '<option value="TB, <em>Makkòt</em>,">Makkòt</option>';
	popupBavli += '<option value="TB, <em>Me‘ilà</em>,">Me‘ilà</option>';
	popupBavli += '<option value="TB, <em>Meghillà</em>,">Meghillà</option>';
	popupBavli += '<option value="TB, <em>Menachòt</em>,">Menachòt</option>';
	popupBavli += '<option value="TB, <em>Mo‘èd Qatàn</em>,">Mo‘èd Qatàn</option>';
	popupBavli += '<option value="TB, <em>Nazìr</em>,">Nazìr</option>';
	popupBavli += '<option value="TB, <em>Nedarìm</em>,">Nedarìm</option>';
	popupBavli += '<option value="TB, <em>Niddà</em>,">Niddà</option>';
	popupBavli += '<option value="TB, <em>Pesachìm</em>,">Pesachìm</option>';
	popupBavli += '<option value="TB, <em>Qiddushìn</em>,">Qiddushìn</option>';
	popupBavli += '<option value="TB, <em>Rosh ha-Shanà</em>,">Rosh ha-Shanà</option>';
	popupBavli += '<option value="TB, <em>Sanhedrìn</em>,">Sanhedrìn</option>';
	popupBavli += '<option value="TB, <em>Shabbàt</em>,">Shabbàt</option>';
	popupBavli += '<option value="TB, <em>Shevu‘òt</em>,">Shevu‘òt</option>';
	popupBavli += '<option value="TB, <em>Sotà</em>,">Sotà</option>';
	popupBavli += '<option value="TB, <em>Sukkà</em>,">Sukkà</option>';
	popupBavli += '<option value="TB, <em>Ta‘anìt</em>,">Ta‘anìt</option>';
	popupBavli += '<option value="TB, <em>Tamìd</em>,">Tamìd</option>';
	popupBavli += '<option value="TB, <em>Temurà</em>,">Temurà</option>';
	popupBavli += '<option value="TB, <em>Yevamòt</em>,">Yevamòt</option>';
	popupBavli += '<option value="TB, <em>Yomà</em>,">Yomà</option>';
	popupBavli += '<option value="TB, <em>Zevachìm</em>,">Zevachìm</option>';
	popupBavli += '</select>';

	var popupTosefta = '<select size = "4" class="specialMenu">';
	popupTosefta += '<option value="Toseftà, <em>‘Arakhìn</em>,">‘Arakhìn</option>';
	popupTosefta += '<option value="Toseftà, <em>‘Avodà Zarà</em>,">‘Avodà Zarà</option>';
	popupTosefta += '<option value="Toseftà, <em>‘Eduyòt</em>,">‘Eduyòt</option>';
	popupTosefta += '<option value="Toseftà, <em>‘Eruvìn</em>,">‘Eruvìn</option>';
	popupTosefta += '<option value="Toseftà, <em>‘Oqatzìn</em>,">‘Oqatzìn</option>';
	popupTosefta += '<option value="Toseftà, <em>‘Orlà</em>,">‘Orlà</option>';
	popupTosefta += '<option value="Toseftà, <em>Bavà Batrà</em>,">Bavà Batrà</option>';
	popupTosefta += '<option value="Toseftà, <em>Bavà Metzi‘à</em>,">Bavà Metzi‘à</option>';
	popupTosefta += '<option value="Toseftà, <em>Bavà Qammà</em>,">Bavà Qammà</option>';
	popupTosefta += '<option value="Toseftà, <em>Bekhoròt</em>,">Bekhoròt</option>';
	popupTosefta += '<option value="Toseftà, <em>Berakhòt</em>,">Berakhòt</option>';
	popupTosefta += '<option value="Toseftà, <em>Betzà</em>,">Betzà</option>';
	popupTosefta += '<option value="Toseftà, <em>Bikkurìm</em>,">Bikkurìm</option>';
	popupTosefta += '<option value="Toseftà, <em>Chaghigà</em>,">Chaghigà</option>';
	popupTosefta += '<option value="Toseftà, <em>Challà</em>,">Challà</option>';
	popupTosefta += '<option value="Toseftà, <em>Chullìn</em>,">Chullìn</option>';
	popupTosefta += '<option value="Toseftà, <em>Demài</em>,">Demài</option>';
	popupTosefta += '<option value="Toseftà, <em>Ghittìn</em>,">Ghittìn</option>';
	popupTosefta += '<option value="Toseftà, <em>Horayòt</em>,">Horayòt</option>';
	popupTosefta += '<option value="Toseftà, <em>Kelìm</em>,">Kelìm</option>';
	popupTosefta += '<option value="Toseftà, <em>Keritòt</em>,">Keritòt</option>';
	popupTosefta += '<option value="Toseftà, <em>Ketubòt</em>,">Ketubòt</option>';
	popupTosefta += '<option value="Toseftà, <em>Kilàyim</em>,">Kilàyim</option>';
	popupTosefta += '<option value="Toseftà, <em>Ma‘asèr Shenì</em>,">Ma‘asèr Shenì</option>';
	popupTosefta += '<option value="Toseftà, <em>Ma‘aseròt</em>,">Ma‘aseròt</option>';
	popupTosefta += '<option value="Toseftà, <em>Makhsirìm</em>,">Makhsirìm</option>';
	popupTosefta += '<option value="Toseftà, <em>Makkòt</em>,">Makkòt</option>';
	popupTosefta += '<option value="Toseftà, <em>Me‘ilà</em>,">Me‘ilà</option>';
	popupTosefta += '<option value="Toseftà, <em>Meghillà</em>,">Meghillà</option>';
	popupTosefta += '<option value="Toseftà, <em>Menachòt</em>,">Menachòt</option>';
	popupTosefta += '<option value="Toseftà, <em>Miqwa’òt</em>,">Miqwa’òt</option>';
	popupTosefta += '<option value="Toseftà, <em>Mo‘èd Qatàn</em>,">Mo‘èd Qatàn</option>';
	popupTosefta += '<option value="Toseftà, <em>Nazìr</em>,">Nazìr</option>';
	popupTosefta += '<option value="Toseftà, <em>Nedarìm</em>,">Nedarìm</option>';
	popupTosefta += '<option value="Toseftà, <em>Nega‘ìm</em>,">Nega‘ìm</option>';
	popupTosefta += '<option value="Toseftà, <em>Niddà</em>,">Niddà</option>';
	popupTosefta += '<option value="Toseftà, <em>Ohalòt</em>,">Ohalòt</option>';
	popupTosefta += '<option value="Toseftà, <em>Parà</em>,">Parà</option>';
	popupTosefta += '<option value="Toseftà, <em>Pe’à</em>,">Pe’à</option>';
	popupTosefta += '<option value="Toseftà, <em>Pesachìm</em>,">Pesachìm</option>';
	popupTosefta += '<option value="Toseftà, <em>Qiddushìn</em>,">Qiddushìn</option>';
	popupTosefta += '<option value="Toseftà, <em>Rosh ha-Shanà</em>,">Rosh ha-Shanà</option>';
	popupTosefta += '<option value="Toseftà, <em>Sanhedrìn</em>,">Sanhedrìn</option>';
	popupTosefta += '<option value="Toseftà, <em>Shabbàt</em>,">Shabbàt</option>';
	popupTosefta += '<option value="Toseftà, <em>Sheqalìm</em>,">Sheqalìm</option>';
	popupTosefta += '<option value="Toseftà, <em>Shevi‘ìt</em>,">Shevi‘ìt</option>';
	popupTosefta += '<option value="Toseftà, <em>Shevu‘òt</em>,">Shevu‘òt</option>';
	popupTosefta += '<option value="Toseftà, <em>Sotà</em>,">Sotà</option>';
	popupTosefta += '<option value="Toseftà, <em>Sukkà</em>,">Sukkà</option>';
	popupTosefta += '<option value="Toseftà, <em>Ta‘anìt</em>,">Ta‘anìt</option>';
	popupTosefta += '<option value="Toseftà, <em>Tahoròt</em>,">Tahoròt</option>';
	popupTosefta += '<option value="Toseftà, <em>Temurà</em>,">Temurà</option>';
	popupTosefta += '<option value="Toseftà, <em>Terumòt</em>,">Terumòt</option>';
	popupTosefta += '<option value="Toseftà, <em>Tevùl Yom</em>,">Tevùl Yom</option>';
	popupTosefta += '<option value="Toseftà, <em>Yadàyim</em>,">Yadàyim</option>';
	popupTosefta += '<option value="Toseftà, <em>Yevamòt</em>,">Yevamòt</option>';
	popupTosefta += '<option value="Toseftà, <em>Yomà</em>,">Yomà</option>';
	popupTosefta += '<option value="Toseftà, <em>Zavìm</em>,">Zavìm</option>';
	popupTosefta += '<option value="Toseftà, <em>Zevachìm</em>,">Zevachìm</option>';
	popupTosefta += '</select>';

	var popupYeru = '<select size = "4" class="specialMenu">';
	popupYeru += '<option value="TY, <em>‘Avodà Zarà</em>,">‘Avodà Zarà</option>';
	popupYeru += '<option value="TY, <em>‘Eruvìn</em>,">‘Eruvìn </option>';
	popupYeru += '<option value="TY, <em>‘Orlà</em>,">‘Orlà </option>';
	popupYeru += '<option value="TY, <em>Bavà Batrà</em>,">Bavà Batrà</option>';
	popupYeru += '<option value="TY, <em>Bavà Metzi‘à</em>,">Bavà Metzi‘à</option>';
	popupYeru += '<option value="TY, <em>Bavà Qammà</em>,">Bavà Qammà</option>';
	popupYeru += '<option value="TY, <em>Berakhòt</em>,">Berakhòt</option>';
	popupYeru += '<option value="TY, <em>Betzà</em>,">Betzà</option>';
	popupYeru += '<option value="TY, <em>Bikkurìm</em>,">Bikkurìm</option>';
	popupYeru += '<option value="TY, <em>Chaghigà</em>,">Chaghigà</option>';
	popupYeru += '<option value="TY, <em>Challà</em>,">Challà</option>';
	popupYeru += '<option value="TY, <em>Demài</em>,">Demài</option>';
	popupYeru += '<option value="TY, <em>Ghittìn</em>,">Ghittìn</option>';
	popupYeru += '<option value="TY, <em>Horayòt</em>,">Horayòt</option>';
	popupYeru += '<option value="TY, <em>Ketubòt</em>,">Ketubòt</option>';
	popupYeru += '<option value="TY, <em>Kilàyim</em>,">Kilàyim</option>';
	popupYeru += '<option value="TY, <em>Ma‘asèr Shenì</em>,">Ma‘asèr Shenì</option>';
	popupYeru += '<option value="TY, <em>Ma‘aseròt</em>,">Ma‘aseròt</option>';
	popupYeru += '<option value="TY, <em>Makkòt</em>,">Makkòt</option>';
	popupYeru += '<option value="TY, <em>Meghillà</em>,">Meghillà</option>';
	popupYeru += '<option value="TY, <em>Mo‘èd Qatàn</em>,">Mo‘èd Qatàn</option>';
	popupYeru += '<option value="TY, <em>Nazìr</em>,">Nazìr</option>';
	popupYeru += '<option value="TY, <em>Nedarìm</em>,">Nedarìm</option>';
	popupYeru += '<option value="TY, <em>Niddà</em>,">Niddà</option>';
	popupYeru += '<option value="TY, <em>Pe’à</em>,">Pe’à</option>';
	popupYeru += '<option value="TY, <em>Pesachìm</em>,">Pesachìm</option>';
	popupYeru += '<option value="TY, <em>Qiddushìn</em>,">Qiddushìn</option>';
	popupYeru += '<option value="TY, <em>Rosh ha-Shanà</em>,">Rosh ha-Shanà</option>';
	popupYeru += '<option value="TY, <em>Sanhedrìn</em>,">Sanhedrìn</option>';
	popupYeru += '<option value="TY, <em>Shabbàt</em>,">Shabbàt</option>';
	popupYeru += '<option value="TY, <em>Sheqalìm</em>,">Sheqalìm</option>';
	popupYeru += '<option value="TY, <em>Shevi‘ìt</em>,">Shevi‘ìt</option>';
	popupYeru += '<option value="TY, <em>Shevu‘òt</em>,">Shevu‘òt</option>';
	popupYeru += '<option value="TY, <em>Sotà</em>,">Sotà</option>';
	popupYeru += '<option value="TY, <em>Sukkà</em>,">Sukkà</option>';
	popupYeru += '<option value="TY, <em>Ta‘anìt</em>,">Ta‘anìt</option>';
	popupYeru += '<option value="TY, <em>Terumòt</em>,">Terumòt</option>';
	popupYeru += '<option value="TY, <em>Yevamòt</em>,">Yevamòt</option>';
	popupYeru += '<option value="TY, <em>Yomà</em>,">Yomà</option>';
	popupYeru += '</select>';
		
	$.cleditor.buttons.glossary = {
		name : "glossary",
		image : "glossaryIcon.png",
		title : "Marcatori di glossario",
		popupName : "glossary",
		popupClass : "cleditorPrompt",
		popupContent : popupGlossary,
		command : "inserthtml",
		buttonClick : onGlossaryClick
	};
	$.cleditor.buttons.notes = {
		name : "notes",
		image : "notesIcon.png",
		title : "Note",
		popupName : "notes",
		popupClass : "cleditorPrompt",
		popupContent : popupNotes,
		command : "inserthtml",
		buttonClick : onNotesClick
	};
	$.cleditor.buttons.chars = {
		name : "chars",
		image : "specCharsIcon.png",
		title : "Caratteri Speciali",
		popupName : "chars",
		popupClass : "cleditorPrompt",
		popupContent : popupChars,
		command : "inserthtml",
		buttonClick : onCharsClick
	};
	$.cleditor.buttons.Asutype = {
		name : "Asutype",
		css : {
			'background-image' : 'url(images/asutypeIconOFF.png)'
		},
		title : "Attiva auto marcatura del testo",
		command : "inserthtml",
		popupName : "charsf",
		popupClass : "cleditorPrompt",
		popupContent : "",
		buttonClick : onAsutypeClick
	};
	$.cleditor.buttons.bibbia = {
		name : "bibbia",
		image : "BibbiaIcon.png",
		title : "Bibbia",
		popupName : "bibbia",
		popupClass : "cleditorPrompt",
		popupContent : popupBibbia,
		command : "inserthtml",
		buttonClick : onSpecialClick
	};
	$.cleditor.buttons.codleg = {
		name : "codleg",
		image : "CLIcon.png",
		title : "Codici legali",
		popupName : "codleg",
		popupClass : "cleditorPrompt",
		popupContent : popupCodLeg,
		command : "inserthtml",
		buttonClick : onSpecialClick
	};
	$.cleditor.buttons.mishna = {
		name : "mishna",
		image : "MishnaIcon.png",
		title : "Mishnà",
		popupName : "mishna",
		popupClass : "cleditorPrompt",
		popupContent : popupMishna,
		command : "inserthtml",
		buttonClick : onSpecialClick
	};
	$.cleditor.buttons.bavli = {
		name : "bavli",
		image : "TBIcon.png",
		title : "Talmud Bavli",
		popupName : "bavli",
		popupClass : "cleditorPrompt",
		popupContent : popupBavli,
		command : "inserthtml",
		buttonClick : onSpecialClick
	};
	$.cleditor.buttons.tosefta = {
		name : "tosefta",
		image : "ToseftaIcon.png",
		title : "Toseftà",
		popupName : "tosefta",
		popupClass : "cleditorPrompt",
		popupContent : popupTosefta,
		command : "inserthtml",
		buttonClick : onSpecialClick
	};
	$.cleditor.buttons.yeru = {
		name : "yeru",
		image : "TYIcon.png",
		title : "Talmud Yerushalmi",
		popupName : "yeru",
		popupClass : "cleditorPrompt",
		popupContent : popupYeru,
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
					editorWidget.editor.$frame[0].contentWindow.document)
					.find('body').html();
			currentEditorWidget.editor.execCommand('selectAll', null, null, null)
			.execCommand('insertHtml', currentHtml+"&nbsp;", null, null);
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
										currentEditorWidget.editor.execCommand('selectAll', null, null, null)
										.execCommand('insertHtml', currentHtml, null, null);
										setEditorCaretPosition(editorWidget, found);
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
	if ($.cleditor == undefined)
		return; // FIXME cleditor viene caricato dinamicamente solo se usato
	// $.cleditor.defaultOptions.useCSS = true;
	$.cleditor.defaultOptions.docCSSFile = "/jquery.cleditor.css";
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
