package service;

// Maybe one day make it a instance..
public class LoginService {
	public static boolean checkLogin(String login, String password){		
		return login != null && password != null && login.equals(password);
	}
}
