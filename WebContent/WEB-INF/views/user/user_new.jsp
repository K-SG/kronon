<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/kronon/css/app.css" rel="stylesheet" type="text/css">
<link href="/kronon/css/common.css" rel="stylesheet" type="text/css">
<link href="/kronon/css/usernew_popup.css" rel="stylesheet" type="text/css">
<link href="/kronon/css/jquery.confirm.css" rel="stylesheet">
<link href="/kronon/css/user_new.css" rel="stylesheet" type="text/css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<script src="./jquery.confirm/jquery.confirm/jquery.confirm.js"></script>

<script type="text/javascript">
	function onButtonClick() {
		target1 = document.getElementById("confirmUserName");
		target1.innerText = document.forms.user_create_form.userName.value;
		target2 = document.getElementById("confirmMail");
		target2.innerText = document.forms.user_create_form.mail.value;
		target3 = document.getElementById("confirmPassword");
		tmp = document.forms.user_create_form.password1.value.length;
		var str = '';
		for (var i = 0; i < tmp; i++) {
			str += '＊';
		}
		target3.innerText = str;
	}
</script>

<title>アカウント作成</title>

</head>

<body>

	<article>



		<div class="user-create-content">
			<div class="user-create-area1">予定管理システム～くろのん～</div>
			<div class="user-create-area2">アカウント作成</div>
			<div class="user-create-area3">
				表示名は日本語で15文字以内、パスワードは半角英数字で8～20文字、<br>
				大文字・小文字・数字を必ず使用して設定してください。
			</div>


			<form action="user/usercreate" method="post" id="user_create_form"
				onsubmit="return checkUserCreate()" name="user_create_form">
				<div class="user-create-input1">
					<input type="text" id="userName" name="userName" maxlength="15" placeholder="表示名" value=${userName } >

				</div>
				<div class="user-create-input2">
					<input type="text" id="mail" name="mail" size="100" maxlength="100"
						placeholder="メールアドレス" value=${mail }>
				</div>
				<div class="user-create-input3">
					<input type="password" id="password1" name=password maxlength="20"
						placeholder="パスワード" value=${password } >
				</div>
				<div class="user-create-input4">
					<input type="password" id="password2" maxlength="20"
						placeholder="パスワード確認" value=${password } >
				</div>


				<input type="text" id="flag" value=${popFlag }>

