$(function () {
	let startTime = document.getElementById('startTime').textContent;
	let endTime = document.getElementById('endTime').textContent;
	const stTime = startTime.substring(0,5);
	const edTime = endTime.substring(0,5);
	$('.startTime').text(stTime) ;
	$('.endTime').text(edTime) ;

    /*場所の入力*/
    let place = document.getElementById('set-place').value;
    let placeMsg;
    if(place == 0){
    	placeMsg='オフィス';
    }else if(place == 1){
    	placeMsg='在宅';
    }else if(place == 2){
    	placeMsg='外出';
    }
    $('#actual-place').html('作業('+placeMsg+')')

    window.onload = function () {

		/*DBと照合した後のポップアップフラグ*/
		let popFlag = document.getElementById('flag').value;

		/*ScheduleCreateServlet返ってきたとき、値の保存をする*/

			let date = document.getElementById('set-date').value;
			let actualTime = document.getElementById('set-actual-time').value;
			let actualHour = Math.floor(actualTime / 60);
			let actualMin = actualTime % 60;

			/*すべての初期選択を外す*/
			$('select option').attr('selected', false);
			/*初期選択がされるようにselectedをつける*/

				$('#actual-hour').val(actualHour);
				$('#actual-min').val(actualMin);

				if(popFlag==='0'){
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
	    let comment = document.getElementById('actual-comment').value;
	    let actualTime = actualHour + '時間' + actualMin +'分';
	    //ポッププラグ変数作成
	    let popFlag;

	    if(actualHour == '' || actualMin == ''){
	    	popFlag = '3';
	    }else{
	    	popFlag = '5';
	    }
	    if(popFlag === '3'){
	    	$('.new-msg').html('実績時間を入力してね！');
	    	$('.error-popup').fadeIn();
	    	return;
	    }else if(popFlag === '5'){
	    	$('#actual-time-msg').html(actualTime);
	    	$('#comment-msg').html(comment);
	    	$('#confirm-popup2').fadeIn();
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
    	location.href = 'scheduleshowall?date=' + date;
    });

    /*登録完了ポップアップの実績一覧へボタン押下時の遷移先*/
    $('.actualindex-popup').click(function () {
    	let date = document.getElementById('set-date').value;
    	location.href='actualindex?date=' + date;
    });

    /*キャンセルボタンを押した際のポップアップ表示*/
    $('.cancel-button').click(function () {
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