package com.test.ukey;

import java.security.MessageDigest;

import ET299jni.CET299;
import ET299jni.IET299;

public class Main {
	// product id
	byte[] pid = "CF351A8B".getBytes();
	IET299 et = new CET299();

	/**
	 * 将字节数组转化成可读的字符串
	 * @param bs 字节数组
	 * @return 可读的字符串
	 */
	public static String byte2HexString(byte[] bs) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < bs.length; i++) {
			String hex = Integer.toHexString(bs[i] & 0xff);
			if (hex.length() == 1) {
				hex = "0" + hex;
			}
			sb.append(hex.toUpperCase());
		}
		return sb.toString();
	}

	/**
	 * 判断是否只插了一个usbkey，我们要求只插一个usbkey，如果多于一个，则提示用户
	 * @return 是否只插了一个usbkey
	 */
	public boolean onlyOneKey() {
		int[] tokenCount = new int[1];
		et.FindToken(pid, tokenCount);
		return tokenCount[0] == 1;
	}

	/**
	 * 客户端生成摘要，需要客户端插上usbkey
	 * @param userPIN usbkey的userPIN码
	 * @param random 随机数
	 * @return 摘要信息
	 * @throws Exception 执行过程中抛出的异常对象，收到异常对象之后需要关闭usbkey
	 */
	public byte[] clientGenerateDigest(String userPIN, byte[] random) throws Exception {
		if (random.length > 50) {
			throw new Exception("random length should less than or equal 50");
		}
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		byte[] userPINDigest = md5.digest(userPIN.getBytes());
		String actualPIN = byte2HexString(userPINDigest).substring(0, 16);
		System.out.println(actualPIN);
		byte[] digest = new byte[16];
		et.OpenToken(pid, 1);
		et.Verify(0, actualPIN.getBytes());
		et.MD5HMAC(1, random, random.length, digest);
		return digest;
	}

	/**
	 * 服务端生成摘要
	 * @param secKey
	 * @param random
	 * @return
	 */
	public byte[] serverGenerateDigest(String secKey, byte[] random) {
		byte[] digest = new byte[16];
		byte[] tokenKey = new byte[32];
		et.SoftMD5HMAC(random, random.length, secKey.getBytes(), secKey.getBytes().length, tokenKey, digest);
		return digest;
	}

	/**
	 * 关闭usbkey
	 */
	public void close() {
		et.CloseToken();
	}


	public static void main(String[] args) {
		byte[] random = "abcdefg".getBytes(); // 应该生成50个字节以内的随机数
		Main m = new Main();
		System.out.println("onlyOneKey:" + m.onlyOneKey());
		try {
			byte[] clientDigest = m.clientGenerateDigest("shterm", random);
			byte[] serverDigest = m.serverGenerateDigest("12345678901234567890123456789012", random);
			System.out.println(byte2HexString(clientDigest));
			System.out.println(byte2HexString(serverDigest));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			m.close();
		}
	}

}
