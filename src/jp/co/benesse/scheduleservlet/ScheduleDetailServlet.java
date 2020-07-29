package jp.co.benesse.scheduleservlet;

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

@WebServlet("/user/scheduledetail")
public class ScheduleDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int scheduleId = 0;
		String scheduleStr = null;
		ConnectionManager connectionManager = null;
		ScheduleBean scheduleBean = null;
		ScheduleDAO scheduleDAO = null;
		Connection connection = null;
		RequestDispatcher dispatcher = null;

		try {
			// リクエストパラメータを取得
			scheduleStr = request.getParameter("scheduleId");
			scheduleId = Integer.parseInt(scheduleStr);

			connectionManager = new ConnectionManager();
			connection = connectionManager.getConnection();
			scheduleDAO = new ScheduleDAO(connection);

			if (scheduleDAO.isDeleted(scheduleId)) {
				throw new RuntimeException("予定が既に削除されている");
			}

			scheduleBean = new ScheduleBean();
			scheduleBean = scheduleDAO.getScheduleByScheduleId(scheduleId);
			request.setAttribute("scheduleBean", scheduleBean);

			dispatcher = request.getRequestDispatcher("../WEB-INF/views/schedule/schedule_detail.jsp");
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
