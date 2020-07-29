package co.jp.benesse.actualservlet;

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

@WebServlet("/user/actualnew")
public class ActualNewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String scheduleIdStr = request.getParameter("scheduleId");
		int scheduleId = Integer.parseInt(scheduleIdStr);

		ConnectionManager connectionManager = new ConnectionManager();
		Connection connection = connectionManager.getConnection();
		ScheduleDAO scheduleDAO= new ScheduleDAO(connection);
		ScheduleBean scheduleBean = new ScheduleBean();
		scheduleBean = scheduleDAO.getScheduleByScheduleId(scheduleId);

		request.setAttribute("scheduleBean",scheduleBean);

		String forwardPath = "../WEB-INF/views/actual/actual_new.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPath);
		dispatcher.forward(request, response);
	}

}
