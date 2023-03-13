package com.KoreaIT.java.AM.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Util {
	/**현재 날짜와 시간 리턴 */
	public static String getNow() {
		return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	}
}
