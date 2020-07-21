"use strict"





    function checkUserCreate() {
    	let popFlag=0;//0confirm 1emp-error 2mail-error 3pass-checkerror 4pass-notsame-error
    	let checkCnt=0;
    	//console.log(checkCnt);
    	//空欄かどうかのチェック--------------------------------------------------------
    	const inputUserName = document.getElementById("userName");
    	const UserName = inputUserName.value;
    	const inputUserMail = document.getElementById("mail");
    	const UserMail = inputUserMail.value;
    	const inputUserPass1 = document.getElementById("password1");
    	const UserPass1 = inputUserPass1.value;
    	const inputUserPass2 = document.getElementById("password2");
    	const UserPass2 = inputUserPass2.value;



    	/*if(UserName=="" || UserMail=="" || UserPass1=="" || UserPass2==""){
    	  //alert("空欄があります");
    	  popFlag=1;
     	  // blockで表示
    	  empError.style.display = "block";
    	  return false;
    	}else {
    		checkCnt++;
    		//return true;
    	}
    	//-------------------------------------------------------空欄かどうかのチェック↑--
    	//console.log("1通過");

    	//----メールチェック↓---------------------------------------------------------------
    	 if (!UserMail.match(/^[A-Za-z0-9]+[\w-]+@[\w\.-]+\.\w{2,}$/)){
    		//alert("メールアドレスをご確認ください。");
    		popFlag=2;
	     	 // blockで表示
	    	  mailError.style.display = "block";
    		return false;
    	}else {
    		checkCnt++;
    		//return true;
    	}
    	// console.log("2通過");
    	//--------------------------------------------------------メールチェック↑-----------
    	// console.log(checkCnt);
    	//----パスワード条件チェック↓---------------------------------------------------------------
    	//8字以上? // 英字の大文字と小文字含む？//英字と数字を含む？
    	 if (UserPass1.length < 8 || !UserPass1.match(/([a-z].*[A-Z])|([A-Z].*[a-z])/) || !UserPass1.match(/([a-zA-Z])/) && password.match(/([0-9])/)){
    		//alert("パスワード要件をご確認ください。");
    		 popFlag=3;
    		 passCheckError.style.display = "block";
    		 return false;
    	 }else {
     		checkCnt++;
     		//return true;
     	}
       //--------------------------------------------------------パスワード条件チェック↑-----------
    	 //console.log("3通過");
    	// console.log(checkCnt);
       //----パスワード一致チェック↓---------------------------------------------------------------
    	 if (UserPass1!==UserPass2){
     		//alert("パスワードが一致してないよ。");
     		popFlag=4;
    		 passNotSameError.style.display = "block";
      		return false;
     	 }else {
     		checkCnt++;
     		//return true;
     	}
       //-------------------------------------------------------パスワード一致チェック↑------------
    	 //console.log("4通過");
    	 //console.log(checkCnt);
    	 if(checkCnt===4){
    		 popFlag=0;
    		 confirmPop.style.display = "block";
    		 //return true;
    		 //return false;
    	 }

*/




    	if(UserName=="" || UserMail=="" || UserPass1=="" || UserPass2==""){

      	  popFlag=1;

      	}else {
      		checkCnt++;

      	}
      	//-------------------------------------------------------空欄かどうかのチェック↑--

      	//----メールチェック↓---------------------------------------------------------------
      	 if (!UserMail.match(/^[A-Za-z0-9]+[\w-]+@[\w\.-]+\.\w{2,}$/)){
      		popFlag=2;

      	}else {
      		checkCnt++;

      	}
      	//--------------------------------------------------------メールチェック↑-----------
       	//----パスワード条件チェック↓---------------------------------------------------------------
      	//8字以上? // 英字の大文字と小文字含む？//英字と数字を含む？
      	 if (UserPass1.length < 8 || !UserPass1.match(/([a-z].*[A-Z])|([A-Z].*[a-z])/) || !UserPass1.match(/([a-zA-Z])/) && password.match(/([0-9])/)){

      		 popFlag=3;

      	 }else {
       		checkCnt++;
        }
         //--------------------------------------------------------パスワード条件チェック↑-----------
      	 //----パスワード一致チェック↓---------------------------------------------------------------
      	 if (UserPass1!==UserPass2){

       		popFlag=4;

       	 }else {
       		checkCnt++;

       	}
         //-------------------------------------------------------パスワード一致チェック↑------------
      	 if(checkCnt===4){
      		 popFlag=0;
      	 }


if(popFlag===0){confirmPop.style.display = "block";
return false;}
else if(popFlag===1){empError.style.display = "block";
return false;}
else if(popFlag===2){mailError.style.display = "block";
return false;}
else if(popFlag===3){passCheckError.style.display = "block";
return false;}
else if(popFlag===4){passNotSameError.style.display = "block";
return false;}







    }




     //初期表示は非表示
    document.getElementById("emp-error").style.display = "none";
    const empError = document.getElementById("emp-error");
     document.getElementById("mail-error").style.display = "none";
     const mailError = document.getElementById("mail-error");
     document.getElementById("pass-check-error").style.display = "none";
     const passCheckError = document.getElementById("pass-check-error");
     document.getElementById("pass-notsame-error").style.display = "none";
     const passNotSameError = document.getElementById("pass-notsame-error");






      //初期表示は非表示
      document.getElementById("confirm-pop").style.display = "none";
      const confirmPop = document.getElementById("comfirm-pop");





