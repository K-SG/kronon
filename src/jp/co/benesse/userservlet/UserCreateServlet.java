package jp.co.benesse.userservlet;

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

/**
 * Servlet implementation class UserNewServlet
 */
@WebServlet("/user/usercreate")
public class UserCreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doGetされたものをdoPostに変換
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("userName");
		String mail = request.getParameter("mail");
		String password = request.getParameter("password");
		ConnectionManager connectionManager = new ConnectionManager();

		UserDAO userDAO = null;
		try{
			Connection connection = connectionManager.getConnection();
			// UserDAOの作成
			userDAO = new UserDAO(connection);

			//メールアドレスかぶりの確認
			if(userDAO.isBooking(mail)){
				//かぶっていたらメールアドレス重複のポップアップが出るようにフラグ立て。
				request.setAttribute("popFlag",1);
				//アカウント新規登録画面へ戻る。
				RequestDispatcher dispatcher = request.getRequestDispatcher("../WEB-INF/view/user/user_new.jsp");
				dispatcher.forward(request, response);
			}

			//新規登録をするために
			int result = userDAO.createUser(userName,mail,password);
			if(result!=1){
				RequestDispatcher dispatcher = request.getRequestDispatcher("../WEB-INF/view/error/error.jsp");
				dispatcher.forward(request, response);
			}
		}
		catch(RuntimeException e){
			RequestDispatcher dispatcher = request.getRequestDispatcher("../WEB-INF/view/error/error.jsp");
			dispatcher.forward(request, response);
			return;
		}
		finally{
			connectionManager.closeConnection();
		}
		UserBean userBean = new UserBean();
		userBean = userDAO.findUser(mail,password);
		HttpSession session = request.getSession();
		session.setAttribute("userName",userBean.getUserName());
		session.setAttribute("userId",userBean.getUserId());
		//新規登録完了ポップアップを出すためのフラグを立てる。
		request.setAttribute("popFlag",0);
		//user_new.jsp(アカウント新規作成画面)にforwardする。
		RequestDispatcher dispatcher = request.getRequestDispatcher("../WEB-INF/view/user/user_new.jsp");
		dispatcher.forward(request, response);
		return;
	}

}
