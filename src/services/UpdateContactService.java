package services;

import dao.IDAOContact;
import dao.jdbc.DAOContact;

public class UpdateContactService {

	public static void upContact(long id, String fname, String lname,
			String email) {
		IDAOContact contact = new DAOContact();
		
		contact.modifyContact(id, fname, lname, email);
		
	}

}
