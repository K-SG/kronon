package jp.co.benesse.scheduletableservlet;

import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

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
		String userId= (String) session.getAttribute("userId");
		int flag = (int) session.getAttribute("flag");


		ScheduleBean scheduleBean =new ScheduleBean();
		List<ArrayList<ScheduleBean>> getOneDayScheduleLists = new ArrayList<ArrayList<ScheduleBean>>();
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

	}catch(RuntimeException e){

	}

}
}

