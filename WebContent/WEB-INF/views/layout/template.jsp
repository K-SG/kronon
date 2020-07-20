<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="common/link.jsp" %>
<title>Insert title here</title>
</head>
<body>

<%@ include file="common/header.jsp" %>
<article>

<!-- ここにコードを書く -->




<!--本当に戻りますかポップアップ------------------------------------------------------------------->
<div class="popup-wrapper back-popup">
  <div class="pop-container">
    <div class="close-popup"> <i class="fa fa-2x fa-times"></i> </div>
    <div class="pop-container-inner">
      <div class="message-container">
        <p>内容は保存されないよ。</p>
        <h2 class="message-title">本当に戻る？</h2>
      </div>
      <a href="#"><div class="ok-button">OK</div></a>
      <div class="ng-button close-popup">キャンセル</div>
      <img src="img/star/star_angry.png" class="pop-img-top"> </div>
  </div>
</div>
<!--本当に戻りますかポップアップここまで------------------------------------------------------------------->

<!--本当に戻りますかポップアップ表示用ボタン---->
<div class="ok-button back-popup-button">戻る</div>





</article>
<%@ include file="common/footer.jsp" %>
<script src="js/common.js"></script>
</body>
</html>