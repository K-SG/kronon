package jp.co.benesse.scheduleservlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.benesse.dataaccess.cm.ConnectionManager;
import jp.co.benesse.dataaccess.value.ScheduleBean;

/**
 * Servlet implementation class ScheduleDetailServlet
 */
@WebServlet("/user/scheduledetail")
public class ScheduleDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));

		try{
			ConnectionManager connectionManager = new ConnectionManager();
			ScheduleBean scheduleBean = new ScheduleBean();
//			scheduleBean = scheduleDAO.getScheduleById(id);

			HttpSession session = request.getSession(true);
//			session.setAttribute("schduleDate", scheduleBean.getSchduleDate());
			session.setAttribute("startTime", scheduleBean.getStartTime());
			session.setAttribute("endTime", scheduleBean.getEndTime());
			session.setAttribute("place", scheduleBean.getPlace());
			session.setAttribute("title", scheduleBean.getTitle());
			session.setAttribute("content", scheduleBean.getContent());
			session.setAttribute("actualTime", scheduleBean.getActualTime());

			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/login/login.jsp");
			dispatcher.forward(request, response);
			return;

	}catch(RuntimeException e){
		throw e;
	}finally {
/*		connectionManager.closeConnection();*/
	}

}
}
