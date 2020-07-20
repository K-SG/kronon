package jp.co.benesse.calendarservlet;

import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDate;
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

@WebServlet("/user/calendar")
public class CalendarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// LoginServletから来た場合
		{
			// Test test = new Test(1, "眠い");
			HttpSession session = request.getSession();
			String userIdStr = (String)session.getAttribute("userId");
			int userId = Integer.parseInt(userIdStr);
			List<ScheduleBean> scheduleBeanList = new ArrayList<>();
			LocalDate today = LocalDate.now();
			ConnectionManager connectionManager = new ConnectionManager();
			try {
				Connection connection = connectionManager.getConnection();
				ScheduleDAO scheduleDAO = new ScheduleDAO(connection);
				scheduleBeanList = scheduleDAO.tooLongSQLSchedule(today, userId);

			} catch (RuntimeException e) {
				throw e;
			} finally {
				connectionManager.closeConnection();
			}

			// https://engineer-club.jp/java-json を参考にした
			// ObjectMapper mapper = new ObjectMapper();
			// String json = mapper.writeValueAsString(test);
			// System.out.println(json);
			// request.setAttribute("json", json);

			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/calendar/sche.jsp");
			dispatcher.forward(request, response);
		}

		// schedule_index.jspから来た場合
		{

		}

	}

}
