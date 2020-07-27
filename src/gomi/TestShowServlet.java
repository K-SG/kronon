package gomi;

import java.io.IOException;
import java.sql.Connection;

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

/**
 * Servlet implementation class TestShowServlet
 */
@WebServlet("/TestShowServlet")
public class TestShowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestShowServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//int id = Integer.parseInt(request.getParameter("id"));
        int id = 3 ;
		ConnectionManager connectionManager = new ConnectionManager();
		ScheduleBean scheduleBean = new ScheduleBean();



			Connection connection = connectionManager.getConnection();
			ScheduleDAO scheduleDAO = new ScheduleDAO(connection);

			if (scheduleDAO.isDeleted(id)){
				RequestDispatcher dispatcher = request.getRequestDispatcher("../WEB-INF/views/error/error.jsp");
				dispatcher.forward(request, response);
				return;
			}

			scheduleBean = scheduleDAO.getScheduleByScheduleId(id);
			System.out.println(scheduleBean);
			request.setAttribute("scheduleBean", scheduleBean);
		HttpSession session = request.getSession(true);
		session.setAttribute("userId", "5");
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/schedule/schedule_detail.jsp");
		dispatcher.forward(request, response);
		return;

	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
