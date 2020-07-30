package test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

import jp.co.benesse.dataaccess.cm.ConnectionManager;
import jp.co.benesse.dataaccess.dao.ScheduleDAO;
import jp.co.benesse.dataaccess.value.ScheduleBean;

public class TestScheduleDAO {

/**********************************************************************/
	/*ScheduleDAOのテスト*/

	@org.junit.Test
	public void selectScheduleTest1(){
		ConnectionManager connectionManager = new ConnectionManager();
		Connection connection = connectionManager.getConnection();
		ScheduleDAO scheduleDAO = new ScheduleDAO(connection);
		List<ScheduleBean> answer;
		answer = scheduleDAO.selectSchedule(1,Date.valueOf( "2020-07-10"),"ごはん");
		assertThat(answer.size(), is(1));//Listが1件返される
		System.out.println(answer);
	}
	@org.junit.Test
	public void selectScheduleTest2(){
		ConnectionManager connectionManager = new ConnectionManager();
		Connection connection = connectionManager.getConnection();
		ScheduleDAO scheduleDAO = new ScheduleDAO(connection);
		List<ScheduleBean> answer;
		answer = scheduleDAO.selectSchedule(1,Date.valueOf( "2020-07-10"));
		assertThat(answer.size(), is(5));//Listが5件返される
		System.out.println(answer);
	}
	@org.junit.Test
	public void selectScheduleTest3(){
		ConnectionManager connectionManager = new ConnectionManager();
		Connection connection = connectionManager.getConnection();
		ScheduleDAO scheduleDAO = new ScheduleDAO(connection);
		List<ScheduleBean> answer;
		answer = scheduleDAO.selectSchedule(1,"会");
		assertThat(answer.size(), is(1));//Listが1件返される
		System.out.println(answer);
	}
	@org.junit.Test
	public void getOneMonthScheduleTest(){
		ConnectionManager connectionManager = new ConnectionManager();
		Connection connection = connectionManager.getConnection();
		ScheduleDAO scheduleDAO = new ScheduleDAO(connection);
		List<ScheduleBean> answer;
		answer = scheduleDAO.getOneMonthSchedule(Date.valueOf( "2020-07-10").toLocalDate(),1);
		assertThat(answer.size(), is(7));//listが7件返される
		System.out.println("listサイズ"+answer.size());
	}
	@org.junit.Test
	public void getOneMonthScheduleByTitleTest(){
		ConnectionManager connectionManager = new ConnectionManager();
		Connection connection = connectionManager.getConnection();
		ScheduleDAO scheduleDAO = new ScheduleDAO(connection);
		List<ScheduleBean> answer;
		answer = scheduleDAO.getOneMonthScheduleByTitle(1,Date.valueOf( "2020-07-27").toLocalDate(),"ごはん");
		assertThat(answer.size(), is(2));//Listが2件返される
		System.out.println(answer);
	}

  @org.junit.Test
	public void getScheduleByScheduleIdTest1(){
		ConnectionManager connectionManager = new ConnectionManager();
		Connection connection = connectionManager.getConnection();
		ScheduleDAO scheduleDAO = new ScheduleDAO(connection);
		ScheduleBean answer = scheduleDAO.getScheduleByScheduleId(8);
		System.out.println("ans:"+answer);//Beanが返ってくる
	}
	@org.junit.Test
	public void getScheduleByScheduleIdTest2(){
		ConnectionManager connectionManager = new ConnectionManager();
		Connection connection = connectionManager.getConnection();
		ScheduleDAO scheduleDAO = new ScheduleDAO(connection);
		ScheduleBean answer;
		answer = scheduleDAO.getScheduleByScheduleId(1500);
		System.out.println("空？ans:"+answer);//空の予定のBeanが返ってくる
	}

