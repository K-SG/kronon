package test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import jp.co.benesse.dataaccess.crypt.CryptographyLogic;

public class TestCryptographyLogic {

	@org.junit.Test
	public void encryptStr1(){
		String str = "9KY7fxp3e";
		assertThat(CryptographyLogic.encryptStr(str),is("16041b234ab7fe47cf1b591b255c71b9f019e2319d0f7e38c5c106fb7564fa85"));
	}
}
