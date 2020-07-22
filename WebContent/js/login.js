$(function () {

  /*DBと照合した後のエラーポップアップ表示*/
  $(document).ready(function () {

	let popFlag = document.getElementById('flag').value;

    if(popFlag == 2){
    	$('.error-popup').fadeIn();
    	return;
    	}
    });

  /*ログインボタンを押下した際の空白エラーチェックとポップアップ表示*/
  $('.login-button').click(function () {

    let mail = document.getElementById('login_mail').value;
    let password = document.getElementById('login_pass').value;

    if(mail == '' || password == ''){
      popFlag = 1;
    }else{
      popFlag = 0;
    }

    if(popFlag == 0){
      $('.login-form').submit();
    }

    if(popFlag == 1){
      $('.login_msg').html('入力されていない<br>項目があるよ');
      $('.error-popup').fadeIn();
      popFlag = 0;
      return;
    }
  });

  /*ポップアップを閉じる際の動き*/
  $('.close-popup').click(function () {
    $('.error-popup').fadeOut();
    let password = document.getElementById('login_pass');
    password.value = '';
  });

});