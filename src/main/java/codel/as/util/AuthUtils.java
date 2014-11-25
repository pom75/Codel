package codel.as.util;

import java.math.BigInteger;
import java.security.SecureRandom;

public class AuthUtils {

	public static final String SECRET = "secret";
	private static final SecureRandom SR = new SecureRandom();

	/**
	 * secret creator
	 * 
	 * @return
	 */
	public static String newSecret() {
		// http://stackoverflow.com/questions/41107/how-to-generate-a-random-alpha-numeric-string
		return new BigInteger(130, SR).toString(32);
	}

}
