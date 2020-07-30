package jp.co.benesse.actualservlet;

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
import jp.co.benesse.dataaccess.value.ScheduleBean;

@WebServlet("/user/actualedit")
public class ActualEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int userId = 0;
		int scheduleId = 0;
		String scheduleIdStr = null;
		ConnectionManager connectionManager = null;
		ScheduleDAO scheduleDAO = null;
		ScheduleBean scheduleBean = null;
		Connection connection = null;
		RequestDispatcher dispatcher = null;
		HttpSession session = null;

		try {

			// セッションからIdを取得
			session = request.getSession();
			userId = (int) session.getAttribute("userId");

			// リクエストパラメータを取得
			scheduleIdStr = request.getParameter("scheduleId");
			scheduleId = Integer.parseInt(scheduleIdStr);

			connectionManager = new ConnectionManager();
			connection = connectionManager.getConnection();
			scheduleDAO = new ScheduleDAO(connection);
			scheduleBean = scheduleDAO.getScheduleByScheduleId(scheduleId);

			// ログイン者以外の予定にアクセスできないようにする
			if (userId != scheduleBean.getUserId()) {
				throw new RuntimeException("ログイン者以外の予定にアクセスした");
			}

			request.setAttribute("scheduleBean", scheduleBean);

			dispatcher = request.getRequestDispatcher("/WEB-INF/views/actual/actual_edit.jsp");
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
