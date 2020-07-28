package gomi;

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

/**
 * Servlet implementation class TestScheduleEdit
 */
@WebServlet("/user/testactualnew")
public class TestActualNew extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ConnectionManager connectionManager = new ConnectionManager();
		Connection connection = connectionManager.getConnection();
		ScheduleDAO scheduleDAO= new ScheduleDAO(connection);
		ScheduleBean scheduleBean = new ScheduleBean();
		scheduleBean = scheduleDAO.getScheduleByScheduleId(1);



		request.setAttribute("userName","長澤");
		request.setAttribute("scheduleBean",scheduleBean);

		RequestDispatcher dispatcher = request.getRequestDispatcher("../WEB-INF/views/actual/actual_new.jsp");
		dispatcher.forward(request, response);
		return;
	}
}
