$(function() {

	//テーブルの曜日整形用
	const dayOfWeek = [ "日", "月", "火", "水", "木", "金", "土" ] ;
  	//console.log(dayOfWeek[date.getDay()] + '曜日');

	//サーブレットから送られてきた日付を取得
	const dateServlet = document.getElementById("date_servlet").value;

	//サーブレットから送られてきた遷移元判定フラグを取得
	const flag = document.getElementById("flag").value;

	//エラーメッセージを取得
	const errorMsg = document.getElementById("error-message").textContent;

	//テーブルに記載されている日付を取得して整形
	/*const dateInTable = document.getElementById("date-in-table").textContent;
	console.log(dateInTable);*/


	//検索結果が0件の場合はテーブルを非表示
	if(errorMsg.trim() == "検索結果は0件でした"){
		$('#actual-content').css('display','none');
	}else{
		$('#actual-content').css('display','block');
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
	}

	// 先月の実績一覧へ
	$('#left').click(function() {
		window.location.href = `../user/actualindex?monthFlag=0&date=${dateServlet}`;
	})

	//次月の実績一覧へ
	$('#right').click(function() {
		window.location.href = `../user/actualindex?monthFlag=1&date=${dateServlet}`;
	})

	//キーワード検索
	$('.search-button').click(function(){
		//フォームに入力された日付とタイトルを取得
		const inputDate = document.getElementById("input-date").value;
		const inputTitle = document.getElementById("input-title").value;
		console.log(inputDate);
		console.log(inputTitle);
		//「-」で文字列を三つに分割し年、月、日を取得
		//年が9999年以上なら多分サーブレット側でエラーが起こる（localDate）

		//日付、タイトルともに未入力
		if(inputDate.trim() == "" && inputTitle.trim() == ""){
			$('#error2').css('display','block');
			return;
		}
		window.location.href = `../user/actualsearch?scheduleDate=${inputDate}&title=${inputTitle}`;
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



