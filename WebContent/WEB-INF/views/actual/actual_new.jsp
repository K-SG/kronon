<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

 <link rel="stylesheet" href="/kronon/css/actual_new.css">
<%@ include file="../layout/common/link.jsp" %>
<title>実績登録</title>
</head>
<body>

<%@ include file="../layout/common/header.jsp" %>

	<article>
		<form action="schedulecreate" method="get" class="actual-new-form">
		<input type="hidden" value="${popFlag}" id="flag">
		<div class="actual-regist-area">
		<div class="loose-leaf"><img src="../img/loose_leaf.svg" alt="loose-leaf" id="loose-leaf"></div>
		  <div class="actual-regist-area-inner">
			<div class="actual-regist-font-lev0">実績登録</div>
			<input type="hidden" name="scheduleId" value="${scheduleBean.scheduleId}" id="set-actual-id">
			<div class="actual-regist-border"></div>
			<div class="table-container">
			<table class="actual-table">
			<tr><td>日付入れるとこ</td><td>場所入れるとこ</td></tr>
			<tr><td>タイトルいれるとこ</td></tr>
			<tr><td>内容</td></tr>
			</table>
			</div>
			<div class="actual-regist-area-1">
				<div class="actual-regist-font-lev1">実績時刻<span>*</span></div>
				<div class="actual-regist-time">
				<input type="hidden" value="${scheduleBean.actualTime}" id="set-start-time">
						<select name="actualHour" id="actual-hour">
							<option value="0">0</option>
							<option value="1">1</option>
							<option value="2">2</option>
							<option value="3">3</option>
							<option value="4">4</option>
							<option value="5">5</option>
							<option value="6">6</option>
							<option value="7">7</option>
							<option value="8">8</option>
							<option value="9">9</option>
							<option value="10">10</option>
							<option value="11">11</option>
							<option value="12">12</option>
						</select>
				</div>
				<div class="actual-regist-time-text">時</div>
				<div class="actual-regist-time">
						<select name="actualMin" id="actual-min">
							<option value="00">00</option>
							<option value="15">15</option>
							<option value="30">30</option>
							<option value="45">45</option>
						</select>
				</div>
				<div class="actual-regist-time-text">分</div>
			</div>
			<div class="actual-regist-area-4">
				<div class="actual-regist-font-lev1">内容</div>
					<div class="actual-regist-content">
						<textarea name="content" id="content" rows="13" cols="40" maxlength="1440" placeholder="予定の内容を1440字以内で入力してください"><c:out value="${scheduleBean.content}" /></textarea>
					</div>
				</div>
		 	</div>
			<div class="kronon-banzai"><img alt="banzai" src="/kronon/img/kronon/kronon_banzai.png"></div>
			<div class=actual-regist-button>
			<!--登録ボタン---->
				<div class=actual-regist-button-left>
				<input type="button" class="ok-button large-popup-button" id="ok-button" value="登録" >
				</div>

			<!--キャンセルボタン----->
				<div class=actual-regist-button-right>
				<input type="button" class="ok-button" id="cancel-button" value="キャンセル">
				</div>
			</div>
			<div class="clear"></div>
	 	 </div>
	</form>

  <!--エラーまたは完了ポップアップ------------------------------------------------------------------->
  <div class="popup-wrapper error-popup">
    <div class="pop-container">
      <div class="close-popup"> <i class="fa fa-2x fa-times"></i> </div>
      <div class="pop-container-inner">
        <div class="message-container">
          <p class=new-msg></p>
        </div>
        <div class="ok-button close-popup">OK</div>
        <img src="/kronon/img/kronon/kronon_komatta.png" class="pop-img"> </div>
      </div>
    </div>
   <!--エラーまたは完了ポップアップここまで-------------------------------------------------------------->



			<!--本当に戻りますかポップアップ------------------------------------------------------------------->
		<div class="popup-wrapper cancel-popup">
		  <div class="pop-container">
			<div class="close-popup"> <i class="fa fa-2x fa-times"></i> </div>
			<div class="pop-container-inner">
			  <div class="message-container">
				<p>内容は保存されないよ。</p>
				<h2 class="message-title">本当に戻る？</h2>
			  </div>
			  <a href="calendar"><div class="ok-button">OK</div></a>
			  <div class="ng-button close-popup">キャンセル</div>
			  <img src="/kronon/img/star/star_angry.png" class="pop-img-top"> </div>
		  </div>
		</div>
		<!--本当に戻りますかポップアップここまで------------------------------------------------------------------->



		<!--内容確認ポップアップ----------------------------------------------------------------->
		<div class="popup-wrapper confirm-popup2" id="confirm-popup2">
		  <div class="pop-container pop-container-large">
			<div class="close-popup"> <i class="fa fa-2x fa-times"></i> </div>
			<div class="pop-container-inner">
			  <div class="message-container-large">
				<h2 class="message-title">この内容で登録するよ。</h2>
				<table class="popup-table">
				  <tr>
					<th class="th">名前：</th>
					<td><c:out value="${userName }" /></td>
				  </tr>
				  <tr>
					<th>予定日時：</th>
					<td id="time-msg"></td>
				  </tr>
				  <tr>
					<th>実績時間：</th>
					<td id="actual-msg"></td>
				  </tr>
				  <tr>
					<th>タイトル：</th>
					<td id="title-msg"></td>
				  </tr>
				  <tr>
					<th class="last-table">内容：</th>
					<td class="last-table" id="content-msg"></td>
				  </tr>
				</table>
			  </div>
			  <input type="button" class="ok-button"  id="confirm-ok" value="OK">
			  <div class="ng-button close-popup">キャンセル</div>
			  <img src="/kronon/img/kronon/kronon_question.png" class="pop-img"> </div>
		  </div>
		</div>
		<!--内容確認ポップアップここまで----------------------------------------------------------------->

<!--登録完了ポップアップ------------------------------------------------------------------->
		<div class="popup-wrapper complete-popup">
			<div class="pop-container">
				<div class="close-popup"><i class="fa fa-2x fa-times"></i></div>
				<div class="pop-container-inner">
					<div class="message-container"><p class=create-msg></p></div>
					<div class="ok-button next-popup">OK</div>
					<img src="/kronon/img/kronon/kronon_star.png" class="pop-img">
				</div>
			</div>
		</div>
<!--登録完了ポップアップここまで-------------------------------------------------------------->


	</article>
<%@ include file="../layout/common/footer.jsp" %>
<script src="/kronon/js/actual_new.js"></script>
<script src="js/common.js"></script>
</body>
</html>