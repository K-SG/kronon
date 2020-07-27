
$(function () {

    const date_servlet = document.getElementById("date_servlet").value;
    const year = document.getElementById("year").textContent; //2020
    const month = document.getElementById("month").textContent ; ; //7

	$('td').hover(

		function(){
			if($(this).hasClass('disabled')){
				return;
			}
			$(this).addClass('cell-active');
		},
		function(){
			$(this).removeClass('cell-active');
		}
	)

	$('td').click(function(){
		if($(this).hasClass('disabled')){
			return;
		}


		var date = $(this).children('.date').text();

		/*var set = '<c:set var="date" value=';
  		console.log(date);
		set += date;
		set += ' scope="session"/>';
		alert(set);
		$(this).append(set);
		$(this).append('<c:set var="date2" value="07-22" scope="session"/>');*/
		if(date.length === 1){
			date = "0"+date;
		}
/*		let date_0 = (String)("0"+date);
		let date_0_cut = date_0.subString(-2,0);*/
		let date_submit = date_servlet.slice(0,-2) + date;
		//date_servlet.setDate(date);
		alert("次のサーブレットに送るパラメータは"+date_submit);
		window.location.href=`miracle?date3=${date_submit}`;//calendar.html

	})

	$('.left').click(function(){
		/*alert(date_servlet);*/
		window.location.href=`../user/calendar?flag=0&date=${date_servlet}`;
	})

		$('.right').click(function(){
			/*alert(date_servlet);*/
		window.location.href=`../user/calendar?flag=1&date=${date_servlet}`;
	})

});