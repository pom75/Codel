package dao;

import java.util.List;
import java.util.Set;

import domain.Address;
import domain.Contact;
import domain.PhoneNumber;

public interface IDAOContact {
	public boolean addContact(String fname, String lname, String email);

	public boolean deleteContact(long id);

	public Contact getContact(long id);

	public boolean modifyContact(long id, String firstname, String lastname, String email);

	public List<Contact> getContactByFirstName(String firstname);

	public List<Contact> getContactByLastName(String lastname);

	public List<Contact> getContactByEmail(String email);
}