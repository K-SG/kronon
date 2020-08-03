<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/kronon/css/scheduledetail.css" rel="stylesheet"
	type="text/css">
<%@ include file="../layout/common/link.jsp"%>
<title>予定詳細画面</title>

</head>
<body>

<%@ include file="/WEB-INF/views/layout/common/header.jsp"%>
<article>

	<div class=white-text>
		<div class=blackboard>
			<table class=schedule_detail>

				<tr>
					<td>名前 : <c:out value="${scheduleBean.userName}" /></td>
					<td id = "actual-time-z">実績時間：<c:out value="${scheduleBean.actualTimeStr}" /></td>
				</tr>

				<tr>
					<td>日時：<c:out value="${scheduleBean.scheduleDateActual}" />
					<span id="startTime"><c:out value="${scheduleBean.startTime}" /></span>～
						<span id="endTime"><c:out value="${scheduleBean.endTime}" /></span></td>

				<!-- 作業場所に応じて色変化 -->
					<c:if test="${scheduleBean.place=='0'}">
						<td class=show-place1>場所：オフィス</td>
					</c:if>

					<c:if test="${scheduleBean.place=='1'}">
						<td class=show-place2>場所：在宅</td>
					</c:if>

					<c:if test="${scheduleBean.place=='2'}">
						<td class=show-place3>場所：外出</td>
					</c:if>

				</tr>

				<tr>
					<td class="new-line" colspan="2">タイトル：<c:out value="${scheduleBean.title}" /></td>
				</tr>

				<tr>
					<td class="new-line" colspan="2">内容：<c:out value="${scheduleBean.content}" /></td>
				</tr>

			</table>

		</div>
	</div>
<!-- くろのんの画像 -->
	<div class="kronon-banzai">
		<img alt="banzai" src="../img/kronon/kronon_banzai.png">
	</div>


<!-- 戻るボタン -->
	<div><a href="scheduleshowall?date=${scheduleBean.scheduleDate}"><img src="/kronon/img/back_buttom.png" alt="戻る" class="back-btn"></a></div>


	<!-- sesseionスコープのuserIDとスケジュールのIDを比較.一致したときのみ表示 -->
	<c:set var="loginUserId" value="${sessionScope.userId}" />
	<c:set var="scheduleUserId" value="${scheduleBean.userId}" />

	<c:if test="${loginUserId == scheduleUserId }">
		<div class="flex_test-box">
			<div class="flex_test-item">

				<a href="scheduleedit?scheduleId=${scheduleBean.scheduleId}"><div class="ok-button">修正</div></a>
			</div>
			<div class="flex_test-item">

				<a href="actualnew?scheduleId=${scheduleBean.scheduleId}"><div class="ok-button">実績入力</div></a>
			</div>
			<div class="flex_test-item">
				<div class="ok-button large-popup-button" id="${popFlag}">削除</div>
			</div>
		</div>
	</c:if>

	<!--内容確認ポップアップ----------------------------------------------------------------->
	<div class="popup-wrapper confirm-popup">
		<div class="pop-container pop-container-large">
			<div class="close-popup">
				<img src="/kronon/img/close_button_orange.png" alt="閉じる" class="back-button">
			</div>
			<div class="pop-container-inner">
				<div class="message-container-large">
					<h2 class="message-title">この内容を本当に削除する？</h2>
					<table class="popup-table">
						<tr>
							<th class="th">名前：</th>
							<td><c:out value="${scheduleBean.userName}" /></td>
						</tr>
						<tr>
							<th>予定日時：</th>
							<td><c:out value="${scheduleBean.scheduleDateActual}" /> <span
						id="startTimee"><c:out value="${scheduleBean.startTime}" /></span>～
						<span id="endTimee"><c:out value="${scheduleBean.endTime}" /></span></td>
						</tr>
						<tr>
							<th>タイトル：</th>
							<td class="new-line"><span class="actual-input-area-4"><c:out value="${scheduleBean.title}" /></span></td>
						</tr>
						<tr>
							<th class="last-table">内容：</th>
							<td class="last-table new-line"><span class="actual-input-area-4"><c:out
									value="${scheduleBean.content}" /></span></td>
						</tr>
					</table>
				</div>
				<form action="http://localhost:8080/kronon/user/scheduledelete" method="post" id="schedule-delete-form">
					<input type="hidden" id="flag" value="${popFlag}">
					<input type="hidden" name="scheduleId" value="${scheduleBean.scheduleId}">
					<input type="hidden" name="userName" value="${scheduleBean.userName}">
					<input type="hidden" name="actualTimeStr" value="${scheduleBean.actualTimeStr}">
					<input type="hidden" name="scheduleDateActual" value="${scheduleBean.scheduleDateActual}">
					<input type="hidden" name="startTime" value="${scheduleBean.startTime}">
					<input type="hidden" name="endTime" value="${scheduleBean.endTime}">
					<input type="hidden" name="place" value="${scheduleBean.place}">
					<input type="hidden" name="title" value="${scheduleBean.title}">
					<input type="hidden" name="content" value="${scheduleBean.content}">
					<input type="hidden" name = "scheduleDate" value="${scheduleBean.scheduleDate}">

					<!-- <button type='submit'class="ok-button" >OK</button> -->
					<div class="ok-button" id="schedule-delete-action">OK</div>


				<div class="ng-button close-popup">キャンセル</div>
				<img src="/kronon/img/star/star_nomal.png" class="pop-large-img-top star-nomal">
				</form>
			</div>
		</div>
	</div>
	<!--内容確認ポップアップここまで----------------------------------------------------------------->

	<!--削除完了ポップアップ------------------------------------------------------------------->
	<div class="popup-wrapper error-popup complete-popup">
		<div class="pop-container">
			<div class="pop-container-inner">
				<div class="message-container">
					<p class=create-msg>削除が完了したよ！</p>
				</div>
				<a href="scheduleshowall?date=${scheduleBean.scheduleDate}"><div class="ok-button next-popup">OK</div></a>
				<img src="/kronon/img/kronon/kronon_star.png" class="pop-img kronon-star">
			</div>
		</div>
	</div>
	<!--削除完了ポップアップここまで-------------------------------------------------------------->



</article>
<%@ include file="/WEB-INF/views/layout/common/footer.jsp"%>
<script src="js/common/common.js"></script>
<script src="js/schedule_detail.js"></script>
</body>
</html>