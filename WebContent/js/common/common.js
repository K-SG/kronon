$(function() {
		  $(document).on("keypress", "input:not(.allow_submit)", function(event) {
		    return event.which !== 13;
		  });
		});
//共通パーツ
//基本的にここをさわらない
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
