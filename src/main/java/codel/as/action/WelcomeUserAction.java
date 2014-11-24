package codel.as.action;

public class WelcomeUserAction{
	 
	private String login;
 
	public String getUsername() {
		return login;
	}
 
	public void setUsername(String login) {
		this.login = login;
	}
 
	// all struts logic here
	public String execute() {
 
		return "SUCCESS";
 
	}
}