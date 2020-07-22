$(function () {

  /*リロード・戻るボタンが押されているか判定し、ポップアップ非表示にする*/
  if(window.performance){
    if(performance.navigation.type===0){
      $(document).ready(function(){
        let popFlag = document.getElementById('flag').value;
        if(popFlag==2){
          $('.error-popup').fadeIn();
          return;
        }
      });
    }
  }

  /*登録ボタンを押した際のエラーチェックとポップアップ表示*/
//  document.getElementById("ok-button").onclick = function() {
  $('#ok-button').click(function () {
//  function okButton(){
	  alert("aaa");
    let newDate = document.getElementById('new_date').text;
    console.log(newDate);
//  }
  });

  /*ポップアップを閉じる際の動き*/
//  $('.close-popup').click(function () {
//    $('.confirm-popup').fadeOut();
//    $('.error-popup').fadeOut();
//    $('.back-popup').fadeOut();
//    let password = document.getElementById('login_pass');
//    password.value='';
//  });

});

