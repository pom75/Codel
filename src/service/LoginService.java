package service;

public class LoginService {
	public static boolean service(String login, String password){
		return login.equals(password);
	}
}
