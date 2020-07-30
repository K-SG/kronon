package jp.co.benesse.actualservlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

@WebServlet("/user/actualsearch")
public class ActualSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int userId = 0;
		final String FLAG = "1";// 遷移元の判定フラグ
		String scheduleDateStr = null;
		String title = null;
		String year = null;
		String month = null;
		String dateStr = null;
		String errorMsg = null;
		Date scheduleDate = null;
		LocalDate LocalScheduleDate = null;
		ConnectionManager connectionManager = null;
		ScheduleDAO scheduleDAO = null;
		List<ScheduleBean> scheduleBeanList = null;
		Connection connection = null;
		HttpSession session = null;
		RequestDispatcher dispatcher = null;

		try {

			// セッションスコープからデータ取得
			session = request.getSession();
			userId = (Integer) session.getAttribute("userId");

			// リクエストパラメータを取得
			scheduleDateStr = request.getParameter("date");
			title = request.getParameter("title");
			year = request.getParameter("year");
			month = request.getParameter("month");

			// 日付の整形
			if (year.length() == 1) {
				year = "0" + year;
			}
			if (month.length() == 1) {
				month = "0" + month;
			}

			// タイトル検索に用いる（検索付きの中日を適当に一つ入れるだけでよい）
			dateStr = year + "-" + month + "-" + "15";

			connectionManager = new ConnectionManager();
			connection = connectionManager.getConnection();
			scheduleDAO = new ScheduleDAO(connection);

			// AND検索、日付検索、タイトル検索の判定
			if (!scheduleDateStr.equals("") && !title.trim().equals("")) {
				scheduleDate = Date.valueOf(scheduleDateStr);
				scheduleBeanList = scheduleDAO.selectSchedule(userId, scheduleDate, title);
			} else if (!scheduleDateStr.equals("") && title.trim().equals("")) {
				scheduleDate = Date.valueOf(scheduleDateStr);
				scheduleBeanList = scheduleDAO.selectSchedule(userId, scheduleDate);
			} else if (scheduleDateStr.equals("") && !title.trim().equals("")) {
				LocalScheduleDate = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
				scheduleBeanList = scheduleDAO.getOneMonthScheduleByTitle(userId, LocalScheduleDate, title);
			} else {
				throw new RuntimeException("不正な検索ワード");
			}

			if (scheduleBeanList.size() == 0) {
				errorMsg = "検索結果は0件でした";
				request.setAttribute("errorMsg", errorMsg);
			}

			// リクエストスコープにセット
			request.setAttribute("flag", FLAG);
			request.setAttribute("scheduleBeanList", scheduleBeanList);

			// 「yyyy年MM月の検索結果」表示用
			if (scheduleDateStr.equals("")) {
				request.setAttribute("month", month);
				request.setAttribute("year", year);
			} else {
				request.setAttribute("month", scheduleDate.toLocalDate().getMonthValue());
				request.setAttribute("year", scheduleDate.toLocalDate().getYear());
			}

			dispatcher = request.getRequestDispatcher("../WEB-INF/views/actual/actual_index.jsp");
			dispatcher.forward(request, response);
			return;
		} catch (RuntimeException e) {
			e.printStackTrace();
			dispatcher = request.getRequestDispatcher("../WEB-INF/views/error/error.jsp");
			dispatcher.forward(request, response);
			return;
		} catch (Exception e) {
			e.printStackTrace();
			dispatcher = request.getRequestDispatcher("../WEB-INF/views/error/error.jsp");
			dispatcher.forward(request, response);
			return;
		} finally {
			connectionManager.closeConnection();
		}
	}

}
