package services;

import dao.IDAOContact;
import dao.hib.DAOContact;

public class NewContactService {
	
	public static void addContact(String fname, String lname, String email){
		IDAOContact contact = new DAOContact();
		contact.addContact(fname, lname, email);
	}

}
