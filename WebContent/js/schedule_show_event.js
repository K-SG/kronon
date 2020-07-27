
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
		alert("次のURLに"+id+"を送ります");
		window.location.href='../user/scheduleDetail?scheduleId='+id;//calendar.html

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