$(function () {

  window.onload = function () {
	  /*日付の入力*/
	    let date = document.getElementById("set-date").value;
	    let d = new Date(date);
	    let weekday = '日月火水木金土'[d.getDay()];
        $('#actual-date').html(year + '/' + month + '/' + day +'(' + weekday + ')' + startHour +':'+ startMin +'～'+ endHour +':'+ endMin);

        /*場所の入力*/
        let place = document.getElementById('set-place').value;
        if(place == 0){

        }else if(){

        }else if(){

        }

		/*DBと照合した後のポップアップフラグ*/
		let popFlag = document.getElementById('flag').value;

		/*ScheduleCreateServlet返ってきたとき、値の保存をする*/
		if(popFlag==='0'||popFlag==='1'){
			let date = document.getElementById('set-date').value;
			let startTime = document.getElementById('set-start-time').value;
			let endTime = document.getElementById('set-end-time').value;
			let startHour = startTime.substring(0, 2);
			let startMin = startTime.substring(3,5);
			let endHour = endTime.substring(0, 2);
			let endMin = endTime.substring(3,5);
			let place = document.getElementById('set-place').value;

			console.log(startHour,startMin,endMin);

			/*すべての初期選択を外す*/
			$('select option').attr('selected', false);
			/*開始時間と終了時間を09→9に変換*/
			if (startHour.slice(0, 1) == 0) {
				startHour = startHour.substring(1, 2);
			}
			if (endHour.slice(0, 1) == 0) {
				endHour = endHour.substring(1, 2);
			}
			/*初期選択がされるようにselectedをつける*/
				$('#new-start-hour').val(startHour);
				$('#new-start-minutes').val(startMin);
				$('#new-end-hour').val(endHour);
				$('#new-end-minutes').val(endMin);
				$('#new-place').val(place);
		}


		/*登録が完了した場合*/
		  if(popFlag === '0'){
		  	$('.create-msg').html('登録が完了したよ！');
		  	$('.complete-popup').fadeIn();
		  	return;
		  }
		  if(popFlag === '1'){
			  	$('.new-msg').html('予定がかぶってるよ');
			  	$('.error-popup').fadeIn();
			  	return;
		  }

	}


  /*登録ボタンを押した際のエラーチェックとポップアップ表示*/
  $('#ok-button').click(function () {
	    let date = document.getElementById('date').value;
	    let startHour = document.getElementById('new-start-hour').value;
	    let startMin = document.getElementById('new-start-minutes').value;
	    let endHour = document.getElementById('new-end-hour').value;
	    let endMin = document.getElementById('new-end-minutes').value;
	    let place = document.getElementById('new-place').value;
	    let title=document.getElementById('title').value;
	    let content=document.getElementById('content').value;

    let d = new Date(date);
    let weekday = '日月火水木金土'[d.getDay()];
    //リリース年と月の取得
    let releaseYear = 2020;
    let releaseMonth = 7;
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
    var popFlag;

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

    if(popFlag==='3'){
        $('.new-msg').html('入力されていない<br>項目があるよ');
        $('.error-popup').fadeIn();
        return;
    }else if(popFlag==='4'){
        $('.new-msg').html('日付や時間の入力が<br>おかしいよ');
        $('.error-popup').fadeIn();
        return;
    }else if(popFlag==='5'){
        $('#time-msg').html(year + '/' + month + '/' + day +'(' + weekday + ')' + startHour +':'+ startMin +'～'+ endHour +':'+ endMin);
        $('#title-msg').html(title);
        $('#content-msg').html(content);
    	$('#confirm-popup2').fadeIn();
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
  let date = document.getElementById('date').value;
    location.href= "calendar?date=" + date;
  });

    /*キャンセルボタンを押した際のポップアップ表示*/
    $('#cancel-button').click(function () {
        $('.cancel-popup').fadeIn();
        return;
  });

  /*ポップアップを閉じる際の動き*/
  $('.close-popup').click(function () {
	$('#confirm-popup2').fadeOut();
    $('.error-popup').fadeOut();
    $('.cancel-popup').fadeOut();
    $('.complete-popup').fadeOut();
  });

});