$(function() {

	//サーブレットから送られてきた日付を取得
	const date_servlet = document.getElementById("date_servlet").value;
    const year = document.getElementById("year").value; //2020
    const month = document.getElementById("month").value; //7
    console.log(date_servlet);
    console.log(year);
    console.log(month);

	// 先月の実績一覧へ
	$('.left').click(function() {
//		alert("先月へ");
//		window.location.href = `../user/actualindex?flag=0&date=${date_servlet}`;
		window.location.href = `../user/actualindex?flag=0&date=${date_servlet}`;
	})

	//次月の実績一覧へ
	$('.right').click(function() {
//		alert("次月へ");
//		window.location.href = `../user/actualindex?flag=1&date=${date_servlet}`;
		window.location.href = `../user/actualindex?flag=1&date=${date_servlet}`;
	})

});