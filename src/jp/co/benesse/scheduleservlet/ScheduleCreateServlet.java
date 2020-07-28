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


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doGetされたものをdoPostに変換
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String scheduleDate = request.getParameter("scheduleDate");
		String startTimeHour = request.getParameter("startTimeHour");
		String startTimeMin = request.getParameter("startTimeMin");
		String endTimeHour = request.getParameter("endTimeHour");
		String endTimeMin = request.getParameter("endTimeMin");
		String place = request.getParameter("place");
		String title = request.getParameter("title");
		String content = request.getParameter("content");

		HttpSession session = request.getSession(true);
//		int userId = 1;
		int userId = (Integer) session.getAttribute("userId");

		ConnectionManager connectionManager = new ConnectionManager();
		try {
			Connection connection = connectionManager.getConnection();

			ScheduleBean scheduleBean = new ScheduleBean();
			scheduleBean.setUserId(userId);
			scheduleBean.setScheduleDate(Date.valueOf(scheduleDate));
			scheduleBean.setStartTime(Time.valueOf(startTimeHour +":"+ startTimeMin+":00"));
			scheduleBean.setEndTime(Time.valueOf(endTimeHour +":"+ endTimeMin+":00"));
			scheduleBean.setPlace(place);
			scheduleBean.setTitle(title);
			scheduleBean.setContent(content);

			ScheduleDAO scheduleDAO = new ScheduleDAO(connection);
			//予定が重複しているかをチェック
			boolean check = scheduleDAO.isBooking(scheduleBean);
			if(check == true) {
				request.setAttribute("popFlag",1);//予定重複フラグ
				request.setAttribute("scheduleBean", scheduleBean);
				request.setAttribute("startTimeHour", startTimeHour);//開始時間
				request.setAttribute("startTimeMin", startTimeMin);//開始分
				request.setAttribute("endTimeHour", endTimeHour);//終了時間
				request.setAttribute("endTimeMin", endTimeMin);//終了分
				//schedule_new.jsp（予定修正画面）にforwardする
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/schedule/schedule_new.jsp");
				dispatcher.forward(request, response);
				return;
			}

			//予定修正
			int result = scheduleDAO.registerSchedule(scheduleBean);
			if(result != 1){
				//error.jsp（エラー画面）にforwardする
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/error/error.jsp");
				dispatcher.forward(request, response);
				return;
			}
			connectionManager.commit();

			request.setAttribute("popFlag",0);//登録完了フラグ
			request.setAttribute("scheduleBean", scheduleBean);

		} catch(RuntimeException e){
			e.printStackTrace();
			connectionManager.rollback();
			//error.jsp（エラー画面）にforwardする
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/error/error.jsp");
			dispatcher.forward(request, response);
			return;
		} finally {
			connectionManager.closeConnection();
		}

		//schedule_new.jsp（予定修正画面）にforwardする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/schedule/schedule_new.jsp");
		dispatcher.forward(request, response);
		return;

	}

}
