package jp.co.benesse.scheduletableservlet;

import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

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

@WebServlet("/user/scheduleshowall")
public class ScheduleShowAllServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doGetされたものをdoPostに変換
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int userId= (int) session.getAttribute("userId");
		int flag = (int) request.getAttribute("flag");
		List<ArrayList<ScheduleBean>> getOneDayScheduleLists = new ArrayList<>();
		List<ScheduleBean> getOneDayScheduleList = new ArrayList<>();
		LocalDate scheduleDate;
		if(flag==0){
			scheduleDate = (LocalDate)request.getAttribute("scheduleDate");
			scheduleDate = scheduleDate.minus(Period.ofDays(1));
		}
		else if(flag==1){
			scheduleDate = (LocalDate)request.getAttribute("scheduleDate");
			scheduleDate = scheduleDate.plus(Period.ofDays(1));
		}
		else{
			scheduleDate=LocalDate.now();
		}
		request.setAttribute("scheduleDate",scheduleDate);
		ConnectionManager connectionManager = new ConnectionManager();
		ScheduleDAO scheduleDAO = null;
		try{
			Connection connection = connectionManager.getConnection();
			scheduleDAO = new ScheduleDAO(connection);
			if(userId<=5){
				getOneDayScheduleList = scheduleDAO.getOneDaySchedule(scheduleDate,userId);
				getOneDayScheduleLists.add((ArrayList<ScheduleBean>)getOneDayScheduleList);

			}
			for(int i=0;i<5;i++){
				if(i!=userId){
					getOneDayScheduleList = scheduleDAO.getOneDaySchedule(scheduleDate,i);
					getOneDayScheduleLists.add((ArrayList<ScheduleBean>)getOneDayScheduleList);
				}
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher("../WEB-INF/view/error/schedule_show.jsp");
			dispatcher.forward(request, response);
	}catch(RuntimeException e){
		RequestDispatcher dispatcher = request.getRequestDispatcher("../WEB-INF/view/error/error.jsp");
		dispatcher.forward(request, response);
	}
		finally{
			connectionManager.closeConnection();
		}

}
}

