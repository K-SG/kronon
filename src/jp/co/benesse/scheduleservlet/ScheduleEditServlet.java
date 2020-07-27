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

@WebServlet("/user/scheduleedit")
public class ScheduleEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doGetされたものをdoPostに変換
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String scheduleIdStr = request.getParameter("scheduleId");
//		String scheduleIdStr = "1";
		int scheduleId = Integer.parseInt(scheduleIdStr);

		ConnectionManager connectionManager = new ConnectionManager();
		try {
			Connection connection = connectionManager.getConnection();
			ScheduleDAO scheduleDAO = new ScheduleDAO(connection);
			ScheduleBean scheduleBean = new ScheduleBean();
			scheduleBean = scheduleDAO.getScheduleByScheduleId(scheduleId);
			request.setAttribute("scheduleBean", scheduleBean);

		} catch(RuntimeException e){
			//error.jsp（エラー画面）にforwardする
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/error/error.jsp");
			dispatcher.forward(request, response);
			return;
		} finally {
			connectionManager.closeConnection();
		}

		//schedule_edit.jsp（予定修正画面）にforwardする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/schedule/schedule_edit.jsp");
		dispatcher.forward(request, response);
		return;
	}

}
