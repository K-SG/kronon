package jp.co.benesse.userservlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UserNewServlet
 */
@WebServlet("/usernew")
public class UserNewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// doGetされたものをdoPostに変換
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// user_new.jsp(アカウント新規作成画面)にforwardする。
		request.setAttribute("popFlag", 7);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/user/user_new.jsp");
		dispatcher.forward(request, response);
		return;
	}

}
