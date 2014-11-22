package services;

import dao.IDAOContact;
import dao.jdbc.DAOContact;
import domain.Contact;

public class UpdateContactService {

	public static void upContact(Contact c, String fname, String lname, String email, 
			String street, String zip, String city, String country, String home, String office, String mobile, int siretnum) {
		IDAOContact contact = new DAOContact();
		
		contact.modifyContact(c, fname, lname, email, street, zip, city, country, home, office, mobile, siretnum);
		
	}

}
