package jp.co.benesse.actualservlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.benesse.dataaccess.cm.ConnectionManager;
import jp.co.benesse.dataaccess.dao.ScheduleDAO;
import jp.co.benesse.dataaccess.value.ScheduleBean;

@WebServlet("/user/actualdetail")
public class ActualDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// int scheduleId = Integer.parseInt(request.getParameter("scheduleId"));
		int scheduleId = 2;

		ConnectionManager connectionManager = new ConnectionManager();
		ScheduleBean scheduleBean = new ScheduleBean();

		try {

			Connection connection = connectionManager.getConnection();
			ScheduleDAO scheduleDAO = new ScheduleDAO(connection);

			if (scheduleDAO.isDeleted(scheduleId)) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("../WEB-INF/views/error/error.jsp");
				dispatcher.forward(request, response);
				return;
			}

			scheduleBean = scheduleDAO.getScheduleByScheduleId(scheduleId);
			System.out.println(scheduleBean);
			request.setAttribute("scheduleBean", scheduleBean);

			RequestDispatcher dispatcher = request.getRequestDispatcher("../WEB-INF/views/actual/actual_detail.jsp");
			dispatcher.forward(request, response);
			return;

		} catch (RuntimeException e) {
			throw e;

		} finally {
			connectionManager.closeConnection();
		}

	}

}
