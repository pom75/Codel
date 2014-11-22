package services;

import dao.IDAOContact;
import dao.jdbc.DAOContact;

public class SuppContactService {

	public static void suppContact(long id) {
		IDAOContact contact = new DAOContact();
		contact.deleteContact(id);
		
	}

}
