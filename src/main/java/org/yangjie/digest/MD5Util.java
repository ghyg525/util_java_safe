package org.yangjie.digest;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * 摘要算法MD5
 * @author YangJie [2017年10月10日 下午3:58:10]
 */
public class MD5Util {
	
	/**
	 * md5
	 * @param str
	 * @return
	 */
	public final static String encode(String str){
		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		messageDigest.update(str.getBytes());
		return Base64.getEncoder().encodeToString(messageDigest.digest());
	}
	
}
