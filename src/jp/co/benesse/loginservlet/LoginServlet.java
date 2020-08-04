package jp.co.benesse.loginservlet;

//loginservletのプッシュ確認
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
import jp.co.benesse.dataaccess.crypt.CryptographyLogic;
import jp.co.benesse.dataaccess.dao.UserDAO;
import jp.co.benesse.dataaccess.value.UserBean;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int preUserCount = 0;
		ConnectionManager connectionManager = null;
		UserDAO userDAO = null;
		Connection connection = null;
		RequestDispatcher dispatcher = null;
		HttpSession session = null;

		try {
			connectionManager = new ConnectionManager();
			connection = connectionManager.getConnection();
			userDAO = new UserDAO(connection);
			preUserCount = userDAO.countUser().size();

			session = request.getSession();
			session.setAttribute("preUserCount", preUserCount);

			dispatcher = request.getRequestDispatcher("WEB-INF/views/login/login.jsp");
			dispatcher.forward(request, response);
			return;

		} catch (RuntimeException e) {
			response.sendRedirect("login");
			return;
		} catch (Exception e) {
			response.sendRedirect("login");
			return;
		} finally {
			connectionManager.closeConnection();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int userCount = 0;
		String mail = null;
		String password = null;
		String hash = null;
		ConnectionManager connectionManager = null;
		UserBean userBean = null;
		UserDAO userDAO = null;
		Connection connection = null;
		HttpSession session = null;
		RequestDispatcher dispatcher = null;

		try {
			mail = request.getParameter("mail");
			password = request.getParameter("password");
			hash = CryptographyLogic.encryptStr(password);

			connectionManager = new ConnectionManager();
			userBean = new UserBean();

			connection = connectionManager.getConnection();
			userDAO = new UserDAO(connection);
			userBean = userDAO.findUser(mail, hash);
			userCount = userDAO.countUser().size();

			if (userBean == null) {
				request.setAttribute("popFlag", 2);
				request.setAttribute("mail", mail);
				dispatcher = request.getRequestDispatcher("WEB-INF/views/login/login.jsp");
				dispatcher.forward(request, response);
				return;
			}

			session = request.getSession(true);
			session.setAttribute("userCount", userCount);
			session.setAttribute("userId", userBean.getUserId());
			session.setAttribute("userName", userBean.getUserName());

			response.sendRedirect("user/calendar");
			return;

		} catch (RuntimeException e) {
			response.sendRedirect("login");
			return;
		} catch (Exception e) {
			response.sendRedirect("login");
			return;
		} finally {
			connectionManager.closeConnection();
		}

	}

}
