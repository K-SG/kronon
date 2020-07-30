package jp.co.benesse.actualservlet;

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

@WebServlet("/user/acutualupdate")
public class AcutualUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int actualTime = 0;
		int scheduleId = 0;
		int result = 0;
		int popFlag = 0;// ポップアップ用のフラグ
		String scheduleIdStr = null;
		String actualHourStr = null;
		String actualMinuteStr = null;
		String comment = null;
		String actualFlag = null;
		ConnectionManager connectionManager = null;
		ScheduleBean scheduleBean = null;
		ScheduleDAO scheduleDAO = null;
		Connection connection = null;
		RequestDispatcher dispatcher = null;

		try {
			// リクエストパラメータの取得
			scheduleIdStr = request.getParameter("scheduleId");
			actualHourStr = request.getParameter("actualHour");
			actualMinuteStr = request.getParameter("actualMinute");
			comment = request.getParameter("comment");
			actualFlag = (String) request.getParameter("actual-flag");
			System.out.println(actualFlag);

			// パラメータの変換・換算
			actualTime = Integer.parseInt(actualHourStr) * 60 + Integer.parseInt(actualMinuteStr);
			scheduleId = Integer.parseInt(scheduleIdStr);

			connectionManager = new ConnectionManager();
			connection = connectionManager.getConnection();
			scheduleDAO = new ScheduleDAO(connection);

			if (scheduleDAO.isDeleted(scheduleId)) {
				throw new RuntimeException("既に削除されている");
			}

			// 実績、振り返りコメントを更新
			result = scheduleDAO.updateSchedule(scheduleId, actualTime, comment);
			connectionManager.commit();

			if (result != 1) {
				throw new RuntimeException("更新できていない");
			}

			scheduleBean = scheduleDAO.getScheduleByScheduleId(scheduleId);

			// 更新しましたポップアップのフラグをセット
			request.setAttribute("popFlag", popFlag);
			request.setAttribute("scheduleBean", scheduleBean);

			if(actualFlag.equals("0")){
			dispatcher = request.getRequestDispatcher("/WEB-INF/views/actual/actual_new.jsp");
			dispatcher.forward(request, response);
			}else{
				dispatcher = request.getRequestDispatcher("/WEB-INF/views/actual/actual_edit.jsp");
				dispatcher.forward(request, response);
				}
			return;
		} catch (RuntimeException e) {
			connectionManager.rollback();
			e.printStackTrace();
			dispatcher = request.getRequestDispatcher("/WEB-INF/views/error/error.jsp");
			dispatcher.forward(request, response);
			return;
		} finally {
			connectionManager.closeConnection();
		}
	}

}
