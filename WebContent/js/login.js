$(function () {

  /*DBと照合した後のエラーポップアップ表示*/
  $(document).ready(function () {

	let popFlag = document.getElementById('flag').value;

    if(popFlag == 2){
    	$('.error-popup').fadeIn();
    	return;
    	}

	if(popFlag == 3){
		$('.login_msg').html('５人以上は<br>アカウント作れないよ<br>管理者に問い合わせて削除してもらってね！');
	  	$('.error-popup').fadeIn();
	  	return;
	  	}
	});

  /*新規登録ボタンを押下した際のユーザー数チェックとポップアップ表示*/

  $('.register-button').click(function () {
	    let preUserCount = document.getElementById('preUserCount').value;
	    if(preUserCount>=5){
	    	$('.login_msg').html('５人以上は<br>アカウント作れないよ<br>管理者に問い合わせて削除してもらってね！');
		  	$('.error-popup').fadeIn();
		  	return;
	    }
	    $('#next-page').submit();
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
      $('.login_msg').html('入力されていない<br>項目があるよ！');
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