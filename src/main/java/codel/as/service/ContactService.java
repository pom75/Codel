package codel.as.service;

import java.util.List;
import java.util.Set;

import codel.as.dao.IDAOContact;
import codel.as.domain.Address;
import codel.as.domain.Contact;
import codel.as.domain.ContactGroup;
import codel.as.domain.Entreprise;
import codel.as.domain.PhoneNumber;

public class ContactService {

	IDAOContact daoContact;

	// FIXME Later add other DAO

	public ContactService(IDAOContact daoContact) {
		super();
		this.daoContact = daoContact;
	}

	public void addContact(String fname, String lname, String email,
			Address address, Set<PhoneNumber> profiles, int numSiret) {
		daoContact.addContact(fname, lname, email, address, profiles, numSiret);
		// FIXME CHANGE SIGNATURE
		// FIXME: send back contact?
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
		return daoContact.getContact(id);

	}

	public List<Contact> search(String fname, String lname, String email,
			String street, String zip, String city, String country,
			String home, String office, String mobile, int siretnum) {
		return daoContact.searchByExampleContact(fname, lname, email, street,
				zip, city, country, home, office, mobile, siretnum);
	}

	public List<Contact> searchContactByEmail(String email) {
		return daoContact.searchContactByEmail(email);
	}
	
	public Entreprise searchContactBySiret(int siret) {
		return daoContact. findEntrepriseBySiret(siret);
	}

	public Contact searchContactByName(String fname, String lname) {
		return daoContact.findContactByName(fname, lname);
	}

	public List<Contact> searchContactByPhone(String phone) {
		return daoContact.searchContactByPhone(phone);
	}

	public boolean generateContacts() {
		return daoContact.generateContacts();
	}

	public List<ContactGroup> getContactGroupByIdContact(long idContact) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Contact> getAllContacts() {
		return daoContact.getAllContacts();
	}

}
