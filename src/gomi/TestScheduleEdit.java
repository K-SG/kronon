package gomi;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestScheduleEdit
 */
@WebServlet("/user/TestScheduleEdit")
public class TestScheduleEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		request.setAttribute("date", "2020-10-16");
		request.setAttribute("startTime", "09:00");
		request.setAttribute("endTime", "15:30");
		request.setAttribute("place", "2");
		request.setAttribute("title", "予定が入るよ");
		request.setAttribute("content", "内容が入るよ");
		request.setAttribute("popFlag", "1");

		RequestDispatcher dispatcher = request.getRequestDispatcher("../WEB-INF/views/actual/actual_new.jsp");
		dispatcher.forward(request, response);
		return;
	}

}
