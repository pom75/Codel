package codel.as.servlets;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import codel.as.domain.Contact;

/**
 * Servlet implementation contact Stubs
 *
 *
 * Servlet de test.
 *
 */
public class ContactStubServlet extends ContactServlet {

	private static final long serialVersionUID = 12222L;

	private static Logger log = Logger.getLogger("ContactStub");

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {


		//FIXME Option to delete the Base!

	    log.info("Génération des contacts stibs");
	    CS.generateContacts();

	}

}
