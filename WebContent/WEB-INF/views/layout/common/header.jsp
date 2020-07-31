<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	<header>
 <div class="content">
		<ul class="top-head">
			<li class="system-name"><a href="calendar">予定管理システム　～くろのん～</a></li>
			<li class="company-name"><img class="top-head-image" alt="くろ☆のすロゴ" src="/kronon/img/star/logo_header.png">（株）くろ☆のす</li>
		</ul>
		<ul class="nav">
			<li><a href="calendar">予定確認</a></li>
	  		<li><a href="schedulenew">予定登録</a></li>
	  		<li><a href="actualindex">実績確認</a></li>
			<div class="nav-right">
				<div class="nav-right-img">
					<a type="button" class="logout-button">
					<img class="logout-icon" alt="ログアウト" src="/kronon/img/logout_icon.png"></a>
				</div>
				<div class="nav-right-user">
					<c:out value="${userName}"></c:out>としてログイン中</div>
			</div>
		</ul>
	</div>
	<div class="popup-wrapper back-popup">
  <div class="pop-container">
    <div class="close-popup"> <img src="/kronon/img/close_button_orange.png" alt="閉じる" class="back-button"> </div>
    <div class="pop-container-inner">
      <div class="message-container">
        <p> </p><br>
        <h2 class="message-title">本当にログアウトする？</h2>
      </div>
      <a href="../logout"><span class="ok-button">OK</span></a>
      <div class="ng-button close-popup">キャンセル</div>
      <img src="../img/star/star_angry.png" class="pop-img-top"> </div>
  	</div>
	</div>
    <script src="../js/logout.js"></script>
    <script src="../js/common/common.js"></script>

	</header>
