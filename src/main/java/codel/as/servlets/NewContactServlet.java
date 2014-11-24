package codel.as.servlets;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

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

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		getServletContext().getRequestDispatcher(PathUtils.ACCUEIL).forward(request,
				response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String email = request.getParameter("email");
		String street = request.getParameter("street");
		String city = request.getParameter("city");
		String zip = request.getParameter("zip");
		String country = request.getParameter("country");
		String mobilepn = request.getParameter("mobilepn");
		String officepn = request.getParameter("officepn");
		String homepn = request.getParameter("homepn");
		String[] contactGroups = request.getParameterValues("ContactGroup"); // FIXME values
		String siretNum = request.getParameter("siretNum");
		
		
		int numSiret = -1;
		
		Address address;
		if(street.isEmpty() && zip.isEmpty() && city.isEmpty() && country.isEmpty()){
			address = null;
		} else {
			address = new Address();
			address.setStreet(street);
			address.setCity(city);
			address.setZip(zip);
			address.setCountry(country);
		}
		
		Set<PhoneNumber> profiles;

		
		if(homepn.isEmpty() && officepn.isEmpty() && mobilepn.isEmpty()){
			profiles = null;
		} else {
			profiles = new HashSet<PhoneNumber>();
			if(! homepn.isEmpty()){
				PhoneNumber home = new PhoneNumber();
				home.setPhoneKind("home");
				home.setPhoneNumber(homepn);
				profiles.add(home);
			}
			if(! officepn.isEmpty()){
				PhoneNumber office = new PhoneNumber();
				office.setPhoneKind("office");
				office.setPhoneNumber(officepn);
				profiles.add(office);
			}
			if(! mobilepn.isEmpty()){
				PhoneNumber mobile = new PhoneNumber();
				mobile.setPhoneKind("mobile");
				mobile.setPhoneNumber(mobilepn);
				profiles.add(mobile);
			}
		}
		
		CS.addContact(fname, lname, email, address, profiles, numSiret);
		// TODO page pour afficher message!!
		
		if(fname.isEmpty() || lname.isEmpty() || email.isEmpty()){
			response.sendRedirect("addContact.jsp"); 
		}else if(! siretNum.isEmpty()){
			try{
				numSiret = Integer.parseInt(siretNum);
			} catch(NumberFormatException e) {
				// TODO add message in response param, set also in page
				response.sendRedirect("addContact.jsp"); 
				return;
			}
		}else {
			getServletContext().getRequestDispatcher(PathUtils.ACCUEIL).forward(
					request, response);
		}
		
		
	}

}