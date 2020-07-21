<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/WEB-INF/views/layout/common/link.jsp" %>
<link rel="stylesheet" href="css/calendar.css">
<title>予定確認</title>
</head>
<body>

<%@ include file="/WEB-INF/views/layout/common/header.jsp" %>
<article>

<!-- ここにコードを書く -->


 <div class="calendar-container">
      <div class="calendar-container-inner">
        <div class="calendar-title">
          <div class="title-content"><img src="img/left_button.png" alt="left" class="left triangle-button"></div>
          <div class="title-content">
            <h2 class="month">7</h2>
          </div>
          <div class="title-content">
            <h3 class="year">2020</h3>
          </div>
          <div class="title-content"><img src="img/right_button.png" alt="right" class="right triangle-button"></div>
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
      </div>
    </div>



</article>
<%@ include file="/WEB-INF/views/layout/common/footer.jsp" %>
<script src="js/common/common.js"></script>
<script src="js/calendar.js"></script>
<script src="js/calendar_event.js"></script>
</body>
</html>