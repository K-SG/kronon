package jp.co.benesse.dataaccess.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jp.co.benesse.dataaccess.value.UserBean;

public class UserDAO {
	/**
	 * コネクション
	 */
	private Connection connection;

	/**
	 * コンストラクタ
	 * @param connection
	 */
	public UserDAO(Connection connection) {
		this.connection = connection;
	}

	/**
	 * [機 能] 新規アカウント作成メソッド<br>
	 * [説 明] 入力されたユーザー名、メールアドレス、パスワードからアカウントの新規作成を行う。<br>
	 * ※例外取得時にはRuntimeExceptionにラップし上位に送出する。<br>
	 * [備 考] なし
	 *
	 * @param ユーザー名、メールアドレス、パスワード
	 * @return 登録件数
	 */
	public int createUser(String userName,String mail, String password){

		int result = 0;
		String sql = null;
		PreparedStatement preparedStatement = null;

		try {

			sql = "INSERT INTO PUBLIC.USER (USER_ID,USER_NAME,MAIL,PASSWORD) VALUES (NEXTVAL('SEQ_USER'),?,?,?)";

			preparedStatement = this.connection.prepareStatement(sql);

			preparedStatement.setString(1, userName);
			preparedStatement.setString(2, mail);
			preparedStatement.setString(3, password);

			result = preparedStatement.executeUpdate();
			return result;
	} catch (SQLException e) {
		throw new RuntimeException("'user'テーブルのINSERTに失敗しました", e);
	} finally {
		try {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
		} catch (SQLException e) {
			throw new RuntimeException("ステートメントの解放に失敗しました", e);
		}

	}
}

	/**
	 * [機 能] ユーザー情報取得メソッド<br>
	 * [説 明] 引数に入れたメアドとパスワードに一致するユーザーをDBから探し、ユーザーIDとユーザー名を返す。<br>
	 * ※例外取得時にはRuntimeExceptionにラップし上位に送出する。<br>
	 * [備 考] なし
	 *
	 * @param メールアドレス、パスワード
	 * @return ユーザーインスタンス（格納されているのはユーザーIDとユーザー名のみ）
	 */
	public UserBean findUser(String mail ,String password){

		int userId = 0;
		String sql = null;
		String userName = null;
		UserBean userBean = null;

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			sql = "SELECT * FROM PUBLIC.USER WHERE MAIL = ? AND PASSWORD = ?";
			preparedStatement = this.connection.prepareStatement(sql);

			preparedStatement.setString(1, mail);
			preparedStatement.setString(2, password);

			resultSet = preparedStatement.executeQuery();

			while(resultSet.next()) {
				userId = resultSet.getInt("USER_ID");
				userName = resultSet.getString("USER_NAME");
				userBean = new UserBean();
				userBean.setUserId(userId);
				userBean.setUserName(userName);
			}
		return userBean;
	} catch (SQLException e) {
		throw new RuntimeException("userテーブルのSELECTに失敗しました", e);
	} finally {
		try {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
		} catch (SQLException e) {
			throw new RuntimeException("ステートメントの解放に失敗しました", e);
		}
	}
}

	/**
	 * [機 能] ユーザー名取得メソッド<br>
	 * [説 明] ユーザーIDからユーザー名を取得する<br>
	 * ※例外取得時にはRuntimeExceptionにラップし上位に送出する。<br>
	 * [備 考] なし
	 *
	 * @param メールアドレス
	 * @return メアド重複判定フラグ（重複があればtrueを返す）
	 */
	public String getUserName(int userId){

		String sql = null;
		String userName = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			sql = "SELECT USER_NAME FROM PUBLIC.USER WHERE USER_ID = ?";

			preparedStatement = this.connection.prepareStatement(sql);
			preparedStatement.setInt(1, userId);

			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				userName = resultSet.getString("USER_NAME");
			}
			if(userName==null){
				throw new RuntimeException("ユーザーが存在しませんでした");
			}
			return userName;
		} catch (SQLException e) {
			throw new RuntimeException("'user'テーブルのSELECTに失敗しました", e);
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException("ステートメントの解放に失敗しました", e);
			}
		}
	}

	/**
	 * [機 能] メアド重複判定メソッド<br>
	 * [説 明] 新規登録したいメアドがDBに保存されているメアドと被っているかどうかを判定する<br>
	 * ※例外取得時にはRuntimeExceptionにラップし上位に送出する。<br>
	 * [備 考] なし
	 *
	 * @param メールアドレス
	 * @return メアド重複判定フラグ（重複があればtrueを返す）
	 */
	public Boolean  isBooking(String mail){

		int count = 0;
		String sql = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			sql = "SELECT COUNT(*) FROM public.user WHERE mail = ?";

			preparedStatement = this.connection.prepareStatement(sql);
			preparedStatement.setString(1, mail);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				count = resultSet.getInt(1);
			}
			if (count != 0) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			throw new RuntimeException("'user'テーブルのSELECTに失敗しました", e);
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException("ステートメントの解放に失敗しました", e);
			}
		}
	}
}