	@org.junit.Test
	public void getOneDayScheduleTest(){
		ConnectionManager connectionManager = new ConnectionManager();
		Connection connection = connectionManager.getConnection();
		ScheduleDAO scheduleDAO = new ScheduleDAO(connection);
		List<ScheduleBean> answer;
		answer = scheduleDAO.getOneDaySchedule(Date.valueOf("2020-07-10").toLocalDate(),1);
		System.out.println(answer);
		assertThat(answer.size(), is(5));//3件が返ってくる
	}
	@org.junit.Test
	public void isBookingTest1(){
		ConnectionManager connectionManager = new ConnectionManager();
		Connection connection = connectionManager.getConnection();
		ScheduleDAO scheduleDAO = new ScheduleDAO(connection);
		ScheduleBean bean=new ScheduleBean();
		bean.setUserId(1);
		bean.setScheduleDate(Date.valueOf("2020-07-10"));
		bean.setStartTime(Time.valueOf("15:00:00"));
		bean.setEndTime(Time.valueOf( "16:00:00"));
		Boolean answerFlg;
		answerFlg = scheduleDAO.isBooking(bean);
		assertThat(answerFlg, is(false));
		System.out.println(answerFlg);
	}
	@org.junit.Test
	public void isBookingTest2(){
		ConnectionManager connectionManager = new ConnectionManager();
		Connection connection = connectionManager.getConnection();
		ScheduleDAO scheduleDAO = new ScheduleDAO(connection);
		ScheduleBean bean= new ScheduleBean();
		bean.setUserId(1);
		bean.setScheduleDate(Date.valueOf("2020-07-10"));
		bean.setStartTime(Time.valueOf("8:00:00"));
		bean.setEndTime(Time.valueOf( "8:30:00"));
		Boolean answerFlg;
		answerFlg = scheduleDAO.isBooking(bean);
		assertThat(answerFlg, is(false));
		System.out.println(answerFlg);
	}
	@org.junit.Test
	public void isBookingTest3(){
		ConnectionManager connectionManager = new ConnectionManager();
		Connection connection = connectionManager.getConnection();
		ScheduleDAO scheduleDAO = new ScheduleDAO(connection);
		ScheduleBean bean=new ScheduleBean();
		bean.setUserId(1);
		bean.setScheduleDate(Date.valueOf("2020-07-10"));
		bean.setStartTime(Time.valueOf("12:15:00"));
		bean.setEndTime(Time.valueOf( "12:45:00"));
		Boolean answerFlg;
		answerFlg = scheduleDAO.isBooking(bean);
		assertThat(answerFlg, is(true));
		System.out.println(answerFlg);
	}
	@org.junit.Test
	public void isBookingTest4(){
		ConnectionManager connectionManager = new ConnectionManager();
		Connection connection = connectionManager.getConnection();
		ScheduleDAO scheduleDAO = new ScheduleDAO(connection);
		ScheduleBean bean=new ScheduleBean();
		bean.setUserId(1);
		bean.setScheduleDate(Date.valueOf("2020-07-10"));
		bean.setStartTime(Time.valueOf("10:15:00"));
		bean.setEndTime(Time.valueOf( "10:45:00"));
		Boolean answerFlg;
		answerFlg = scheduleDAO.isBooking(bean);
		assertThat(answerFlg, is(true));
		System.out.println(answerFlg);
	}
	@org.junit.Test
	public void isBookingTest5(){
		ConnectionManager connectionManager = new ConnectionManager();
		Connection connection = connectionManager.getConnection();
		ScheduleDAO scheduleDAO = new ScheduleDAO(connection);
		ScheduleBean bean=new ScheduleBean();
		bean.setUserId(1);
		bean.setScheduleDate(Date.valueOf("2020-07-10"));
		bean.setStartTime(Time.valueOf("11:45:00"));
		bean.setEndTime(Time.valueOf( "12:15:00"));
		boolean answerFlg;
		answerFlg = scheduleDAO.isBooking(bean);
		assertThat(answerFlg, is(true));
		System.out.println(answerFlg);
	}
	@org.junit.Test
	public void isBookingTest6(){
		ConnectionManager connectionManager = new ConnectionManager();
		Connection connection = connectionManager.getConnection();
		ScheduleDAO scheduleDAO = new ScheduleDAO(connection);
		ScheduleBean bean=new ScheduleBean();
		bean.setUserId(1);
		bean.setScheduleDate(Date.valueOf("2020-07-10"));
		bean.setStartTime(Time.valueOf("10:45:00"));
		bean.setEndTime(Time.valueOf( "11:45:00"));
		boolean answerFlg;
		answerFlg = scheduleDAO.isBooking(bean);
		assertThat(answerFlg, is(true));
		System.out.println(answerFlg);
	}

