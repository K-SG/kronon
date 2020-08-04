$(function () {
	$('.small-popup-button').click(function () {
		$('.error-popup').fadeIn();
	});

	$('.large-popup-button').click(function () {
		$('.confirm-popup').fadeIn();
    });

	$('.back-popup-button').click(function () {
		$('.back-popup').fadeIn();
	});
	// 閉じるボタン
	$('.close-popup').click(function () {
		$('.confirm-popup').fadeOut();
		$('.error-popup').fadeOut();
		$('.back-popup').fadeOut();
	})

});
