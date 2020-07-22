package test;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ControlServlet
 */
@WebServlet("/web/control")
public class ControlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ControlServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String kakakuStr = request.getParameter("kakaku");

		String comp;
		ErrorCheck rCheck = new ErrorCheck();
		comp = rCheck.numCheck(id, kakakuStr);
		if (comp == null) {
			if(rCheck.exitId(id)!=null){
				comp = rCheck.exitId(id);
				request.setAttribute("comp", comp);
				RequestDispatcher dispatcher = request.getRequestDispatcher("mod.jsp");
				dispatcher.forward(request, response);
				return;
			}
			Integer kakaku = Integer.parseInt(kakakuStr);
			request.setAttribute("id", id);
			request.setAttribute("kakaku", kakaku);
			request.setAttribute("name", name);
			RequestDispatcher dispatcher = request.getRequestDispatcher("kakunin.jsp");
			dispatcher.forward(request, response);
			return;
		} else {
			request.setAttribute("comp", comp);
			RequestDispatcher dispatcher = request.getRequestDispatcher("mod.jsp");
			dispatcher.forward(request, response);
		}

	}

}
