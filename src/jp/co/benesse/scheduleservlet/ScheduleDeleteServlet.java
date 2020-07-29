package jp.co.benesse.scheduleservlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Time;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.benesse.dataaccess.cm.ConnectionManager;
import jp.co.benesse.dataaccess.dao.ScheduleDAO;
import jp.co.benesse.dataaccess.value.ScheduleBean;

@WebServlet("/user/scheduledelete")
public class ScheduleDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int scheduleId = 0;
		String userName = null;
		String actualTimeStr = null;
		String schduleDateActual = null;
		String scheduleDateStr = null;
		String startTime = null;
		String endTime = null;
		String place = null;
		String title = null;
		String content = null;
		Date scheduleDate = null;
		ScheduleBean scheduleBean = null;
		ConnectionManager connectionManager = null;
		ScheduleDAO scheduleDAO = null;
		Connection connection = null;
		RequestDispatcher dispatcher = null;

		try {
			// リクエストパラメータを取得
			scheduleId = Integer.parseInt(request.getParameter("scheduleId"));
			userName = request.getParameter("userName");
			actualTimeStr = request.getParameter("actualTimeStr");
			scheduleDateStr = request.getParameter("scheduleDate");
			scheduleDate = Date.valueOf(scheduleDateStr);
			schduleDateActual = request.getParameter("scheduleDateActual");
			startTime = request.getParameter("startTime");
			endTime = request.getParameter("endTime");
			place = request.getParameter("place");
			title = request.getParameter("title");
			content = request.getParameter("content");

			scheduleBean = new ScheduleBean();
			scheduleBean.setUserName(userName);
			scheduleBean.setScheduleDate(scheduleDate);
			scheduleBean.setActualTimeStr(actualTimeStr);
			scheduleBean.setScheduleDateActual(schduleDateActual);
			scheduleBean.setStartTime(Time.valueOf(startTime));
			scheduleBean.setEndTime(Time.valueOf(endTime));
			scheduleBean.setPlace(place);
			scheduleBean.setTitle(title);
			scheduleBean.setContent(content);

			connectionManager = new ConnectionManager();
			connection = connectionManager.getConnection();
			scheduleDAO = new ScheduleDAO(connection);

			if (scheduleDAO.isDeleted(scheduleId)) {
				throw new RuntimeException("既に削除されている");
			}

			scheduleDAO.deleteSchedule(scheduleId);
			connectionManager.commit();

			request.setAttribute("popFlag", 1);
			request.setAttribute("scheduleBean", scheduleBean);

			dispatcher = request.getRequestDispatcher("../WEB-INF/views/schedule/schedule_detail.jsp");
			dispatcher.forward(request, response);
			return;
		} catch (RuntimeException e) {
			connectionManager.rollback();
			e.printStackTrace();
			dispatcher = request.getRequestDispatcher("../WEB-INF/views/error/error.jsp");
			dispatcher.forward(request, response);
			return;
		} catch (Exception e) {
			connectionManager.rollback();
			e.printStackTrace();
			dispatcher = request.getRequestDispatcher("../WEB-INF/views/error/error.jsp");
			dispatcher.forward(request, response);
			return;
		} finally {
			connectionManager.closeConnection();
		}
	}
}
