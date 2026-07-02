package com.netbanking.accounts;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Test {

	public static void main(String[] args) {
		LocalDateTime localDateTime = LocalDateTime.parse(
				LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd:HH:mm:ss:SSS")),
						DateTimeFormatter.ofPattern("yyyy-MM-dd:HH:mm:ss:SSS"));
		System.out.println(localDateTime);
	}

}
