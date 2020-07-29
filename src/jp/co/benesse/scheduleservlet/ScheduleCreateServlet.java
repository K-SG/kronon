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
import javax.servlet.http.HttpSession;

import jp.co.benesse.dataaccess.cm.ConnectionManager;
import jp.co.benesse.dataaccess.dao.ScheduleDAO;
import jp.co.benesse.dataaccess.value.ScheduleBean;

@WebServlet("/user/schedulecreate")
public class ScheduleCreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// doGetされたものをdoPostに変換
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int userId = 0;
		int result = 0;
		String scheduleDate = null;
		String startTimeHour = null;
		String startTimeMin = null;
		String endTimeHour = null;
		String endTimeMin = null;
		String place = null;
		String title = null;
		String content = null;
		boolean check = true;
		ConnectionManager connectionManager = null;
		ScheduleDAO scheduleDAO = null;
		ScheduleBean scheduleBean = null;
		Connection connection = null;
		HttpSession session = null;
		RequestDispatcher dispatcher = null;

		try {
			// セッションスコープから値を取得
			session = request.getSession(true);
			userId = (Integer) session.getAttribute("userId");

			// リクエストパラメータを取得
			scheduleDate = request.getParameter("scheduleDate");
			startTimeHour = request.getParameter("startTimeHour");
			startTimeMin = request.getParameter("startTimeMin");
			endTimeHour = request.getParameter("endTimeHour");
			endTimeMin = request.getParameter("endTimeMin");
			place = request.getParameter("place");
			title = request.getParameter("title");
			content = request.getParameter("content");

			scheduleBean = new ScheduleBean();
			scheduleBean.setUserId(userId);
			scheduleBean.setScheduleDate(Date.valueOf(scheduleDate));
			scheduleBean.setStartTime(Time.valueOf(startTimeHour + ":" + startTimeMin + ":00"));
			scheduleBean.setEndTime(Time.valueOf(endTimeHour + ":" + endTimeMin + ":00"));
			scheduleBean.setPlace(place);
			scheduleBean.setTitle(title);
			scheduleBean.setContent(content);

			connectionManager = new ConnectionManager();
			connection = connectionManager.getConnection();
			scheduleDAO = new ScheduleDAO(connection);

			// 予定が重複しているかをチェック
			check = scheduleDAO.isBooking(scheduleBean);
			if (check == true) {
				request.setAttribute("popFlag", 1);// 予定重複フラグ
				request.setAttribute("scheduleBean", scheduleBean);
				request.setAttribute("startTimeHour", startTimeHour);// 開始時間
				request.setAttribute("startTimeMin", startTimeMin);// 開始分
				request.setAttribute("endTimeHour", endTimeHour);// 終了時間
				request.setAttribute("endTimeMin", endTimeMin);// 終了分
				// schedule_new.jsp（予定修正画面）にforwardする
				dispatcher = request.getRequestDispatcher("/WEB-INF/views/schedule/schedule_new.jsp");
				dispatcher.forward(request, response);
				return;
			}

			// 予定修正
			result = scheduleDAO.registerSchedule(scheduleBean);
			if (result != 1) {
				throw new RuntimeException("予定修正に失敗");
			}
			connectionManager.commit();

			request.setAttribute("popFlag", 0);// 登録完了フラグ
			request.setAttribute("scheduleBean", scheduleBean);

			// schedule_new.jsp（予定修正画面）にforwardする
			dispatcher = request.getRequestDispatcher("/WEB-INF/views/schedule/schedule_new.jsp");
			dispatcher.forward(request, response);
			return;

		} catch (RuntimeException e) {
			e.printStackTrace();
			connectionManager.rollback();
			dispatcher = request.getRequestDispatcher("/WEB-INF/views/error/error.jsp");
			dispatcher.forward(request, response);
			return;
		} catch (Exception e) {
			e.printStackTrace();
			connectionManager.rollback();
			dispatcher = request.getRequestDispatcher("/WEB-INF/views/error/error.jsp");
			dispatcher.forward(request, response);
			return;
		} finally {
			connectionManager.closeConnection();
		}

	}

}
