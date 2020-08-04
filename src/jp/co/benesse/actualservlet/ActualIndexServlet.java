package jp.co.benesse.actualservlet;

import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.benesse.dataaccess.cm.ConnectionManager;
import jp.co.benesse.dataaccess.dao.ScheduleDAO;
import jp.co.benesse.dataaccess.value.ScheduleBean;

@WebServlet("/user/actualindex")
public class ActualIndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int userId = 0;
		final String FLAG = "0";// 遷移元の判定フラグ
		String dateStr = null;
		String monthFlag = null;
		LocalDate date = null;
		LocalDate firstDayOfMonth = null;
		LocalDate lastDayOfMonth = null;
		ConnectionManager connectionManager = null;
		ScheduleDAO scheduleDAO = null;
		List<ScheduleBean> scheduleBeanList = null;
		Connection connection = null;
		HttpSession session = null;
		RequestDispatcher dispatcher = null;

		try {
			// セッションスコープから値を取得
			session = request.getSession();
			userId = (Integer) session.getAttribute("userId");

			// リクエストパラメータを取得
			dateStr = request.getParameter("date");
			monthFlag = request.getParameter("monthFlag");

			if (monthFlag == null || dateStr == null) {// 実績確認画面以外から
				date = LocalDate.now();
			} else if (monthFlag.equals("0")) {// 前月
				date = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
				firstDayOfMonth = date.with(TemporalAdjusters.firstDayOfMonth()); // 初日
				date = firstDayOfMonth.minusDays(1);// 先月の末日
			} else if (monthFlag.equals("1")) {// 翌月
				date = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
				lastDayOfMonth = date.with(TemporalAdjusters.lastDayOfMonth()); // 末日
				date = lastDayOfMonth.plusDays(1);// 次月の初日
			} else {// 実績確認画面以外から
				date = LocalDate.now();
			}

			connectionManager = new ConnectionManager();
			connection = connectionManager.getConnection();
			scheduleDAO = new ScheduleDAO(connection);
			scheduleBeanList = scheduleDAO.getOneMonthSchedule(date, userId);

			// リクエストスコープにセット
			request.setAttribute("flag", FLAG);
			request.setAttribute("date", date);
			request.setAttribute("month", date.getMonthValue());
			request.setAttribute("year", date.getYear());
			request.setAttribute("scheduleBeanList", scheduleBeanList);

			dispatcher = request.getRequestDispatcher("../WEB-INF/views/actual/actual_index.jsp");
			dispatcher.forward(request, response);
			return;
		} catch (RuntimeException e) {
			dispatcher = request.getRequestDispatcher("../WEB-INF/views/error/error.jsp");
			dispatcher.forward(request, response);
			return;
		} catch (Exception e) {
			dispatcher = request.getRequestDispatcher("../WEB-INF/views/error/error.jsp");
			dispatcher.forward(request, response);
			return;
		} finally {
			connectionManager.closeConnection();
		}
	}

}
