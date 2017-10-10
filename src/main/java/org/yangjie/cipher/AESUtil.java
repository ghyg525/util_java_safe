package org.yangjie.cipher;

import java.security.Key;
import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;

/**
 * 对称加密AES
 * @author YangJie [2017年10月10日 下午3:58:10]
 */
public class AESUtil {

	/**
	 * 加密
	 * @param str
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String encrypt(String str, String key) throws Exception {
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.ENCRYPT_MODE, generateKey(key));
		byte[] bytes = cipher.doFinal(str.getBytes());

		return Base64.getEncoder().encodeToString(bytes);
	}
	
	/**
	 * 解密
	 * @param str
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String decrypt(String str, String key) throws Exception {
		byte[] bytes = Base64.getDecoder().decode(str);
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.DECRYPT_MODE, generateKey(key));
		return new String(cipher.doFinal(bytes));
	}

	/**
	 * 生成key
	 * @param key
	 * @return
	 * @throws Exception
	 */
	private static Key generateKey(String key) throws Exception {
		KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
		keyGenerator.init(128, new SecureRandom(key.getBytes())); // 密钥长度 128/192/256
		return new SecretKeySpec(keyGenerator.generateKey().getEncoded(), "AES");  
	}

	public static void main(String[] args) throws Exception {

		String str = "hello world";
		String key = "password";
		
		// 加密
		String encryptedString = encrypt(str, key);
		System.out.println("加密后：" + encryptedString);

		// 解密
		String decryptedString = decrypt(encryptedString, key);
		System.out.println("解密后：" + new String(decryptedString));
		
	}
	
}
