package codel.as.service;

import java.util.Set;

import codel.as.dao.IDAOContact;
import codel.as.domain.Address;
import codel.as.domain.Contact;
import codel.as.domain.PhoneNumber;

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
		// MAYBE CHANGE SIGNATURE
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

	public Contact getContact(long id) {
		return daoContact.getContactById(id);

	}

	// TODO SEARCH. Meme méthodes que dans DAO!!

}
