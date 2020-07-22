package jp.co.benesse.actualservlet;

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

@WebServlet("/user/actualindex")
public class ActualIndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//セッションスコープから値を取得
		HttpSession session = request.getSession();
		String userIdStr = (String) session.getAttribute("userId");
		int userId = Integer.parseInt(userIdStr);
		String userName = (String) session.getAttribute("userId");

		List<ScheduleBean> scheduleBeanList = new ArrayList<>();
		LocalDate scheduleDate = LocalDate.now();
		// 遷移元の判定フラグ
		String flag = "";

		ConnectionManager connectionManager = new ConnectionManager();

		ScheduleDAO scheduleDAO;

		try {
			Connection connection = connectionManager.getConnection();
			scheduleDAO = new ScheduleDAO(connection);
			scheduleBeanList = scheduleDAO.getOneMonthSchedule(scheduleDate, userId);
		} catch (RuntimeException e) {
			connectionManager.rollback();
		} finally {
			connectionManager.closeConnection();
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("../WEB-INF/views/actual/actual_index.jsp");
		dispatcher.forward(request, response);
		return;
	}

}
