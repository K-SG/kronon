package jp.co.benesse.dataaccess.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jp.co.benesse.dataaccess.value.ScheduleBean;
public class ScheduleDAO {

	/**
	 * コネクション
	 */
	private Connection connection;

	public ScheduleDAO(Connection connection) {
		this.connection = connection;
	}

	/**
	 * [機 能] 予定削除メソッド<br>
	 * [説 明] DBに保存されている予定を論理削除する<br>
	 * ※例外取得時にはRuntimeExceptionにラップし上位に送出する。<br>
	 * [備 考] なし
	 *
	 * @param 予定ID
	 * @return 書籍リスト
	 */
	public int deleteSchedule(int scheduleId) {
		// ステートメントの定義
		PreparedStatement preparedStatement = null;
		try {
			// SQLの定義
			String sql = "UPDATE SCHEDULE SET DELETE_FLAG = '1' WHERE SCHEDULE_ID = ?";
			// SQLの作成(準備)
			preparedStatement = this.connection.prepareStatement(sql);
			// SQLバインド変数への値設定
			preparedStatement.setInt(1, scheduleId);

			// SQLの実行
			int result = preparedStatement.executeUpdate();

			return result;
		} catch (SQLException e) {
			throw new RuntimeException("USERテーブルのUPDATEに失敗しました", e);
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
	 * [機 能] 予定重複判定メソッド<br>
	 * [説 明] 登録・修正したい予定がDBに保存されている予定と被っているかどうかを判定する<br>
	 * ※例外取得時にはRuntimeExceptionにラップし上位に送出する。<br>
	 * [備 考] なし
	 *
	 * @param  予定インスタンス
	 * @return 書籍リスト重複判定フラグ
	 */
	public boolean isBooking(ScheduleBean scheduleBean) {
		try {
			PreparedStatement preparedStatement = null;

			// SQLの定義
			String sql = "SELECT COUNT(*) FROM SCHEDULE " + "WHERE ? < END_TIME AND START_TIME < ? "
					+ "AND SCHEDULE_DATE = ? AND USER_ID = ? AND DELETE_FLAG = '0'";
			// SQLの作成(準備)
			preparedStatement = this.connection.prepareStatement(sql);
			preparedStatement.setTime(1, scheduleBean.getStartTime());
			preparedStatement.setTime(2, scheduleBean.getEndTime());
			preparedStatement.setDate(3, scheduleBean.getScheduleDate());
			preparedStatement.setInt(4, scheduleBean.getUserId());
			// SQLの実行
			ResultSet resultSet = preparedStatement.executeQuery();
			System.out.println("取得結果");
			// 問い合わせ結果の取得
			int count = 0;
			while (resultSet.next()) {
				//COUNT(*)を取得
				count = resultSet.getInt(1);
			}

//			System.out.println("count = " + count);
			if (count != 0) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			throw new RuntimeException("SELECTに失敗しました", e);
		}
	}
}
