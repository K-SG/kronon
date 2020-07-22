package gomi;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

		request.setAttribute("owner", "樋口");
		request.setAttribute("actualTime", "2時間");
		request.setAttribute("scheduleDate", "2020/07/11");
		request.setAttribute("startTime", "10:00");
		request.setAttribute("endTime", "11:00");
		request.setAttribute("place", "在宅");
		request.setAttribute("title", "テスト");
		request.setAttribute("content", "テストテスト");
		HttpSession session = request.getSession(true);
		session.setAttribute("userName", "樋口");
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
