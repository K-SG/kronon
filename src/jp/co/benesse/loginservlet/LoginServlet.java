package jp.co.benesse.loginservlet;
//test

//test2
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
import jp.co.benesse.dataaccess.dao.UserDAO;
import jp.co.benesse.dataaccess.value.UserBean;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/login/login.jsp");
		dispatcher.forward(request, response);
		return;
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String mail = request.getParameter("mail");
		String password = request.getParameter("password");


		//正しいユーザーかの判定

		//DBから入力されたuserIDの情報を取得

		ConnectionManager connectionManager = new ConnectionManager();
		UserBean userBean = new UserBean();

		try {
			Connection connection = connectionManager.getConnection();
			UserDAO userDAO = new UserDAO(connection);
			User user = userDAO.selectByUserId(Integer.parseInt(userId));

			if(user == null){
				request.setAttribute("inputIDerrorMsg", "入力したユーザーIDは未登録です。");
				RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/userLogin.jsp");
				dispatcher.forward(request, response);
				return;
			}

			if(!(user.getPassword().equals(password))){
				request.setAttribute("inputPassworderrorMsg", "パスワードが間違っています。");
				RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/userLogin.jsp");
				dispatcher.forward(request, response);
				return;
			}

			//セッションにインスタンスの設定
			HttpSession session = request.getSession(true);
			session.setAttribute("user", user);

		} finally {
			connectionManager.closeConnection();

		}

		//リダイレクト
		response.sendRedirect("menu");
		return;

	}

}
