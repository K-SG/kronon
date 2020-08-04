package jp.co.benesse.dataaccess.crypt;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * [機 能] パスワードをハッシュ化するためのクラス<br>
 * [説 明] なし<br>
 * [備 考] なし<br>
 * [環 境] OpenJDK 11 <br>
 *
 * @author [作 成] 2020/07/16 山崎和樹 [修 正]
 */
public class CryptographyLogic {
	/**
	 * 文字列をハッシュ化するメソッド
	 *
	 * @param text
	 *            ハッシュ化するテキスト
	 *
	 * @return ハッシュ化した計算値(16進数)
	 */
	public static String encryptStr(String text) {

		byte[] valueArray = null;
		String tmpStr = null;
		MessageDigest md = null;
		StringBuffer buffer = new StringBuffer();

		try {
			md = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("指定された暗号化アルゴリズムがありません。暗号化に失敗しました");
		}

		md.update(text.getBytes());
		valueArray = md.digest();

		for (int i = 0; i < valueArray.length; i++) {
			tmpStr = Integer.toHexString(valueArray[i] & 0xff);
			if (tmpStr.length() == 1) {
				buffer.append('0').append(tmpStr);
			} else {
				buffer.append(tmpStr);
			}
		}
		return buffer.toString();
	}

}
