$(function() {

	//サーブレットから送られてきた日付を取得
	const dateServlet = document.getElementById("date_servlet").value;

	//サーブレットから送られてきた遷移元判定フラグを取得
	const flag = document.getElementById("flag").value;

	//エラーメッセージを取得
	const errorMsg = document.getElementById("error-message").textContent;

	//一覧から内容と見積時間と実績時間を取得
	const estimateTimes = Array.from(document.getElementsByClassName("estimate-time"));
	const actualTimes = Array.from(document.getElementsByClassName("actual-time"));
	const contents = Array.from(document.getElementsByClassName("contents"));

	for(let i = 0; i < estimateTimes.length; i++){

		//見積時間を分換算
		let estimateTimeArray = estimateTimes[i].textContent.split(/\D/g);
		let estimateHour = Number(estimateTimeArray[0]);
		let estimateMinute = Number(estimateTimeArray[2]);
		let estimateTimeMinute = 60*estimateHour + estimateMinute;

		//実績時間を分換算
		let actualTimeArray = actualTimes[i].textContent.split(/\D/g);
		let actualHour = Number(actualTimeArray[0]);
		let actualMinute = Number(actualTimeArray[2]);
		let actualTimeMinute = 60*actualHour + actualMinute;

		//実績時間が見積時間の80%を下回った場合は青表記
		if(actualTimeMinute < estimateTimeMinute*0.8){
			document.getElementsByClassName("actual-time")[i].style.color = "blue";
		}

		//実績時間が見積時間の120%を上回った場合は青表記
		if(actualTimeMinute > estimateTimeMinute*1.2){
			document.getElementsByClassName("actual-time")[i].style.color = "red";
		}

		//実績時間が0時間0分の場合は緑表記
		if(actualTimeMinute == 0){
			document.getElementsByClassName("actual-time")[i].style.color = "green";
		}

		//実績時間未入力（今回はdefaultで10000を格納）の場合は「-」に書き換え
		if(actualTimeMinute > 1500){
			document.getElementsByClassName("actual-time")[i].textContent = "-";
			document.getElementsByClassName("actual-time")[i].classList.add('hyphen');
		}
	}

	//検索結果が0件の場合はテーブルを非表示
	if(errorMsg.trim() == "検索結果は0件でした"){
		$('#actual-content').css('display','none');
		$('#kronon-prin-img').css('display','block');
		$('#actual-button').css('margin-bottom','');
	}else{
		$('#actual-content').css('display','block');
		$('#kronon-prin-img').css('display','none');
	}

	//月送りボタン、実績確認ボタンの表示切替判定
	if(flag == 0){
		$('#left').css('display','block');
		$('#right').css('display','block');
		$('#actual-button').css('display','none');
	}

	//月送りボタン、実績確認ボタンの表示切替判定
	if(flag == 1){
		$('#left').css('display','none');
		$('#right').css('display','none');
		$('#actual-button').css('display','block');
		$('#actual-button').css('margin-bottom','15vh');
	}

	if(flag == 1 && errorMsg.trim() == "検索結果は0件でした"){
		$('#actual-button').css('margin-bottom','');
	}


	//リリース月より遡れなくする
	if(dateServlet.substring(0,7) == "2020-08"){
		$('#left').css('display','none');
	}

	//サービス終了月より先を見れなくする
	if(dateServlet.substring(0,7) == "2023-07"){
		$('#right').css('display','none');
	}

	// 先月の実績一覧へ
	$('#left').click(function() {
		$('#left-form').submit();
	})

	//次月の実績一覧へ
	$('#right').click(function() {
		$('#right-form').submit();
	})

	//マウスオーバー中の予定に色付け
	$('.schedule-actual').hover(
		function(){
			$(this).addClass('cell-active');
		},
		function(){
			$(this).removeClass('cell-active');
		}
	)

	//実績の詳細へ
	$('.schedule-actual').click(function(){
		const scheduleId = $(this).children('.schedule-id').text();
		window.location.href = `../user/actualdetail?scheduleId=${scheduleId}`;
	})

	//キーワード検索
	$('.search-button').click(function(){
		//フォームに入力された日付とタイトルを取得
		const inputDate = document.getElementById("input-date").value;
		const inputTitle = document.getElementById("input-title").value;
		const yearServlet = document.getElementById("year").value;
		const monthServlet = document.getElementById("month").value;

		//「-」で文字列を三つに分割し年、月、日を取得
		const year = inputDate.split('-')[0];

		//年が9999年以上ならサーブレット側でエラーが起こる（localDate）
		if(year.toString().trim().length >= 5){
			$('#error1').css('display','block');
			return;
		}

		//日付、タイトルともに未入力
		if(inputDate.trim() == "" && inputTitle.trim() == ""){
			$('#error2').css('display','block');
			return;
		}

		document.getElementById("input-title").textContent = inputTitle.trim();
		$('#search-form').submit();
	})

	//実績確認へ
	$('#actual-button').click(function(){
		window.location.href = `../user/actualindex`;
	})

	// 上に戻るボタンの初期化
	let topBtn = $('#scrollTop');
	topBtn.hide();

	// ある程度スクロールされたら、上に戻るボタンを表示する
	$(window).scroll(function() {
	  if ($(this).scrollTop() > 200) {
	    topBtn.fadeIn(); // フェードインで表示
	  } else {
	    topBtn.fadeOut();  // フェードアウトで非表示
	  }
	});

	// クリックで上に戻るボタン
	topBtn.click(function(event) {
	  event.preventDefault(); // ページ内リンクの挙動をキャンセル
	  $('body,html').animate({
	    scrollTop: 0
	  }, 500);
	});

});



