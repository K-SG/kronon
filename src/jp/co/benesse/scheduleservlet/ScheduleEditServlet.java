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
import jp.co.benesse.dataaccess.value.ScheduleBean;

@WebServlet("/user/scheduleedit")
public class ScheduleEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
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
			//セッションスコープから値を取得
			session = request.getSession();
			userId = (int) session.getAttribute("userId");

			// リクエストパラメータを取得
			scheduleIdStr = request.getParameter("scheduleId");
			scheduleId = Integer.parseInt(scheduleIdStr);

			connectionManager = new ConnectionManager();
			connection = connectionManager.getConnection();
			scheduleDAO = new ScheduleDAO(connection);
			scheduleBean = new ScheduleBean();
			scheduleBean = scheduleDAO.getScheduleByScheduleId(scheduleId);

			// ログイン者以外の予定にアクセスできないようにする
			if (userId != scheduleBean.getUserId()) {
				throw new RuntimeException("ログイン者以外の予定にアクセスした");
			}

			request.setAttribute("scheduleBean", scheduleBean);

			// schedule_edit.jsp（予定修正画面）にforwardする
			dispatcher = request.getRequestDispatcher("/WEB-INF/views/schedule/schedule_edit.jsp");
			dispatcher.forward(request, response);
			return;
		} catch (RuntimeException e) {
			// error.jsp（エラー画面）にforwardする
			dispatcher = request.getRequestDispatcher("/WEB-INF/views/error/error.jsp");
			dispatcher.forward(request, response);
			return;
		} catch (Exception e) {
			// error.jsp（エラー画面）にforwardする
			dispatcher = request.getRequestDispatcher("/WEB-INF/views/error/error.jsp");
			dispatcher.forward(request, response);
			return;
		} finally {
			connectionManager.closeConnection();
		}
	}

}
