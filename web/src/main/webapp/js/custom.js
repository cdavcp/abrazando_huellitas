$(document).ready(function() {
    //Custom Js Start
    'use strict';


    //Mian Featured News Slider	
    if ($(".featured-slider").length) {
        $(".featured-slider").owlCarousel({
            autoPlay: 3000,
            items: 2,
            pagination: false,
            itemsMobile: [768, 1],
            itemsDesktop: [1199, 2],
            itemsDesktopSmall: [979, 1]
        });
    }


	// News Slider	
    if ($(".full-news-slider").length) {
        $(".full-news-slider").owlCarousel({
            navigation: true, // Show next and prev buttons
            slideSpeed: 300,
            paginationSpeed: 400,
            pagination: false,
            singleItem: true
        });
    }

	// Featured News Slider
    if ($(".side-featured-slider").length) {
        $(".side-featured-slider").owlCarousel({
            navigation: true, // Show next and prev buttons
            pagination: false,
            slideSpeed: 300,
            paginationSpeed: 400,
            singleItem: true
        });
    }
	
	
			// Featured News Slider
			if ($(".cp-megamenu").length) {
			// grab the initial top offset of the navigation 
		   	var stickyNavTop = $('.cp-megamenu').offset().top;
		   	// our function that decides weather the navigation bar should have "fixed" css position or not.
		   	var stickyNav = function(){
			    var scrollTop = $(window).scrollTop(); // our current vertical position from the top
			         
			    // if we've scrolled more than the navigation, change its position to fixed to stick to top,
			    // otherwise change it back to relative
			    if (scrollTop > stickyNavTop) { 
			        $('.cp-megamenu').addClass('sticky');
			    } else {
			        $('.cp-megamenu').removeClass('sticky'); 
			    }
			};

			stickyNav();
			// and run it again every time you scroll
			$(window).scroll(function() {
				stickyNav();
			});
			}




    //Pretty Photo
    if ($(".gallery").length) {
        $("area[data-rel^='prettyPhoto']").prettyPhoto();
        $(".gallery:first a[data-rel^='prettyPhoto']").prettyPhoto({
            animation_speed: 'normal',
            theme: 'facebook',
            slideshow: 3000,
            autoplay_slideshow: false
        });
        $(".gallery:gt(0) a[data-rel^='prettyPhoto']").prettyPhoto({
            animation_speed: 'fast',
            slideshow: 10000,
            hideflash: true
        });
    }

	// Material Form 
    if ($("form.material").length) {
        $('form.material').materialForm(); // Apply material
    }
	
	// List News Slider
    if ($("#cp-list-news-slider").length) {
        $("#cp-list-news-slider").owlCarousel({
            navigation: true, // Show next and prev buttons
            slideSpeed: 300,
            paginationSpeed: 400,
            singleItem: true,
            pagination: false,
        });
    }


	// Category Slider
    if ($("#cp-cat-slider").length) {
        $("#cp-cat-slider").owlCarousel({
            navigation: true, // Show next and prev buttons
            slideSpeed: 300,
            paginationSpeed: 400,
            singleItem: true,
            pagination: false,
        });
    }


    // Footer Image Gallery
    var sync1 = $("#sync1");
    var sync2 = $("#sync2");

    sync1.owlCarousel({
        singleItem: true,
        slideSpeed: 1000,
        navigation: true,
        pagination: false,
        afterAction: syncPosition,
        responsiveRefreshRate: 200,
    });

    sync2.owlCarousel({
        items: 3,
        itemsDesktop: [1199, 10],
        itemsDesktopSmall: [979, 10],
        itemsTablet: [768, 8],
        itemsMobile: [479, 4],
        pagination: false,
        responsiveRefreshRate: 100,
        afterInit: function(el) {
            el.find(".owl-item").eq(0).addClass("synced");
        }
    });

    function syncPosition(el) {
        var current = this.currentItem;
        $("#sync2")
            .find(".owl-item")
            .removeClass("synced")
            .eq(current)
            .addClass("synced")
        if ($("#sync2").data("owlCarousel") !== undefined) {
            center(current)
        }
    }

    $("#sync2").on("click", ".owl-item", function(e) {
        e.preventDefault();
        var number = $(this).data("owlItem");
        sync1.trigger("owl.goTo", number);
    });

    function center(number) {
        var sync2visible = sync2.data("owlCarousel").owl.visibleItems;
        var num = number;
        var found = false;
        for (var i in sync2visible) {
            if (num === sync2visible[i]) {
                var found = true;
            }
        }

        if (found === false) {
            if (num > sync2visible[sync2visible.length - 1]) {
                sync2.trigger("owl.goTo", num - sync2visible.length + 2)
            } else {
                if (num - 1 === -1) {
                    num = 0;
                }
                sync2.trigger("owl.goTo", num);
            }
        } else if (num === sync2visible[sync2visible.length - 1]) {
            sync2.trigger("owl.goTo", sync2visible[1])
        } else if (num === sync2visible[0]) {
            sync2.trigger("owl.goTo", num - 1)
        }

    }

    var w = new Waves();
    w.displayEffect();


});