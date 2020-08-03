$(function() {

	let startTime = document.getElementById("startTime").textContent;
	let endTime = document.getElementById("endTime").textContent;

	const stTime = startTime.substring(0, 5);
	const edTime = endTime.substring(0, 5);

	$('#startTime').text(stTime);
	$('#endTime').text(edTime);
	$('#startTimePop').text(stTime);
	$('#endTimePop').text(edTime);

	let actualTime = document.getElementById("actual-time-z").textContent;
	let actualTimeArray = actualTime.substring(5, 12).split(/\D/g);
	let actualHour = Number(actualTimeArray[0]);
	let actualMinute = Number(actualTimeArray[2]);
	let actualTimeMinute = 60 * actualHour + actualMinute;

	// 実績時間未入力（今回はdefaultで10000を格納）の場合は「-」に書き換え
	if (actualTimeMinute > 1500) {
		document.getElementById("actual-time-z").textContent = "実績時間：　  ―";
	}

	/* 削除完了ポップアップ表示 */
	$(document).ready(function() {
		let popFlag = document.getElementById('flag').value;
		if (popFlag == 1) {
			$('.complete-popup').fadeIn();
			return;
		}
	});

	/* 削除完了ポップアップでのOKボタン */
	$('.next-popup').click(function() {
		window.location.href = "actualindex";
	});

	// フォーム送信
	$('#actual-delete-action').click((function() {
		$('#actual-delete-form').submit();
		return;
	}))

});