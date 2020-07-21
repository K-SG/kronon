"use strict"




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


      //初期は非表示
      document.getElementById("confirm-pop").style.display = "none";
      const confirmPop = document.getElementById("comfirm-pop");



    function checkUserCreate() {
    	let popFlag=0;//0confirm 1emp-error 2mail-error 3pass-checkerror 4pass-notsame-error
    	let checkCnt=0;
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

    	 // popFlag=1;
     	  // blockで表示
    	  empError.style.display = "block";
    	  return false;
    	}else {
    		checkCnt++;

    	}
    	//-------------------------------------------------------空欄かどうかのチェック↑--


    	//----メールチェック↓---------------------------------------------------------------
    	 if (!UserMail.match(/^[A-Za-z0-9]+[\w-]+@[\w\.-]+\.\w{2,}$/)){

    		//popFlag=2;
	     	 // blockで表示
	    	  mailError.style.display = "block";
    		return false;
    	}else {
    		checkCnt++;

    	}

    	//--------------------------------------------------------メールチェック↑-----------

    	//----パスワード条件チェック↓---------------------------------------------------------------
    	//8字以上? // 英字の大文字と小文字含む？//英字と数字を含む？
    	 if (UserPass1.length < 8 || !UserPass1.match(/([a-z].*[A-Z])|([A-Z].*[a-z])/) || !UserPass1.match(/([a-zA-Z])/) && password.match(/([0-9])/)){
    		// popFlag=3;
    		 passCheckError.style.display = "block";
    		 return false;
    	 }else {
     		checkCnt++;
     	}
       //--------------------------------------------------------パスワード条件チェック↑-----------

       //----パスワード一致チェック↓---------------------------------------------------------------
    	 if (UserPass1!==UserPass2){
     		//popFlag=4;
    		 passNotSameError.style.display = "block";
      		return false;
     	 }else {
     		checkCnt++;
     	}
       //-------------------------------------------------------パスワード一致チェック↑------------





    	 if(popFlag===0 && checkCnt===4){
    		 //window.confirm();
    		 //confirmPop.style.display = "block";

    		 $('.confirm-popup').fadeIn();

    		 return false;
    	 }



         //----メールアドレス重複チェック↓---------------------------------------------------------------
    	 if (popFlag===1){
    		 mailSameError.style.display = "block";
      		return false;
     	 }else {
     		checkCnt++;
     	}
       //-------------------------------------------------------メールアドレス重複チェック↑------------



    }
