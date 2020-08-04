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
	final LocalDate START_DATE = LocalDate.of(2020, 8, 1);
	final LocalDate END_DATE = LocalDate.of(2023, 7, 31);

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// doGetされたものをdoPostに変換
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int userId = 0;
		String flag = null;
		String calendarDate = null;
		String dateStr = null;
		String displayDate = null;
		String userName = null;
		String json = null;
		String jsonReplace = null;
		String jsonName = null;
		String jsonNameReplace = null;
		Date sqlDate = null;
		LocalDate scheduleDate = null;
		LocalDate localDate = null;
		ConnectionManager connectionManager = null;
		ScheduleDAO scheduleDAO = null;
		UserDAO userDAO = null;
		ObjectMapper mapper = null;
		List<String> userNameList = null;
		List<ArrayList<ScheduleBean>> getOneDayScheduleLists = null;
		List<ScheduleBean> getOneDayScheduleList = null;
		List<Integer> userIdList = null;
		Connection connection = null;
		HttpSession session = null;
		RequestDispatcher dispatcher = null;

		try {
			// セッションスコープから値を取得
			session = request.getSession();
			userId = (Integer) session.getAttribute("userId");

			// リクエストパラメータを取得
			flag = request.getParameter("flag");
			calendarDate = request.getParameter("date");
			dateStr = request.getParameter("scheduleDate");

			getOneDayScheduleLists = new ArrayList<>();
			getOneDayScheduleList = new ArrayList<>();

			if (flag == null) {
				if (calendarDate != null) {
					scheduleDate = LocalDate.parse(calendarDate, DateTimeFormatter.ofPattern("yyyy-MM-dd")); // 日付取得
					if (scheduleDate.isBefore(START_DATE) || scheduleDate.isAfter(END_DATE)) {
						throw new RuntimeException("日付エラー");// 例外処理
					}
				} else {
					scheduleDate = LocalDate.now();// 今日の日付取得
				}
			} else if (flag.equals("0")) { // 先日ボタンが押されたとき
				localDate = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd")); // 日付取得
				scheduleDate = localDate.minus(Period.ofDays(1));// 日付を-1する
			} else if (flag.equals("1")) { // 翌日ボタンが押されたとき
				localDate = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd")); // 日付取得
				scheduleDate = localDate.plus(Period.ofDays(1));// 日付を+1する
			} else { // それ以外
				scheduleDate = LocalDate.now();// 今日の日付取得
			}

			// yyyy/MM/dd(日)の形式の取得
			sqlDate = Date.valueOf(scheduleDate);
			displayDate = Calc.convertActualDate(sqlDate);

			connectionManager = new ConnectionManager();
			scheduleDAO = null;
			userDAO = null;

			connection = connectionManager.getConnection();
			scheduleDAO = new ScheduleDAO(connection);
			userDAO = new UserDAO(connection);
			userNameList = new ArrayList<String>();

			// ログインユーザーの予定を取得
			getOneDayScheduleList = scheduleDAO.getOneDaySchedule(scheduleDate, userId);
			getOneDayScheduleLists.add((ArrayList<ScheduleBean>) getOneDayScheduleList);
			userName = userDAO.getUserName(userId);
			userIdList = userDAO.countUser();
			userNameList.add(userName);

			// ログインユーザー以外の予定を取得
			for (int i = 0; i < userIdList.size(); i++) {
				if (userIdList.get(i) != userId) {
					getOneDayScheduleList = scheduleDAO.getOneDaySchedule(scheduleDate, userIdList.get(i));
					getOneDayScheduleLists.add((ArrayList<ScheduleBean>) getOneDayScheduleList);
					userName = userDAO.getUserName(userIdList.get(i));
					userNameList.add(userName);
				}
			}

			// json形式に変換し、リクエスト領域にset
			mapper = new ObjectMapper();
			json = mapper.writeValueAsString(getOneDayScheduleLists);
			jsonReplace = json.replaceAll("\"", "krnooon");

			jsonName = mapper.writeValueAsString(userNameList);
			jsonNameReplace = jsonName.replaceAll("\"", "krnooon");

			request.setAttribute("displayDate", displayDate);
			request.setAttribute("scheduleDate", scheduleDate);
			request.setAttribute("json", jsonReplace);
			request.setAttribute("json_name", jsonNameReplace);
			request.setAttribute("userNameList", userNameList);
			request.setAttribute("getOneDayScheduleList", getOneDayScheduleList);

			dispatcher = request.getRequestDispatcher("../WEB-INF/views/schedule/schedule_show.jsp");
			dispatcher.forward(request, response);
			return;
		} catch (RuntimeException e) {
			dispatcher = request.getRequestDispatcher("../WEB-INF/views/error/error.jsp");
			dispatcher.forward(request, response);
			return;
		} catch (Exception e) {
			dispatcher = request.getRequestDispatcher("../WEB-INF/views/error/error.jsp");
			dispatcher.forward(request, response);
			return;
		} finally {
			connectionManager.closeConnection();
		}

	}
}
