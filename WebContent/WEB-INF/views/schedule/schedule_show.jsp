<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/WEB-INF/views/layout/common/link.jsp" %>
<link rel="stylesheet" href="/kronon/css/schedule_table.css">
<title>予定一覧</title>
</head>
<body>

<%@ include file="/WEB-INF/views/layout/common/header.jsp" %>
<article class="article">

	<form action="../user/scheduleshowall" method="post" id="left-form">
		<input type="hidden" name="flag" value="0"> <input
			type="hidden" id="scheduleDate" name="scheduleDate" value="${scheduleDate}">
		<div class="left-button-container">
			<img src="../img/left_button.png" alt="left" id="left"
				class="left triangle-button">
		</div>
	</form>
	<div class="blackboard-container">

		<div class="blackboard-inner">
			<div class="blackboard-head">
				<div class="today-list">
					<c:out value="${displayDate}" />
					の予定
				</div>
				<div class="hanrei">
					<div class="hanrei-item hanrei-item1"></div>
					: オフィス
					<div class="hanrei-item hanrei-item2"></div>
					: 在宅
					<div class="hanrei-item hanrei-item3"></div>
					: 外出
				</div>
			</div>
			<div id="blackboard-table">
				<div id="name-container"></div>
			</div>
		</div>

	</div>
	<form action="../user/scheduleshowall" method="post" id="right-form">
		<input type="hidden" name="flag" value="1"> <input
			type="hidden" name="scheduleDate" value="${scheduleDate}">
		<div class="title-content">
			<img src="../img/right_button.png" alt="right" id="right"
				class="right triangle-button">
		</div>
	</form>

	<div><a href="../user/calendar"><img src="../img/back_buttom.png" alt="back-buttom" class="back-btn"></a></div>

	<input type="hidden" id="list" value='${json}' style="display:none">
	<input type="hidden" id="name-list" value='${json_name}' style="display:none">

</article>
<%@ include file="/WEB-INF/views/layout/common/footer.jsp" %>
<script src="/kronon/js/common/common.js"></script>
<script src="/kronon/js/schedule_show.js"></script>
<script src="/kronon/js/schedule_show_event.js"></script>
</body>
</html>