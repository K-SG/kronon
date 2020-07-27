<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>ポップアップ雛形</title>
<link href="/kronon/css/common/common.css" rel="stylesheet" type="text/css">
<link href="/kronon/css/common/common.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
</head>

<body>

<!--エラーまたは完了ポップアップ------------------------------------------------------------------->
<div class="popup-wrapper error-popup">
  <div class="pop-container">
    <div class="close-popup"> <i class="fa fa-2x fa-times"></i> </div>
    <div class="pop-container-inner">
      <div class="message-container">
        <p>メールアドレス・パスワードが違うよ。</p>
      </div>
      <div class="ok-button close-popup">OK</div>
      <img src="img/kronon/kronon_question.png" class="pop-img"> </div>
  </div>
</div>
<!--エラーまたは完了ポップアップここまで-------------------------------------------------------------->

<!--エラーまたは完了ポップアップ表示用ボタン--->
<div class="ok-button small-popup-button">エラー/完了</div>



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



<!--内容確認ポップアップ----------------------------------------------------------------->
<div class="popup-wrapper confirm-popup">
  <div class="pop-container pop-container-large">
    <div class="close-popup"> <i class="fa fa-2x fa-times"></i> </div>
    <div class="pop-container-inner">
      <div class="message-container-large">
        <h2 class="message-title">この内容で登録するよ。</h2>
        <table class="popup-table">
          <tr>
            <th class="th">名前：</th>
            <td>樋口</td>
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
      <img src="img/kronon/kronon_question.png" class="pop-img"> </div>
  </div>
</div>
<!--内容確認ポップアップここまで----------------------------------------------------------------->

<!--内容確認ポップアップ表示用ボタン----->
<div class="ok-button large-popup-button">新規登録</div>





<script src="/kronon/js/common/common.js"></script>
</body>
</html>
