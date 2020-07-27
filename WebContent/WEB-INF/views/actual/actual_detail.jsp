<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/kronon/css/actual_detail.css" rel="stylesheet" type="text/css">
<%@ include file="../layout/common/link.jsp" %>
<title>実績詳細画面</title>

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
          <td><c:out value="${scheduleDate}" /> <c:out value="${startTime}" />～
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
<div class="kronon-banzai"><img alt="banzai" src="./img/kronon/kronon_banzai.png"></div>
<div class="button-return"><img alt="戻る" src="/kronon/img/back_buttom.png" class="back-popup-button"></div>



<div class="flex_test-box">
    <div class="flex_test-item">
        <div class="ok-button">修正</div>
        <input type="submit" class="ok-button" value="修正">
        <a href="kronon/user/"><div class="modify-button">修正</div></a>
    </div>

    <div class="flex_test-item">
       <div class="ok-button large-popup-button">削除</div>
    </div>
</div>


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
            <td><c:out value="${owner}" /></td>
          </tr>
          <tr>
            <th>予定日時：</th>
            <td><c:out value="${scheduleDate}" />
          <c:out value="${startTime}" />～
          <c:out value="${endTime}" /></td>
          </tr>
          <tr>
            <th>タイトル：</th>
            <td><c:out value="${title}" /></td>
          </tr>
          <tr>
            <th>内容：</th>
            <td><c:out value="${content}" /></td>
          </tr>

          <tr>
            <th class="last-table">コメント：</th>
            <td class="last-table"><c:out value="${comment}" /></td>
          </tr>
        </table>
      </div>

      <input type="submit" id="user-create-button2" class="ok-button next-button" value="OK"/>
      <div class="ng-button close-popup">キャンセル</div>
      <img src="img/kronon/kronon_question.png" class="pop-img"> </div>
  </div>
</div>
<!--内容確認ポップアップここまで----------------------------------------------------------------->

  <!--削除完了ポップアップ------------------------------------------------------------------->
<div id="input-cmp" class="popup-wrapper error-popup complete-popup">
  <div class="pop-container">
    <div class="pop-container-inner">
      <div class="message-container">
        <p>削除が完了したよ！</p>
      </div>
    <div class="ok-button next-popup">OK</div>
       <img src="img/kronon/kronon_question.png" class="pop-img">
    </div>
  </div>
</div>
  <!--削除完了ポップアップここまで-------------------------------------------------------------->


</article>
<%@ include file="/WEB-INF/views/layout/common/footer.jsp" %>
<script src="js/common/common.js"></script>
<script src="js/actual_detail.js"></script>
</body>
</html>