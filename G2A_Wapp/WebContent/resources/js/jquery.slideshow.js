// (C) 2012 Enrico KiccoMcCotten;) Carniani
//-----------------------------------------
// built on jquery 1.7.2
// uses jquery.imgpreload.min.js 
//
// Gallery plugin for jquery

(function($) {
	$.fn.slideShow = function(loaderSelector, slideWidth) {
		var me = this;
		$(document)
				.ready(
						function() {
							var currentPosition = 0;
							var slides = me.find('.slide');
							var numberOfSlides = slides.length;
							// manageControls: Hides and shows controls
							// depending on currentPosition
							function manageControls(position) {
								// Hide left arrow if position is first slide
								if (position == 0)
									$('#leftControl').hide();
								else
									$('#leftControl').show();
								// Hide right arrow if position is last slide
								if (position == numberOfSlides - 1)
									$('#rightControl').hide();
								else
									$('#rightControl').show();
							}
							// Remove scrollbar in JS
							me.find('#slidesContainer').css('overflow',
									'hidden');
							// Wrap all .slides with #slideInner div
							slides.wrapAll('<div id="slideInner"></div>')
							// Float left to display horizontally, readjust
							// .slides width
							.css({
								'float' : 'left',
								'width' : slideWidth
							});
							// Set #slideInner width equal to total width of all
							// slides
							$('#slideInner').css('width',
									slideWidth * numberOfSlides);
							// Insert left and right arrow controls in the DOM
							me
									.prepend(
											'<div class="control" id="leftControl">&lt;</div>')
									.append(
											'<div class="control" id="rightControl">&gt;</div>');
							// Hide left arrow control on first load
							manageControls(currentPosition);
							// Create event listeners for .controls clicks
							$('.control')
									.bind(
											'click',
											function() {
												// Determine new position
												currentPosition = ($(this)
														.attr('id') == 'rightControl') ? currentPosition + 1
														: currentPosition - 1;
												// Hide / show controls
												manageControls(currentPosition);
												// Move slideInner using
												// margin-left
												$('#slideInner')
														.animate(
																{
																	'marginLeft' : slideWidth
																			* (-currentPosition)
																});
											});
							// loading ...
							if (loaderSelector === undefined)
								;
							else {
								$(loaderSelector).fadeIn(0);
								me.find('img').imgpreload(function() {
									$(loaderSelector).fadeOut(300);
								});
							}
						});
	};
})(jQuery);