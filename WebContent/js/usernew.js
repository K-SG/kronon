


// JavaScript Document
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





function chkEmp(str){
    if(str==""){
        alert("必須項目を入力して下さい。");
	return false;
    }else{
	return true;
    }
}

/**
 * [関数名] chkRegEmail
 * [機　能] 正規表現によるメールアドレス（E-mail）チェック
 * [引　数]
 * @param str 入力された文字列
 * [返り値]
 * @return true(E-mail形式である場合) | false(E-mail形式でない場合)
*/
function chkRegEmail(str){
    /* E-mail形式の正規表現パターン */
    /* @が含まれていて、最後が .(ドット)でないなら正しいとする */
      var Seiki=/[!#-9A-~]+@+[a-z0-9]+.+[^.]$/i;
    /* 入力された値がパターンにマッチするか調べる */
    if(str!=""){
        if(str.match(Seiki)){
            alert(str.match(Seiki)+"\n\nメールアドレスの形式は正しいです");
            return true;
        }else{
            alert("メールアドレスの形式が不正です");
            return false;
        }
    }else{
        /* 何も入力されていない場合はアラート表示 */
        alert("メールアドレスを入力してください");
        return false;
    }
}