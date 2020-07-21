<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/app.css" rel="stylesheet" type="text/css">
<link href="css/common.css" rel="stylesheet" type="text/css">
<link href="css/usernew_popup.css" rel="stylesheet" type="text/css">
<link href="css/user_new.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<title>アカウント作成</title>


        <!-- ↓JavaScriptの処理を追加 -->
        <script type="text/javascript">
            function check(){
                if (user_create_form.userName.value == ""){
                    //条件に一致する場合(メールアドレスが空の場合)
                    alert("メールアドレスを入力してください");    //エラーメッセージを出力
                    return false;    //送信ボタン本来の動作をキャンセルします
                }else{
                    //条件に一致しない場合(メールアドレスが入力されている場合)
                    return true;    //送信ボタン本来の動作を実行します
                }
            }
        </script>


</head>
<body>
<%--
<%@ include file="../layout/common/header.jsp" %> --%>
<article>



<div class="user-create-content">

<div class="user-create-area1">予定管理システム～くろのん～</div>
<div class="user-create-area2">アカウント作成</div>
<div class="user-create-area3">表示名は日本語で15文字以内、パスワードは半角英数字で8～20文字、<br>
	大文字・小文字・数字を必ず使用して設定してください。</div>




	<!-- <form action="/user/usercreate" method="post" name="user_create_form"> -->

	<form action="/user/usercreate" method="post" name="user_create_form" onsubmit="return checkUserCreate()">
		<div class="user-create-input1">
			<input type="text" id="userName" maxlength="15" placeholder="表示名" />
		</div>
		<!-- <input type="submit" value="送信" onclick="chkEmp(this.form.userName.value)"> -->
		<!-- </form> -->
		<!-- <form action="/user/usercreate" method="post" name="user_create_form"> -->
		<div class="user-create-input2">
	<!-- 		<input type="text" value="" name="mail" maxlength="100" placeholder="メールアドレス" />
	 -->
    	<input type="text" id="mail" size="100" maxlength="100"   placeholder="メールアドレス" />
		</div>
		 <!-- <input type="button" value="メールアドレスチェック" onclick="chkRegEmail(this.form.mail.value)" /> -->


		<div class="user-create-input3">
			<input type="text" id="password1" maxlength="20" placeholder="パスワード" />
		</div>
		<div class="user-create-input4">
			<input type="text" id="password2" maxlength="20" placeholder="パスワード確認" />
		</div>
		<div class="user-create-button"></div>


		 <!--エラーまたは完了ポップアップ表示用ボタン---->
  			<div class="ok-button large-popup-button">新規登録</div>
  			<!--
  			<input class="ok-button large-popup-button" type="submit" value="新規登録"/> -->

  			<input type="submit" value="新規登録"/>

</form>






<!--空欄チェックポップアップ--popFlag1----------------------------------------------------------------->
<div id="emp-error" class="popup-wrapper error-popup">
	<div class="pop-container">
	  <div class="close-popup"> <i class="fa fa-2x fa-times"></i> </div>
	  <div class="pop-container-inner">
		<div class="message-container">
		  <p>入力されていない項目があるよ！</p>
		</div>
		<div class="ok-button close-popup">OK</div>
		<img src="../img/kronon_question.png" class="pop-img"> </div>
	</div>
  </div>
  <!--空欄チェックポップアップここまで-------------------------------------------------------------->




<!--メール入力条件チェックポップアップ-------popFlag2------------------------------------------------------------>
<div id="mail-error" class="popup-wrapper error-popup">
	<div class="pop-container">
	  <div class="close-popup"> <i class="fa fa-2x fa-times"></i> </div>
	  <div class="pop-container-inner">
		<div class="message-container">
		  <p>メールアドレスが不正だよ！</p>
		</div>
		<div class="ok-button close-popup">OK</div>
		<img src="../img/kronon_question.png" class="pop-img"> </div>
	</div>
  </div>
  <!--メール入力条件チェックポップアップここまで-------------------------------------------------------------->




<!--パスワード入力条件チェックポップアップ-------popFlag3------------------------------------------------------------>
<div id="pass-check-error" class="popup-wrapper error-popup">
	<div class="pop-container">
	  <div class="close-popup"> <i class="fa fa-2x fa-times"></i> </div>
	  <div class="pop-container-inner">
		<div class="message-container">
		  <p>パスワードの条件を満たしていないよ！</p>
		</div>
		<div class="ok-button close-popup">OK</div>
		<img src="../img/kronon_question.png" class="pop-img"> </div>
	</div>
  </div>
  <!--パスワード入力条件チェックポップアップここまで-------------------------------------------------------------->





  <!--パスワード不一致チェックポップアップ-------popFlag4------------------------------------------------------------>
<div id="pass-notsame-error" class="popup-wrapper error-popup">
	<div class="pop-container">
	  <div class="close-popup"> <i class="fa fa-2x fa-times"></i> </div>
	  <div class="pop-container-inner">
		<div class="message-container">
		  <p>パスワードが一致していないよ！</p>
		</div>
		<div class="ok-button close-popup">OK</div>
		<img src="../img/kronon_question.png" class="pop-img"> </div>
	</div>
  </div>
  <!--パスワード不一致チェックポップアップここまで-------------------------------------------------------------->



<!--内容確認ポップアップ----------------------------------------------------------------->
  <div id="confirm-pop" class="popup-wrapper confirm-popup">
	<div class="pop-container pop-container-large">
	  <div class="close-popup"> <i class="fa fa-2x fa-times"></i> </div>
	  <div class="pop-container-inner">
		<div class="message-container-large">
		  <h2 class="message-title">この内容で登録するよ。</h2>
		  <table class="popup-table">
			<tr>
			  <th class="th">名前：</th>
			  <td><%-- <c:out value="${userBean.userName}" /> --%></td>
			</tr>
			<tr>
			  <th>予定日時：</th>
			  <td>2020/7/11(月) 10:00-11:00</td>
			</tr>
			<tr>
			  <th>タイトル：</th>
			  <td>外部設計レビュー</td>
			</tr>
			<tr>
			  <th class="last-table">内容：</th>
			  <td class="last-table">森岡さん作成外部設計書レビュー<br>
				〆切7/13</td>
			</tr>
		  </table>
		</div>
		<a href="#"><div class="ok-button">OK</div></a>
		<div class="ng-button close-popup">キャンセル</div>
		<img src="../img/kronon_question.png" class="pop-img"> </div>
	</div>
  </div>
  <!--内容確認ポップアップここまで----------------------------------------------------------------->








<%--   <!--エラーまたは完了ポップアップ表示用ボタン--->
  <div  class="ok-button small-popup-button">エラー/完了</div>
</c:if> --%>


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
		<img src="../img/star_angry.png" class="pop-img-top"> </div>
	</div>
  </div>
  <!--本当に戻りますかポップアップここまで------------------------------------------------------------------->


<%--
<c:if test="${popFlag!=0}"> --%>


  <div class="user-create-button-return">
  <!--エラーまたは完了ポップアップ表示用ボタン----->
  <div class="ok-button back-popup-button">戻る</div>
</div>

<%--
</c:if> --%>


<!--
</form> -->
</div>

</article>
<%--
<%@ include file="../layout/common/footer.jsp" %> --%>
<script src="js/common.js"></script>
<script src="js/user_new.js"></script>
</body>
</html>