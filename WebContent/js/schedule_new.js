$(function () {

/*日付に今日の日付が挿入されるようにする*/
  window.onload = function () {
	    var date = new Date();
	    date.setDate(date.getDate());
	    var yyyy = date.getFullYear();
	    var mm = ("0"+(date.getMonth()+1)).slice(-2);
	    var dd = ("0"+date.getDate()).slice(-2);
	    document.getElementById("date").value=yyyy+'-'+mm+'-'+dd;

		/*DBと照合した後のポップアップフラグ*/
		var popFlag = document.getElementById('flag').value;

		/*登録が完了した場合*/
		  if(popFlag == 0){
		  	$('.create-msg').html('登録が完了したよ！');
		  	$('.complete-popup').fadeIn();
		  	return;
		  }
		  if(popFlag == 1){
			  	$('.new-msg').html('予定がかぶってるよ');
			  	$('.error-popup').fadeIn();
			  	return;
		  }

	}


  /*登録ボタンを押した際のエラーチェックとポップアップ表示*/
  $('#ok-button').click(function () {
    let date = document.getElementById('new-date').value;
    let startHour = document.getElementById('new_start_hour').value;
    let startMin = document.getElementById('new_start_minutes').value;
    let endHour = document.getElementById('new_end_hour').value;
    let endMin = document.getElementById('new_end_minutes').value;
    let place = document.getElementById('new_place').value;
    let title=document.getElementById('title').value;
    let content=document.getElementById('content').value;

    let d = new Date(date);
    let weekday = '日月火水木金土'[d.getDay()];
    //リリース年と月の取得
    let releaseYear = 2020;
    let releaseMonth = 8;
    //サービス終了年と月の取得
    let releaseLastYear = 2023;
    let releaseLastMonth = 8;
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
  //月初、月末の日付を取得
    let firstDayOfMonth = new Date(year, month - 1, 1).getDate();
    let lastDayOfMonth = new Date(year, month, 0).getDate();

    //ポッププラグ変数作成
    let popFlag;

//    console.log(date,startHour,startMin,endHour,endMin,place,title,content);

    if(date==""||startHour=='' || startMin=='' || endHour=='' || endMin=='' || place=='' || title==''){
    	popFlag='3';

    }else if((startHour*60+startMin)-(endHour*60+endMin)>=0){
    	//終了時間よりも開始時間のほうが遅かったら
//    	console.log("開始時間のほうが遅い");
    	popFlag='4';
    }else if(!(firstDayOfMonth <= day && day <= lastDayOfMonth)){
    	//存在しない日付を入力したら（2/31など）
//    	console.log("存在しない日付");
    	popFlag='4';
    }else if(year < releaseYear || (year == releaseYear && month < releaseMonth)){
    	//リリース前の日付を選択したら
//    	console.log("リリース前の日付");
    	popFlag='4';
    }else if(year > releaseLastYear || (year == releaseLastYear && month > releaseLastMonth)){
    	//サービス終了後の日付を選択したら
//    	console.log("サービス終了後の日付");
    	popFlag='4';
    }else{
    	popFlag='5';
    }


    if(popFlag==='5'){
        $('#time-msg').html(year + '/' + month + '/' + day +'(' + weekday + ')' + startHour +':'+ startMin +'～'+ endHour +':'+ endMin);
        $('#title-msg').html(title);
        $('#content-msg').html(content);
    	$('.confirm-popup').fadeIn();
        return;
    }
    else if(popFlag==='3'){
        $('.new_msg').html('入力されていない<br>項目があるよ');
        $('.error-popup').fadeIn();
        return;
    }
    else if(popFlag==='4'){
        $('.new_msg').html('日付や時間の入力が<br>おかしいよ');
        $('.error-popup').fadeIn();
        return;
    }
  });

  /*確認ポップアップのOKを押した際の動き*/
  $('#confirm-ok').click(function () {
      $('.schedule-new-form').submit();
      return;
});

  /*登録完了ポップアップのOKボタン押下時の遷移先*/
  $('.next-popup').click(function () {
  var date = document.getElementById('date').value;
    location.href= "user/calendar?date=" + date;
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
    $('.complete-popup').fadeOut();
  });

});