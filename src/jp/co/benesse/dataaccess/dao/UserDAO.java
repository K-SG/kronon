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
		// ステートメントの定義
		PreparedStatement preparedStatement = null;
		int result = 0;
		try {
			// SQLの定義


			String sql = "INSERT INTO public.user (user_id,user_name,mail,password) values (nextval('SEQ_USER'),?,?,?)";

			// SQLの作成(準備)
			preparedStatement = this.connection.prepareStatement(sql);
			// SQLバインド変数への値設定
			preparedStatement.setString(1, userName);
			preparedStatement.setString(2, mail);
			preparedStatement.setString(3, password);
			// SQLの実行
			result = preparedStatement.executeUpdate();
			return result;
	} catch (SQLException e) {
		throw new RuntimeException("'user'テーブルのINSERTに失敗しました", e);
	} finally {
		try {
			if (preparedStatement != null) {
				preparedStatement.close();
				System.out.println("ステートメントの解放に成功しました");
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
		UserBean userBean = null;
		// ステートメントの定義
		PreparedStatement preparedStatement = null;
		try {
			// SQLの定義
			String sql = "SELECT * FROM public.user WHERE mail = ? and password = ?";
			// SQLの作成(準備)
			preparedStatement = this.connection.prepareStatement(sql);
			// SQLバインド変数への値設定
			preparedStatement.setString(1, mail);
			preparedStatement.setString(2, password);


			//SQLの実行
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
			userBean = new UserBean();
			//userIdの取得
			int userId = resultSet.getInt("USER_ID");
			//userNameの取得
			String userName = resultSet.getString("USER_NAME");
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
				System.out.println("ステートメントの解放に成功しました");
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
		PreparedStatement preparedStatement = null;
		try {
			String sql = "SELECT COUNT(*) FROM public.user WHERE mail = ?";
			preparedStatement = this.connection.prepareStatement(sql);
			preparedStatement.setString(1, mail);
			// SQLの実行
			ResultSet resultSet = preparedStatement.executeQuery();
			// 問い合わせ結果の取得
			int count = 0;
			while (resultSet.next()) {
				// COUNT(*)を取得
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
					System.out.println("ステートメントの解放に成功しました");
				}
			} catch (SQLException e) {
				throw new RuntimeException("ステートメントの解放に失敗しました", e);
			}
		}
	}
}