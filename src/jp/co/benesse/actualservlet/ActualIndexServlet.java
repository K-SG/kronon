package jp.co.benesse.actualservlet;

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
		// セッションスコープから値を取得
//		HttpSession session = request.getSession();
//		String userIdStr = (String) session.getAttribute("userId");
//		int userId = Integer.parseInt(userIdStr);
		int userId = 1;
//		String userName = (String) session.getAttribute("userId");
		String dateStr = request.getParameter("date");

		LocalDate date;
		List<ScheduleBean> scheduleBeanList = new ArrayList<>();

		// 遷移元の判定フラグ
		String flag = "";

		ConnectionManager connectionManager = new ConnectionManager();
		ScheduleDAO scheduleDAO;

		if (flag == null || dateStr == null) {// 実績確認画面以外から
			date = LocalDate.now();
		} else if (flag.equals("0")) {// 前月
			date = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			LocalDate firstDayOfMonth = date.with(TemporalAdjusters.firstDayOfMonth()); // 初日
			date = firstDayOfMonth.minusDays(1);// 先月の末日
		} else if (flag.equals("1")) {// 翌月
			date = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			LocalDate lastDayOfMonth = date.with(TemporalAdjusters.lastDayOfMonth()); // 末日
			date = lastDayOfMonth.plusDays(1);// 次月の初日
		} else {// 実績確認画面以外から
			date = LocalDate.now();
		}

		try {
			Connection connection = connectionManager.getConnection();
			scheduleDAO = new ScheduleDAO(connection);
			scheduleBeanList = scheduleDAO.getOneMonthSchedule(date, userId);

			// リクエストスコープにセット
			request.setAttribute("date", date);
			request.setAttribute("month", date.getMonthValue());
			request.setAttribute("year", date.getYear());
			request.setAttribute("scheduleBeanList", scheduleBeanList);

			RequestDispatcher dispatcher = request.getRequestDispatcher("../WEB-INF/views/actual/actual_index.jsp");
			dispatcher.forward(request, response);
			return;
		} catch (RuntimeException e) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("../WEB-INF/views/error/error.jsp");
			dispatcher.forward(request, response);
			return;
		} finally {
			connectionManager.closeConnection();
		}
	}

}
