package jp.co.benesse.scheduleservlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import jp.co.benesse.calc.Calc;
import jp.co.benesse.dataaccess.cm.ConnectionManager;
import jp.co.benesse.dataaccess.dao.ScheduleDAO;
import jp.co.benesse.dataaccess.dao.UserDAO;
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

		//SessionからユーザーIDを取ってくる
		int userId= (Integer)session.getAttribute("userId");
		//どこからこのサーブレットに来たか判断するためのフラグを取ってくる
		String flag = request.getParameter("flag");
		String calendarDate = request.getParameter("date");
		System.out.println("flag"+flag);

		List<ArrayList<ScheduleBean>> getOneDayScheduleLists = new ArrayList<>();
		List<ScheduleBean> getOneDayScheduleList = new ArrayList<>();
		LocalDate scheduleDate;
		if(flag==null ){
			if( calendarDate!=null){
				scheduleDate =LocalDate.parse(calendarDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));	//日付取得
			}else{
				scheduleDate=LocalDate.now();//今日の日付取得
			}
		}
		else if(flag.equals("0")){	//先日ボタンが押されたとき
			String dateStr = request.getParameter("scheduleDate");
			LocalDate localDate =LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));	//日付取得
			scheduleDate = localDate.minus(Period.ofDays(1));//日付を-1する
		}
		else if(flag.equals("1")){		//翌日ボタンが押されたとき
			String dateStr = request.getParameter("scheduleDate");
			LocalDate localDate =LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));	//日付取得
			scheduleDate = localDate.plus(Period.ofDays(1));//日付を+1する
		}
		else{	//それ以外
			scheduleDate=LocalDate.now();//今日の日付取得
		}

		//yyyy/MM/dd(日)の形式の取得
		Date sqlDate = Date.valueOf(scheduleDate);
		String displayDate = Calc.convertActualDate(sqlDate);
		//日付をリクエスト領域にセットする
		request.setAttribute("displayDate",displayDate);
		request.setAttribute("scheduleDate",scheduleDate);

		ConnectionManager connectionManager = new ConnectionManager();
		ScheduleDAO scheduleDAO = null;
		UserDAO userDAO = null;

		try{
			Connection connection = connectionManager.getConnection();
			scheduleDAO = new ScheduleDAO(connection);
			userDAO = new UserDAO(connection);
			List<String> userNameList = new ArrayList<String>();
			String userName;
			if(userId<=5){
				//まずはログインユーザーの予定を取得する
				getOneDayScheduleList = scheduleDAO.getOneDaySchedule(scheduleDate,userId);
				getOneDayScheduleLists.add((ArrayList<ScheduleBean>)getOneDayScheduleList);
				userName = userDAO.getUserName(userId);
				userNameList.add(userName);
			}
			for(int i=1;i<=5;i++){
				if(i!=userId){
					//まずはログインユーザー以外の予定を取得する
					getOneDayScheduleList = scheduleDAO.getOneDaySchedule(scheduleDate,i);
					getOneDayScheduleLists.add((ArrayList<ScheduleBean>)getOneDayScheduleList);
					userName = userDAO.getUserName(i);
					userNameList.add(userName);
				}
			}

			//json形式に変換し、リクエスト領域にset
			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writeValueAsString(getOneDayScheduleLists);
			String jsonReplace = json.replaceAll("\"","krnooon");
			request.setAttribute("json", jsonReplace);
			System.out.println(json);

			String jsonName = mapper.writeValueAsString(userNameList);
			String jsonNameReplace = jsonName.replaceAll("\"","krnooon");
			request.setAttribute("json_name", jsonNameReplace);
			System.out.println(jsonName);

			request.setAttribute("userNameList",userNameList);
			request.setAttribute("getOneDayScheduleList",getOneDayScheduleList);
			RequestDispatcher dispatcher = request.getRequestDispatcher("../WEB-INF/views/schedule/schedule_show.jsp");
			dispatcher.forward(request, response);
	}catch(RuntimeException e){
		RequestDispatcher dispatcher = request.getRequestDispatcher("../WEB-INF/views/error/error.jsp");
		dispatcher.forward(request, response);
	}
		finally{
			connectionManager.closeConnection();
		}

}
}

