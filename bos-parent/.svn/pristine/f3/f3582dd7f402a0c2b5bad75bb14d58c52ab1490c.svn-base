package cn.bos.redis.utils;

import java.util.Random;

public class RandStringUtils {

	public static String getCode(){
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i <4; i++) {
			builder.append(getRandomString());
		}
		return builder.toString();
	}

	private static int getRandomString() {
		Random random = new Random();
		return random.nextInt(9);
	}
	
	public static void main(String[] args) {
		for (int i = 0; i <10; i++) {
			System.out.println(getCode());
		}
	}
}
