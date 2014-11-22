package dao.hib;

import java.util.List;

import org.hibernate.Session;

import util.DBTools;
import dao.IDAOContact;
import domain.Contact;

public class DAOContact implements IDAOContact {

	@Override
	public boolean addContact(String fname, String lname, String email) {
		Session session = DBTools.getSessionFactory().getCurrentSession();
		//démarrer une transaction
		session.beginTransaction();
		//persister l’objet
		session.save(new Contact());
		//committer la transaction
		session.getTransaction().commit();
		return false;
	}

	@Override
	public boolean deleteContact(long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Contact getContact(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean modifyContact(long id, String firstname, String lastname,
			String email) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Contact> getContactByFirstName(String firstname) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Contact> getContactByLastName(String lastname) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Contact> getContactByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

}
