package jp.co.benesse.actualservlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Time;

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

/**
 * Servlet implementation class ActualDeleteServlet
 */
@WebServlet("/user/actualdelete")
public class ActualDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(true);
		String loginUser = (String)session.getAttribute("userName");
		String userName = request.getParameter("userName");
		if(!(loginUser == userName)){
			RequestDispatcher dispatcher = request.getRequestDispatcher("../WEB-INF/views/error/error.jsp");
			dispatcher.forward(request, response);
			return;
		}


		int scheduleId = Integer.parseInt(request.getParameter("scheduleId"));
		String actualTimeStr = request.getParameter("actualTimeStr");
		String schduleDateActual = request.getParameter("scheduleDateActual");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String place = request.getParameter("place");
		String title = request.getParameter("title");
		String content = request.getParameter("content");

		ScheduleBean scheduleBean = new ScheduleBean();
		scheduleBean.setUserName(userName);
		scheduleBean.setActualTimeStr(actualTimeStr);
		scheduleBean.setScheduleDateActual(schduleDateActual);
		scheduleBean.setStartTime(Time.valueOf(startTime));
		scheduleBean.setEndTime(Time.valueOf(endTime));
		scheduleBean.setPlace(place);
		scheduleBean.setTitle(title);
		scheduleBean.setContent(content);


		ConnectionManager connectionManager = new ConnectionManager();

		try {

			Connection connection = connectionManager.getConnection();
			ScheduleDAO scheduleDAO = new ScheduleDAO(connection);

			if (scheduleDAO.isDeleted(scheduleId)){
				RequestDispatcher dispatcher = request.getRequestDispatcher("../WEB-INF/views/error/error.jsp");
				dispatcher.forward(request, response);
				return;
			}

			scheduleDAO.deleteSchedule(scheduleId);
			connectionManager.commit();

			request.setAttribute("popFlag",1);

			request.setAttribute("scheduleBean",scheduleBean);


			RequestDispatcher dispatcher = request.getRequestDispatcher("../WEB-INF/views/schedule/schedule_detail.jsp");
			dispatcher.forward(request, response);
			return;

		} catch (RuntimeException e) {
			connectionManager.rollback();
			throw e;

		} finally {
			connectionManager.closeConnection();
		}
	}

}
