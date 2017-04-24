package com.free.common.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5转化为十六进制
 * 
 * @author mefly
 */
public class Encryption {
	private static MessageDigest digest = null;

	public static final String algorithm = "MD5";

	public synchronized static final String hashToMD5(String data) {
		if (digest == null) {
			try {
				digest = MessageDigest.getInstance(algorithm);
			} catch (NoSuchAlgorithmException nsae) {
				String prompt = "Failed to load the MD5 MessageDigest.";
				System.err.println(prompt);
				nsae.printStackTrace();
			}
		}
		// Now, compute hash.
		digest.update(data.getBytes());
		return encodeHex(digest.digest());
	}

	private static final String encodeHex(byte[] bytes) {

		// Note: StringBuilder is not a thread-safe type, it saves time
		// processing data. More details see about "Do Java 6 threading
		// optimizations actually work?"
		// http://www.infoq.com/articles/java-threading-optimizations-p1

		StringBuilder buf = new StringBuilder(bytes.length * 2);
		int i;

		for (i = 0; i < bytes.length; i++) {
			if ((bytes[i] & 0xff) < 0x10) {
				// 不够十六进制进位，则在转化后的数字前加0，以保证每一个字节(byte)正确的转化为两位的十六进制数字
				// (1 bytes == 8 bits == 2 hex)
				buf.append("0");
			}
			// 0xFF与运算，得到8(1 byte)位结果，参数16表明最终返回的字符串以十六进制表示
			buf.append(Long.toString(bytes[i] & 0xff, 16));
		}
		return buf.toString();
	}

}
