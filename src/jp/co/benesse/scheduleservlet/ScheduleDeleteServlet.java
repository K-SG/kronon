package jp.co.benesse.scheduleservlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.benesse.dataaccess.cm.ConnectionManager;
import jp.co.benesse.dataaccess.dao.ScheduleDAO;

@WebServlet("/user/scheduledelete")
public class ScheduleDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(true);
		int id = (Integer)(session.getAttribute("id"));

		ConnectionManager connectionManager = new ConnectionManager();

		try {

			Connection connection = connectionManager.getConnection();
			ScheduleDAO scheduleDAO = new ScheduleDAO(connection);

			if (scheduleDAO.isDeleted(id)){
				RequestDispatcher dispatcher = request.getRequestDispatcher("../WEB-INF/views/error/error.jsp");
				dispatcher.forward(request, response);
				return;
			}

			scheduleDAO.deleteSchedule(id);

			request.setAttribute("popFlag",1);

			RequestDispatcher dispatcher = request.getRequestDispatcher("../WEB-INF/views/schedule/schedule_detail.jsp");
			dispatcher.forward(request, response);
			return;

		} catch (RuntimeException e) {
			throw e;
		} finally {
			connectionManager.closeConnection();
		}
	}

}
