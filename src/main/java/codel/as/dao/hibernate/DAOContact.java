package codel.as.dao.hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.ApplicationContext;

import codel.as.dao.IDAOContact;
import codel.as.domain.Address;
import codel.as.domain.Contact;
import codel.as.domain.Entreprise;
import codel.as.domain.PhoneNumber;
import codel.as.util.ApplicationContextUtils;
import codel.as.util.HibernateUtil;

/**
 * Old, do not follow implem
 *
 */
abstract public class DAOContact implements IDAOContact {

	public boolean createContact(String fname, String lname, String email,
			Address address, Set<PhoneNumber> profiles, int numSiret) {
		Session session = HibernateUtil.getSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			Contact c;
			if (numSiret <= 0) {
				c = new Contact();
				// c =
				// (Contact)ApplicationContextUtils.getApplicationContext().getBean("ContactSetter");
			} else {
				c = new Entreprise();
				// c =
				// (Entreprise)ApplicationContextUtils.getApplicationContext().getBean("Entreprise");
				((Entreprise) c).setNumSiret(numSiret);
			}

			c.setFirstname(fname);
			c.setLastname(lname);
			c.setEmail(email);
			c.setAddress(address);
			session.save(c);

			if (profiles != null) {
				for (PhoneNumber profile : profiles) {
					profile.setContact(c);
					c.getProfiles().add(profile);
					// session.save(profile);
				}
			}

			tx.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null)
				tx.rollback();
			return false;
		} finally {
			session.close();
		}
	}


	public boolean updateContact(Contact c, String fname, String lname,
			String email, String street, String zip, String city,
			String country, String home, String office, String mobile,
			int siretnum) {
		// long idNum = 0;
		// try{
		// idNum = Integer.parseInt(id);
		// } catch (NumberFormatException e){
		// e.printStackTrace();
		// return false;
		// }

		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			// IContact c = (IContact)session.get(Contact.class, idNum);
			// if(c == null){
			// System.out.println("Contact " + id + " not found");
			// return false;
			// }

			System.out.println("version prev : " + c.getVersion());

			c.setFirstname(fname);
			c.setLastname(lname);
			c.setEmail(email);
			c.getAddress().setStreet(street);
			c.getAddress().setZip(zip);
			c.getAddress().setCity(city);
			c.getAddress().setCountry(country);
			checkAndAdd("home", home, c, c.getProfiles(), session);
			checkAndAdd("office", office, c, c.getProfiles(), session);
			checkAndAdd("mobile", mobile, c, c.getProfiles(), session);

			if (siretnum == -1) {
				if (c instanceof Entreprise) {
					return false;
				}
			} else {
				if (c instanceof Entreprise) {
					((Entreprise) c).setNumSiret(siretnum);
				} else {
					return false;
				}
			}

			session.update(c);

			tx.commit();

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null)
				tx.rollback();
			return false;
		} finally {
			System.out.println("version : " + c.getVersion());
			session.close();
		}
	}

	public boolean deleteContact(String id) {
		long idNum = 0;
		try {
			idNum = Integer.parseInt(id);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return false;
		}

		Session session = HibernateUtil.getSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			Contact c = (Contact) session.get(Contact.class, idNum);
			Address a = c.getAddress();

			c.getProfiles().clear();
			c.getBooks().clear();

			session.delete(a);
			session.delete(c);

			tx.commit();

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null)
				tx.rollback();
			return false;
		} finally {
			session.close();
		}
	}

//	@Override
	public List searchContact(String fname, String lname, String email,
			Address address, String home, String office, String mobile) {
		Session session = HibernateUtil.getSession();

		try {
			// StringBuffer s = new StringBuffer();
			//
			// s.append("select c, a from Contact c, Address a where c.address = a ");
			//
			// if(! fname.isEmpty()){
			// s.append("and c.firstname like '%" + fname + "%' ");
			// }
			// if(! lname.isEmpty()){
			// s.append("and c.lastname like '%" + lname + "%' ");
			// }
			// if(! email.isEmpty()){
			// s.append("and c.email like '%" + email + "%' ");
			// }
			// if(! address.getStreet().isEmpty()){
			// s.append("and a.street like '%" + address.getStreet() + "%' ");
			// }
			// if(! address.getZip().isEmpty()){
			// s.append("and a.zip like '%" + address.getZip() + "%' ");
			// }
			// if(! address.getCity().isEmpty()){
			// s.append("and a.city like '%" + address.getCity() + "%' ");
			// }
			// if(! address.getCountry().isEmpty()){
			// s.append("and a.country like '%" + address.getCountry() + "%' ");
			// }
			// System.out.println("Query : " + s.toString());
			// Query query = session.createQuery(s.toString());
			// List contacts = query.list();

			Criteria criteria = session.createCriteria(Contact.class);
			if (!fname.isEmpty()) {
				criteria.add(Restrictions.like("firstname", fname,
						MatchMode.ANYWHERE));
			}
			if (!lname.isEmpty()) {
				criteria.add(Restrictions.like("lastname", lname,
						MatchMode.ANYWHERE));
			}
			if (!email.isEmpty()) {
				criteria.add(Restrictions.like("email", email,
						MatchMode.ANYWHERE));
			}
			if (!address.getStreet().isEmpty()) {
				criteria.add(Restrictions.like("address.street",
						address.getStreet(), MatchMode.ANYWHERE));
			}
			if (!address.getZip().isEmpty()) {
				criteria.add(Restrictions.like("address.zip", address.getZip(),
						MatchMode.ANYWHERE));
			}
			if (!address.getCity().isEmpty()) {
				criteria.add(Restrictions.like("address.city",
						address.getCity(), MatchMode.ANYWHERE));
			}
			if (!address.getCountry().isEmpty()) {
				criteria.add(Restrictions.like("address.country",
						address.getCountry(), MatchMode.ANYWHERE));
			}

			List contacts = criteria.list();

			if (home.isEmpty() && office.isEmpty() && mobile.isEmpty()) {
				return contacts;
			}

			List toRemove = new ArrayList();

			// DAOPhoneNumber daoP = new DAOPhoneNumber();
			ApplicationContext context = ApplicationContextUtils
					.getApplicationContext();
			DAOPhoneNumber daoP = (DAOPhoneNumber) context
					.getBean("DAOPhoneNumber");

			for (int i = 0; i < contacts.size(); i++) {
				// Object[] o = (Object[])contacts.get(i);
				// Contact c = (Contact) o[0];

				Contact c = (Contact) contacts.get(i);

				List pns = daoP.getPhoneNumbersByIdContact(c.getId());
				if ((!keep("home", home, pns))
						|| (!keep("office", office, pns))
						|| (!keep("mobile", mobile, pns))) {
					toRemove.add(c);
				}
			}

			contacts.removeAll(toRemove);
			return contacts;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			session.close();
		}
	}

