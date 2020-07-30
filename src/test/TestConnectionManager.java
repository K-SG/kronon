package test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

import jp.co.benesse.dataaccess.cm.ConnectionManager;

public class TestConnectionManager {

	/*@Test
	public void CMtest1() {
		try {
			ConnectionManager connectionmanager = new ConnectionManager();
			connectionmanager.getConnection();
		} catch(RuntimeException e){
			assertThat(e.getMessage(),is("ドライバーのロードに失敗しました"));
			System.out.println(e.getMessage());
		}
	}*/
	@Test
	public void CMtest2() {
		try {
			ConnectionManager connectionmanager = new ConnectionManager();
			connectionmanager.getConnection();
		} catch(RuntimeException e){
			assertThat(e.getMessage(),is("データベースの接続に失敗しました"));
			System.out.println(e.getMessage());
		}
	}


}