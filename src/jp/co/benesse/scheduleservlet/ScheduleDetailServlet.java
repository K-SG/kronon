package jp.co.benesse.scheduleservlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

	/*	int id = request.getParameter("id");*/

		ConnectionManager connectionManager = new ConnectionManager();
		ScheduleBean scheduleBean = new ScheduleBean();



	}

}
