package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SearchContactServlet extends ContactServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/index.jsp").forward(request,
				response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String email = request.getParameter("email");


		// ContactService.updateContact(fname, lname, email);
		// FIXME Send to some specific page, after having set the result in a
		// var!!
		// RequestDispatcher rd = getServletContext().getRequestDispatcher(
		// responseUrl );
		// rd.forward(request, response);

		getServletContext().getRequestDispatcher("/accueil.jsp").forward(
				request, response);
	}
}
