package codel.as.dao.hibernate;

import org.springframework.dao.DataAccessException;

import static codel.as.domain.PhoneNumber.MOBILE_CATEGORY;
import static codel.as.domain.PhoneNumber.WORK_CATEGORY;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import codel.as.dao.IDAOContact;
import codel.as.dao.IDAOContactGroup;
import codel.as.domain.Address;
import codel.as.domain.Contact;
import codel.as.domain.ContactGroup;
import codel.as.domain.Entreprise;
import codel.as.domain.PhoneNumber;
import codel.as.util.ApplicationContextUtils;

@SuppressWarnings({ "rawtypes", "unchecked" })
// FIXME Try togenetic
// Notes: converts checked HibernateExceptions into unchecked
// DataAccessExceptions
public class DAOContact extends HibernateDaoSupport implements IDAOContact {

	// http://stackoverflow.com/questions/8977121/advantages-of-using-hibernate-callback
	private static Logger log = Logger.getLogger("template.DAOContact");

	private IDAOContactGroup daoContactGroup;

	public DAOContact(IDAOContactGroup daoContactGroup) {
		super();
		this.daoContactGroup = daoContactGroup;
	}

	@Override
	public Contact getContact(long id) {
		return getHibernateTemplate().get(Contact.class, id);
	}

	@Override
	public List getAllContacts() {
		// TODO TEST
		return getHibernateTemplate().find(
				"from Contact c left join fetch c.address address");
		// CHECK should modif to have something else
	}

	@Override
	public List getContactGroupByIdContact(long idContact) {
		try {
			List contactGroup = getHibernateTemplate().findByNamedParam(
					"select elements(c.books) from Contact c where c.id = :id",
					"id", idContact);
			// FIXME CHECK QUERRY
			return contactGroup;
		} catch (DataAccessException e) {
			log.warning(e.getMessage());
			log.warning("idContact: " + idContact + ":");
			return null;
		}
	}

	@Override
	public boolean addContact(String fname, String lname, String email,
			Address address, Set<PhoneNumber> phones, int numSiret) {
		// FIXME Signature.
		// Preconditions checkying

		Contact c;
		if (numSiret <= 0) {
			c = new Contact(fname, lname, email, address, phones);
		} else {
			c = new Entreprise(fname, lname, email, address, phones, numSiret);
		}

		getHibernateTemplate().setCheckWriteOperations(false);
		// FIXME what is this?

		if (phones != null) {
			for (PhoneNumber profile : phones) {
				profile.setContact(c);
				c.getProfiles().add(profile);
				getHibernateTemplate().save(profile);
			}
		}
		try {
			getHibernateTemplate().save(c);
			// CHECK SOME HYBERNATE EXCEPTION
			return true;
		} catch (DataAccessException e) {
			log.info("Could not save contact:" + fname);
			log.info("Error (probable duplicate): " + e.getLocalizedMessage());
			return false;
		}
	}

