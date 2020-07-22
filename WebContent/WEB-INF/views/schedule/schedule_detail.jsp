<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/kronon/css/common/app.css" rel="stylesheet" type="text/css">
<link href="/kronon/css/common/common.css" rel="stylesheet" type="text/css">
<link href="/kronon/css/common/blackboard.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<title>予定詳細画面</title>

</head>
<body>

<%@ include file="/WEB-INF/views/layout/common/header.jsp" %>
<article>

 <div class=white-text>
    <div class=blackboard>

      <table class=schedule_detail>

        <tr>
          <td><c:out value="${owner}" /></td>
          <td><c:out value="${actualTime}" /></td>
        </tr>

        <tr>
          <td><c:out value="${scheduleDate}" />
          <c:out value="${startTime}" />～
          <c:out value="${endTime}" /></td>


          	<c:if test="${place=='オフィス'}" >
			<td class=show-place1><c:out value="${place}" /></td>
			</c:if>

			<c:if test="${place=='在宅'}" >
			<td class=show-place2><c:out value="${place}" /></td>
			</c:if>

			<c:if test="${place=='外出'}" >
			<td class=show-place3><c:out value="${place}" /></td>
			</c:if>

        </tr>

        <tr>
          <td colspan="2">
            <c:out value="${title}" />
          </td>
        </tr>

        <tr>
          <td colspan="2">
            <c:out value="${content}" />
          </td>
        </tr>

      </table>

    </div>
  </div>

<div class="ok-button">修正</div>
<div class="ok-button">実績入力</div>
<div class="ok-button">戻る</div>

<c:if test="${owner=={sessionScope.userName}}" >
<div class="ok-button">修正</div>
<div class="ok-button">実績入力</div>
<div class="ok-button">戻る</div>
</c:if>

</article>
<%@ include file="/WEB-INF/views/layout/common/footer.jsp" %>
<script src="js/common/common.js"></script>
</body>
</html>