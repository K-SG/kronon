<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="../layout/common/link.jsp" %>
<link href="css/common/testschedule.css" rel="stylesheet" type="text/css">
<title>Insert title here</title>
</head>
<body>

<%@ include file="../layout/common/header.jsp" %>
<article>

<!-- ここにコードを書く -->
<style>
table.sche{border:1px solid #a9a9a9;padding:0px;margin:0px;border-collapse:collapse;}
td{vertical-align:top;margin:0px;padding:2px;font-size:0.75em;height:20px;}
td.top{border-bottom:1px solid #a9a9a9;text-align:center;}
td.time{text-align:right;border-right:1px double #a9a9a9;padding-right:5px;}
td.timeb{border-bottom:1px solid #a9a9a9;border-right:1px double #a9a9a9;}
td.contents{border-bottom:1px dotted #a9a9a9;}
td.contentsb{border-bottom:1px solid #a9a9a9;}
td.ex{background-color:#ffebcd;border:1px solid #8b0000;}
img{border:0px;}
p{font-size:0.75em;}

#contents{margin:0;padding:0;width:710px;}
#left{margin:0;padding:0;float:left;width:400px;}
#right{margin:0;padding:0;float:right;width:300px;background-color:#ffffff;}
#contents:after{content:".";display:block;height:0;clear:both;visibility:hidden;}
</style>

<p>作業している</p>

<div id="contents">

<div id="left">

<table class="sche">
<tr><td class="top" style="width:80px"></td><td class="top" style="width:100px">杉</td></tr>

<tr><td class="time">08:00</td><td class="contents"></td></tr>
<tr><td class="timeb"></td><td class="contentsb"></td></tr>
<tr><td class="time">09:00</td><td class="contents"></td></tr>
<tr><td class="timeb"></td><td class="contentsb"></td></tr>
<tr><td class="time">10:00</td><td class="contents"></td></tr>
<tr><td class="timeb"></td><td class="contentsb"></td></tr>
<tr><td class="time">11:00</td><td class="contents"></td></tr>
<tr><td class="timeb"></td><td class="contentsb"></td></tr>
<tr><td class="time">12:00</td><td class="contents"></td></tr>
<tr><td class="timeb"></td><td class="contentsb"></td></tr>
<tr><td class="time">13:00</td><td class="contents"></td></tr>
<tr><td class="timeb"></td><td class="contentsb"></td></tr>
<tr><td class="time">14:00</td><td class="contents"></td></tr>
<tr><td class="timeb"></td><td class="contentsb"></td></tr>
<tr><td class="time">15:00</td><td class="contents"></td></tr>
<tr><td class="timeb"></td><td class="contentsb"></td></tr>
<tr><td class="time">16:00</td><td class="contents"></td></tr>
<tr><td class="timeb"></td><td class="contentsb"></td></tr>
<tr><td class="time">17:00</td><td class="contents"></td></tr>
<tr><td class="timeb"></td><td class="contentsb"></td></tr>
<tr><td class="time">18:00</td><td class="contents"></td></tr>
<tr><td class="timeb"></td><td class="contentsb"></td></tr>
<tr><td class="time">19:00</td><td class="contents"></td></tr>
<tr><td class="timeb"></td><td class="contentsb"></td></tr>
<tr><td class="time">20:00</td><td class="contents"></td></tr>
<tr><td class="timeb"></td><td class="contentsb"></td></tr>
</table>

</div>
</div>

</article>
<%@ include file="../layout/common/footer.jsp" %>
<script src="js/common.js"></script>
</body>
</html>