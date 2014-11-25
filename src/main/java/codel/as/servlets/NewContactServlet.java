package codel.as.servlets;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import codel.as.domain.Address;
import codel.as.domain.PhoneNumber;
import codel.as.util.PathUtils;

/**
 * Servlet implementation class login
 */
public class NewContactServlet extends ContactServlet {

	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger("NewContactServlet");

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		getServletContext().getRequestDispatcher(PathUtils.ACCUEIL).forward(
				request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// FIXME Extract Const!!
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String email = request.getParameter("email");
		String street = request.getParameter("street");
		String city = request.getParameter("city");
		String zip = request.getParameter("zip");
		String country = request.getParameter("country");
		String mobileNum = request.getParameter("mobilepn");
		String officeNum = request.getParameter("officepn");
		String homeNum = request.getParameter("homepn");
		String[] contactGroups = request.getParameterValues("ContactGroup");
		// FIXME values
		String siretNum = request.getParameter("siretNum");

		if (fname.isEmpty() || lname.isEmpty() || email.isEmpty()
				|| StringUtils.isNumericSpace(siretNum)) {
			// or guava Ints.tryParse
			
			// FIXME add error message:!!!
			log.warning("Invalid param");
			response.sendRedirect(PathUtils.ADD_PAGE);
		} else {

			int numSiret = (siretNum == null) ? -1 : Integer.valueOf(siretNum);

			Address address = (street.isEmpty() && zip.isEmpty()
					&& city.isEmpty() && country.isEmpty()) ? null
					: new Address(street, city, zip, country);

			// FIXME Extract util
			Set<PhoneNumber> profiles = PhoneNumber.newSet(homeNum,mobileNum,officeNum);

			CS.addContact(fname, lname, email, address, profiles, numSiret);
			// FIXME insert in page page pour afficher message!!

			getServletContext().getRequestDispatcher(PathUtils.ACCUEIL)
					.forward(request, response);
		}
	}
}