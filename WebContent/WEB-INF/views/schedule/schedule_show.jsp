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
<article class="article">


 <div class="blackboard-container">
      <div class="blackboard-inner">
        <div class="blackboard-head">
          <div class="today-list">2020/7/26(日)の予定</div>
			<div class="hanrei">
				<div class="hanrei-item hanrei-item1"></div> : オフィス
				<div class="hanrei-item hanrei-item2"></div> : 在宅
				<div class="hanrei-item hanrei-item3"></div> : 外出
			</div>
        </div>
        <div id="blackboard-table"> <div id="name-container"></div></div>
      </div>
    </div>

<input type="hidden" id="list" value='${json}' style="display:none">
<input type="hidden" id="name-list" value='${json_name}' style="display:none">

</article>
<%@ include file="/WEB-INF/views/layout/common/footer.jsp" %>
<script src="/kronon/js/common/common.js"></script>
<script src="/kronon/js/schedule_show.js"></script>
<script src="/kronon/js/schedule_show_event.js"></script>
</body>
</html>