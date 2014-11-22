package services;

import dao.IDAOContact;
import dao.hib.DAOContact;

public class SuppContactService {

	public static void suppContact(long id) {
		IDAOContact contact = new DAOContact();
		contact.deleteContact(id);
		
	}

}