//	@Override
//	public Object[] getContactById(String id) {
//		Session session = HibernateUtil.getSession();
//
//		try {
//			Query query = session
//					.createQuery("select c, a from Contact c, Address a where c.id = "
//							+ id + " and c.address= a");
//			List contacts = query.list();
//			if ((contacts != null) && (contacts.size() != 0)) {
//				return (Object[]) contacts.get(0);
//			}
//			return null;
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		} finally {
//			session.close();
//		}
//	}

	@Override
	public List getAllContacts() {
		Session session = HibernateUtil.getSession();

		try {
			// Query query = session.createQuery(
			// "select c, a from Contact c, Address a " +
			// "where c.address = a");
			Query query = session
					.createQuery("from Contact c left join fetch c.address address");
			List contacts = query.setCacheable(true).list();
			return contacts;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			session.close();
		}
	}

	private void checkAndAdd(String kind, String number, Contact contact,
			Set<PhoneNumber> profiles, Session session) {
		if (number.equals("")) {
			for (PhoneNumber p : profiles) {
				if (p.getPhoneKind().equalsIgnoreCase(kind)) {
					session.delete(p);
					break;
				}
			}
		} else {
			boolean add = true;
			for (PhoneNumber p : profiles) {
				if (p.getPhoneKind().equalsIgnoreCase(kind)) {
					add = false;
					p.setPhoneNumber(number);
				}
			}
			if (add) {
				// PhoneNumber p = new PhoneNumber(kind, number, contact);
				PhoneNumber p = (PhoneNumber) ApplicationContextUtils
						.getApplicationContext().getBean("PhoneNumber");
				p.setPhoneKind(kind);
				p.setPhoneNumber(number);
				p.setContact(contact);
				session.save(p);
				profiles.add(p);
			}
		}
	}

	private boolean keep(String kind, String number, List phoneNumbers) {
		if (number.isEmpty()) {
			return true;
		}
		if ((!number.isEmpty()) && (phoneNumbers == null)) {
			return false;
		}
		for (Object o : phoneNumbers) {
			PhoneNumber p = (PhoneNumber) o;
			if (p.getPhoneKind().equalsIgnoreCase(kind)
					&& (p.getPhoneNumber().equalsIgnoreCase(number) || (p
							.getPhoneNumber().contains(number)))) {
				return true;
			}
		}
		return false;
	}

	@Override
	public List getContactGroupByIdContact(long idContact) {
		Session session = HibernateUtil.getSession();

		try {
		
			Contact c = (Contact) session.get(Contact.class, idContact);

			Query query = session
					.createQuery("select elements(c.books) from Contact c where c.id = "
							+ idContact);
			List contactGroup = query.list();

			return contactGroup;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("================================ idContact = "
					+ idContact
					+ "======================================================");
			return null;
		} finally {
			session.close();
		}

	}

	@Override
	public boolean addContact(String fname, String lname, String email,
			Address address, Set<PhoneNumber> profiles, int numSiret) {
		throw new UnsupportedOperationException("Not supported bu ths dao");
	}

	@Override
	public boolean deleteContact(long id) {
		throw new UnsupportedOperationException("Not supported bu ths dao");
	}

	@Override
	public Contact getContact(long id) {
		throw new UnsupportedOperationException("Not supported bu ths dao");
	}

	@Override
	public boolean modifyContact(Contact c, String fname, String lname,
			String email, String street, String zip, String city,
			String country, String home, String office, String mobile,
			int siretnum) {
		throw new UnsupportedOperationException("Not supported bu ths dao");
	}

	@Override
	public boolean generateContacts() {
		throw new UnsupportedOperationException("Not supported bu ths dao");
	}
}
