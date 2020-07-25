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

import jp.co.benesse.calc.Calc;
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
	 * [機 能] 予定登録メソッド<br>
	 * [説 明] 予定を登録する<br>
	 * ※例外取得時にはRuntimeExceptionにラップし上位に送出する。<br>
	 * [備 考] なし
	 *
	 * @param 予定
	 * @return 登録件数
	 */
	public int registerSchedule(ScheduleBean scheduleBean) {

		PreparedStatement preparedStatement = null;
		try {
			// SQLの定義
			String sql = "INSERT INTO SCHEDULE (SCHEDULE_ID,USER_ID,SCHEDULE_DATE,"
					+ "START_TIME,END_TIME,PLACE,TITLE,CONTENT,ACTUAL_TIME,DELETE_FLAG)"
					+ "VALUES (NEXTVAL('SEQ'),?,?,?,?,?,?,?,1000,'0')";
			// SQLの作成(準備)
			preparedStatement = this.connection.prepareStatement(sql);
			// SQLバインド変数への値設定
			preparedStatement.setInt(1, scheduleBean.getUserId());
			preparedStatement.setDate(2, scheduleBean.getScheduleDate());
			preparedStatement.setTime(3, scheduleBean.getStartTime());
			preparedStatement.setTime(4, scheduleBean.getEndTime());
			preparedStatement.setString(5, scheduleBean.getPlace());
			preparedStatement.setString(6, scheduleBean.getTitle());
			preparedStatement.setString(7, scheduleBean.getContent());

			// SQLの実行
			int result = preparedStatement.executeUpdate();

			return result;
		} catch (SQLException e) {
			throw new RuntimeException("SCHEDULEテーブルのINSERTに失敗しました", e);
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
	 * [機 能] 実績検索メソッド<br>
	 * [説 明] 入力された日付とタイトルから実績を取得する<br>
	 * ※例外取得時にはRuntimeExceptionにラップし上位に送出する。<br>
	 * [備 考] なし
	 *
	 * @param 利用者ID、日付、タイトル
	 * @return 予定リスト
	 */
	public List<ScheduleBean> selectSchedule(int userId, Date scheduleDate, String title) {
		List<ScheduleBean> scheduleBeanList = new ArrayList<>();
if (title.contains("%")) {
			title = title.replace("%", "\\%");
		}
		title = "%" + title + "%";
		PreparedStatement preparedStatement = null;
		try {

			// SQLの定義
			String sql = "SELECT * FROM SCHEDULE INNER JOIN PUBLIC.USER ON PUBLIC.USER.USER_ID = SCHEDULE.USER_ID "
					+ "WHERE DELETE_FLAG = '0' AND (TITLE LIKE ? AND SCHEDULE_DATE = ?) AND SCHEDULE.USER_ID = ? ORDER BY SCHEDULE_DATE,START_TIME";
			// SQLの作成(準備)
			preparedStatement = this.connection.prepareStatement(sql);
			preparedStatement.setString(1, title);
			preparedStatement.setDate(2, scheduleDate);
			preparedStatement.setInt(3, userId);
			// SQLの実行
			ResultSet resultSet = preparedStatement.executeQuery();

			// 問い合わせ結果の取得

			while (resultSet.next()) {
				ScheduleBean scheduleBean = new ScheduleBean();
				scheduleBean.setScheduleId(resultSet.getInt("SCHEDULE_ID"));
				scheduleBean.setUserId(userId);
				scheduleBean.setScheduleDate(resultSet.getDate("SCHEDULE_DATE"));
				scheduleBean.setStartTime(resultSet.getTime("START_TIME"));
				scheduleBean.setEndTime(resultSet.getTime("END_TIME"));
				scheduleBean.setUserName(resultSet.getString("USER_NAME"));
				scheduleBean.setPlace(resultSet.getString("PLACE"));
				scheduleBean.setTitle(resultSet.getString("TITLE"));
				scheduleBean.setContent(resultSet.getString("CONTENT"));
				scheduleBean.setActualTime(resultSet.getInt("ACTUAL_TIME"));
				scheduleBean.setComment(resultSet.getString("COMMENT"));
				scheduleBean.setEstimateTime(Calc.calcEstimateTime(scheduleBean));
				scheduleBean.setActualTimeStr(Calc.calcActualTime(scheduleBean));
				scheduleBean.setScheduleDateActual(Calc.convertActualDate(scheduleBean.getScheduleDate().toLocalDate()));
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

	/**
	 * [機 能] 実績検索メソッド<br>
	 * [説 明] 入力された日付から実績を取得する<br>
	 * ※例外取得時にはRuntimeExceptionにラップし上位に送出する。<br>
	 * [備 考] なし
	 *
	 * @param 利用者ID、日付
	 * @return 予定リスト
	 */
	public List<ScheduleBean> selectSchedule(int userId, Date scheduleDate) {
		List<ScheduleBean> scheduleBeanList = new ArrayList<>();

		PreparedStatement preparedStatement = null;
		try {

			// SQLの定義
			String sql = "SELECT * FROM SCHEDULE INNER JOIN PUBLIC.USER ON PUBLIC.USER.USER_ID = SCHEDULE.USER_ID "
					+ "WHERE DELETE_FLAG = '0' AND SCHEDULE_DATE = ? AND SCHEDULE.USER_ID = ? ORDER BY SCHEDULE_DATE,START_TIME";
			// SQLの作成(準備)
			preparedStatement = this.connection.prepareStatement(sql);
			preparedStatement.setDate(1, scheduleDate);
			preparedStatement.setInt(2, userId);
			// SQLの実行
			ResultSet resultSet = preparedStatement.executeQuery();

			// 問い合わせ結果の取得

			while (resultSet.next()) {
				ScheduleBean scheduleBean = new ScheduleBean();
				scheduleBean.setScheduleId(resultSet.getInt("SCHEDULE_ID"));
				scheduleBean.setUserId(userId);
				scheduleBean.setScheduleDate(resultSet.getDate("SCHEDULE_DATE"));
				scheduleBean.setStartTime(resultSet.getTime("START_TIME"));
				scheduleBean.setEndTime(resultSet.getTime("END_TIME"));
				scheduleBean.setUserName(resultSet.getString("USER_NAME"));
				scheduleBean.setPlace(resultSet.getString("PLACE"));
				scheduleBean.setTitle(resultSet.getString("TITLE"));
				scheduleBean.setContent(resultSet.getString("CONTENT"));
				scheduleBean.setActualTime(resultSet.getInt("ACTUAL_TIME"));
				scheduleBean.setComment(resultSet.getString("COMMENT"));
				scheduleBean.setEstimateTime(Calc.calcEstimateTime(scheduleBean));
				scheduleBean.setActualTimeStr(Calc.calcActualTime(scheduleBean));
				scheduleBean.setScheduleDateActual(Calc.convertActualDate(scheduleBean.getScheduleDate().toLocalDate()));
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

	/**
	 * [機 能] 実績検索メソッド<br>
	 * [説 明] 入力されたタイトルから実績を取得する<br>
	 * ※例外取得時にはRuntimeExceptionにラップし上位に送出する。<br>
	 * [備 考] なし
	 *
	 * @param 利用者ID、タイトル
	 * @return 予定リスト
	 */
	public List<ScheduleBean> selectSchedule(int userId, String title) {
		List<ScheduleBean> scheduleBeanList = new ArrayList<>();
		if (title.contains("%")) {
			title = title.replace("%", "\\%");
		}

		title = "%" + title + "%";
		PreparedStatement preparedStatement = null;
		try {

			// SQLの定義
			String sql = "SELECT * FROM SCHEDULE INNER JOIN PUBLIC.USER ON PUBLIC.USER.USER_ID = SCHEDULE.USER_ID "
					+ "WHERE DELETE_FLAG = '0' AND TITLE LIKE ? AND SCHEDULE.USER_ID = ? ORDER BY SCHEDULE_DATE,START_TIME";
			// SQLの作成(準備)
			preparedStatement = this.connection.prepareStatement(sql);
			preparedStatement.setString(1, title);
			preparedStatement.setInt(2, userId);
			// SQLの実行
			ResultSet resultSet = preparedStatement.executeQuery();

			// 問い合わせ結果の取得

			while (resultSet.next()) {
				ScheduleBean scheduleBean = new ScheduleBean();
				scheduleBean.setScheduleId(resultSet.getInt("SCHEDULE_ID"));
				scheduleBean.setUserId(userId);
				scheduleBean.setScheduleDate(resultSet.getDate("SCHEDULE_DATE"));
				scheduleBean.setStartTime(resultSet.getTime("START_TIME"));
				scheduleBean.setEndTime(resultSet.getTime("END_TIME"));
				scheduleBean.setUserName(resultSet.getString("USER_NAME"));
				scheduleBean.setPlace(resultSet.getString("PLACE"));
				scheduleBean.setTitle(resultSet.getString("TITLE"));
				scheduleBean.setContent(resultSet.getString("CONTENT"));
				scheduleBean.setActualTime(resultSet.getInt("ACTUAL_TIME"));
				scheduleBean.setComment(resultSet.getString("COMMENT"));
				scheduleBean.setEstimateTime(Calc.calcEstimateTime(scheduleBean));
				scheduleBean.setActualTimeStr(Calc.calcActualTime(scheduleBean));
				scheduleBean.setScheduleDateActual(Calc.convertActualDate(scheduleBean.getScheduleDate().toLocalDate()));
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

	/**
	 * [機 能] 予定取得メソッド<br>
	 * [説 明] 一人分の一か月の予定を取得する<br>
	 * ※例外取得時にはRuntimeExceptionにラップし上位に送出する。<br>
	 * [備 考] なし
	 *
	 * @param 利用者ID、日付、タイトル
	 * @return 予定リスト
	 */
	public List<ScheduleBean> getOneMonthSchedule(LocalDate scheduleDate, int userId) {
		List<ScheduleBean> scheduleBeanList = new ArrayList<>();
		LocalDate lastDayOfMonth = scheduleDate.with(TemporalAdjusters.lastDayOfMonth()); // 末日
		LocalDate firstDayOfMonth = scheduleDate.with(TemporalAdjusters.firstDayOfMonth()); // 初日
		Date sqlLastDayOfMonth = Date.valueOf(lastDayOfMonth);
		Date sqlFirstDayOfMonth = Date.valueOf(firstDayOfMonth);
		PreparedStatement preparedStatement = null;
		try {

			// SQLの定義
			String sql = "SELECT * FROM SCHEDULE INNER JOIN PUBLIC.USER ON PUBLIC.USER.USER_ID = SCHEDULE.USER_ID "
					+ "WHERE DELETE_FLAG = '0' AND SCHEDULE.USER_ID = ? "
					+ "AND SCHEDULE_DATE BETWEEN ? AND ? ORDER BY SCHEDULE_DATE,START_TIME";
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
				scheduleBean.setScheduleId(resultSet.getInt("SCHEDULE_ID"));
				scheduleBean.setUserId(userId);
				scheduleBean.setScheduleDate(resultSet.getDate("SCHEDULE_DATE"));
				scheduleBean.setStartTime(resultSet.getTime("START_TIME"));
				scheduleBean.setEndTime(resultSet.getTime("END_TIME"));
				scheduleBean.setUserName(resultSet.getString("USER_NAME"));
				scheduleBean.setPlace(resultSet.getString("PLACE"));
				scheduleBean.setTitle(resultSet.getString("TITLE"));
				scheduleBean.setContent(resultSet.getString("CONTENT"));
				scheduleBean.setActualTime(resultSet.getInt("ACTUAL_TIME"));
				scheduleBean.setComment(resultSet.getString("COMMENT"));
				scheduleBean.setEstimateTime(Calc.calcEstimateTime(scheduleBean));
				scheduleBean.setActualTimeStr(Calc.calcActualTime(scheduleBean));
				scheduleBean.setScheduleDateActual(Calc.convertActualDate(scheduleBean.getScheduleDate().toLocalDate()));
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

	/**
	 * [機 能] 予定取得メソッド<br>
	 * [説 明] 予定IDから一件の予定を取得する<br>
	 * ※例外取得時にはRuntimeExceptionにラップし上位に送出する。<br>
	 * [備 考] なし
	 *
	 * @param 予定ID
	 * @return 予定インスタンス
	 */
	public ScheduleBean getScheduleByScheduleId(int scheduleId) {
		ScheduleBean scheduleBean = new ScheduleBean();
		PreparedStatement preparedStatement = null;
		try {

			// SQLの定義
			String sql = "SELECT * FROM SCHEDULE INNER JOIN PUBLIC.USER ON PUBLIC.USER.USER_ID = SCHEDULE.USER_ID "
					+ "WHERE DELETE_FLAG = '0' AND SCHEDULE_ID = ? ";

			// SQLの作成(準備)
			preparedStatement = this.connection.prepareStatement(sql);
			preparedStatement.setInt(1, scheduleId);

			// SQLの実行
			ResultSet resultSet = preparedStatement.executeQuery();

			// 問い合わせ結果の取得

			while (resultSet.next()) {
				scheduleBean.setScheduleId(resultSet.getInt("SCHEDULE_ID"));
				scheduleBean.setUserId(resultSet.getInt("USER_ID"));
				scheduleBean.setScheduleDate(resultSet.getDate("SCHEDULE_DATE"));
				scheduleBean.setStartTime(resultSet.getTime("START_TIME"));
				scheduleBean.setEndTime(resultSet.getTime("END_TIME"));
				scheduleBean.setUserName(resultSet.getString("USER_NAME"));
				scheduleBean.setPlace(resultSet.getString("PLACE"));
				scheduleBean.setTitle(resultSet.getString("TITLE"));
				scheduleBean.setContent(resultSet.getString("CONTENT"));
				scheduleBean.setActualTime(resultSet.getInt("ACTUAL_TIME"));
				scheduleBean.setComment(resultSet.getString("COMMENT"));
				scheduleBean.setActualTimeStr(Calc.calcActualTime(scheduleBean));
				scheduleBean.setScheduleDateActual(Calc.convertActualDate(scheduleBean.getScheduleDate().toLocalDate()));
			}

			return scheduleBean;
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
	 * [機 能] 予定取得メソッド<br>
	 * [説 明] 一人分の一日の予定を取得する<br>
	 * ※例外取得時にはRuntimeExceptionにラップし上位に送出する。<br>
	 * [備 考] なし
	 *
	 * @param 利用者ID、日付、タイトル
	 * @return 予定リスト
	 */
	public List<ScheduleBean> getOneDaySchedule(LocalDate scheduleDate, int userId) {
		List<ScheduleBean> scheduleBeanList = new ArrayList<>();
		Date sqlScheduleDate = Date.valueOf(scheduleDate);
		PreparedStatement preparedStatement = null;
		try {

			// SQLの定義
			String sql = "SELECT * FROM SCHEDULE INNER JOIN PUBLIC.USER ON PUBLIC.USER.USER_ID = SCHEDULE.USER_ID "
					+ "WHERE DELETE_FLAG = '0' AND SCHEDULE.USER_ID = ? AND SCHEDULE_DATE = ?" + "ORDER BY START_TIME";
			// SQLの作成(準備)
			preparedStatement = this.connection.prepareStatement(sql);
			preparedStatement.setInt(1, userId);
			preparedStatement.setDate(2, sqlScheduleDate);

			// SQLの実行
			ResultSet resultSet = preparedStatement.executeQuery();

			// 問い合わせ結果の取得

			while (resultSet.next()) {
				ScheduleBean scheduleBean = new ScheduleBean();
				scheduleBean.setScheduleId(resultSet.getInt("SCHEDULE_ID"));
				scheduleBean.setUserId(userId);
				scheduleBean.setScheduleDate(resultSet.getDate("SCHEDULE_DATE"));
				scheduleBean.setStartTime(resultSet.getTime("START_TIME"));
				scheduleBean.setEndTime(resultSet.getTime("END_TIME"));
				scheduleBean.setUserName(resultSet.getString("USER_NAME"));
				scheduleBean.setPlace(resultSet.getString("PLACE"));
				scheduleBean.setTitle(resultSet.getString("TITLE"));
				scheduleBean.setContent(resultSet.getString("CONTENT"));
				scheduleBean.setActualTime(resultSet.getInt("ACTUAL_TIME"));
				scheduleBean.setComment(resultSet.getString("COMMENT"));
				scheduleBean.setActualTimeStr(Calc.calcActualTime(scheduleBean));
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
			String sql = "SELECT COUNT(*) FROM SCHEDULE "
					+ "WHERE SCHEDULE_DATE = ? AND USER_ID = ? AND DELETE_FLAG = '0'"
					// 判定したい予定の終了時間＜=既存予定の開始時間
					+ "AND NOT ((? <= START_TIME) "
					// 既存予定の終了時間＜=判定したい予定の開始時間
					+ "OR (END_TIME <= ?))";

			// SQLの作成(準備)
			preparedStatement = this.connection.prepareStatement(sql);
			preparedStatement.setDate(1, scheduleBean.getScheduleDate());
			preparedStatement.setInt(2, scheduleBean.getUserId());
			preparedStatement.setTime(3, scheduleBean.getEndTime());
			preparedStatement.setTime(4, scheduleBean.getStartTime());

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
				// JSON用に日付を整形して格納
				scheduleBean.setJsonDate(scheduleBean.getScheduleDate().toString());

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
