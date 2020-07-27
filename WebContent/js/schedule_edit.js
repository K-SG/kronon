$(function () {

	/*DBと照合した後のポップアップ表示*/
	$(document).ready(function () {
		let popFlag = document.getElementById('flag').value;

		/*登録が完了した場合*/
		  if(popFlag == 0){
		  	$('.create-msg').html('登録が完了したよ！');
		  	$('.complete-popup').fadeIn();
		  	return;
		  }
	});

	  /*登録完了ポップアップのOKボタン押下時の遷移先*/
    $('.next-popup').click(function () {
    	let scheduleId = document.getElementById('set-schedule-id').value;
      location.href= "scheduledetailservlet?id="+ scheduleId ;
    });

/*変更前の情報を事前に入力された状態にしておくために必要な変数*/
  window.onload = function () {
let date = document.getElementById('set-date').value;
let startTime = document.getElementById('set-start-time').value;
let endTime = document.getElementById('set-end-time').value;
let startHour = startTime.substring(0, 2);
let startMin = startTime.substring(2,4);
let endHour = endTime.substring(0, 2);
let endMin = endTime.substring(2,4);
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
	$('#edit-start-hour').val(startHour);
	$('#edit-start-minutes').val(startMin);
	$('#edit-end-hour').val(endHour);
	$('#edit-end-minutes').val(endMin);
	$('#edit-place').val(place);
}

  /*修正ボタンを押した際のエラーチェックとポップアップ表示*/
  $('#ok-button').click(function () {
    let date = document.getElementById('edit-date').value;
    let startHour = document.getElementById('edit-start-hour').value;
    let startMin = document.getElementById('edit-start-minutes').value;
    let endHour = document.getElementById('edit-end-hour').value;
    let endMin = document.getElementById('edit-end-minutes').value;
    let place = document.getElementById('edit-place').value;
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

//    console.log(date,startHour,startMin,endHour,endMin,place,title,content);

    if(date==""||startHour=='' || startMin=='' || endHour=='' || endMin=='' || place=='' || title==''){
    	popFlag='1';

    }else if((startHour*60+startMin)-(endHour*60+endMin)>=0){
    	//終了時間よりも開始時間のほうが遅かったら
//    	console.log("開始時間のほうが遅い");
    	popFlag='2';
    }else if(!(firstDayOfMonth <= day && day <= lastDayOfMonth)){
    	//存在しない日付を入力したら（2/31など）
//    	console.log("存在しない日付");
    	popFlag='2';
    }else if(year < releaseYear || (year == releaseYear && month < releaseMonth)){
    	//リリース前の日付を選択したら
//    	console.log("リリース前の日付");
    	popFlag='2';
    }else if(year > releaseLastYear || (year == releaseLastYear && month > releaseLastMonth)){
    	//サービス終了後の日付を選択したら
//    	console.log("サービス終了後の日付");
    	popFlag='2';
    }else{
    	popFlag='0';
    }


    if(popFlag==='0'){
        $('#time-msg').html(year + '/' + month + '/' + day +'(' + weekday + ')' + startHour +':'+ startMin +'～'+ endHour +':'+ endMin);
        $('#title-msg').html(title);
        $('#content-msg').html(content);
    	$('.confirm-popup').fadeIn();
        popFlag='0';
        return;
    }
    else if(popFlag==='1'){
        $('.edit-msg').html('入力されていない<br>項目があるよ');
        $('.error-popup').fadeIn();
        popFlag='0';
        return;
    }
    else if(popFlag==='2'){
        $('.edit-msg').html('日付や時間の入力が<br>おかしいよ');
        $('.error-popup').fadeIn();
        popFlag='0';
        return;
    }
  });

  /*確認ポップアップのOKを押した際の動き*/
  $('#confirm-ok').click(function () {
      $('.schedule-edit-form').submit();
      return;
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
    let password = document.getElementById('login-pass');
    password.value='';
  });

});