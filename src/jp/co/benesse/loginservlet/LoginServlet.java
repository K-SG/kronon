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

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/login/login.jsp");
		dispatcher.forward(request, response);
		return;
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String mail = request.getParameter("mail");
		String password = request.getParameter("password");
		String hash;


		ConnectionManager connectionManager = new ConnectionManager();
		UserBean userBean = new UserBean();

		try {
			hash= CryptographyLogic.encryptStr(password);
			System.out.println(hash);
			Connection connection = connectionManager.getConnection();
			UserDAO userDAO = new UserDAO(connection);
			userBean = userDAO.findUser(mail, hash);
			System.out.println(userBean);


			if(userBean == null){
				request.setAttribute("popFlag", 2);
				request.setAttribute("mail",mail);
				RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/login/login.jsp");
				dispatcher.forward(request, response);
				return;
			}

			HttpSession session = request.getSession(true);
			session.setAttribute("userId", userBean.getUserId());
			session.setAttribute("userName", userBean.getUserName());

			response.sendRedirect("user/calendar");
			return;

		}catch(RuntimeException e){
			throw e;
		}finally {
			connectionManager.closeConnection();
		}

	}

}
