package codel.as.servlets;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import codel.as.domain.Contact;

/**
 * Servlet implementation de test. Pour facilement invoquer les tests
 *
 * Servlet de test.
 *
 */
public class TestServlet extends ContactServlet {
	private static final long serialVersionUID = 1L;


	private static Logger log = Logger.getLogger("LoginServlet");
	private static final String CURRENT = "all";

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String test = request.getParameter("test");

		if(test == null) test = CURRENT;

		//FIXME Retrieve toString

		switch(test){
		case "delete":
			// PUT TEST HERE
			Contact c = CS.getContact(2);

			log.info("maybe null?"+c.toString());

			break;
		case "add":
			CS.addContact("toto", "toto", "toto", null, null, 12);
			break;
		case "stub":
			CS.generateContacts();
			break;
		case "all":
		    log.info(CS.getAllContacts());
		    break;
		case "nb":
		    log.info(CS.getAllContacts().size());


		default:
			log.severe("Should not test this: provide a test");

		}




	}

}
