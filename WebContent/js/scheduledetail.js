$(function () {

	let startTime = document.getElementById("startTime").textContent;
	let endTime = document.getElementById("endTime").textContent;

	const stTime = startTime.substring(0,5);
	const edTime = endTime.substring(0,5);
	$('#startTime').text(stTime) ;
	$('#endTime').text(edTime) ;
	$('#startTimePop').text(stTime) ;
	$('#endTimePop').text(edTime) ;

	/*DBと照合した後のエラーポップアップ表示*/
	$(document).ready(function () {
		let popFlag = document.getElementById('flag').value;
		console.log(popFlag);
		/*登録が完了した場合*/
		  if(popFlag == 1){
		  	$('.complete-popup').fadeIn();
		  	return;
		  }
	});

	/*ポップアップを閉じる*/
	  $('.close-popup').click(function () {
      $('.confirm-popup').fadeOut();
      $('.error-popup').fadeOut();

    });
	  /*登録完了ポップアップのOKボタン押下時の遷移先*/
    $('.next-popup').click(function () {
      location.href= "/user/scheduleshowall";
    });
});