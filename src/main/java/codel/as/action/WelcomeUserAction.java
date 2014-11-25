package codel.as.action;

import codel.as.service.LoginService;

public class WelcomeUserAction{
	 
	private String username;
	private String password;


	public String getUsername() {
		return username;
	}
 
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUsername(String username) {
		this.username = username;
	}
 
	// all struts logic here
	public String execute() {
		if(LoginService.checkLogin(username, password)){
			return "SUCCESS";
		}else{
			return "ERROR";
		}
		
 
	}
}