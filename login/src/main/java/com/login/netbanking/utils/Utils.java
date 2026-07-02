/**
 * 
 */
package com.login.netbanking.utils;

import org.springframework.security.crypto.bcrypt.BCrypt;

/**
 * 
 */
public class Utils {
	
	public static String encrypt(String strToEncrypt)	{
		String salt = BCrypt.gensalt();
		String encryptedStr = BCrypt.hashpw(strToEncrypt, salt);
		return encryptedStr;
	}
}
