package dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.NonUniqueObjectException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import util.ApplicationContextUtils;
import util.HibernateUtil;
import dao.IDAOContactGroup;
import domain.Contact;
import domain.ContactGroup;

public class DAOContactGroup implements IDAOContactGroup {

	@Override
	public boolean createContactGroup(String name, String idContact) {
		Session session = HibernateUtil.getSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			ContactGroup c = new ContactGroup();
			session.save(c);
			c.setGroupName(name);
			long idNum = Integer.parseInt(idContact);
			Contact contact = (Contact) session.get(Contact.class, idNum);

			if (contact == null) {
				System.out.println("Contact " + idContact + " not found");
				return false;
			}
			contact.getBooks().add(c);
			System.out.println("Id Contact = " + idNum + " => "
					+ contact.getId());

			tx.commit();
			return true;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			if (tx != null)
				tx.rollback();
			return false;
		} finally {
			session.close();
		}
	}

	@Override
	public List getAllContactGroup() {
		Session session = HibernateUtil.getSession();

		try {
			Query query = session.createQuery("select c from ContactGroup c ");
			List contactGroup = query.list();
			return contactGroup;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			session.close();
		}
	}

	@Override
	public ContactGroup getContactGroupById(String id) {
		Session session = HibernateUtil.getSession();

		try {
			Query query = session
					.createQuery("select c from ContactGroup c where c.id = "
							+ id);
			List contactsGroup = query.list();
			if ((contactsGroup != null) && (contactsGroup.size() != 0)) {
				return (ContactGroup) contactsGroup.get(0);
			}
			return null;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			session.close();
		}
	}

	@Override
	public boolean addContact(String[] contacts, String idContactGroup) {
		Session session = HibernateUtil.getSession();
		Transaction tx = null;

		try {
			long idNum = Integer.parseInt(idContactGroup);
			tx = session.beginTransaction();
			ContactGroup c = (ContactGroup) session.get(ContactGroup.class,
					idNum);
			if (c == null) {
				System.out.println("Group contact " + idContactGroup
						+ " not found");
				return false;
			}

			ApplicationContext context = ApplicationContextUtils
					.getApplicationContext();
			DAOContact daoContact = (DAOContact) context.getBean("DAOContact");

			if (contacts != null) {
				for (String idContact : contacts) {
					try {
						// Object[] result =
						// daoContact.getContactById(idContact);
						// Contact contact = (Contact) result[0];
						long id = Integer.parseInt(idContact);
						Contact contact = (Contact) session.load(Contact.class,
								id);
						contact.getBooks().add(c);
						c.getContacts().add(contact);
						System.out
								.println("Dans le foreach DAOCOntactGroup methode addContact idContact = "
										+ idContact);
						session.save(c);
						// tx.commit();
					} catch (NonUniqueObjectException e) {
					}
				}
				tx.commit();
			}
			return true;
		} catch (Exception e) {
			System.out
					.println("========================================= Erreur DAOContactGroup AddContact ============================================");
			e.printStackTrace();
			if (tx != null)
				tx.rollback();
			return false;
		} finally {
			System.out.println("Add contact termine !!!");
			session.close();
		}

	}

	@Override
	public List getContactGroupByIdContactGroup(String idContactGroup) {
		Session session = HibernateUtil.getSession();

		try {
			Query query = session
					.createQuery("select elements(c.contacts) from ContactGroup c where c.groupId = "
							+ idContactGroup);
			// List contactGroup = query.list();
			ContactGroup cg = new ContactGroup();
			long id = Integer.parseInt(idContactGroup);
			cg.setGroupId(id);
			List listContactGroup = session.createCriteria(ContactGroup.class)
					.add(Example.create(cg)).list();
			cg = (ContactGroup) listContactGroup.get(0);
			List contactGroup = new ArrayList(cg.getContacts());
			return contactGroup;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			session.close();
		}
	}

	@Override
	public boolean deleteContactGroup(ContactGroup contactGroup) {
		throw new UnsupportedOperationException("Not supported bu ths dao");
	}
}
