
$(function () {

	$('.schedule-item').hover(
		function(){
			$(this).addClass('cell-active');
		},
		function(){
			$(this).removeClass('cell-active');
		}
	)

	$('.schedule-item').click(function(){

		var set = '<% setAttribute("date",';
		var id = $(this).children('.schedule_id').text();
		console.log(id);
		set += id;
		set += ') %>';
		$(this).append(set);
		window.location.href='#';//calendar.html

	})

	$('#left').click(function(){
		/*alert(date_servlet);*/
		//window.location.href=`../user/calendar?flag=0&date=${date_servlet}`;
		$('#left-form').submit();

	})

	$('#right').click(function(){
			/*alert(date_servlet);*/
		//window.location.href=`../user/calendar?flag=1&date=${date_servlet}`;
		$('#right-form').submit();
	})

});