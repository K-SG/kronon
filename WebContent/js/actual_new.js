$(function () {

  window.onload = function () {
	  /*日付の入力*/
	    let date = document.getElementById("set-date").value;
	    let startTime = document.getElementById("set-start-time").value;
	    let endTime = document.getElementById("set-end-time").value;
	    let d = new Date(date);
	    let weekday = '日月火水木金土'[d.getDay()];
        $('#actual-date').html(date +'(' + weekday + ')' + startTime +'～'+ endTime);

        /*場所の入力*/
        let place = document.getElementById('set-place').value;
        let placeMsg;
        if(place == 0){
        	placeMsg="オフィス";
        }else if(palace == 1){
        	placeMsg="在宅";
        }else if(place == 2){
        	placeMsg="外出";
        }
        $('#actual-place').html("作業("+placeMsg+")")



		/*DBと照合した後のポップアップフラグ*/
		let popFlag = document.getElementById('flag').value;

		/*ScheduleCreateServlet返ってきたとき、値の保存をする*/
		if(popFlag==='0'){
			let date = document.getElementById('set-date').value;
			let actualTime = document.getElementById('set-tart-time').value;
			let actualHour = actualTime.substring(0, 2);
			let actualMin = actualTime.substring(3,5);

			/*すべての初期選択を外す*/
			$('select option').attr('selected', false);
			/*開始時間と終了時間を09→9に変換*/
			if (actualHour.slice(0, 1) == 0) {
				actualHour = actualHour.substring(1, 2);
			}
			/*初期選択がされるようにselectedをつける*/
				$('#actual-hour').val(actualHour);
				$('#actual-min').val(actualMin);

				/*実績登録ポップアップ*/
				$('.create-msg').html('実績を登録したよ！');
				$('.complete-popup').fadeIn();
				return;
		  }
	}


  /*登録ボタンを押した際のエラーチェックとポップアップ表示*/
  $('#ok-button').click(function () {
	    let actualHour = document.getElementById('actual-hour').value;
	    let actualMin = document.getElementById('actual-min').value;

//ポッププラグ変数作成
    let popFlag;

    if(actualHour==""||actualMin==''){
    	popFlag='3';
    }
    if(popFlag==='3'){
        $('.new-msg').html('入力されていない<br>項目があるよ');
        $('.error-popup').fadeIn();
        return;
    }
  });

  /*確認ポップアップのOKを押した際の動き*/
  $('#confirm-ok').click(function () {
      $('.actual-new-form').submit();
      return;
});

  /*登録完了ポップアップの予定表へボタン押下時の遷移先*/
  $('.scheduleshowall-popup').click(function () {
  let date = document.getElementById('set-date').value;
    location.href= "scheduleshowall?date=" + date;
  });

  /*登録完了ポップアップの実績一覧へボタン押下時の遷移先*/
  $('.actualindex-popup').click(function () {
  let date = document.getElementById('set-date').value;
    location.href= "actualindex?date=" + date;
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