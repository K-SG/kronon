package jp.co.benesse.scheduleservlet;

import java.io.IOException;
import java.sql.Connection;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.benesse.calc.Calc;
import jp.co.benesse.dataaccess.cm.ConnectionManager;
import jp.co.benesse.dataaccess.dao.ScheduleDAO;
import jp.co.benesse.dataaccess.value.ScheduleBean;

@WebServlet("/user/scheduledetail")
public class ScheduleDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));

		ConnectionManager connectionManager = new ConnectionManager();
		ScheduleBean scheduleBean = new ScheduleBean();

		try {

			Connection connection = connectionManager.getConnection();
			ScheduleDAO scheduleDAO = new ScheduleDAO(connection);

			if (scheduleDAO.isDeleted(id)){
				RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/error/error.jsp");
				dispatcher.forward(request, response);
				return;
			}

			scheduleBean = scheduleDAO.getScheduleByScheduleId(id);
			String actualTime = Calc.calcActualTime(scheduleBean);
			SimpleDateFormat date = new SimpleDateFormat("yyyy/MM/dd");
			String scheduleDate = date.format(scheduleBean.getScheduleDate());
			SimpleDateFormat time = new SimpleDateFormat("HH:mm");
			String scheduleStartTime = time.format(scheduleBean.getStartTime());
			String scheduleEndTime = time.format(scheduleBean.getEndTime());

			System.out.println(scheduleBean);
			System.out.println(scheduleBean.getUserName());
			System.out.println(actualTime);
			System.out.println(scheduleDate);
			System.out.println(scheduleStartTime);
			System.out.println(scheduleEndTime);
			System.out.println(scheduleBean.getPlace());
			System.out.println(scheduleBean.getTitle());
			System.out.println(scheduleBean.getContent());

			HttpSession session = request.getSession(true);
			session.setAttribute("owner", scheduleBean.getUserName());
			session.setAttribute("actualTime", actualTime);
			session.setAttribute("schduleDate", scheduleDate);
			session.setAttribute("startTime", scheduleStartTime);
			session.setAttribute("endTime", scheduleEndTime);
			session.setAttribute("place", scheduleBean.getPlace());
			session.setAttribute("title", scheduleBean.getTitle());
			session.setAttribute("content", scheduleBean.getContent());

			RequestDispatcher dispatcher = request.getRequestDispatcher("../WEB-INF/views/schedule/schedule_detail.jsp");
			dispatcher.forward(request, response);
			return;

		} catch (RuntimeException e) {
			throw e;
		} finally {
			connectionManager.closeConnection();
		}

	}
}
