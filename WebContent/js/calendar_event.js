
$(function () {

	$('td').hover(
		function(){
			$(this).addClass('cell-active');
		},
		function(){
			$(this).removeClass('cell-active');
		}
	)

	$('td').click(function(){

		var set = '<% setAttribute("date",';
		var date = $(this).children('.date').text();
		console.log(date);
		set += date;
		set += ') %>';
		$(this).append(set);
		window.location.href='#';//calendar.html

	})

});