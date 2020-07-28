<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ include file="/WEB-INF/views/layout/common/link.jsp"%>
    <title>エラー画面</title>
    <link href="/kronon/css/error.css" rel="stylesheet" type="text/css">
  </head>
  <body>
    <%@ include file="/WEB-INF/views/layout/common/header.jsp"%>
    <article>
      <div id="error-msg">お探しのページは見つからなかったよ</div>
	  <img alt="エラーロゴ" id="error-img" src="img/kronon/kronon_komatta.png">
    </article>
    <%@ include file="/WEB-INF/views/layout/common/footer.jsp"%>
    <script src="js/common/common.js"></script>
  </body>
</html>