	@org.junit.Test
	public void isBookingTest7(){
		ConnectionManager connectionManager = new ConnectionManager();
		Connection connection = connectionManager.getConnection();
		ScheduleDAO scheduleDAO = new ScheduleDAO(connection);
		ScheduleBean bean=new ScheduleBean();
		bean.setUserId(1);
		bean.setScheduleId(8);
		bean.setScheduleDate(Date.valueOf("2020-07-10"));
		bean.setStartTime(Time.valueOf("08:30:00"));
		bean.setEndTime(Time.valueOf( "08:45:00"));
		boolean answerFlg;
		answerFlg = scheduleDAO.isBooking(bean);
		assertThat(answerFlg, is(false));
		System.out.println(answerFlg);
	}

	@org.junit.Test
	public void isDeleted1(){
		ConnectionManager connectionManager = new ConnectionManager();
		Connection connection = connectionManager.getConnection();
		ScheduleDAO scheduleDAO = new ScheduleDAO(connection);
		boolean answerFlg;
		answerFlg = scheduleDAO.isDeleted(6);
		assertThat(answerFlg, is(true));
		System.out.println(answerFlg);
	}
	@org.junit.Test
	public void isDeleted2(){
		ConnectionManager connectionManager = new ConnectionManager();
		Connection connection = connectionManager.getConnection();
		ScheduleDAO scheduleDAO = new ScheduleDAO(connection);
		boolean answerFlg;
		answerFlg = scheduleDAO.isDeleted(3);
		assertThat(answerFlg, is(false));
		System.out.println(answerFlg);
	}
	@org.junit.Test
	public void isDeleted3(){
		ConnectionManager connectionManager = new ConnectionManager();
		Connection connection = connectionManager.getConnection();
		ScheduleDAO scheduleDAO = new ScheduleDAO(connection);
		boolean answerFlg;
		answerFlg = scheduleDAO.isDeleted(100);
		assertThat(answerFlg, is(false));
		System.out.println("isdeleted3:"+answerFlg);
	}
	@org.junit.Test
	public void tooLongSQLScheduleTest1(){
		ConnectionManager connectionManager = new ConnectionManager();
		Connection connection = connectionManager.getConnection();
		ScheduleDAO scheduleDAO = new ScheduleDAO(connection);
		List<ScheduleBean> answer;
		answer = scheduleDAO.tooLongSQLSchedule(Date.valueOf("2020-07-07").toLocalDate(),1);
		System.out.println("3件？"+answer);
		assertThat(answer.size(), is(3));//3件
	}
	@org.junit.Test
	public void tooLongSQLScheduleTest2(){
		ConnectionManager connectionManager = new ConnectionManager();
		Connection connection = connectionManager.getConnection();
		ScheduleDAO scheduleDAO = new ScheduleDAO(connection);
		List<ScheduleBean> answer;
		answer = scheduleDAO.tooLongSQLSchedule(Date.valueOf("2022-07-08").toLocalDate(),1);
		System.out.println("空のリスト"+answer);//空のリストが返ってくる
	}




