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

		//セッションからIdを取得
		HttpSession session = request.getSession();
		int userId = (int)session.getAttribute("userId");

		int scheduleId = Integer.parseInt(request.getParameter("scheduleId"));

		ConnectionManager connectionManager = new ConnectionManager();
		ScheduleBean scheduleBean = new ScheduleBean();

		try {

			Connection connection = connectionManager.getConnection();
			ScheduleDAO scheduleDAO = new ScheduleDAO(connection);

			//予定が既に削除されている場合
			if (scheduleDAO.isDeleted(scheduleId)) {
				throw new RuntimeException("予定が既に削除されている");
			}

			scheduleBean = scheduleDAO.getScheduleByScheduleId(scheduleId);

			//ログイン者以外の予定にアクセスできないようにする
			if(userId != scheduleBean.getUserId()){
				throw new RuntimeException("ログイン者以外の予定にアクセスした");
			}

			request.setAttribute("scheduleBean", scheduleBean);

			RequestDispatcher dispatcher = request.getRequestDispatcher("../WEB-INF/views/actual/actual_detail.jsp");
			dispatcher.forward(request, response);
			return;

		} catch (RuntimeException e) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("../WEB-INF/views/error/error.jsp");
			dispatcher.forward(request, response);
			return;
		} finally {
			connectionManager.closeConnection();
		}

	}

}
