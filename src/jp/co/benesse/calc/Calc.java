package jp.co.benesse.calc;

import java.sql.Date;
import java.sql.Time;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

import jp.co.benesse.dataaccess.value.ScheduleBean;

public class Calc {

	/**
	 *
	 * [機 能] 実績時間計算メソッド<br>
	 * [説 明] 実績時間を計算し、「hh時間mm分」という書式で返す<br>
	 * [備 考] なし
	 *
	 * @param 予定インスタンス
	 * @return 実績時間
	 */
	public static String calcActualTime(ScheduleBean schedulebean) {
		String actualTimeStr = schedulebean.getActualTime() / 60 + "時間" + schedulebean.getActualTime() % 60 + "分";
		return actualTimeStr;
	}

	/**
	 *
	 * [機 能] 見積時間計算メソッド<br>
	 * [説 明] 見積時間を計算し、「hh時間mm分」という書式で返す<br>
	 * [備 考] なし
	 *
	 * @param 予定インスタンス
	 * @return 見積時間
	 */
	public static String calcEstimateTime(ScheduleBean scheduleBean) {

		int estimateMinutes = 0;
		String estimateTimeStr = null;
		Time start = null;
		Time end = null;
		LocalTime startLocal = null;
		LocalTime endLocal = null;
		Duration duration = null;

		try {
			start = scheduleBean.getStartTime();
			end = scheduleBean.getEndTime();
			startLocal = start.toLocalTime();
			endLocal = end.toLocalTime();
			duration = Duration.between(startLocal, endLocal);
			estimateMinutes = (int) duration.toMinutes();
			estimateTimeStr = estimateMinutes / 60 + "時間" + estimateMinutes % 60 + "分";

			return estimateTimeStr;
		} catch (NullPointerException e) {
			return "";
		}

	}

	/**
	 *
	 * [機 能] 日時表記整形メソッド<br>
	 * [説 明] 予定日を「MM/dd(曜日)」という書式で返す<br>
	 * [備 考] 例外発生時にはRuntimeExceptionにラップして上位に送出する
	 *
	 * @param 予定日
	 * @return 整形された日付の文字列
	 */
	public static String convertActualDate(LocalDate scheduleDate) {
		Map<Integer, String> dayOfWeek = new HashMap<>();
		dayOfWeek.put(1, "月");
		dayOfWeek.put(2, "火");
		dayOfWeek.put(3, "水");
		dayOfWeek.put(4, "木");
		dayOfWeek.put(5, "金");
		dayOfWeek.put(6, "土");
		dayOfWeek.put(7, "日");

		scheduleDate.getMonthValue();
		scheduleDate.getDayOfMonth();
		return scheduleDate.getMonthValue() + "/" + scheduleDate.getDayOfMonth() + "("
				+ dayOfWeek.get(scheduleDate.getDayOfWeek().getValue()) + ")";
	}

	/**
	 *
	 * [機 能] 日時表記整形メソッド<br>
	 * [説 明] 予定日を「MM/dd(曜日)」という書式で返す<br>
	 * [備 考] 例外発生時にはRuntimeExceptionにラップして上位に送出する
	 *
	 * @param 予定日
	 * @return 整形された日付の文字列
	 */
	public static String convertActualDate(Date sqlScheduleDate) {
		LocalDate scheduleDate = null;
		Map<Integer, String> dayOfWeek = new HashMap<>();
		dayOfWeek.put(1, "月");
		dayOfWeek.put(2, "火");
		dayOfWeek.put(3, "水");
		dayOfWeek.put(4, "木");
		dayOfWeek.put(5, "金");
		dayOfWeek.put(6, "土");
		dayOfWeek.put(7, "日");

		scheduleDate = sqlScheduleDate.toLocalDate();
		scheduleDate.getMonthValue();
		scheduleDate.getDayOfMonth();
		return scheduleDate.getYear() + "/" + scheduleDate.getMonthValue() + "/" + scheduleDate.getDayOfMonth() + "("
				+ dayOfWeek.get(scheduleDate.getDayOfWeek().getValue()) + ")";
	}
}
