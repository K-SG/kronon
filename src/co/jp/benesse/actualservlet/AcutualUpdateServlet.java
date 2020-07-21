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

@WebServlet("/user/AcutualUpdateServlet")
public class AcutualUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// リクエストパラメータの取得
		String scheduleIdStr = request.getParameter("scheduleId");
		String actualHourStr = request.getParameter("actualHour");
		String actualMinuteStr = request.getParameter("actualMinute");
		String comment = request.getParameter("comment");

		// パラメータの変換・換算
		int actualTime = Integer.parseInt(actualHourStr) * 60 + Integer.parseInt(actualMinuteStr);
		int scheduleId = Integer.parseInt(scheduleIdStr);

		//ポップアップ用のフラグ
		int popFlag = 0;

		ConnectionManager connectionManager = new ConnectionManager();

		try {
			Connection connection = connectionManager.getConnection();
			ScheduleDAO scheduleDAO = new ScheduleDAO(connection);

			if (scheduleDAO.isDeleted(scheduleId)) {// すでに別タブで予定が削除されていた場合
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/error/error.jsp");
				dispatcher.forward(request, response);
				return;
			}

			// 実績、振り返りコメントを更新。更新件数0件の場合はエラー画面へフォワード
			if(scheduleDAO.updateSchedule(scheduleId, actualTime, comment) != 1){
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/error/error.jsp");
				dispatcher.forward(request, response);
				return;
			}
			connectionManager.commit();

			// 更新しましたポップアップのフラグをセット
			request.setAttribute("popFlag", popFlag);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/actual/actual_new.jsp");
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
