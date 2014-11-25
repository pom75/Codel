package codel.as.action;

import org.springframework.context.ApplicationContext;

import codel.as.service.ContactService;
import codel.as.util.ApplicationContextUtils;

public abstract class ContactAction {
	
	private static final long serialVersionUID = -2794211203463898L;
	
	protected ContactService CS;

	public ContactAction() {
				ApplicationContext  ac =	ApplicationContextUtils.getApplicationContext();
		this.CS = (ContactService) ac.getBean("ContactService");
		
	}
}
