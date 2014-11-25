package codel.as.dao.hibernate;

import static codel.as.domain.PhoneNumber.MOBILE_CATEGORY;
import static codel.as.domain.PhoneNumber.WORK_CATEGORY;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
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
		} catch (Exception e) {
			log.warning(e.getMessage());
			log.warning("idContact: " + idContact + ":");
			return null;
		}
	}

	// FIXME What is it?
	private void checkAndAdd(String kind, String number, Contact contact,
			Set<PhoneNumber> profiles) {
		if (number.equals("")) {
			for (PhoneNumber p : profiles) {
				if (p.getPhoneKind().equalsIgnoreCase(kind)) {
					getHibernateTemplate().delete(p);
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

				PhoneNumber p = new PhoneNumber(kind, number, contact);
				getHibernateTemplate().save(p);
				profiles.add(p);
			}
		}
	}

	// FIXME See what is it
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
	public boolean addContact(String fname, String lname, String email,
			Address address, Set<PhoneNumber> phones, int numSiret) {
		// FIXME Signature.

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
		getHibernateTemplate().save(c);
		// CHECK SOME HYBERNATE EXCEPTION
		return true;
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
			// FIXME JUST CHECL VALUE!!!
			log.info("Updating contact. version prev : " + c.getVersion());

			c.setFirstname(fname);
			c.setLastname(lname);
			c.setEmail(email);
			Address adr = new Address(street, city, zip, country);
			c.setAddress(adr);

			checkAndAdd(MOBILE_CATEGORY, home, c, c.getProfiles());
			checkAndAdd(WORK_CATEGORY, office, c, c.getProfiles());
			checkAndAdd(MOBILE_CATEGORY, mobile, c, c.getProfiles());

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

			getHibernateTemplate().update(c);

			System.out.println("version : " + c.getVersion());

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
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
	public List<Contact> searchContactByName(String fname, String lname) {
		DetachedCriteria filter = DetachedCriteria.forClass(Contact.class);
		filter.add(Restrictions.like("firstname", fname));
		filter.add(Restrictions.like("lastname", lname));
		return (List<Contact>) getHibernateTemplate().findByCriteria(filter);
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
		if (this.searchContactByName(premierContact.getFirstname(),
				premierContact.getLastname()).isEmpty()) {

			// FIXME
			List<Contact> contacts = new ArrayList<Contact>();
			List<Address> addresses = new ArrayList<Address>();
			List<PhoneNumber> phoneNumbers = new ArrayList<PhoneNumber>();

			contacts.add(premierContact);
			contacts.add((Contact) ApplicationContextUtils
					.getApplicationContext().getBean("L'entreprise"));
			contacts.add((Contact) ApplicationContextUtils
					.getApplicationContext().getBean("EntrepriseExp2"));

			((Entreprise) contacts.get(1)).setNumSiret(999999999);
			((Entreprise) contacts.get(2)).setNumSiret(888888888);

			for (int i = 1; i <= 3; i++) {
				addresses.add((Address) ApplicationContextUtils
						.getApplicationContext().getBean("AddressExp" + i));
				contacts.get(i - 1).setAddress(addresses.get(i - 1));
				getHibernateTemplate().save(contacts.get(i - 1));
			}

			for (int i = 1; i <= 9; i++) {
				phoneNumbers.add((PhoneNumber) ApplicationContextUtils
						.getApplicationContext().getBean("PhoneNumberExp" + i));
			}
			// Good idea, indexed names of bean!

			for (int i = 0; i <= 2; i++) {
				for (int j = 0; j < 3; j++) {
					phoneNumbers.get(j + 3 * i).setContact(contacts.get(i));
					contacts.get(i).getProfiles()
							.add(phoneNumbers.get(j + 3 * i));
					getHibernateTemplate().save(phoneNumbers.get(j + 3 * i));
				}
			}
			return true;
		} else {
			log.warning("Stub contact already created");
			return false;
		}
	}
}
