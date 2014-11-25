package codel.as.dao;

import java.util.List;
import java.util.Set;

import codel.as.domain.Address;
import codel.as.domain.Contact;
import codel.as.domain.Entreprise;
import codel.as.domain.PhoneNumber;

public interface IDAOContact {
	
	public boolean addContact(String fname, String lname, String email, Address address, Set<PhoneNumber> profiles, int numSiret);

	public boolean deleteContact(long id);

	public Contact getContact(long id);
	
	

	public boolean modifyContact(Contact c, String fname, String lname, String email, 
			String street, String zip, String city, String country, String home, String office, String mobile, int siretnum);

	public List<Contact> searchByExampleContact (final String fname, final String lname, final String email, String street, String zip, String city, String country, String home, String office, String mobile, int siretnum);
	
	public List<Contact> searchContactByEmail ( final String email);
	
	public Contact findContactByName (final String fname, final String lname);
	
	public Entreprise findEntrepriseBySiret (int siret);
	
	public List<Contact> searchContactByPhone ( final String phone);
	
	// MAYBE JUST AN UPDATE, SAVE OPERATION??
	
	public boolean generateContacts();
	
	public List<Contact> getContactGroupByIdContact(long idContact);
	
	public List<Contact> getAllContacts();

	
}
