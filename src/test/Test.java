package test;


import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.sql.Connection;

import jp.co.benesse.dataaccess.cm.ConnectionManager;
import jp.co.benesse.dataaccess.crypt.CryptographyLogic;
import jp.co.benesse.dataaccess.dao.UserDAO;
import jp.co.benesse.dataaccess.value.UserBean;

public class Test {
	//UserDAO
	@org.junit.Test
	public void userfindTest() {
		ConnectionManager connectionManager = new ConnectionManager();
		Connection connection = connectionManager.getConnection();
		UserDAO userDAO = new UserDAO(connection);
		UserBean bean=userDAO.findUser("kronon@gmail.com",  CryptographyLogic.encryptStr("Kronon1122"));
		assertThat(bean.getUserName(),is("くろのす"));
		assertThat(bean.getUserId(),is(1));
	}
	@org.junit.Test
	public void userCreateTest(){
		ConnectionManager connectionManager = new ConnectionManager();
		Connection connection = connectionManager.getConnection();
		UserDAO userDAO = new UserDAO(connection);
		int answer = 0;
		answer = userDAO.createUser("くろのす", "kronon@gmail.com", "Kronon11");
		assertThat(answer, is(1));
	}
	@org.junit.Test
	public void getUserNameTest(){
		ConnectionManager connectionManager = new ConnectionManager();
		Connection connection = connectionManager.getConnection();
		UserDAO userDAO = new UserDAO(connection);
		String answerStr = null;
		answerStr = userDAO.getUserName(1);
		assertThat(answerStr, is("くろのす"));
	}
	@org.junit.Test
	public void isBookingTest(){
		ConnectionManager connectionManager = new ConnectionManager();
		Connection connection = connectionManager.getConnection();
		UserDAO userDAO = new UserDAO(connection);
		Boolean answerFlg;
		answerFlg = userDAO.isBooking("kronon@gmail.com");
		assertThat(answerFlg, is(true));
	}

}
