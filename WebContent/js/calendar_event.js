
$(function () {

    const date_servlet = document.getElementById("date_servlet").value;

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
		alert(date);
		/*var set = '<c:set var="date" value=';
  		console.log(date);
		set += date;
		set += ' scope="session"/>';
		alert(set);
		$(this).append(set);
		$(this).append('<c:set var="date2" value="07-22" scope="session"/>');*/
		window.location.href=`miracle?date3=${date}`;//calendar.html

	})

	$('.left').click(function(){
		alert(date_servlet);
		window.location.href=`../user/calendar?flag=0&date=${date_servlet}`;
	})

		$('.right').click(function(){
			alert(date_servlet);
		window.location.href=`../user/calendar?flag=1&date=${date_servlet}`;
	})

});