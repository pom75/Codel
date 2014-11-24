package codel.as.servlets;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import codel.as.domain.Contact;

public class SearchContactServlet extends ContactServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger("SearchContactServlet");
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/index.jsp").forward(request,
				response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");

		
		
		Contact c = CS.getContact(id); //<- Comment caste sa en bon objet ?
		
		
		System.out.println(c.getLastname());
		
		//og.info(c.getFirstname());
		
		getServletContext().getRequestDispatcher("/accueil.jsp").forward(
				request, response);
	}
}