	@org.junit.Test
	public void registerScheduleTest1(){
		ConnectionManager connectionManager = new ConnectionManager();
		Connection connection = connectionManager.getConnection();
		ScheduleDAO scheduleDAO = new ScheduleDAO(connection);
		ScheduleBean bean = new ScheduleBean();
		bean.setUserId(3);
		bean.setScheduleDate(Date.valueOf("2020-10-10"));
		bean.setStartTime(Time.valueOf("12:00:00"));
		bean.setEndTime(Time.valueOf( "15:00:00"));
		bean.setPlace("0");
		bean.setTitle("プレゼン");
		bean.setContent("来季プロジェクト");
		int answer;
		answer = scheduleDAO.registerSchedule(bean);
		assertThat(answer, is(1));
		System.out.println(answer);
	}
/*	@org.junit.Test
	public void registerScheduleTest2(){
		ConnectionManager connectionManager = new ConnectionManager();
		Connection connection = connectionManager.getConnection();
		ScheduleDAO scheduleDAO = new ScheduleDAO(connection);
		ScheduleBean bean= new ScheduleBean();
		bean.setUserId(3);
		bean.setScheduleDate(Date.valueOf("2020-10-10"));
		bean.setStartTime(Time.valueOf("12:00:00"));
		bean.setEndTime(Time.valueOf( "15:00:00"));
		bean.setPlace("0");
		bean.setTitle("プレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼン");
		bean.setContent("来季プロジェクト");
		int answer;
		answer = scheduleDAO.registerSchedule(bean);
		System.out.println(answer);
		connectionManager.commit();
	}*/
	@org.junit.Test
	public void updateScheduleTest1(){
		ConnectionManager connectionManager = new ConnectionManager();
		Connection connection = connectionManager.getConnection();
		ScheduleDAO scheduleDAO = new ScheduleDAO(connection);
		int answer =  scheduleDAO.updateSchedule(3,Date.valueOf("2020-10-10"),Time.valueOf("12:00:00"),Time.valueOf( "15:00:00"),"プレゼン","来季プロジェクト","0");
		assertThat(answer, is(1));
		System.out.println(answer);
		connectionManager.commit();
	}
	@org.junit.Test
	public void updateScheduleTest2(){
		ConnectionManager connectionManager = new ConnectionManager();
		Connection connection = connectionManager.getConnection();
		ScheduleDAO scheduleDAO = new ScheduleDAO(connection);
		int answer = scheduleDAO.updateSchedule(3,Date.valueOf( "2020-12-12"),Time.valueOf("13:00:00"),Time.valueOf("16:00:00"),"プレゼント購入","クリスマス用","1");
		assertThat(answer, is(1));
		System.out.println(answer);
		connectionManager.commit();
	}


/*	@org.junit.Test
	public void updateScheduleTest3(){
		ConnectionManager connectionManager = new ConnectionManager();
		Connection connection = connectionManager.getConnection();
		ScheduleDAO scheduleDAO = new ScheduleDAO(connection);
		int answer;
		answer = scheduleDAO.updateSchedule(3,Date.valueOf( "2020-12-12"),Time.valueOf("13:00:00"),Time.valueOf("16:00:00"),"1","レゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼンプレゼン","クリスマス用");
		System.out.println(answer);//RuntimeException
	}*/
	@org.junit.Test
	public void updateScheduleTest4(){
		ConnectionManager connectionManager = new ConnectionManager();
		Connection connection = connectionManager.getConnection();
		ScheduleDAO scheduleDAO = new ScheduleDAO(connection);
		int answer = scheduleDAO.updateSchedule(9,60,"もっと短く終わらせます");
		assertThat(answer, is(1));
		System.out.println(answer);
	}



/*	@org.junit.Test
	public void deleteScheduleTest1(){
		ConnectionManager connectionManager = new ConnectionManager();
		Connection connection = connectionManager.getConnection();
		ScheduleDAO scheduleDAO = new ScheduleDAO(connection);
		int answer;
		answer = scheduleDAO.deleteSchedule(2);
		System.out.println(answer);//this.connectionを削除した際、RuntimeException
	}*/

	@org.junit.Test
	public void deleteScheduleTest(){
		ConnectionManager connectionManager = new ConnectionManager();
		Connection connection = connectionManager.getConnection();
		ScheduleDAO scheduleDAO = new ScheduleDAO(connection);
		int answer = scheduleDAO.deleteSchedule(4);
		System.out.println("answer:"+answer);
		assertThat(answer, is(1));
		System.out.println(answer);
		connectionManager.commit();
		}

	@org.junit.Test
	public void deleteScheduleTest2(){
		ConnectionManager connectionManager = new ConnectionManager();
		Connection connection = connectionManager.getConnection();
		ScheduleDAO scheduleDAO = new ScheduleDAO(connection);
		int answer;
		answer = scheduleDAO.deleteSchedule(100);
		assertThat(answer, is(0));//戻り値0?
		System.out.println(answer);
	}
/*	@org.junit.Test
	public void deleteScheduleTest3(){
		ConnectionManager connectionManager = new ConnectionManager();
		Connection connection = connectionManager.getConnection();
		ScheduleDAO scheduleDAO = new ScheduleDAO(connection);
		int answer;
		answer = scheduleDAO.deleteSchedule(2);
		System.out.println(answer);//ELECTにした際、RuntimeException
	}*/



}