	@Override
	public boolean deleteContact(long id) {
		long idNum = id;
		// FIXME Do it recursive?
		try {
			Contact c = (Contact) getHibernateTemplate().get(Contact.class,
					idNum);
			c.getProfiles().clear();

			for (ContactGroup cg : c.getBooks()) {
				cg.getContacts().remove(c);
				if (cg.getContacts().size() == 0) {
					daoContactGroup.deleteContactGroup(cg);
				}
			}

			getHibernateTemplate().delete(c);

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean modifyContact(Contact c, String fname, String lname,
			String email, String street, String zip, String city,
			String country, String home, String office, String mobile,
			int siretnum) {
		try {
			// FIXME JUST CHECK VALUE!!!
			log.info("Updating contact. version prev : " + c.getVersion());

			if (siretnum <= 0) {
				if (c instanceof Entreprise) {
					log.info("Num siret vas not valid!....");
					return false;
				}
			} else {
				if (c instanceof Entreprise) {
					((Entreprise) c).setNumSiret(siretnum);
				} else {
					return false;
				}
			}

			c.setFirstname(fname);
			c.setLastname(lname);
			c.setEmail(email);
			Address adr = new Address(street, city, zip, country);
			c.setAddress(adr);

			updatePhones(home, office, mobile, c.getProfiles());
			log.warning("Updating all the thing" + c);
			getHibernateTemplate().update(c);

			log.info("version après mise à jour : " + c.getVersion());

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	private void updatePhones(String home, String office, String mobile,
			Set<PhoneNumber> profiles) {
		for (PhoneNumber p : profiles) {
			switch (p.getPhoneKind()) {
			case HOME_CATEGORY:
				updatePhone(home, profiles, p);
				break;
			case MOBILE_CATEGORY:
				updatePhone(mobile, profiles, p);
				break;

			case WORK_CATEGORY:
				updatePhone(office, profiles, p);
				break;
			
			}
		}

	}

	private void updatePhone(String home, Set<PhoneNumber> profiles,
			PhoneNumber p) {
		if (home != null) {
			if (home.isEmpty()) {
				profiles.remove(p);
			} else {
				p.setPhoneNumber(home);
			}
		}
	}

	@Override
	public List<Contact> searchByExampleContact(String firstname,
			String lastname, String email, String street, String zip,
			String city, String country, String home, String office,
			String mobile, int siretnum) {

		Address address = new Address(street, city, zip, country);
		// Create the example
		Contact example;
		if (siretnum <= 0) {
			example = new Contact(firstname, lastname, email, address);
		} else {
			example = new Entreprise(firstname, lastname, email, address,
					siretnum);
		}
		return (List<Contact>) getHibernateTemplate().findByExample(example);
	}

	@Override
	public List<Contact> searchContactByEmail(String email) {
		DetachedCriteria filter = DetachedCriteria.forClass(Contact.class);
		filter.add(Restrictions.eq("email", email));
		return (List<Contact>) getHibernateTemplate().findByCriteria(filter);
	}

	@Override
	public Contact findContactByName(String fname, String lname) {
		DetachedCriteria filter = DetachedCriteria.forClass(Contact.class);
		filter.add(Restrictions.like("firstname", fname));
		filter.add(Restrictions.like("lastname", lname));
		List<Contact> res = (List<Contact>) getHibernateTemplate()
				.findByCriteria(filter);

		if (res.isEmpty()) {
			return null;
		} else {
			return res.get(0);
		}
	}

	@Override
	public Entreprise findEntrepriseBySiret(int siret) {
		DetachedCriteria filter = DetachedCriteria.forClass(Entreprise.class);
		filter.add(Restrictions.like("numSiret", siret));
		List<Entreprise> res = (List<Entreprise>) getHibernateTemplate()
				.findByCriteria(filter);
		if (res.isEmpty()) {
			return null;
		} else {
			return res.get(0);
		}
	}

	@Override
	public List<Contact> searchContactByPhone(String phone) {
		// TODO Check
		return (List<Contact>) getHibernateTemplate()
				.findByNamedParam(
						"FROM Contact c WHERE (  elements(c.profiles).phoneNumber LIKE :phone   ) ",
						"phone", phone);
	}

	// FIXME Update with ours
	@Override
	public boolean generateContacts() {

		Contact premierContact = (Contact) ApplicationContextUtils
				.getApplicationContext().getBean("ContactExp1");

		// TODO Check no exist?
		if (this.findContactByName(premierContact.getFirstname(),
				premierContact.getLastname()) == null) {

			// FIXME
			List<Contact> contacts = new ArrayList<Contact>();

			contacts.add(premierContact);
			contacts.add((Contact) ApplicationContextUtils
					.getApplicationContext().getBean("EntrepriseExp1"));
			contacts.add((Contact) ApplicationContextUtils
					.getApplicationContext().getBean("EntrepriseExp2"));

			Session s = getHibernateTemplate().getSessionFactory()
					.getCurrentSession(); // FIXME

			getHibernateTemplate().execute(new HibernateCallback<Void>() {

				@Override
				public Void doInHibernate(Session session)
						throws HibernateException {
					for (Contact c : contacts) {
						s.save(c);
					}
					return null;
				}
			});
			// HERE get Session, save

			return true;
		} else {
			log.warning("Stub contact already created");
			return false;
		}
	}

}
