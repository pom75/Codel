package dao;

import java.util.List;
import java.util.Set;

import domain.Address;
import domain.Contact;
import domain.PhoneNumber;

public interface IDAOContact {
	public boolean addContact(String fname, String lname, String email, Address address, Set<PhoneNumber> profiles, int numSiret);

	public boolean deleteContact(long id);

	public Contact getContact(long id);

	public boolean modifyContact(Contact c, String fname, String lname, String email, 
			String street, String zip, String city, String country, String home, String office, String mobile, int siretnum);

	public List<Contact> searchContact(final String fname, final String lname, final String email, final Address address,
			final String home, final String office, final String mobile);
	public boolean generateContacts();
	public List<Contact> getContactGroupByIdContact(String idContact);
	public List<Contact> getAllContacts();
	public Object[] getContactById(String id);
}