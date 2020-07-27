"use strict"

$(function () {

	/*DBと照合した後のエラーポップアップ表示*/
	$(document).ready(function () {
		let popFlag = document.getElementById('flag').value;

		/*登録が完了した場合*/
		  if(popFlag == 1){
		  	$('.create-msg').html('削除が完了したよ！');
		  	$('.complete-popup').fadeIn();
		  	return;
		  }
	});

	/*ポップアップを閉じる*/
	  $('.close-popup').click(function () {
      $('.confirm-popup').fadeOut();
      $('.error-popup').fadeOut();
      $('.back-popup').fadeOut();
      $('.create-popup').fadeOut();
    });
	  /*登録完了ポップアップのOKボタン押下時の遷移先*/
    $('.next-popup').click(function () {
      location.href= "calendar";
    });
});