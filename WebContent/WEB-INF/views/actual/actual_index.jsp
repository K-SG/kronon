<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/WEB-INF/views/layout/common/link.jsp"%>
<link rel="stylesheet" href="../css/actual_index.css">
<title>実績一覧</title>
</head>
<body>
<input type = "hidden" id = "flag" value = "${flag}">
	<%@ include file="/WEB-INF/views/layout/common/header.jsp"%>
<article>
	<div class="actual-search-area">
	<form action = "../user/actualsearch" method = "post" id = "search-form">
		<div class="actual-search-date">
			<div class="actual-input-font-lev1">日付</div>
		</div>
		<div class="actual-search-date-area">
			<input name="date" type="date" id = "input-date"/>
		</div>

		<div class="actual-search-title">
			<div class="actual-input-font-lev1">タイトル</div>
		</div>
		<div class="actual-search-title-area">
			<input name="title" type="text" maxlength="100" id = "input-title"/>
		</div>
		<div class="search-button">
		<input type = "hidden" name = "year" id = "year" value = "${year}">
		<input type = "hidden" name = "month" id = "month" value = "${month}">
			<div class = "word">検索</div>
			<div><img id = "search-img" alt="検索" src="../img/search_icon.png"></div>
		</div>
	</form>
	</div>

<div id = "actual-content" align=center>
	<div>
	<div class="title-content">
	<form action = "../user/actualindex" method = "post" id = "left-form">
		<img src="../img/left_button.png" alt="left"  id="left" >
		<input type = "hidden" name = "date" value = "${date}">
		<input type = "hidden" name = "monthFlag" value = "0">
	</form>
	</div>
	<div class="title-content">
	<form action = "../user/actualindex" method = "post" id = "right-form">
		<img src="../img/right_button.png" alt="right" id="right" >
		<input type = "hidden" name = "date" id = "date_servlet" value = "${date}">
		<input type = "hidden" name = "monthFlag" value = "1">
	</form>
	</div>
		<div class="actual-content">
		<c:if test="${flag.equals('0')}">
			<p id = "actual-title"><c:out value="${year}年${month}月の実績"/></p>
		</c:if>
		<c:if test="${flag.equals('1')}">
			<p id = "actual-result"><c:out value="${year}年${month}月の検索結果"/></p>
		</c:if>
    	</div>

	</div>

	<table border="1" >
		<thead>
			<tr>
				<th id = "date-in-thead">日付</th>
				<th id = "title-in-thead">タイトル</th>
				<th id = "content-in-thead" class = "contents">内容</th>
				<th id = "estimate-in-thead" class = "time">見積時間</th>
				<th id = "actual-in-thead" class = "time">実績時間</th>
			</tr>
		</thead>
		<tbody>

		<c:forEach var="scheduleBeanList" items="${scheduleBeanList}">
		<c:out value="${errorMsg}" />
				<tr class = "schedule-actual">
					<td id = "date-in-table"><c:out value="${scheduleBeanList.scheduleDateActual}" /></td>
					<td class = "schedule-id" style = "display:none;"><c:out value="${scheduleBeanList.scheduleId}" /></td>
					<td id = "title" class = "title"><c:out value="${scheduleBeanList.title}" /></td>
					<td class = "title-and-content contents"><div class = "item"><c:out value="${scheduleBeanList.content}" /></div></td>
					<td class = "estimate-time time"><c:out value="${scheduleBeanList.estimateTime}" /></td>
					<td class = "actual-time time"><c:out value="${scheduleBeanList.actualTimeStr}" /></td>
				</tr>
		</c:forEach>
		</tbody>

			</table>
</div>
<div id = "error-message">
	<c:out value="${errorMsg}" />
</div>

<div id="actual-button" align = center>
	実績確認へ
</div>

<p id="scrollTop">
	<a href="#"><img id = "to-top-button" src="../img/up_bottom.png" alt = "トップへ" class="pop-img"></a>
</p>
<img alt="くろのん" id = "kronon-prin-img" src="../img/kronon/kronon_prin.png">


<!--エラーまたは完了ポップアップ------------------------------------------------------------------->
<div id = "error1" class="popup-wrapper error-popup">
	<div class="pop-container">
		<div class="close-popup">
			<i class="fa fa-2x fa-times"></i>
		</div>
		<div class="pop-container-inner">
			<div class="message-container">
					<p>日付の入力がおかしいよ</p>
			</div>
			<div class="ok-button close-popup">OK</div>
			<img src="../img/kronon/kronon_question.png" class="pop-img">
		</div>
	</div>
</div>
<!--エラーまたは完了ポップアップここまで-------------------------------------------------------------->
<!--エラーまたは完了ポップアップ------------------------------------------------------------------->
<div id = "error2"  class="popup-wrapper error-popup">
	<div class="pop-container">
		<div class="close-popup">
			<i class="fa fa-2x fa-times"></i>
		</div>
		<div class="pop-container-inner">
			<div class="message-container">
					<p>日付かタイトルを入力してね</p>
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