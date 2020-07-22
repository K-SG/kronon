<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/WEB-INF/views/layout/common/link.jsp" %>
<link rel="stylesheet" href="../css/calendar.css">
<title>実績一覧</title>
</head>
<body>

<%@ include file="/WEB-INF/views/layout/common/header.jsp" %>
<article>






<!--エラーまたは完了ポップアップ------------------------------------------------------------------->
<div class="popup-wrapper error-popup">
  <div class="pop-container">
    <div class="close-popup"> <i class="fa fa-2x fa-times"></i> </div>
    <div class="pop-container-inner">
      <div class="message-container">
      <!--  メッセージを分岐未入力、日付おかしい-->
        <p>メールアドレス・パスワードが違うよ。</p>
      </div>
      <div class="ok-button close-popup">OK</div>
      <img src="img/kronon/kronon_question.png" class="pop-img"> </div>
  </div>
</div>
<!--エラーまたは完了ポップアップここまで-------------------------------------------------------------->
</article>
<%@ include file="/WEB-INF/views/layout/common/footer.jsp" %>
<script src="../js/common/common.js"></script>
<script src="../js/calendar2.js"></script>
<script src="../js/calendar_event.js"></script>
</body>
</html>