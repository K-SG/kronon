package gomi;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.benesse.dataaccess.value.ScheduleBean;

/**
 * Servlet implementation class TestScheduleEdit
 */
@WebServlet("/user/testactualnew")
public class TestActualNew extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String strDate = "2020-07-12";
		Date date = Date.valueOf(strDate);
		String startTimeStr = "12:45:00";
		Time startTime = Time.valueOf(startTimeStr);
		String endTimeStr = "14:00:00";
		Time endTime = Time.valueOf(endTimeStr);
		int actualTime =120;

		ScheduleBean scheduleBean = new ScheduleBean();
		scheduleBean.setScheduleId(1);
		scheduleBean.setScheduleDate(date);
		scheduleBean.setStartTime(startTime);
		scheduleBean.setEndTime(endTime);
		scheduleBean.setPlace("1");
		scheduleBean.setTitle("たいとるだお");
		scheduleBean.setContent("ないようだお");
		scheduleBean.setActualTime(actualTime);




		request.setAttribute("scheduleBean",scheduleBean);

		RequestDispatcher dispatcher = request.getRequestDispatcher("../WEB-INF/views/actual/actual_new.jsp");
		dispatcher.forward(request, response);
		return;
	}

}
