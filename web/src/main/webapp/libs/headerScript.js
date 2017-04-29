setTimeout(onHeaderTemplateLoaded, 300);

function onHeaderTemplateLoaded() {
	$('.show-sidebar').on('click', function (e) {
		e.preventDefault();
		$('div#main').toggleClass('sidebar-show');
	});
	$('.dropdown-toggle').dropdown();
	$('#top-panel').on('click','a', function(e){
		if ($(this).hasClass('ajax-link')) {
			//e.preventDefault();
			if ($(this).hasClass('add-full')) {
				$('#content').addClass('full-content');
			} else {
				$('#content').removeClass('full-content');
			}
		}
	});
};