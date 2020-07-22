<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/kronon/css/common/app.css" rel="stylesheet" type="text/css">
<link href="/kronon/css/common/common.css" rel="stylesheet" type="text/css">
<link href="/kronon/css/common/blackboard.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<title>予定詳細画面</title>

</head>
<body>

<%@ include file="/WEB-INF/views/layout/common/header.jsp" %>
<article>

 <div class=white-text>
    <div class=blackboard>

      <table class=schedule_detail>

        <tr>
          <td><c:out value="${userName}" /></td>
          <td>実績：2時間30分</td>
        </tr>

        <tr>
          <td>2020/07/11(月) 11:00～12:00</td>
          <td class=show-place>作業（オフィス）</td>
        </tr>

        <tr>
          <td colspan="2">
            外部設計書レビュー
          </td>
        </tr>

        <tr>
          <td colspan="2">
            森岡さん作成の外部設計書
          </td>
        </tr>

      </table>

    </div>
  </div>

</article>
<%@ include file="/WEB-INF/views/layout/common/footer.jsp" %>
<script src="js/common/common.js"></script>
</body>
</html>