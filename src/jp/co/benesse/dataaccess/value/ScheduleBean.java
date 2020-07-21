package jp.co.benesse.dataaccess.value;
import java.sql.Date;
import java.sql.Time;

public class ScheduleBean {
	/**
	 * 予定ID
	 */
	private int scheduleId;
	/**
	 * 利用者ID
	 */
	private int userId;
	/**
	 * 予定日時
	 */
	private Date scheduleDate;
	/**
	 * 予定開始時間
	 */
	private Time startTime;
	/**
	 * 予定終了時間
	 */
	private Time endTime;
	/**
	 * 作業場所
	 */
	private String place;
	/**
	 * タイトル
	 */
	private String title;
	/**
	 * 内容
	 */
	private String content;
	/**
	 * 実績時間
	 */
	private int actualTime;
	/**
	 * 振り返りコメント
	 */
	private String comment;
	/**
	 * 削除フラグ
	 */
	private String deleteFlag;
	/**
	 * 利用者名
	 */
	private String userName;

	/**
	 * 見積時間「hh時間mm分」
	 */
	private String estimateTime;

	/**
	 * json整形用
	 */
	private String jsonDate;

	/**
	 * コンストラクタ
	 */
	public ScheduleBean() {}

	/**
	 * コンストラクタ
	 */
	public ScheduleBean(int scheduleId, int userId, Date scheduleDate, Time startTime, Time endTime, String place,
			String title, String content, int actualTime, String comment, String deleteFlag, String userName) {
		this.scheduleId = scheduleId;
		this.userId = userId;
		this.scheduleDate = scheduleDate;
		this.startTime = startTime;
		this.endTime = endTime;
		this.place = place;
		this.title = title;
		this.content = content;
		this.actualTime = actualTime;
		this.comment = comment;
		this.deleteFlag = deleteFlag;
		this.userName = userName;
	}

	/**
	 * コンストラクタ
	 */
	public ScheduleBean(int scheduleId, int userId, Date scheduleDate, Time startTime, Time endTime, String place,
			String title, String content, int actualTime, String comment, String deleteFlag) {
		super();
		this.scheduleId = scheduleId;
		this.userId = userId;
		this.scheduleDate = scheduleDate;
		this.startTime = startTime;
		this.endTime = endTime;
		this.place = place;
		this.title = title;
		this.content = content;
		this.actualTime = actualTime;
		this.comment = comment;
		this.deleteFlag = deleteFlag;
	}
	public int getScheduleId() {
		return scheduleId;
	}
	public void setScheduleId(int scheduleId) {
		this.scheduleId = scheduleId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public Date getScheduleDate() {
		return scheduleDate;
	}
	public void setScheduleDate(Date scheduleDate) {
		this.scheduleDate = scheduleDate;
	}
	public Time getStartTime() {
		return startTime;
	}
	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}
	public Time getEndTime() {
		return endTime;
	}
	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getActualTime() {
		return actualTime;
	}
	public void setActualTime(int actualTime) {
		this.actualTime = actualTime;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getDeleteFlag() {
		return deleteFlag;
	}
	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEstimateTime() {
		return estimateTime;
	}
	public void setEstimateTime(String estimateTime) {
		this.estimateTime = estimateTime;
	}

	public String getJsonDate() {
		return jsonDate;
	}

	public void setJsonDate(String jsonDate) {
		this.jsonDate = jsonDate;
	}

	@Override
	public String toString() {
		return "ScheduleBean [scheduleId=" + scheduleId + ", userId=" + userId + ", scheduleDate=" + scheduleDate
				+ ", startTime=" + startTime + ", endTime=" + endTime + ", place=" + place + ", title=" + title
				+ ", content=" + content + ", actualTime=" + actualTime + ", comment=" + comment + ", deleteFlag="
				+ deleteFlag + ", userName=" + userName + "]";
	}

}