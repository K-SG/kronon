$(function () {
	let startTime = document.getElementById("startTime").textContent;
	let endTime = document.getElementById("endTime").textContent;
	let startTimee = document.getElementById("startTimee").textContent;
	let endTimee = document.getElementById("endTimee").textContent;
	let actualTime = document.getElementById("actual-time-z").textContent;

	let actualTimeArray = actualTime.substring(5,12).split(/\D/g);
	let actualHour = Number(actualTimeArray[0]);
	let actualMinute = Number(actualTimeArray[2]);
	let actualTimeMinute = 60*actualHour + actualMinute;

	//実績時間未入力（今回はdefaultで10000を格納）の場合は「-」に書き換え
	if(actualTimeMinute > 1500){
		document.getElementById("actual-time-z").textContent = "実績時間：　　―";
	}

	const stTime = startTime.substring(0,5);
	const edTime = endTime.substring(0,5);
	$('#startTime').text(stTime) ;
	$('#endTime').text(edTime) ;
	const stTimee = startTimee.substring(0,5);
	const edTimee = endTimee.substring(0,5);
	$('#startTimee').text(stTimee) ;
	$('#endTimee').text(edTimee) ;

	/*DBと照合した後のエラーポップアップ表示*/
	$(document).ready(function () {
		let popFlag = document.getElementById('flag').value;
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
	//フォーム送信
	$('#schedule-delete-action').click((function(){
		$('#schedule-delete-form').submit();
		return;
	}))

});