package service;

import java.util.Set;

import dao.IDAOContact;
import domain.Address;
import domain.Contact;
import domain.PhoneNumber;

// FIXME Extract interface and make it a bean!m!!
public class ContactService {
	// FIXME Static MEthod??

	IDAOContact daoContact;

	// FIXME Later add other DAO

	public ContactService(IDAOContact daoContact) {
		super();
		this.daoContact = daoContact;
	}

	public void addContact(String fname, String lname, String email,
			Address address, Set<PhoneNumber> profiles, int numSiret) {
		daoContact.addContact(fname, lname, email, address, profiles, numSiret);

		// MAYBE: send back contact?
	}

	public void updateContact(Contact c, String fname, String lname,
			String email, String street, String zip, String city,
			String country, String home, String office, String mobile,
			int siretnum) {
		daoContact.modifyContact(c, fname, lname, email, street, zip, city,
				country, home, office, mobile, siretnum);
	}

	public void deleteContact(long id) {
		daoContact.deleteContact(id);

	}

	// TODO SEARCH. Meme méthodes que dans DAO!!

}
