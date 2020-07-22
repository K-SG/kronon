<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/WEB-INF/views/layout/common/link.jsp" %>
<link rel="stylesheet" href="../css/calendar.css">
<title>予定確認</title>
</head>
<body>

<%@ include file="/WEB-INF/views/layout/common/header.jsp" %>
<article>

<input type="hidden" id="list" value='${json}' style="display:none">


 <div class="calendar-container">
      <div class="calendar-container-inner">
        <div class="calendar-title">
          <div class="title-content"><img src="../img/left_button.png" alt="left"  id="left" class="left triangle-button"></div>
          <div class="title-content">
            <h2 id="month"><c:out value="${month}"/></h2>
          </div>
          <div class="title-content">
            <h3 id="year"><c:out value="${year}"/></h3>
          </div>
          <div class="title-content"><img src="../img/right_button.png" alt="right" id="right" class="right triangle-button"></div>
          <div class="clear"></div>
        </div>
        <table class="calendar">
          <thead>
            <!--      <tr>
        <th id="prev">&laquo;</th>
        <th id="title" colspan="5">2020/07</th>
        <th id="next">&raquo;</th>
      </tr>-->
            <tr class="youbi">
              <th>日</th>
              <th>月</th>
              <th>火</th>
              <th>水</th>
              <th>木</th>
              <th>金</th>
              <th>土</th>
            </tr>
          </thead>
          <tbody>
          </tbody>
          <!--    <tfoot>
      <tr>
        <td id="today" colspan="7">Today</td>
      </tr>
    </tfoot>-->
        </table>

		<div class="kronon-speak-container">
			<div class="kronon-speak">
				<div class="arrow-container">
					<div class="arrow_box" id="kronon-message">
						<!-- おはよう★<br>今日はどんな予定があるの？ -->
					</div>
				</div>
				<img src="/kronon/img/kronon/kronon_prin.png" class="kronon-img">
			</div>
		</div>

	</div>
    </div>
<input type="hidden" id="date_servlet"  value='${date}' style="display:none">

</article>
<%@ include file="/WEB-INF/views/layout/common/footer.jsp" %>
<script src="../js/common/common.js"></script>
<script src="../js/calendar2.js"></script>
<script src="../js/calendar_event.js"></script>
</body>
</html>