$(function () {


	let startTime = document.getElementById("startTime").textContent;
	let endTime = document.getElementById("endTime").textContent;

	const stTime = startTime.substring(0,5);
	const edTime = endTime.substring(0,5);
	$('#startTime').text(stTime) ;
	$('#endTime').text(edTime) ;

	/*削除完了ポップアップ表示*/
	$(document).ready(function () {
	let popFlag = document.getElementById('flag').value;

	  if(popFlag == 1){
	  	$('.complete-popup').fadeIn();
	  	return;
	  }
	});

  /*削除完了ポップアップでのOKボタン*/
  $('.next-popup').click(function () {
	    location.href= "user/actualdetail";
	  });

  $('.large-popup').click(function () {
	  $('.confirm-popup').fadeIn();
	  });

});