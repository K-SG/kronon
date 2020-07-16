package jp.co.benesse.dataaccess.value;

public class UserBean {
	/**
	 * 利用者ID
	 */
	private int userId;
	/**
	 * 利用者名
	 */
	private String userName;

	/**
	 * メールアドレス
	 */
	private String mail;

	/**
	 * パスワード
	 */
	private String password;
	public UserBean(int userId, String userName, String mail, String password) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.mail = mail;
		this.password = password;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "UserBean [userId=" + userId + ", userName=" + userName + ", mail=" + mail + ", password=" + password
				+ "]";
	}
}
