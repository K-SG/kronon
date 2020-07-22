<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/WEB-INF/views/layout/common/link.jsp" %>
<title>予定詳細画面</title>
<link rel="stylesheet" href="/kronon/css/blackboard.css">
</head>
<body>

<%@ include file="/WEB-INF/views/layout/common/header.jsp" %>
<article>

 <div class=white-text>
    <div class=blackboard>

      <table class=schedule_detail>

        <tr>
          <td>名前：〇〇〇〇〇〇〇〇〇</td>
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