package jp.co.benesse.dataaccess.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

import jp.co.benesse.dataaccess.value.ScheduleBean;

public class ScheduleDAO {

	/**
	 * コネクション
	 */
	private Connection connection;

	/**
	 * コンストラクタ
	 * @param connection
	 */
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
			throw new RuntimeException("SCHEDULEテーブルのUPDATEに失敗しました", e);
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
	 * [機 能] 予定更新メソッド<br>
	 * [説 明] 予定を更新する<br>
	 * ※例外取得時にはRuntimeExceptionにラップし上位に送出する。<br>
	 * [備 考] なし
	 *
	 * @param 予定ID、予定日時、予定開始時間、予定終了時間、タイトル、内容、作業場所
	 * @return 更新件数
	 */
	public int updateSchedule(int scheduleId, Date scheduleDate, Time startTime, Time endTime, String title,
			String content,String place) {
		// ステートメントの定義
		PreparedStatement preparedStatement = null;
		try {
			// SQLの定義
			String sql = "UPDATE SCHEDULE SET (SCHEDULE_DATE,START_TIME,END_TIME,PLACE,TITLE,CONTENT) "
					+ "= (?,?,?,?,?,?) WHERE SCHEDULE_ID = ?";
			// SQLの作成(準備)
			preparedStatement = this.connection.prepareStatement(sql);
			// SQLバインド変数への値設定
			preparedStatement.setDate(1, scheduleDate);
			preparedStatement.setTime(2, startTime);
			preparedStatement.setTime(3, endTime);
			preparedStatement.setString(4, place);
			preparedStatement.setString(5, title);
			preparedStatement.setString(6, content);
			preparedStatement.setInt(7, scheduleId);

			// SQLの実行
			int result = preparedStatement.executeUpdate();
			return result;
		} catch (SQLException e) {
			throw new RuntimeException("SCHEDULEテーブルのUPDATEに失敗しました", e);
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
	 * [機 能] 実績登録・修正メソッド<br>
	 * [説 明] 実績時間と振り返りコメントを登録・修正する<br>
	 * ※例外取得時にはRuntimeExceptionにラップし上位に送出する。<br>
	 * [備 考] なし
	 *
	 * @param 予定ID、実績時間、振り返りコメント
	 * @return 更新件数
	 */
	public int updateSchedule(int scheduleId, int actualTime ,String comment){
		PreparedStatement preparedStatement = null;
		try {
			// SQLの定義
			String sql = "UPDATE SCHEDULE SET (ACTUAL_TIME,COMMENT) "
					+ "= (?,?) WHERE SCHEDULE_ID = ?";
			// SQLの作成(準備)
			preparedStatement = this.connection.prepareStatement(sql);
			// SQLバインド変数への値設定
			preparedStatement.setInt(1,actualTime);
			preparedStatement.setString(2, comment);
			preparedStatement.setInt(3, scheduleId);

			// SQLの実行
			int result = preparedStatement.executeUpdate();
			return result;
		} catch (SQLException e) {
			throw new RuntimeException("SCHEDULEテーブルのUPDATEに失敗しました", e);
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
	 * [機 能] 予定重複判定メソッド<br>
	 * [説 明] 登録・修正したい予定がDBに保存されている予定と被っているかどうかを判定する<br>
	 * ※例外取得時にはRuntimeExceptionにラップし上位に送出する。<br>
	 * [備 考] なし
	 *
	 * @param 予定インスタンス
	 * @return 書籍リスト重複判定フラグ（重複があればtrueを返す）
	 */
	public boolean isBooking(ScheduleBean scheduleBean) {
		PreparedStatement preparedStatement = null;
		try {

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
			throw new RuntimeException("SCHEDULEテーブルのSELECTに失敗しました", e);
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
	 * [機 能] 削除判定メソッド<br>
	 * [説 明] 予定が論理削除されているかどうかを判定する<br>
	 * ※例外取得時にはRuntimeExceptionにラップし上位に送出する。<br>
	 * [備 考] なし
	 *
	 * @param 予定ID
	 * @return 削除判定フラグ（削除されていればtrueを返す）
	 */
	public boolean isDeleted(int scheduleId) {
		PreparedStatement preparedStatement = null;
		try {

			// SQLの定義
			String sql = "SELECT DELETE_FLAG FROM SCHEDULE WHERE SCHEDULE_ID = ?";
			// SQLの作成(準備)
			preparedStatement = this.connection.prepareStatement(sql);
			preparedStatement.setInt(1, scheduleId);
			// SQLの実行
			ResultSet resultSet = preparedStatement.executeQuery();

			String deleteFlag = "";

			// 問い合わせ結果の取得
			while (resultSet.next()) {
				deleteFlag = resultSet.getString("DELETE_FLAG");
			}

			if (deleteFlag.equals("1")) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			throw new RuntimeException("SCHEDULEテーブルのSELECTに失敗しました", e);
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
	 * [機 能] 見出し取得メソッド<br>
	 * [説 明] 一か月間の各日付にて一番開始時間の早い予定のタイトルを取得する<br>
	 * ※例外取得時にはRuntimeExceptionにラップし上位に送出する。<br>
	 * [備 考] なし
	 *
	 * @param  当月の日付、利用者ID
	 * @return 予定リスト
	 */
	public List<ScheduleBean> tooLongSQLSchedule(LocalDate scheduleDate, int userId) {
		List<ScheduleBean> scheduleBeanList = new ArrayList<>();

		// scheduleDateの月の初日と末日を取得
		LocalDate firstDayOfMonth = scheduleDate.with(TemporalAdjusters.firstDayOfMonth());
		LocalDate lastDayOfMonth = scheduleDate.with(TemporalAdjusters.lastDayOfMonth());

		// 上記日付をsql.Date型に変換
		Date sqlFirstDayOfMonth = Date.valueOf(firstDayOfMonth);
		Date sqlLastDayOfMonth = Date.valueOf(lastDayOfMonth);

		PreparedStatement preparedStatement = null;
		try {


			// SQLの定義
			String sql = "SELECT SCHEDULE_DATE,START_TIME,MIN(TITLE) FROM SCHEDULE "
					+ "WHERE USER_ID = ? AND DELETE_FLAG = '0' "
					+ "AND SCHEDULE_DATE BETWEEN ? AND ? AND (SCHEDULE_DATE,START_TIME) IN ("
					+ "SELECT SCHEDULE_DATE,MIN(START_TIME) FROM SCHEDULE GROUP BY SCHEDULE_DATE)"
					+ "GROUP BY SCHEDULE_DATE,START_TIME";

			// SQLの作成(準備)
			preparedStatement = this.connection.prepareStatement(sql);
			preparedStatement.setInt(1, userId);
			preparedStatement.setDate(2, sqlFirstDayOfMonth);
			preparedStatement.setDate(3, sqlLastDayOfMonth);

			// SQLの実行
			ResultSet resultSet = preparedStatement.executeQuery();

			// 問い合わせ結果の取得
			while (resultSet.next()) {
				ScheduleBean scheduleBean = new ScheduleBean();

				Date schDate = resultSet.getDate("schedule_date");
				//MIN(TITLE)の列、つまりtitleの値を取得
				String title = resultSet.getString(3);

				scheduleBean.setScheduleDate(schDate);
				scheduleBean.setTitle(title);

				scheduleBeanList.add(scheduleBean);
			}
			return scheduleBeanList;
		} catch (SQLException e) {
			throw new RuntimeException("SCHEDULEテーブルのSELECTに失敗しました", e);
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
