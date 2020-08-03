"use strict"

$(function () {

	/*DBと照合した後のエラーポップアップ表示*/
	$(document).ready(function () {
		let popFlag = document.getElementById('flag').value;
		  /*登録が完了した場合*/
		  if(popFlag == 0){
			  $('.create-msg').html('登録が完了したよ！');
			  $('.complete-popup').fadeIn();
			  return;
		  }
		  /*メールアドレスがすでに登録されていた場合*/
		  if(popFlag == 1){
			  $('.create-popup').fadeIn();
			  return;
		  }
	});

	/*新規登録ボタンを押下した際のエラーチェックとポップアップ表示*/
	$('.user-create-button').click(function () {
		let popFlag = document.getElementById('flag').value;
		let userName = document.getElementById('create_name').value;
	    let mail = document.getElementById('create_mail').value;
	    let password1 = document.getElementById('create_password1').value;
	    let password2 = document.getElementById('create_password2').value;

	    if(userName.trim() == '' || mail == '' || password1 == '' || password2 == ''){
	    	popFlag = 2;
	    }else if(!mail.match(/^[A-Za-z0-9]+[\w\-_]+@[\w\._]+\.\w{2,}$/)){
	    	popFlag = 3;
        }else if(password1.length < 8 || !(password1.match(/([a-z].*[A-Z])|([A-Z].*[a-z])/)&& password1.match(/([0-9])/)) || !(password1.match(/([a-zA-Z])/) && password1.match(/([0-9])/))){
	        popFlag = 4;
	    }else if(password1 !== password2){
	        popFlag = 5;
	    }else{
	    	popFlag = 0;
	    }
	    if(popFlag == 0){
	        /*表示名の値をセット*/
	    	let nameText = document.getElementById("confirmUserName");
			nameText.innerText = document.forms.id_create_form.create_name.value;
			/*メールの値をセット*/
			let mailText = document.getElementById("confirmMail");
			mailText.innerText = document.forms.id_create_form.create_mail.value;
			/*パスワードの値をセット*/
			let passwordText = document.getElementById("confirmPassword");
			/*パスワードの字数を取得*/
			let tmp = document.forms.id_create_form.create_password1.value.length;
			var str = '';
			for (var i = 0; i < tmp; i++) {
				str += '＊';
			}
			passwordText.innerText = str;
	        $('.confirm-popup').fadeIn();
	        $('user-create-form').submit();
	        return;
	      }
	    if(popFlag == 2){
	        $('.create-msg').html('入力されていない<br>項目があるよ！');
	        $('.create-popup').fadeIn();
	        return;
	      }
	    if(popFlag == 3){
	        $('.create-msg').html('メールアドレスが<br>不正だよ！');
	        $('.create-popup').fadeIn();
	        return;
	      }
	    if(popFlag == 4){
	        $('.create-msg').html('パスワードの条件を<br>満たしていないよ！');
	        $('.create-popup').fadeIn();
	        return;
	      }
	    if(popFlag == 5){
	        $('.create-msg').html('パスワードが<br>一致していないよ！');
	        $('.create-popup').fadeIn();
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
        location.href= "/kronon/user/calendar";
	});
	$('#submit-user').click(function(){
		$('#id_create_form').submit();
		return;
	})
});
