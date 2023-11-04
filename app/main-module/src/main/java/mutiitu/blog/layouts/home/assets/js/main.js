/*
	Astral by HTML5 UP
	html5up.net | @ajlkn
	Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
*/

(function($) {

	var $window = $(window),
		$body = $('body'),
		$wrapper = $('#wrapper'),
		$main = $('#main'),
		$panels = $main.children('.panel'),
		$nav = $('#nav'), $nav_links = $nav.children('a');

	// Breakpoints.
		breakpoints({
			xlarge:  [ '1281px',  '1680px' ],
			large:   [ '981px',   '1280px' ],
			medium:  [ '737px',   '980px'  ],
			small:   [ '361px',   '736px'  ],
			xsmall:  [ null,      '360px'  ]
		});

	// Nav.
		$nav_links
			.on('click', function(event) {

				var href = $(this).attr('href');

				navLinkCallback(href)

				// Not a panel link? Bail.
					if (href.charAt(0) != '#'
					||	$panels.filter(href).length == 0)
						return;

				// Prevent default.
					event.preventDefault();
					event.stopPropagation();

				// Change panels.
					if (window.location.hash != href)
						window.location.hash = href;

			});

	// Panels.

		// Initialize.
			(function() {

				var $panel, $link;

				// Get panel, link.
					if (window.location.hash) {

				 		$panel = $panels.filter(window.location.hash);
						$link = $nav_links.filter('[href="' + window.location.hash + '"]');

					}

				// No panel/link? Default to first.
					if (!$panel
					||	$panel.length == 0) {

						$panel = $panels.first();
						$link = $nav_links.first();

					}

				// Deactivate all panels except this one.
					$panels.not($panel)
						.addClass('inactive')
						.hide();

				// Activate link.
					$link
						.addClass('active');

				// Reset scroll.
					$window.scrollTop(0);

			})();

		// Hashchange event.
			$window.on('hashchange', function(event) {

				var $panel, $link;

				// Get panel, link.
					if (window.location.hash) {

				 		$panel = $panels.filter(window.location.hash);
						$link = $nav_links.filter('[href="' + window.location.hash + '"]');

						// No target panel? Bail.
							if ($panel.length == 0)
								return;

					}

				// No panel/link? Default to first.
					else {

						$panel = $panels.first();
						$link = $nav_links.first();

					}

				// Deactivate all panels.
					$panels.addClass('inactive');

				// Deactivate all links.
					$nav_links.removeClass('active');

				// Activate target link.
					$link.addClass('active');

				// Set max/min height.
					$main
						.css('max-height', $main.height() + 'px')
						.css('min-height', $main.height() + 'px');

				// Delay.
					setTimeout(function() {

						// Hide all panels.
							$panels.hide();

						// Show target panel.
							$panel.show();

						// Set new max/min height.
							$main
								.css('max-height', $panel.outerHeight() + 'px')
								.css('min-height', $panel.outerHeight() + 'px');

						// Reset scroll.
							$window.scrollTop(0);

						// Delay.
							window.setTimeout(function() {

								// Activate target panel.
									$panel.removeClass('inactive');

								// Clear max/min height.
									$main
										.css('max-height', '')
										.css('min-height', '');

								// IE: Refresh.
									$window.triggerHandler('--refresh');

								// Unlock.
									locked = false;

							}, (breakpoints.active('small') ? 0 : 500));

					}, 250);

			});

	// IE: Fixes.
		if (browser.name == 'ie') {

			// Fix min-height/flexbox.
				$window.on('--refresh', function() {

					$wrapper.css('height', 'auto');

					window.setTimeout(function() {

						var h = $wrapper.height(),
							wh = $window.height();

						if (h < wh)
							$wrapper.css('height', '100vh');

					}, 0);

				});

				$window.on('resize load', function() {
					$window.triggerHandler('--refresh');
				});

			// Fix intro pic.
				$('.panel.intro').each(function() {

					var $pic = $(this).children('.pic'),
						$img = $pic.children('img');

					$pic
						.css('background-image', 'url(' + $img.attr('src') + ')')
						.css('background-size', 'cover')
						.css('background-position', 'center');

					$img
						.css('visibility', 'hidden');

				});

		}

		// ------------------------------------------------------------------------------
		// rfranr: added
		function addToOnLoadToTemplate () {
			const slides = ['ascii-art.txt', 'ascii-art-01.txt']
			let slidesCount = 0;
			$('#jumplink_01').click ( () => {
				const file = slides[(++slidesCount)%slides.length]
				$('#iframe_01').attr('src', `/layouts/home/ascii-art/public?f=${file}`);
			})
			var fragment = window.location.hash;
			navLinkCallback(fragment);
		}



		let callBackTimeout;
		function articlesCallback (href){
			if (callBackTimeout) {
				clearTimeout(callBackTimeout);
			}
			document.documentElement.style.setProperty('--color-nav-after', "#FFAF33")

			callBackTimeout = setTimeout ( () => {
				//document.body.style.backgroundColor = "cyan"
				$('nav a').each(function(index, element) {
					element.style.color = "black"
					
				});
				document.body.classList.remove('svg-1')
				document.body.classList.remove('svg-3')
				document.body.classList.add('svg-2')
			},600)
		}

		function contactCallback (href){
			if (callBackTimeout) {
				clearTimeout(callBackTimeout);
			}
			document.documentElement.style.setProperty('--color-nav-after', "blue")
			

			callBackTimeout = setTimeout ( () => {
				document.body.classList.remove('svg-1')
				document.body.classList.remove('svg-2')
				document.body.classList.add('svg-3')

			},600)
		}
		
		function defaultCallback (){
			if (callBackTimeout) {
				clearTimeout(callBackTimeout);
			}
			document.documentElement.style.setProperty('--color-nav-after', '#fff')

			callBackTimeout = setTimeout ( () => {
				$('nav a').each(function(index, element) {
					element.style.color = ''
				});
				document.body.classList.remove('svg-3')
				document.body.classList.remove('svg-2')
				document.body.classList.add('svg-1')
				document.body.style.backgroundPositionX = "0px"
			},600)
		}


		function navLinkCallback (href){
			switch ( href ) {
				case '#articles':
					articlesCallback(href);
					return;
				case '#contact':
					contactCallback(href);
					return;
				}
			defaultCallback();
		}
		// ------------------------------------------------------------------------------

	// Play initial animations on page load.
	$window.on('load', function() {
		window.setTimeout(function() {
			$body.removeClass('is-preload');
			$('body').css('display', '');
			$('body').css('background-color', '');
			addToOnLoadToTemplate();
		}, 100);
	});


})(jQuery);