/**
 * 
 */
package com.netbanking.accounts.utils;

import java.util.Random;

import org.springframework.stereotype.Component;

/**
 * 
 */

public class Utils {

	public String generateRandomNumbers(int length)	{
		Random random = new Random();
		String randomString = "";
		for(int i=0; i<length; i++)	{
			randomString += random.nextInt(0, 10);
		}
		return randomString;
	}
	
	/*public static void main(String[] args) {
		System.out.println(new Utils().generateRandomNumbers(12));
	}*/
}
