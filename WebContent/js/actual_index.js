$(function() {

	//サーブレットから送られてきた日付を取得
	const date_servlet = document.getElementById("date_servlet").value;

	// 先月の実績一覧へ
	$('#left').click(function() {
//		alert("先月へ");
//		window.location.href = `../user/actualindex?flag=0&date=${date_servlet}`;
		window.location.href = `../user/actualindex?flag=0&date=${date_servlet}`;
	})

	//次月の実績一覧へ
	$('#right').click(function() {
//		alert("次月へ");
//		window.location.href = `../user/actualindex?flag=1&date=${date_servlet}`;
		window.location.href = `../user/actualindex?flag=1&date=${date_servlet}`;
	})

	//キーワード検索
	$('.search-button').click(function(){
		const inputDate = document.getElementById("input-date").value;
		const inputTitle = document.getElementById("input-title").value;
		window.location.href = `../user/actualsearch?scheduleDate=${inputDate}&title=${inputTitle}`;
	})

});

