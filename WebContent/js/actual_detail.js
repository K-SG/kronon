$(function () {

	/*削除完了ポップアップ表示*/
	$(document).ready(function () {
	let popFlag = document.getElementById('flag').value;

	  if(popFlag == 1){
	  	$('.complete-popup').fadeIn();
	  	return;
	  }
	});

  /*削除完了ポップアップでのOKボタン*/
  $('.next-popup').click(function () {
	    location.href= "kronon/user/actualDetail";
	  });

  $('.large-popup').click(function () {
	  $('.confirm-popup').fadeIn();
	  });






});