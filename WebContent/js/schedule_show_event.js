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
		var id = $(this).children('.schedule_id').text();
		window.location.href='../user/scheduledetail?scheduleId='+id;l
	})

	//前日へ
	$('#left').click(function(){
		$('#left-form').submit();
	})

	//翌日へ
	$('#right').click(function(){
		$('#right-form').submit();
	})

});