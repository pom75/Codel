package services;

import java.util.Set;

import dao.IDAOContact;
import dao.hib.DAOContact;
import domain.Address;
import domain.PhoneNumber;

public class NewContactService {
	
	public static void addContact(String fname, String lname, String email, Address address, Set<PhoneNumber> profiles, int numSiret){
		IDAOContact contact = new DAOContact();
		contact.addContact(fname, lname, email, address, profiles, numSiret);
	}

}
