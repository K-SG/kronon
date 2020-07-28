package test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;

import org.junit.Test;

import jp.co.benesse.calc.Calc;
import jp.co.benesse.dataaccess.value.ScheduleBean;

public class TestCalc {
	@Test
	public void testCalcActualTime() {
		ScheduleBean scheduleBean = new ScheduleBean();
		scheduleBean.setActualTime(100);
		String time = Calc.calcActualTime(scheduleBean);
		assertThat(time,is("1時間40分"));
	}

	@Test
	public void testCalcEstimateTime() {
		ScheduleBean scheduleBean = new ScheduleBean();
		Time startTime = Time.valueOf("13:00:00");
		Time endTime = Time.valueOf("14:00:00");
		scheduleBean.setStartTime(startTime);
		scheduleBean.setEndTime(endTime);
		String s = Calc.calcEstimateTime(scheduleBean);
		assertThat(s,is("1時間0分"));

		ScheduleBean scheduleBean2 = new ScheduleBean();
		Time startTime2 = Time.valueOf("12:00:00");
		scheduleBean2.setStartTime(startTime2);
		String ss = Calc.calcEstimateTime(scheduleBean2);
		assertThat(ss,is(""));
	}

	@Test
	public void testConvertActualDate() {
		LocalDate date = LocalDate.now();
		String s = Calc.convertActualDate(date);
		assertThat(s,is("7/28(火)"));
	}
	@Test
	public void testConvertActualDate2() {
		Date date = Date.valueOf("2020-07-28");
		String s = Calc.convertActualDate(date);
		assertThat(s,is("2020/7/28(火)"));
	}
/*	@Test
	public void testConvertActualDate3() {
		Date date = Date.valueOf("2020-47-28");
		String s = Calc.convertActualDate(date);
		assertThat(s,is("RuntimeException"));
	}*/

}
