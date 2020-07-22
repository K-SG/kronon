"use strict"

$(function () {

  /*リロード・戻るボタンが押されているか判定し、ポップアップ非表示にする*/

      $(document).ready(function(){
        let popFlag = document.getElementById('flag').value;
        if(popFlag==1){
          $('.error-popup').fadeIn();
          return;
        }
  }

/*

$(function () {

  リロード・戻るボタンが押されているか判定し、ポップアップ非表示にする
  if(window.performance){
    if(performance.navigation.type===0){
      $(document).ready(function(){
        let popFlag = document.getElementById('flag').value;
        if(popFlag==1){
          $('.error-popup').fadeIn();
          return;
        }
      });
    }
  }

  ログインボタンを押下した際のエラーチェックとポップアップ表示
  $('.user-create-button').click(function () {
    let userName = document.getElementById('userName').value;
    let mail = document.getElementById('mail').value;
    let password1 = document.getElementById('password1').value;
    let password2 = document.getElementById('password2').value;
    let checkCnt=0;
console.log("sssssss");
    if (userName==""|| mail=="" || password1=="" || password2=="") {
      popFlag='2';
      chkCnt++;
    }else if(!mail.match(/^[A-Za-z0-9]+[\w-]+@[\w\.-]+\.\w{2,}$/)){
        popFlag='3';
        chkCnt++;
    }else if(password1.length < 8 || !password1.match(/([a-z].*[A-Z])|([A-Z].*[a-z])/) || !password1.match(/([a-zA-Z])/) && password1.match(/([0-9])/)){
        popFlag='4';
        chkCnt++;
    }else if(password1!==password2){
        popFlag='5';
        chkCnt++;
    }else if(chkCnt===4){
        popFlag='6';
    }
    else{
      popFlag='0';
    }

    if(popFlag=='0'){
      $('.input_error1_msg').html('登録が完了したよ');
      $('.error-popup').fadeIn();
      $('.user-create-button').submit();
    }

    if(popFlag=='1'){
       $('.error-popup').fadeIn();
      popFlag='0';
      return;
    }

    if(popFlag=='2'){
        $('.input_error1_msg').html('入力されていない<br>項目があるよ');
        $('.error-popup').fadeIn();
        popFlag='0';
        return;
      }

    if(popFlag=='3'){
        $('.input_error1_msg').html('メールアドレスが不正だよ！');
        $('.error-popup').fadeIn();
        popFlag='0';
        return;
      }
    if(popFlag=='4'){
        $('.input_error1_msg').html('パスワードの条件を満たしていないよ！');
        $('.error-popup').fadeIn();
        popFlag='0';
        return;
      }
    if(popFlag=='5'){
        $('.input_error1_msg').html('パスワードが一致していないよ！');
        $('.error-popup').fadeIn();
        popFlag='0';
        return;
      }
    if(chkCnt==4){
        $('.confirm-popup').fadeIn();
        popFlag='0';
        return;
      }


  });

  ポップアップを閉じる際の動き
  $('.close-popup').click(function () {
    $('.confirm-popup').fadeOut();
    $('.error-popup').fadeOut();
    $('.back-popup').fadeOut();
    let password = document.getElementById('login_pass');
    password.value='';
  });

});


*/








	 //初期は非表示
	document.getElementById("emp-error").style.display = "none";
	const empError = document.getElementById("emp-error");
	 document.getElementById("mail-error").style.display = "none";
	 const mailError = document.getElementById("mail-error");
	 document.getElementById("pass-check-error").style.display = "none";
	 const passCheckError = document.getElementById("pass-check-error");
	 document.getElementById("pass-notsame-error").style.display = "none";
	 const passNotSameError = document.getElementById("pass-notsame-error");

	 document.getElementById("mail-same-error").style.display = "none";
	 const mailSameError = document.getElementById("mail-same-error");

	 document.getElementById("input-cmp").style.display = "none";
	 const inputCmp = document.getElementById("input-cmp");


	  //初期は非表示
	  document.getElementById("confirm-pop").style.display = "none";
	  const confirmPop = document.getElementById("comfirm-pop");





    function checkUserCreate() {
    	//let popFlag=0;//0confirm 1emp-error 2mail-error 3pass-checkerror 4pass-notsame-error
    	let checkCnt=0;
    	 let popFlag = document.getElementById("flag").value;
    	//空欄かどうかのチェック--------------------------------------------------------
    	const inputUserName = document.getElementById("userName");
    	const UserName = inputUserName.value;
    	const inputUserMail = document.getElementById("mail");
    	const UserMail = inputUserMail.value;
    	const inputUserPass1 = document.getElementById("password1");
    	const UserPass1 = inputUserPass1.value;
    	const inputUserPass2 = document.getElementById("password2");
    	const UserPass2 = inputUserPass2.value;



    	if(UserName=="" || UserMail=="" || UserPass1=="" || UserPass2==""){

    	  popFlag=2;
     	  // blockで表示
    	  empError.style.display = "block";
    	  return false;
    	}else {
    		checkCnt++;

    	}
    	//-------------------------------------------------------空欄かどうかのチェック↑--


    	//----メールチェック↓---------------------------------------------------------------
    	 if (!UserMail.match(/^[A-Za-z0-9]+[\w-]+@[\w\.-]+\.\w{2,}$/)){

    		popFlag=3;
	     	 // blockで表示
	    	  mailError.style.display = "block";
    		return false;
    	}else {
    		checkCnt++;

    	}

    	//--------------------------------------------------------メールチェック↑-----------

    	//----パスワード条件チェック↓---------------------------------------------------------------
    	//8字以上? // 英字の大文字と小文字含む？//英字と数字を含む？
    	 if (UserPass1.length < 8 || !UserPass1.match(/([a-z].*[A-Z])|([A-Z].*[a-z])/) || !UserPass1.match(/([a-zA-Z])/) && UserPass1.match(/([0-9])/)){
    		 popFlag=4;
    		 passCheckError.style.display = "block";
    		 return false;
    	 }else {
     		checkCnt++;
     	}
       //--------------------------------------------------------パスワード条件チェック↑-----------

       //----パスワード一致チェック↓---------------------------------------------------------------
    	 if (UserPass1!==UserPass2){
     		popFlag=5;
    		 passNotSameError.style.display = "block";
      		return false;
     	 }else {
     		checkCnt++;
     	}
       //-------------------------------------------------------パスワード一致チェック↑------------





    	 if(checkCnt===4){
    		 //window.confirm();
    		 //confirmPop.style.display = "block";
    		 $('.confirm-popup').fadeIn();
    		 return false;
    	 }
    	 if(popFlag==1){
    		 $('.confirm-popup').fadeOut();
       		 mailSameError.style.display = "block";
         		return false;
        	 }else {
        		return true;
    	 }
    	 //popFlag=0;
    	 if(popFlag==0){
    	     // $('.input_error1_msg').html('登録が完了したよ');
    	      inputCmp.style.display = "block";
    	      $('.error-popup').fadeIn();
    	      $('.user-create-button').submit();
    	 }

    }


    function checkUserCreateConfirm() {
    	let popFlag = document.getElementById("flag").value;
    	//popFlag= 1;
        //----メールアドレス重複チェック↓---------------------------------------------------------------
   	 if (popFlag==1){
   		 $('.confirm-popup').fadeOut();
   		 mailSameError.style.display = "block";
     		return false;
    	 }else {
    		return true;
    	}
      //-------------------------------------------------------メールアドレス重複チェック↑------------

    }
//confirmのok押したときのボタン
    function clickEvent() {
        document.user_create_form.submit();
    }

