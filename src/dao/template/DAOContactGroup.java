package dao.template;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.NonUniqueObjectException;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import util.ApplicationContextUtils;
import dao.IDAOContact;
import dao.IDAOContactGroup;
import domain.Contact;
import domain.ContactGroup;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class DAOContactGroup extends HibernateDaoSupport implements
		IDAOContactGroup {
	public boolean createContactGroup(String name, String idContact) {
		try {

			// FIXME WHY CONTACT GROUP DI
			ContactGroup c = (ContactGroup) ApplicationContextUtils
					.getApplicationContext().getBean("ContactGroup");
			c.setGroupName(name);

			long idNum = Integer.parseInt(idContact);
			Contact contact = (Contact) getHibernateTemplate().get(
					Contact.class, idNum);

			if (contact == null) {
				System.out.println("Contact " + idContact + " not found");
				return false;
			}

			c.getContacts().add(contact);
			contact.getBooks().add(c);
			getHibernateTemplate().save(c);
			System.out.println("Id Contact = " + idNum + " => "
					+ contact.getId());

			return true;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return false;
		}
	}

	public List getAllContactGroup() {
		try {
			// ¤hib:sql
			List contactGroup = getHibernateTemplate().find(
					"select c from ContactGroup c ");
			return contactGroup;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	public ContactGroup getContactGroupById(String id) {
		try {
			List contactsGroup = getHibernateTemplate().find(
					"select c from ContactGroup c where c.id = " + id);
			if ((contactsGroup != null) && (!contactsGroup.isEmpty())) {
				return (ContactGroup) contactsGroup.get(0);
			}
			return null;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	public boolean addContact(String[] contacts, String idContactGroup) {
		try {
			long idNum = Integer.parseInt(idContactGroup);
			ContactGroup c = (ContactGroup) getHibernateTemplate().get(
					ContactGroup.class, idNum);
			if (c == null) {
				System.out.println("Group contact " + idContactGroup
						+ " not found");
				return false;
			}

			ApplicationContext context = ApplicationContextUtils
					.getApplicationContext();
			IDAOContact daoContact = (IDAOContact) context
					.getBean("DAOContact");
			// FIXME Not used?

			if (contacts != null) {
				for (String idContact : contacts) {
					try {
						long id = Integer.parseInt(idContact);
						Contact contact = (Contact) getHibernateTemplate().get(
								Contact.class, id);
						if (contact == null) {
							System.out.println("Cannot find the contact "
									+ idContact);
							return false;
						}
						contact.getBooks().add(c);
						c.getContacts().add(contact);
						System.out
								.println("Dans le foreach DAOCOntactGroup methode addContact idContact = "
										+ idContact);
					} catch (NonUniqueObjectException e) {
					}
				}
				System.out.println("Add contact termine !!!");
				getHibernateTemplate().update(c);
			}
			return true;
		} catch (Exception e) {
			System.out
					.println("========================================= Erreur DAOContactGroup AddContact ============================================");
			e.printStackTrace();
			return false;
		}
	}

	public List getContactGroupByIdContactGroup(final String idContactGroup) {
		return (List) getHibernateTemplate().executeFind(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						try {
							// FIXME WTF BEAN?
							ContactGroup cg = (ContactGroup) ApplicationContextUtils
									.getApplicationContext().getBean(
											"ContactGroup");
							long id = Integer.parseInt(idContactGroup);

							System.out.println("cg.getGroupId() : "
									+ cg.getGroupId());
							System.out.println("cg.getGroupName()"
									+ cg.getGroupName());

							cg.setGroupId(id);
							// ¤hib:crit
							List listContactGroup = session
									.createCriteria(ContactGroup.class)
									.add(Example.create(cg)).list();
							for (Object g : listContactGroup) {
								if (String.valueOf(
										((ContactGroup) g).getGroupId())
										.equals(idContactGroup)) {
									cg = (ContactGroup) g;
									break;
								}
							}
							List contacts = new ArrayList(cg.getContacts());
							return contacts;
						} catch (Exception e) {
							System.out.println(e.getMessage());
							return null;
						}
					}
				});
	}

	public boolean deleteContactGroup(ContactGroup contactGroup) {
		try {
			getHibernateTemplate().delete(contactGroup);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
