<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/WEB-INF/views/layout/common/link.jsp"%>
<link rel="stylesheet" href="../css/actual_index.css">
<link rel="stylesheet" href="../css/actual_new.css">
<title>実績一覧</title>
</head>
<body>
<input type = "hidden" id = "date_servlet" value = "${date}">
<input type = "hidden" id = "year" value = "${year}">
<input type = "hidden" id = "month" value = "${month}">
	<%@ include file="/WEB-INF/views/layout/common/header.jsp"%>
<article>
	<div class="actual-search-area">
		<div class="actual-search-date">
			<div class="actual-input-font-lev1">日付</div>
		</div>
		<div class="actual-search-date-area">
			<input name="date" type="date" />
		</div>

		<div class="actual-search-title">
			<div class="actual-input-font-lev1">タイトル</div>
		</div>
		<div class="actual-search-title-area">
			<input name="title" type="text" maxlength="100" />
		</div>
		<div class=search-button>
			<div class = "word">検索</div>
			<div><img id = "search-img" alt="検索" src="../img/search_icon.png"></div>
		</div>
	</div>

<div id = "actual-content" align=center>
	<div class="title-content">
		<img src="../img/left_button.png" alt="left"  id="left" >
	</div>
	<div>
		<div class="actual-content">
    		<p id = "actual-title"><c:out value="${year}年${month}月の実績"/></p>
    	</div>
	</div>
	<div class="title-content">
		<img src="../img/right_button.png" alt="right" id="right" >
	</div>
	<table border="1" >
		<thead>
			<tr>
				<th>日付</th>
				<th>タイトル</th>
				<th>内容</th>
				<th>見積時間</th>
				<th>実績時間</th>
			</tr>
		</thead>
		<tbody>

		<c:forEach var="scheduleBeanList" items="${scheduleBeanList}">
		<c:out value="${errorMsg}" />
				<tr>
					<td><c:out value="${scheduleBeanList.scheduleDate}" /></td>
					<td><c:out value="${scheduleBeanList.title}" /></td>
					<td><c:out value="${scheduleBeanList.content}" /></td>
					<td><c:out value="${scheduleBeanList.estimateTime}" /></td>
					<td><c:out value="${scheduleBeanList.actualTimeStr}" /></td>
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
						<c:if test="">
							<p>日付かタイトルを入力してね</p>
						</c:if>
						<c:if test="">
							<p>日付の入力がおかしいよ</p>
						</c:if>

					</div>
					<div class="ok-button close-popup">OK</div>
					<img src="../img/kronon/kronon_question.png" class="pop-img">
				</div>
			</div>
		</div>
		<!--エラーまたは完了ポップアップここまで-------------------------------------------------------------->
	</article>
	<%@ include file="/WEB-INF/views/layout/common/footer.jsp"%>
	<script src="../js/common/common.js"></script>
	<script src="../js/actual_index.js"></script>
	<script src="../js/popup.js"></script>
</body>
</html>