package org.yangjie.signature;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.util.Base64;

/**
 * 数字签名DSA
 * @author YangJie [2017年10月10日 下午3:58:10]
 */
public class DSAUtil {

	/**
	 * 签名
	 * @param data
	 * @param privateKey
	 * @return
	 * @throws Exception
	 */
	public static String sign(String data, PrivateKey privateKey) throws Exception {
		Signature signature = Signature.getInstance(KeyFactory.getInstance("DSA").getAlgorithm());
		signature.initSign(privateKey);
		signature.update(data.getBytes());
		return Base64.getEncoder().encodeToString(signature.sign());
	}

	/**
	 * 验证
	 * @param data
	 * @param sign
	 * @param publicKey
	 * @return
	 * @throws Exception
	 */
	public static boolean verify(String data, String sign, PublicKey publicKey) throws Exception {
		byte[] bytes = Base64.getDecoder().decode(sign);
		Signature signature = Signature.getInstance(KeyFactory.getInstance("DSA").getAlgorithm());
		signature.initVerify(publicKey);
		signature.update(data.getBytes());
		return signature.verify(bytes);
	}

	/**
	 * 生成密钥对
	 * @return
	 * @throws Exception
	 */
	public static KeyPair genKeyPair() throws Exception {
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("DSA");
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

		// 签名
		String sign = sign(data, privateKey);
		System.out.println("签名：" + sign);

		// 验证
		boolean flag = verify(data, sign, publicKey);
		System.out.println("验证：" + flag);
		
	}
	
	
}
