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
import jp.co.benesse.dataaccess.crypt.CryptographyLogic;
import jp.co.benesse.dataaccess.dao.UserDAO;
import jp.co.benesse.dataaccess.value.UserBean;

/**
 * Servlet implementation class UserNewServlet
 */
@WebServlet("/usercreate")
public class UserCreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// doGetされたものをdoPostに変換
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int userCount = 0;
		int result = 0;
		String userName = null;
		String mail = null;
		String password = null;
		String hash = null;
		String popFlag = null;
		ConnectionManager connectionManager = null;
		UserBean userBean = null;
		UserDAO userDAO = null;
		Connection connection = null;
		RequestDispatcher dispatcher = null;
		HttpSession session = null;

		try {
			// リクエストパラメータを取得
			userName = request.getParameter("userName");
			mail = request.getParameter("mail");
			password = request.getParameter("password");
			hash = CryptographyLogic.encryptStr(password);

			connectionManager = new ConnectionManager();
			connection = connectionManager.getConnection();
			userDAO = new UserDAO(connection);

			// メールアドレス重複の確認
			if (userDAO.isBooking(mail)) {
				// メールアドレス重複のポップアップが出るようにフラグ立て
				request.setAttribute("popFlag", 1);
				// 内容が保持されるように入力情報をセットする
				request.setAttribute("username", userName);
				request.setAttribute("mail", mail);
				request.setAttribute("password", password);

				// アカウント新規登録画面へ戻る。
				dispatcher = request.getRequestDispatcher("/WEB-INF/views/user/user_new.jsp");
				dispatcher.forward(request, response);
				return;
			}

			userCount = userDAO.countUser();

			//五人以上の利用者登録を無効
			if (userCount >= 5) {
				System.out.println("作りすぎ");
				//５人以上はアカウント作れないよ。管理者に問い合わせて削除してもらってね
				//というポップアップを表示するフラグをセット
				popFlag = "";
				response.sendRedirect("login");
				return;
			}

			result = userDAO.createUser(userName, mail, hash);

			if (result != 1) {
				throw new RuntimeException("アカウントが作成できなかった");
			}

			userBean = new UserBean();
			userBean = userDAO.findUser(mail, hash);
			connectionManager.commit();

			session = request.getSession();
			session.setAttribute("userCount", userCount);
			session.setAttribute("userName", userBean.getUserName());
			session.setAttribute("userId", userBean.getUserId());

			// 新規登録完了ポップアップを出すためのフラグを立てる。
			request.setAttribute("popFlag", 0);

			// user_new.jsp(アカウント新規作成画面)にforwardする。
			dispatcher = request.getRequestDispatcher("/WEB-INF/views/user/user_new.jsp");
			dispatcher.forward(request, response);
			return;
		} catch (RuntimeException e) {
			connectionManager.rollback();
			e.printStackTrace();
			response.sendRedirect("login");
			return;
		} catch (Exception e) {
			connectionManager.rollback();
			e.printStackTrace();
			response.sendRedirect("login");
			return;
		} finally {
			connectionManager.closeConnection();
		}
	}

}
