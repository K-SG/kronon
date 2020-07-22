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
/*日付に今日の日付が挿入されるようにする*/
  window.onload = function () {
	    var date = new Date();
	    date.setDate(date.getDate());
	    var yyyy = date.getFullYear();
	    var mm = ("0"+(date.getMonth()+1)).slice(-2);
	    var dd = ("0"+date.getDate()).slice(-2);
	    document.getElementById("date").value=yyyy+'-'+mm+'-'+dd;
	}

  /*登録ボタンを押した際のエラーチェックとポップアップ表示*/
  $('#ok-button').click(function () {
    let date = document.getElementById('date').value;
    let startHour = document.getElementById('new_start_hour').value;
    let startMin = document.getElementById('new_start_minutes').value;
    let endHour = document.getElementById('new_end_hour').value;
    let endMin = document.getElementById('new_end_minutes').value;
    let place = document.getElementById('new_place').value;
    let title=document.getElementById('title').value;
    let content=document.getElementById('content').value;
  //年、月、日をそれぞれ取得
    let year = date.substring(0, 4);
    let month = date.substring(5, 7);
    let day = date.substring(8, 10);
  //月と日付を07→7に変換
    if (month.slice(0, 1) == 0) {
        month = month.substring(1, 2);
    }
    if (day.slice(0, 1) == 0) {
        day = day.substring(1, 2);
    }

    console.log(date,startHour,startMin,endHour,endMin,place,title,content);

    if(date==""||startHour=='' || startMin=='' || endHour=='' || endMin=='' || place=='' || title=='' || content==''){
    	popFlag='1';
    	console.log(popFlag);
    }else if((startHour*60+startMin)-(endHour*60+endMin)>=0){
    	popFlag='2';
    }else{
    	popFlag='0';
    }
    if(popFlag==='0'){
    	$('.schedule-new-form').submit();
    	return;
    }
    else if(popFlag==='1'){
        $('.new_msg').html('入力されていない<br>項目があるよ');
        $('.error-popup').fadeIn();
        popFlag='0';
        return;
    }
    else if(popFlag==='2'){
        $('.new_msg').html('日付や時間の入力が<br>おかしいよ');
        $('.error-popup').fadeIn();
        popFlag='0';
        return;
    }
  });

    /*キャンセルボタンを押した際のポップアップ表示*/
    $('#cancel-button').click(function () {
        $('.back-popup').fadeIn();
        return;
  });

  /*ポップアップを閉じる際の動き*/
  $('.close-popup').click(function () {
    $('.confirm-popup').fadeOut();
    $('.error-popup').fadeOut();
    $('.back-popup').fadeOut();
    let password = document.getElementById('login_pass');
    password.value='';
  });

});