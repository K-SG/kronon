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

@WebServlet("/user/actualdetail")
public class ActualDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int userId = 0;
		int scheduleId = 0;
		ConnectionManager connectionManager = null;
		ScheduleBean scheduleBean = null;
		ScheduleDAO scheduleDAO = null;
		HttpSession session = null;
		Connection connection = null;
		RequestDispatcher dispatcher = null;

		try {
			// セッションからIdを取得
			session = request.getSession();
			userId = (int) session.getAttribute("userId");

			scheduleId = Integer.parseInt(request.getParameter("scheduleId"));

			connectionManager = new ConnectionManager();
			connection = connectionManager.getConnection();
			scheduleDAO = new ScheduleDAO(connection);
			scheduleBean = new ScheduleBean();

			// 予定が既に削除されている場合
			if (scheduleDAO.isDeleted(scheduleId)) {
				throw new RuntimeException("予定が既に削除されている");
			}

			scheduleBean = scheduleDAO.getScheduleByScheduleId(scheduleId);

			// ログイン者以外の予定にアクセスできないようにする
			if (userId != scheduleBean.getUserId()) {
				throw new RuntimeException("ログイン者以外の予定にアクセスした");
			}

			request.setAttribute("scheduleBean", scheduleBean);

			dispatcher = request.getRequestDispatcher("../WEB-INF/views/actual/actual_detail.jsp");
			dispatcher.forward(request, response);
			return;

		} catch (RuntimeException e) {
			dispatcher = request.getRequestDispatcher("../WEB-INF/views/error/error.jsp");
			dispatcher.forward(request, response);
			return;
		} catch (Exception e) {
			dispatcher = request.getRequestDispatcher("../WEB-INF/views/error/error.jsp");
			dispatcher.forward(request, response);
			return;
		} finally {
			connectionManager.closeConnection();
		}

	}

}
