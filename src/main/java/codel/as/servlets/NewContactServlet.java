package codel.as.servlets;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

import codel.as.domain.PhoneNumber;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import codel.as.domain.Address;
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

		//FIXME Extract Const!!
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

		int numSiret = -1;
		if (fname.isEmpty() || lname.isEmpty() || email.isEmpty()) {
			response.sendRedirect(PathUtils.ADD_PAGE);
		} else {

			if (!siretNum.isEmpty()) {
				try {
					numSiret = Integer.parseInt(siretNum);
				} catch (NumberFormatException e) {
					System.out.println(e);
					response.sendRedirect(PathUtils.ADD_PAGE);
				}
			} else {
				Address address;
				if (street.isEmpty() && zip.isEmpty() && city.isEmpty()
						&& country.isEmpty()) {
					address = null;
				} else {
					address = new Address();
					address.setStreet(street);
					address.setCity(city);
					address.setZip(zip);
					address.setCountry(country);
				}

				Set<PhoneNumber> profiles;
				if (homeNum.isEmpty() && officeNum.isEmpty()
						&& mobileNum.isEmpty()) {
					profiles = null;
				} else {
					profiles = new HashSet<PhoneNumber>();
					if (!homeNum.isEmpty())
						profiles.add(PhoneNumber.newHome(homeNum));

					if (!officeNum.isEmpty())
						profiles.add(PhoneNumber.newHome(officeNum));

					if (!mobileNum.isEmpty())
						profiles.add(PhoneNumber.newHome(mobileNum));
				}

				CS.addContact(fname, lname, email, address, profiles, numSiret);
				// FIXME insert in page page pour afficher message!!

				getServletContext().getRequestDispatcher(PathUtils.ACCUEIL)
						.forward(request, response);
			}

		}
	}

}