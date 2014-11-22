package services;

import java.util.Set;

import dao.IDAOContact;
import dao.hibernate.DAOContact;
import domain.Address;
import domain.Contact;
import domain.PhoneNumber;

// FIXME Extract interface and make it a bean!m!!
public class ContactService {
	//FIXME Static MEthod??	

	public static void addContact(String fname, String lname, String email, Address address, Set<PhoneNumber> profiles, int numSiret){
		IDAOContact contact = new DAOContact(); //FIXME
		contact.addContact(fname, lname, email, address, profiles, numSiret);
		
		//MAYBE: send back contact?
	}

	public static void updateContact(Contact c, String fname, String lname, String email, 
			String street, String zip, String city, String country, String home, String office, String mobile, int siretnum) {
		IDAOContact contact = new DAOContact();
		
		contact.modifyContact(c, fname, lname, email, street, zip, city, country, home, office, mobile, siretnum);	
	}
	
	public static void deleteContact(long id) {
		IDAOContact contact = new DAOContact();
		contact.deleteContact(id);
		
	}
	
	//TODO SEARCH. Meme méthodes que dans DAO!!
	
}
