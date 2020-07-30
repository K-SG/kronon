<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/kronon/css/app.css" rel="stylesheet" type="text/css">
<link href="/kronon/css/common.css" rel="stylesheet" type="text/css">
<link href="../css/common.css" rel="stylesheet" type="text/css">
<link href="/kronon/css/user_new.css" rel="stylesheet" type="text/css">
<link href="../css/user_new.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<head>
<title>アカウント作成</title>
</head>
<body>
	<article>
		<div class="create-user-content">
			<div class="create-user-system-name">予定管理システム～くろのん～</div>
			<div class="create-user-title">アカウント作成</div>
			<div class="create-user-note">表示名は日本語で15文字以内、パスワードは半角英数字で8～20文字、<br>大文字・小文字・数字を必ず使用して設定してください。</div>

			<!-- <form action="/WEB-INF/views/user/usercreate" method="post" id="user_create_form" class="user_create_form"> -->
			<form action="http://localhost:8080/kronon/usercreate" method="post" class="user_create_form" id="id_create_form" >
				<input type="text" name="userName" id="create_name" class="create_textbox" placeholder="表示名" maxlength="15" value=${username }><br>
				<input type="text" name="mail" id="create_mail" class="create_textbox" placeholder="メールアドレス" maxlength="100" value="${mail}"><br>
				<input type="password" name="password" id="create_password1" class="create_textbox" placeholder="パスワード" maxlength="20" value=${password }><br>
				<input type="password" name="password" id="create_password2" class="create_textbox" placeholder="パスワード確認" maxlength="20" value=${password }><br>
				<input type="hidden" id="flag" value="${popFlag}"> <input type="button" class="user-create-button" value="新規登録">

				<!--エラーポップアップ------------------------------------------------------------------->
				<div class="popup-wrapper error-popup create-popup">
					<div class="pop-container">
						<div class="close-popup"><i class="fa fa-2x fa-times"></i></div>
						<div class="pop-container-inner">
							<div class="message-container"><p class=create-msg>メールアドレスが<br>既に登録されているよ！</p></div>
							<div class="ok-button close-popup">OK</div>
							<img src="/kronon/img/kronon/kronon_question.png" class="pop-img kronon-question">
						</div>
					</div>
				</div>
				<!--エラーポップアップここまで-------------------------------------------------------------->

				<!--登録完了ポップアップ------------------------------------------------------------------->
				<div class="popup-wrapper error-popup complete-popup">
					<div class="pop-container">
						<div class="close-popup"><i class="fa fa-2x fa-times"></i></div>
						<div class="pop-container-inner">
							<div class="message-container"><p class=create-msg></p></div>
							<div class="ok-button next-popup">OK</div>
							<img src="/kronon/img/star/star_nomal.png" class="pop-img-star star-nomal">
						</div>
					</div>
				</div>
				<!--登録完了ポップアップここまで-------------------------------------------------------------->

				<!--内容確認ポップアップ---------------------------------------------------------------->
				<div class="popup-wrapper confirm-popup">
					<div class="pop-container pop-container-large">
						<div class="close-popup"><i class="fa fa-2x fa-times"></i></div>
						<div class="pop-container-inner">
							<div class="message-container-large">
								<h2 class="message-title">この内容で登録するよ。</h2>
								<table class="popup-table">
									<tr>
										<th class="th">表示名：</th>
										<td><div id="confirmUserName"></div></td>
									</tr>
									<tr>
										<th>メールアドレス：</th>
										<td  class="confirmMailView"><span ><div id="confirmMail"></div></span></td>
									</tr>
									<tr>
										<th>パスワード：</th>
										<td><div id="confirmPassword"></div></td>
									</tr>
								</table>
							</div>
							<div class="ok-button large-pop-ok-buttom" id="submit-user">OK</div>
							<!-- <div class="ok-button"><input type="submit" class="ok-button-inner" value="OK" /></div> -->
							<div class="ng-button close-popup large-pop-ok-buttom">キャンセル</div>
							<img src="/kronon/img/star/star_nomal.png" class="pop-large-img-top star-nomal">
						</div>
					</div>
				</div>
			</form>
			<!--内容確認ポップアップここまで----------------------------------------------------------------->

			<!--本当に戻りますかポップアップ------------------------------------------------------------------->
			<div class="popup-wrapper back-popup">
				<div class="pop-container">
					<div class="close-popup">
						<i class="fa fa-2x fa-times"></i>
					</div>
					<div class="pop-container-inner">
						<div class="message-container">
							<p>内容は保存されないよ。</p>
							<h2 class="message-title">本当に戻る？</h2>
						</div>
						<a href="/kronon/login"><div class="ok-button">OK</div></a>
						<div class="ng-button close-popup">キャンセル</div>
						<img src="/kronon/img/star/star_angry.png" class="pop-img-top star-angry">
					</div>
				</div>
			</div>
			<!--本当に戻りますかポップアップここまで------------------------------------------------------------------->
		</div>
		<div class="user-create-button-return"><img alt="戻る" src="/kronon/img/back_buttom.png" class="back-popup-button"></div>
	</article>

	<script src="js/common.js"></script>
	<script src="../js/common.js"></script>
	<script src="js/user_new.js"></script>
	<script src="../js/user_new.js"></script>
</body>
</html>
