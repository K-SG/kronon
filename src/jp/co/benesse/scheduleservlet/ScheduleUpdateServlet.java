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
		Date date;
		Time startTime;
		Time endTime;
		System.out.println(scheduleId+","+scheduleDate+","+startTimeHour+","+startTimeMin+","+endTimeHour+","+endTimeMin+","+place+","+title+","+content);
		HttpSession session = request.getSession(true);
		int userId = (int)session.getAttribute("userId");

		ConnectionManager connectionManager = new ConnectionManager();
		try {
			Connection connection = connectionManager.getConnection();
			date=Date.valueOf(scheduleDate);
			startTime = Time.valueOf(startTimeHour +":"+ startTimeMin + ":00");
			endTime = Time.valueOf(endTimeHour +":"+ endTimeMin+ ":00");
			ScheduleBean scheduleBean = new ScheduleBean();
			scheduleBean.setUserId(userId);
//			scheduleBean.setUserId(1);
			scheduleBean.setScheduleId(scheduleId);
			scheduleBean.setScheduleDate(date);
			scheduleBean.setStartTime(startTime);
			scheduleBean.setEndTime(endTime);
			scheduleBean.setPlace(place);
			scheduleBean.setTitle(title);
			scheduleBean.setContent(content);

			//すでに削除されているかをチェック
			ScheduleDAO scheduleDAO = new ScheduleDAO(connection);
			boolean check = scheduleDAO.isDeleted(scheduleId);
			if(check == true){
				System.out.println("既に削除されている");
				//error.jsp（エラー画面）にforwardする
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/viws/error/error.jsp");
				dispatcher.forward(request, response);
				return;
			}

			//予定が重複しているかをチェック
			check = scheduleDAO.isBooking(scheduleBean);
			if(check != true) {
				request.setAttribute("popFlag",0);//予定重複フラグ
				request.setAttribute("scheduleBean", scheduleBean);
				request.setAttribute("startTimeHour", startTimeHour);//開始時間
				request.setAttribute("startTimeMin", startTimeMin);//開始分
				request.setAttribute("endTimeHour", endTimeHour);//終了時間
				request.setAttribute("endTimeMin", endTimeMin);//終了分
				//schedule_edit.jsp（予定修正画面）にforwardする
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/schedule/schedule_edit.jsp");
				dispatcher.forward(request, response);
				return;
			}

			//予定修正

			int result = scheduleDAO.updateSchedule(scheduleId,date,startTime,endTime,title,content,place);
			System.out.println(result);
			if(result != 1){
				System.out.println("アップデートができていない");
				//error.jsp（エラー画面）にforwardする
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/error/error.jsp");
				dispatcher.forward(request, response);
				return;
			}
			connectionManager.commit();

			request.setAttribute("popFlag",0);//修正完了フラグ
			request.setAttribute("scheduleBean", scheduleBean);

		} catch(RuntimeException e){
			System.out.println("予期せぬエラー");
			e.printStackTrace();
			connectionManager.rollback();
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
