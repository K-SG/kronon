$(function () {

  /*内容確認ポップアップでのOKボタン*/
  $('.next-button').click(function () {
	  $('.complete-popup').fadeIn();
      return;
  });

  /*削除完了ポップアップでのOKボタン*/
  $('.next-popup').click(function () {
	    location.href= "kronon/user/actualDetail";
	  });


});