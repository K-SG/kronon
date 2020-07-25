
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

});