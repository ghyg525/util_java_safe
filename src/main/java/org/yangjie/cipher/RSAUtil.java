package org.yangjie.cipher;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

import javax.crypto.Cipher;

/**
 * 非对称加密RSA
 * @author YangJie [2017年10月10日 下午3:58:10]
 */
public class RSAUtil {

	/**
	 * 公钥加密
	 * @param content
	 * @param publicKey
	 * @return
	 * @throws Exception
	 */
	public static String encrypt(String data, PublicKey publicKey) throws Exception {
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		return Base64.getEncoder().encodeToString(cipher.doFinal(data.getBytes()));
	}

	/**
	 * 私钥解密
	 * @param content
	 * @param privateKey
	 * @return
	 * @throws Exception
	 */
	public static String decrypt(String data, PrivateKey privateKey) throws Exception {
		byte[] bytes = Base64.getDecoder().decode(data);
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		return new String(cipher.doFinal(bytes));
	}

	/**
	 * 生成密钥对
	 * @return
	 * @throws Exception
	 */
	public static KeyPair genKeyPair() throws Exception {
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
		keyPairGenerator.initialize(1024);
		return keyPairGenerator.generateKeyPair();
	}
	

	public static void main(String[] args) throws Exception {

		String data = "hello world";
		
		KeyPair keyPair = genKeyPair();

		// 获取公钥，并以base64格式打印出来
		PublicKey publicKey = keyPair.getPublic();
		System.out.println("公钥：" + Base64.getEncoder().encodeToString(publicKey.getEncoded()));

		// 获取私钥，并以base64格式打印出来
		PrivateKey privateKey = keyPair.getPrivate();
		System.out.println("私钥：" + Base64.getEncoder().encodeToString(privateKey.getEncoded()));

		// 公钥加密
		String encryptedString = encrypt(data, publicKey);
		System.out.println("加密后：" + encryptedString);

		// 私钥解密
		String decryptedString = decrypt(encryptedString, privateKey);
		System.out.println("解密后：" + decryptedString);
		
	}
	
	
}
