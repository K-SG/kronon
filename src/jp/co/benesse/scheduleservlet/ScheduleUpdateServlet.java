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
import jp.co.benesse.dataaccess.value.UserBean;

@WebServlet("/user/scheduleupdate")
public class ScheduleUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doGetされたものをdoPostに変換
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String scheduleIdStr = request.getParameter("scheduleId");
		int scheduleId = Integer.parseInt(scheduleIdStr);
		String scheduleDate = request.getParameter("scheduleDate");
		String startTimeHour = request.getParameter("startTimeHour");
		String startTimeMin = request.getParameter("startTimeMin");
		String endTimeHour = request.getParameter("endTimeHour");
		String endTimeMin = request.getParameter("endTimeMin");
		String place = request.getParameter("place");
		String title = request.getParameter("title");
		String content = request.getParameter("content");

		HttpSession session = request.getSession(true);
		UserBean userBean = (UserBean)session.getAttribute("userBean");

		ConnectionManager connectionManager = new ConnectionManager();
		try {
			Connection connection = connectionManager.getConnection();

			ScheduleBean scheduleBean = new ScheduleBean();
			scheduleBean.setUserId(userBean.getUserId());
			scheduleBean.setScheduleId(scheduleId);
			scheduleBean.setScheduleDate(Date.valueOf(scheduleDate));
			scheduleBean.setStartTime(Time.valueOf(startTimeHour +":"+ startTimeMin));
			scheduleBean.setEndTime(Time.valueOf(endTimeHour +":"+ endTimeMin));
			scheduleBean.setPlace(place);
			scheduleBean.setTitle(title);
			scheduleBean.setContent(content);

			//すでに削除されているかをチェック
			ScheduleDAO scheduleDAO = new ScheduleDAO(connection);
			boolean check = scheduleDAO.isDeleted(scheduleId);
			if(check == true){
				//error.jsp（エラー画面）にforwardする
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/viws/error/error.jsp");
				dispatcher.forward(request, response);
				return;
			}

			//予定が重複しているかをチェック
			check = scheduleDAO.isBooking(scheduleBean);
			if(check == true) {
				request.setAttribute("popFlag",0);//予定重複フラグ
				request.setAttribute("scheduleBean", scheduleBean);
				request.setAttribute("startTimeHour", startTimeHour);//開始時間
				request.setAttribute("startTimeMin", startTimeMin);//開始分
				request.setAttribute("endTimeHour", endTimeHour);//終了時間
				request.setAttribute("endTimeMin", endTimeMin);//終了分
				//schedule_edit.jsp（予定修正画面）にforwardする
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/viws/schedule/schedule_edit.jsp");
				dispatcher.forward(request, response);
				return;
			}

			//予定修正
			int result = scheduleDAO.registerSchedule(scheduleBean);
			if(result != 1){
				//error.jsp（エラー画面）にforwardする
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/viws/error/error.jsp");
				dispatcher.forward(request, response);
				return;
			}
			connectionManager.commit();

			request.setAttribute("popFlag",0);//修正完了フラグ
			request.setAttribute("scheduleBean", scheduleBean);

		} catch(RuntimeException e){
			connectionManager.rollback();
			//error.jsp（エラー画面）にforwardする
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/error/error.jsp");
			dispatcher.forward(request, response);
			return;
		} finally {
			connectionManager.closeConnection();
		}

		//schedule_edit.jsp（予定修正画面）にforwardする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/viws/schedule/schedule_edit.jsp");
		dispatcher.forward(request, response);
		return;

	}

}
