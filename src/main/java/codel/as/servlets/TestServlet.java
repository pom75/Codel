package codel.as.servlets;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import codel.as.domain.Contact;

/**
 * Servlet implementation class login
 * 
 * 
 * Servlet de test. 
 * 
 */
public class TestServlet extends ContactServlet {
	private static final long serialVersionUID = 1L;


	private static Logger log = Logger.getLogger("LoginServlet");
	private static final String CURRENT = "0";

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String test = request.getParameter("test");

		if(test == null) test = CURRENT;
		
		
		switch(test){
			case"delete":
				// PUT TEST HERE
				Contact c = CS.getContact("0");
				log.info(c.toString());
				
				break;
			
			
			default:
				log.severe("Should not test this");
			
		}
		
		
		
		
	}

}