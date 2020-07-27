<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/kronon/css/scheduledetail.css" rel="stylesheet" type="text/css">
<%@ include file="../layout/common/link.jsp" %>
<title>予定詳細画面</title>

</head>
<body>

<%@ include file="/WEB-INF/views/layout/common/header.jsp" %>
<article>

 <div class=white-text>
    <div class=blackboard>

      <table class=schedule_detail>

        <tr>
          <td>名前:<c:out value="${scheduleBean.userName}" /></td>
          <td>実績時間:<c:out value="${scheduleBean.actualTime}" /></td>
        </tr>

        <tr>
          <td><c:out value="${scheduleBean.scheduleDate}" /> <c:out value="${scheduleBean.startTime}" />～
          <c:out value="${scheduleBean.endTime}" /></td>


          	<c:if test="${scheduleBean.place=='1'}" >
			<td class=show-place1>オフィス</td>
			</c:if>

			<c:if test="${scheduleBean.place=='2'}" >
			<td class=show-place2>在宅</td>
			</c:if>

			<c:if test="${scheduleBean.place=='3'}" >
			<td class=show-place3>外出</td>
			</c:if>

        </tr>

        <tr>
          <td colspan="2">
            <c:out value="${scheduleBean.title}" />
          </td>
        </tr>

        <tr>
          <td colspan="2">
            <c:out value="${scheduleBean.content}" />
          </td>
        </tr>

      </table>

    </div>
  </div>
<div class="kronon-banzai"><img alt="banzai" src="./img/kronon/kronon_banzai.png"></div>
<div class="ok-button back-popup-button">戻る</div>

<!-- sesseionスコープのuserIDとスケジュールのIDを比較 -->
<c:if test="${scheduleBean.userId== userId}" >
<div class="flex_test-box">
    <div class="flex_test-item">
        <a href="scheduleEdit?schedule_id=${scheduleBean.sheduleId}">
        <div class="ok-button">修正</div>
        </a>
    </div>
    <div class="flex_test-item">
    <a href="scheduleEdit?schedule_id=${scheduleBean.sheduleId}">
        <div class="ok-button">実績入力</div>
        </a>
    </div>
    <div class="flex_test-item">
       <div class="ok-button large-popup-button">削除</div>
    </div>
</div>
</c:if>


<!--内容確認ポップアップ----------------------------------------------------------------->
<div class="popup-wrapper confirm-popup">
  <div class="pop-container pop-container-large">
    <div class="close-popup"> <i class="fa fa-2x fa-times"></i> </div>
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
            <td><c:out value="${scheduleBean.scheduleDate}" />
          <c:out value="${scheduleBean.startTime}" />～
          <c:out value="${scheduleBean.endTime}" /></td>
          </tr>
          <tr>
            <th>タイトル：</th>
            <td><c:out value="${scheduleBean.title}" /></td>
          </tr>
          <tr>
            <th class="last-table">内容：</th>
            <td class="last-table"><c:out value="${scheduleBean.content}" /></td>
          </tr>
        </table>
      </div>
      <a href="#"><div class="ok-button">OK</div></a>
      <div class="ng-button close-popup">キャンセル</div>
      <img src="img/kronon/kronon_question.png" class="pop-img"> </div>
  </div>
</div>
<!--内容確認ポップアップここまで----------------------------------------------------------------->



</article>
<%@ include file="/WEB-INF/views/layout/common/footer.jsp" %>
<script src="js/common/common.js"></script>
</body>
</html>