<!-- 新規登録ボタン 下に配置 -->

				<!--空欄チェックポップアップ--popFlag2----------------------------------------------------------------->
				<div id="emp-error" class="popup-wrapper error-popup">
					<div class="pop-container">
						<div class="close-popup">
							<i class="fa fa-2x fa-times"></i>
						</div>
						<div class="pop-container-inner">
							<div class="message-container">
								<p>入力されていない項目があるよ！</p>
							</div>
							<div class="ok-button close-popup">OK</div>
							<img src="img/kronon/kronon_question.png" class="pop-img">
						</div>
					</div>
				</div>
				<!--空欄チェックポップアップここまで-------------------------------------------------------------->


				<!--メール入力条件チェックポップアップ-------popFlag3------------------------------------------------------------>
				<div id="mail-error" class="popup-wrapper error-popup">
					<div class="pop-container">
						<div class="close-popup">
							<i class="fa fa-2x fa-times"></i>
						</div>
						<div class="pop-container-inner">
							<div class="message-container">
								<p>メールアドレスが不正だよ！</p>
							</div>
							<div class="ok-button close-popup">OK</div>
							<img src="img/kronon/kronon_question.png" class="pop-img">
						</div>
					</div>
				</div>
				<!--メール入力条件チェックポップアップここまで-------------------------------------------------------------->


				<!--パスワード入力条件チェックポップアップ-------popFlag4------------------------------------------------------------>
				<div id="pass-check-error" class="popup-wrapper error-popup">
					<div class="pop-container">
						<div class="close-popup">
							<i class="fa fa-2x fa-times"></i>
						</div>
						<div class="pop-container-inner">
							<div class="message-container">
								<p>パスワードの条件を満たしていないよ！</p>
							</div>
							<div class="ok-button close-popup">OK</div>
							<img src="img/kronon/kronon_question.png" class="pop-img">
						</div>
					</div>
				</div>
				<!--パスワード入力条件チェックポップアップここまで-------------------------------------------------------------->

				<!--パスワード不一致チェックポップアップ-------popFlag5------------------------------------------------------------>
				<div id="pass-notsame-error" class="popup-wrapper error-popup">
					<div class="pop-container">
						<div class="close-popup">
							<i class="fa fa-2x fa-times"></i>
						</div>
						<div class="pop-container-inner">
							<div class="message-container">
								<p>パスワードが一致していないよ！</p>
							</div>
							<div class="ok-button close-popup">OK</div>
							<img src="img/kronon/kronon_question.png" class="pop-img">
						</div>
					</div>
				</div>
				<!--パスワード不一致チェックポップアップここまで-------------------------------------------------------------->

				<!--メール登録済みチェックポップアップ-------popFlag1------------------------------------------------------------>
				<div id="mail-same-error" class="popup-wrapper error-popup">
					<div class="pop-container">
						<div class="close-popup">
							<i class="fa fa-2x fa-times"></i>
						</div>
						<div class="pop-container-inner">
							<div class="message-container">
								<p>メールアドレスが既に登録されているよ！</p>
							</div>
							<div class="ok-button close-popup">OK</div>
							<img src="img/kronon/kronon_question.png" class="pop-img">
						</div>
					</div>
				</div>
				<!--メール登録済みチェックポップアップここまで-------------------------------------------------------------->


				<!--登録完了チェックポップアップ-------popFlag1------------------------------------------------------------>
				<div id="input-cmp" class="popup-wrapper error-popup">
					<div class="pop-container">
						<div class="close-popup">
							<i class="fa fa-2x fa-times"></i>
						</div>
						<div class="pop-container-inner">
							<div class="message-container">
								<p>登録が完了したよ！</p>
							</div>
							<div class="ok-button close-popup">OK</div>
							<img src="img/kronon/kronon_question.png" class="pop-img">
						</div>
					</div>
				</div>
				<!--登録完了チェックポップアップここまで-------------------------------------------------------------->


				<!--内容確認ポップアップ--------------------popFlag6--------------------------------------------->
				<div id="confirm-pop" class="popup-wrapper confirm-popup">
					<div class="pop-container pop-container-large">
						<div class="close-popup">
							<i class="fa fa-2x fa-times"></i>
						</div>
						<div class="pop-container-inner">
							<div class="message-container-large">
								<h2 class="message-title">この内容で登録するよ。</h2>
								<table class="popup-table">
									<tr>
										<th class="th">表示名：</th>
										<td><div id="confirmUserName"></div></td>
									</tr>
									<tr>
										<th>メールアドレス</th>
										<td><div id="confirmMail"></div></td>
									</tr>
									<tr>
										<th>パスワード</th>
										<td><div id="confirmPassword"></div></td>
									</tr>
								</table>
							</div>

							<!-- <form action="user/usercreate" method="post"> -->

    					  <%-- <input type="hidden" name="userName" value=${userName }>
    						<input type="hidden" name="mail" value=${mail }>
							<input type="hidden" name="password" value=${password }> --%>

							<input type="button" id="user-create-button2"
							class="ok-button" value="OK" onclick="clickEvent()" />

							<!-- <button name="btnClickEvent" onclick="clickEvent()">送信したい</button> -->
							 <!-- <div class="ok-button">OK</div> -->
							<!-- </form> -->


							<div class="ng-button close-popup">キャンセル</div>
							<img src="img/kronon/kronon_question.png" class="pop-img">
						</div>
					</div>
				</div>

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
							<a href="LoginServlet"><div class="ok-button">OK</div></a>
							<div class="ng-button close-popup">キャンセル</div>
							<img src="img/star/star_angry.png" class="pop-img-top">
						</div>
					</div>
				</div>
				<!--本当に戻りますかポップアップここまで------------------------------------------------------------------->

<!-- 新規登録のボタン -->
				<!-- <input type="button" value="新規登録" id="user-create-button"
					class="ok-button" onclick="onButtonClick();" /> -->
					<input type="submit" value="新規登録" id="user-create-button"
					class="ok-button" onclick="onButtonClick();" >

	 		</form>



		</div>


		<div class="user-create-button-return">
			<img alt="戻る" src="/kronon/img/back_buttom.png" class="back-popup-button">
		</div>




	</article>
	<script src="js/common.js"></script>
	<script src="js/jquery.confirm.js"></script>
	<script src="js/user_new.js"></script>
</body>
</html>