package jp.co.benesse.calc;

import java.sql.Time;
import java.time.Duration;
import java.time.LocalTime;

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
		Time start = scheduleBean.getStartTime();
		Time end = scheduleBean.getEndTime();
		try{
			LocalTime startLocal = start.toLocalTime();
			LocalTime endLocal = end.toLocalTime();

			Duration duration = Duration.between(startLocal, endLocal);
			int estimateMinutes = (int) duration.toMinutes();
			String estimateTimeStr = estimateMinutes / 60 + "時間" + estimateMinutes % 60 + "分";

			return estimateTimeStr;
		}catch(NullPointerException e){
			return "";
		}

	}
}
