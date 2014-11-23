package servlets;

import javax.servlet.http.HttpServlet;

import org.springframework.context.ApplicationContext;

import service.ContactService;
import util.ApplicationContextUtils;

abstract public class ContactServlet extends HttpServlet{

	private static final long serialVersionUID = -2794211261403463898L;
	protected ContactService CS;

	public ContactServlet() {
		super();
		ApplicationContext  ac =	ApplicationContextUtils.getApplicationContext();
		this.CS = (ContactService) ac.getBean("ContactService");
		
	}
}
