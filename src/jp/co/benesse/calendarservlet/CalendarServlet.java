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

		int userId = 0;
		String flag  = null;
		String dateStr = null;
		String json = null;
		String json_replace = null;
		LocalDate date = null;
		LocalDate firstDayOfMonth = null;
		LocalDate lastDayOfMonth = null;
		ObjectMapper mapper = null;
		ConnectionManager connectionManager = null;
		ScheduleDAO scheduleDAO = null;
		List<ScheduleBean> scheduleBeanList = null;
		HttpSession session = null;
		Connection connection = null;
		RequestDispatcher dispatcher = null;

		try {
			//セッションスコープから利用者IDを取得
			session = request.getSession();
			userId = (Integer) session.getAttribute("userId");

			//リクエストパラメータを取得
			flag = request.getParameter("flag");
			dateStr = request.getParameter("date");

			// いつの月の予定を表示するか
			if (flag == null || dateStr == null) {
				date = LocalDate.now();
			} else if (flag.equals("0")) {// 前月
				date = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
				firstDayOfMonth = date.with(TemporalAdjusters.firstDayOfMonth()); // 初日
				date = firstDayOfMonth.minusDays(1);// 先月の末日
			} else if (flag.equals("1")) {// 翌月
				date = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
				lastDayOfMonth = date.with(TemporalAdjusters.lastDayOfMonth()); // 末日
				date = lastDayOfMonth.plusDays(1);// 次月の初日
			} else {// ログイン画面から
				date = LocalDate.now();
			}

			connectionManager = new ConnectionManager();
			connection = connectionManager.getConnection();
			scheduleDAO = new ScheduleDAO(connection);

			scheduleBeanList = new ArrayList<>();
			scheduleBeanList = scheduleDAO.tooLongSQLSchedule(date, userId);

			// Beanのリスト→JSON形式の整形
			mapper = new ObjectMapper();
			json = mapper.writeValueAsString(scheduleBeanList);
			json_replace = json.replaceAll("\"", "krnooon");
			System.out.println("calendar:"+scheduleBeanList);

			// リクエストスコープにセット
			request.setAttribute("json", json_replace);
			request.setAttribute("date", date);
			request.setAttribute("month", date.getMonthValue());
			request.setAttribute("year", date.getYear());

			dispatcher = request.getRequestDispatcher("../WEB-INF/views/calendar/schedule_index.jsp");
			dispatcher.forward(request, response);
			return;
		} catch (RuntimeException e) {
			e.printStackTrace();
			response.sendRedirect("../login");
			return;
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("../login");
			return;
		} finally {
			connectionManager.closeConnection();
		}
	}
}
