package jp.co.benesse.scheduletableservlet;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		if(flag==0){
			LocalDate scheduleDate = (LocalDate)request.getAttribute("scheduleDate");
			
		}
		else if(flag==1){

		}
		else{
			LocalDate scheduleDate=LocalDate.now();
			request.setAttribute("scheduleDate",scheduleDate);
		}
	}

}

