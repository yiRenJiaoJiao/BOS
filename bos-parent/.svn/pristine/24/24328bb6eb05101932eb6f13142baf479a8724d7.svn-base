package cn.bos.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import sun.misc.BASE64Encoder;

public class DownLoadUtils {
	public static String getAttachmentFileName(String filename,
			String browserType) throws UnsupportedEncodingException {
		if (browserType.contains("Firefox")) {
			// 访问火狐浏览器 =?utf-8?B?
			filename = "=?utf-8?B?"
					+ new BASE64Encoder().encode(filename.getBytes("utf-8"))
					+ "?=";
			// filename = MimeUtility.encodeText(filename, "utf-8", "B");

		} else {
			// IE CHROME 等浏览器 处理附件名乱码
			filename = URLEncoder.encode(filename, "utf-8");
			filename = filename.replace("+", " ");
		}
		return filename;
	}
}
