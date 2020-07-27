package jp.co.benesse.calc;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.sql.Time;
import java.time.LocalDate;

import org.junit.Test;

import jp.co.benesse.dataaccess.value.ScheduleBean;

public class CalcTest {

	@Test
	public void testCalcActualTime() {
		ScheduleBean scheduleBean = new ScheduleBean();
		scheduleBean.setActualTime(260);
		String time = Calc.calcActualTime(scheduleBean);
		assertThat(time,is("4時間20分"));
	}

	@Test
	public void testCalcEstimateTime() {
		ScheduleBean scheduleBean = new ScheduleBean();
		Time startTime = Time.valueOf("12:00:00");
		Time endTime = Time.valueOf("13:30:00");
		scheduleBean.setStartTime(startTime);
		scheduleBean.setEndTime(endTime);
		String s = Calc.calcEstimateTime(scheduleBean);
		assertThat(s,is("1時間30分"));

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
		assertThat(s,is("7/27(月)"));
	}

}
