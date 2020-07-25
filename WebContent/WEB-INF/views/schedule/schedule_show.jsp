<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/WEB-INF/views/layout/common/link.jsp" %>
<link rel="stylesheet" href="/kronon/css/schedule_table.css">
<title>Insert title here</title>
</head>
<body>

<%@ include file="/WEB-INF/views/layout/common/header.jsp" %>
<article>


 <div class="blackboard-container">
      <div class="blackboard">
        <div class="blackboard-head">
          <div class="today-list">2020/7/26(日)の予定</div>
			<div class="hanrei">
				<div class="hanrei-item hanrei-item1"></div> : オフィス
				<div class="hanrei-item hanrei-item2"></div> : 在宅
				<div class="hanrei-item hanrei-item3"></div> : 外出
			</div>
        </div>
        <div id="blackboard-table"> <div class="name-list"><div class="name1">樋口</div></div></div>
      </div>
    </div>




</article>
<%@ include file="/WEB-INF/views/layout/common/footer.jsp" %>
<script src="js/common/common.js"></script>
</body>
</html>