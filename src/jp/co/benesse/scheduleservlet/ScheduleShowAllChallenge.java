package jp.co.benesse.scheduleservlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
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

import com.fasterxml.jackson.databind.ObjectMapper;

import jp.co.benesse.dataaccess.cm.ConnectionManager;
import jp.co.benesse.dataaccess.dao.ScheduleDAO;
import jp.co.benesse.dataaccess.value.ScheduleBean;

@WebServlet("/user/scheduleshowallchallenge")
public class ScheduleShowAllChallenge extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doGetされたものをdoPostに変換
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		//SessionからユーザーIDを取ってくる
		int userId= 1;//(int) session.getAttribute("userId");
		//どこからこのサーブレットに来たか判断するためのフラグを取ってくる
		String flag = (String) request.getAttribute("flag");
		List<ArrayList<ScheduleBean>> getOneDayScheduleLists = new ArrayList<>();
		List<ScheduleBean> getOneDayScheduleList = new ArrayList<>();
		LocalDate scheduleDate;

		if(flag==null){
			Date date = Date.valueOf("2020-07-10");
			scheduleDate=date.toLocalDate();//LocalDate.now();//今日の日付取得
		}
		else if(flag.equals('0')){	//先日ボタンが押されたとき
			scheduleDate = (LocalDate)request.getAttribute("scheduleDate");	//日付取得
			scheduleDate = scheduleDate.minus(Period.ofDays(1));//日付を-1する
		}
		else if(flag.equals('1')){		//翌日ボタンが押されたとき
			scheduleDate = (LocalDate)request.getAttribute("scheduleDate");//日付取得
			scheduleDate = scheduleDate.plus(Period.ofDays(1));//日付を+1する
		}
		else{	//それ以外
			Date date = Date.valueOf("2020-07-10");
			scheduleDate=date.toLocalDate();//LocalDate.now();//今日の日付取得
		}
		//日付をリクエスト領域にセットする
		request.setAttribute("scheduleDate",scheduleDate);

		ConnectionManager connectionManager = new ConnectionManager();
		ScheduleDAO scheduleDAO = null;
		try{
			Connection connection = connectionManager.getConnection();
			scheduleDAO = new ScheduleDAO(connection);
			if(userId<=5){
				//まずはログインユーザーの予定を取得する
				System.out.println("scheduleDate : "+scheduleDate);
				System.out.println(userId);
				getOneDayScheduleList = scheduleDAO.getOneDaySchedule(scheduleDate,userId);
				getOneDayScheduleLists.add((ArrayList<ScheduleBean>)getOneDayScheduleList);
				System.out.println("ここだよ"+ getOneDayScheduleList);
			}
			for(int i=0;i<5;i++){
				if(i!=userId){
					//まずはログインユーザー以外の予定を取得する
					getOneDayScheduleList = scheduleDAO.getOneDaySchedule(scheduleDate,i);
					getOneDayScheduleLists.add((ArrayList<ScheduleBean>)getOneDayScheduleList);
				}
			}

			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writeValueAsString(getOneDayScheduleLists);
			String json_replace = json.replaceAll("\"","krnooon");
			request.setAttribute("json", json_replace);
			System.out.println(json);

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

