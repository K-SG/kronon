<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <%@ include file="../layout/common/link.jsp" %>
  <link rel="stylesheet" href="./css/login.css">
  <title>Insert title here</title>
</head>

<body>

  <article>

    <div class="login">

      <div class="login_title">
        予定管理システム～くろのん～
      </div>

      <img class="logo_login" alt="ログインロゴ" src="img/star/logo_login.png">

      <div class="login_company">
        （株）くろ☆のす
      </div>


      <input type="text" name="mail" id="login_mail" class="login_textbox" placeholder="メールアドレス" value=${mail} ><br>
      <input type="password" name="password" id="login_pass" class="login_textbox" placeholder="パスワード"><br>

      <form action="login">
        <input type="button" class="login-button" value="ログイン">
      </form>
      <!-- <div class="login-button">ログイン</div> -->


      <div class="login_account">
        アカウントを持っていないかな？
      </div>

      <form action="usernew">
        <input type="button" class="register-button" value="新規登録">
      </form>

    </article>

  </div>

  <!--エラーまたは完了ポップアップ------------------------------------------------------------------->
  <div class="popup-wrapper error-popup">
    <div class="pop-container">
      <div class="close-popup"> <i class="fa fa-2x fa-times"></i> </div>
      <div class="pop-container-inner">
        <div class="message-container">
          <p class=login_msg>メールアドレス・パスワードが違うよ。</p>
        </div>
        <div class="ok-button close-popup">OK</div>
        <img src="img/kronon/kronon_question.png" class="pop-img"> </div>
      </div>
    </div>
    <!--エラーまたは完了ポップアップここまで-------------------------------------------------------------->

    <!--エラーまたは完了ポップアップ表示用ボタン--->
    <!-- <div class="ok-button small-popup-button">エラー/完了</div> -->


    <script src="js/common.js"></script>
    <script src="js/login.js"></script>
  </body>
  </html>
