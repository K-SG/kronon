package test;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/menus")
public class MenuServlet extends HttpServlet {

	public void doGet( HttpServletRequest request,HttpServletResponse response )
		   throws ServletException, IOException {
		this.doPost(request, response);
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher( "jsp/menu.jsp" );
		dispatcher.forward( request, response );
	}
}
