package jp.co.benesse.calendarservlet;

import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import jp.co.benesse.dataaccess.cm.ConnectionManager;
import jp.co.benesse.dataaccess.dao.ScheduleDAO;
import jp.co.benesse.dataaccess.value.ScheduleBean;

@WebServlet("/user/calendar")
public class CalendarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String flag = request.getParameter("flag");
		String dateStr = request.getParameter("date");
		String userIdStr = "1";

		// 本来はこっち
		// String userIdStr = (String)session.getAttribute("userId");
		int userId = Integer.parseInt(userIdStr);

		LocalDate date;
		List<ScheduleBean> scheduleBeanList = new ArrayList<>();

		ObjectMapper mapper = new ObjectMapper();
		ConnectionManager connectionManager = new ConnectionManager();

		if (flag == null || dateStr == null) {// ログイン画面から
			date = LocalDate.now();
		} else if (flag.equals("0")) {// 前月
			date = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			LocalDate firstDayOfMonth = date.with(TemporalAdjusters.firstDayOfMonth()); // 初日
			date = firstDayOfMonth.minusDays(1);// 先月の末日
		} else if (flag.equals("1")) {// 翌月
			date = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			LocalDate lastDayOfMonth = date.with(TemporalAdjusters.lastDayOfMonth()); // 末日
			date = lastDayOfMonth.plusDays(1);// 次月の初日
		} else {// ログイン画面から
			date = LocalDate.now();
		}

		try {
			Connection connection = connectionManager.getConnection();
			ScheduleDAO scheduleDAO = new ScheduleDAO(connection);
			scheduleBeanList = scheduleDAO.tooLongSQLSchedule(date, userId);

			for (ScheduleBean sBean : scheduleBeanList) {
				sBean.setJsonDate(sBean.getScheduleDate().toString());
			}

			//Beanのリスト→JSON形式の整形
			String json = mapper.writeValueAsString(scheduleBeanList);
			String json_replace = json.replaceAll("\"", "krnooon");
			request.setAttribute("json", json_replace);
			request.setAttribute("date", date);
			request.setAttribute("month", date.getMonthValue());
			request.setAttribute("year", date.getYear());
			System.out.println(date.getMonthValue());
			System.out.println(json);
			RequestDispatcher dispatcher = request.getRequestDispatcher("../WEB-INF/views/calendar/schedule_index.jsp");
			dispatcher.forward(request, response);
		} catch (RuntimeException e) {
			throw e;
		} finally {
			connectionManager.closeConnection();
		}
	}
}
