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
					<td>実績時間:<c:out value="${scheduleBean.actualTimeStr}" /></td>
				</tr>

				<tr>
					<td><c:out value="${scheduleBean.scheduleDateActual}" /> <span
						id="startTime"><c:out value="${scheduleBean.startTime}" /></span>～
						<span id="endTime"><c:out value="${scheduleBean.endTime}" /></span></td>

				<!-- 作業場所に応じて色変化 -->
					<c:if test="${scheduleBean.place=='0'}">
						<td class=show-place1>オフィス</td>
					</c:if>

					<c:if test="${scheduleBean.place=='1'}">
						<td class=show-place2>在宅</td>
					</c:if>

					<c:if test="${scheduleBean.place=='2'}">
						<td class=show-place3>外出</td>
					</c:if>

				</tr>

				<tr>
					<td colspan="2"><c:out value="${scheduleBean.title}" /></td>
				</tr>

				<tr>
					<td colspan="2"><c:out value="${scheduleBean.content}" /></td>
				</tr>

			</table>

		</div>
	</div>
<!-- くろのんの画像 -->
	<div class="kronon-banzai">
		<img alt="banzai" src="./img/kronon/kronon_banzai.png">
	</div>
<!-- 戻るボタン -->
	<a href="/user/scheduleshowall"><div class="user-create-button-return">
			<img alt="戻る" src="/kronon/img/back_buttom.png"></div>
	</a>


	<!-- sesseionスコープのuserIDとスケジュールのIDを比較.一致したときのみ表示 -->
	<c:set var="loginUserId" value="${sessionScope.userId}" />
	<c:set var="scheduleUserId" value="${scheduleBean.userId}" />

	<c:if test="${loginUserId == scheduleUserId }">
		<div class="flex_test-box">
			<div class="flex_test-item">

				<a href="/user/scheduleedit?scheduleId=${scheduleBean.scheduleId}"><div class="ok-button">修正</div></a>
			</div>
			<div class="flex_test-item">

				<a href="/user/actualedit?scheduleId=${scheduleBean.scheduleId}"><div class="ok-button">実績入力</div></a>
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
				<i class="fa fa-2x fa-times"></i>
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
							<td><span class="actual-input-area-4"><c:out value="${scheduleBean.title}" /></span></td>
						</tr>
						<tr>
							<th class="last-table">内容：</th>
							<td class="last-table"><span class="actual-input-area-4"><c:out
									value="${scheduleBean.content}" /></span></td>
						</tr>
					</table>
				</div>
				<form action="http://localhost:8080/kronon/user/scheduledelete" method="post">
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

					<button type='submit'class="ok-button" >OK</button>


				<div class="ng-button close-popup">キャンセル</div>
				<img src="img/kronon/kronon_question.png" class="pop-img">
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
					<p class=create-msg>削除が完了したよ</p>
				</div>
				<div class="ok-button next-popup">OK</div>
				<img src="/kronon/img/kronon/kronon_question.png" class="pop-img">
			</div>
		</div>
	</div>
	<!--削除完了ポップアップここまで-------------------------------------------------------------->



</article>
<%@ include file="/WEB-INF/views/layout/common/footer.jsp"%>
<script src="js/common/common.js"></script>
<script src="js/scheduledetail.js"></script>
<script src="../js/scheduledetail.js"></script>
</body>
</html>