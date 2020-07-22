<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/WEB-INF/views/layout/common/link.jsp"%>
<link rel="stylesheet" href="../css/calendar.css">
<title>実績一覧</title>
</head>
<body>

	<%@ include file="/WEB-INF/views/layout/common/header.jsp"%>
<article>
<div>
	<form>
		日付<input type="date">
		タイトル<input type="text" maxlength=100>
		<input type="submit" value="検索">
	</form>
</div>

<div align="center">
	<table border="1" class="mytable">
		<thead>
			<tr>
				<th class="column1">日付</th>
				<th class="column2">タイトル</th>
				<th class="column3">内容</th>
				<th class="column4">見積時間</th>
				<th class="column5">実績時間</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="scheduleBeanList" items="${scheduleBeanList}">
			<tr>
				<td><c:out value="${scheduleBeanList.scheduleDate}" /></td>
				<td><c:out value="${scheduleBeanList.title}" /></td>
				<td><c:out value="${scheduleBeanList.content}" /></td>
				<td><c:out value="${scheduleBeanList.estimateTime}" /></td>
				<td><c:out value="${scheduleBeanList.actualTime}" /></td>
			</tr>
				</c:forEach>
		</tbody>

			</table>
		</div>




		<!--エラーまたは完了ポップアップ------------------------------------------------------------------->
		<div class="popup-wrapper error-popup">
			<div class="pop-container">
				<div class="close-popup">
					<i class="fa fa-2x fa-times"></i>
				</div>
				<div class="pop-container-inner">
					<div class="message-container">
						<!--  メッセージを分岐未入力、日付おかしい-->
						<p>メールアドレス・パスワードが違うよ。</p>
					</div>
					<div class="ok-button close-popup">OK</div>
					<img src="img/kronon/kronon_question.png" class="pop-img">
				</div>
			</div>
		</div>
		<!--エラーまたは完了ポップアップここまで-------------------------------------------------------------->
	</article>
	<%@ include file="/WEB-INF/views/layout/common/footer.jsp"%>
	<script src="../js/common/common.js"></script>
	<script src="../js/calendar2.js"></script>
	<script src="../js/calendar_event.js"></script>
</body>
</html>