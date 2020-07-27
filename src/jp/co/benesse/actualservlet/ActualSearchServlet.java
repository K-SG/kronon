package jp.co.benesse.actualservlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
		//セッションスコープからデータ取得
		HttpSession session = request.getSession();
		String userIdStr = (String) session.getAttribute("userId");
		// int userId = Integer.parseInt(userIdStr);
		int userId = 1;

		//リクエストパラメータを取得
		String scheduleDateStr = request.getParameter("scheduleDate");
		String title = request.getParameter("title");
		String year = request.getParameter("year");
		String month = request.getParameter("month");

		String dateStr = year + "-" + month + "-" + "15";
		// 遷移元の判定フラグ
		String flag = "1";

		List<ScheduleBean> scheduleBeanList = new ArrayList<>();

		ConnectionManager connectionManager = new ConnectionManager();
		ScheduleDAO scheduleDAO;

		//検索結果0件の場合のエラーメッセージ
		String errorMsg = null;

		try {
			Connection connection = connectionManager.getConnection();
			scheduleDAO = new ScheduleDAO(connection);

			//AND検索、日付検索、タイトル検索の判定
			if (!scheduleDateStr.equals("") && !title.equals("")) {
				Date scheduleDate = Date.valueOf(scheduleDateStr);
				scheduleBeanList = scheduleDAO.selectSchedule(userId, scheduleDate, title);
			} else if (!scheduleDateStr.equals("") && title.equals("")) {
				Date scheduleDate = Date.valueOf(scheduleDateStr);
				scheduleBeanList = scheduleDAO.selectSchedule(userId, scheduleDate);
			} else if (scheduleDateStr.equals("") && !title.equals("")) {
				LocalDate scheduleDate = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
				scheduleBeanList = scheduleDAO.getOneMonthScheduleByTitle(userId,scheduleDate,title);
			}else{
				throw new RuntimeException();
			}

			if(scheduleBeanList.size() == 0){
				errorMsg = "検索結果は0件でした";
				request.setAttribute("errorMsg", errorMsg);
			}

			// リクエストスコープにセット
			request.setAttribute("flag", flag);
			request.setAttribute("scheduleBeanList", scheduleBeanList);
			request.setAttribute("month", month);
			request.setAttribute("year", year);

			RequestDispatcher dispatcher = request.getRequestDispatcher("../WEB-INF/views/actual/actual_index.jsp");
			dispatcher.forward(request, response);
			return;
		} catch (RuntimeException e) {
			e.printStackTrace();
			RequestDispatcher dispatcher = request.getRequestDispatcher("../WEB-INF/views/error/error.jsp");
			dispatcher.forward(request, response);
			return;
		} finally {
			connectionManager.closeConnection();
		}
	}

}
