package com.test.ukey;

import java.security.MessageDigest;

import ET299jni.CET299;
import ET299jni.IET299;

public class Main {
	// product id
	byte[] pid = "CF351A8B".getBytes();
	IET299 et = new CET299();

	/**
	 * ���ֽ�����ת���ɿɶ����ַ���
	 * @param bs �ֽ�����
	 * @return �ɶ����ַ���
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
	 * �ж��Ƿ�ֻ����һ��usbkey������Ҫ��ֻ��һ��usbkey���������һ��������ʾ�û�
	 * @return �Ƿ�ֻ����һ��usbkey
	 */
	public boolean onlyOneKey() {
		int[] tokenCount = new int[1];
		et.FindToken(pid, tokenCount);
		return tokenCount[0] == 1;
	}

	/**
	 * �ͻ�������ժҪ����Ҫ�ͻ��˲���usbkey
	 * @param userPIN usbkey��userPIN��
	 * @param random �����
	 * @return ժҪ��Ϣ
	 * @throws Exception ִ�й������׳����쳣�����յ��쳣����֮����Ҫ�ر�usbkey
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
	 * ���������ժҪ
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
	 * �ر�usbkey
	 */
	public void close() {
		et.CloseToken();
	}


	public static void main(String[] args) {
		byte[] random = "abcdefg".getBytes(); // Ӧ������50���ֽ����ڵ������
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
