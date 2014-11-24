package codel.as.dao.template;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import codel.as.dao.IDAOContact;
import codel.as.dao.IDAOContactGroup;
import codel.as.dao.IDAOPhoneNumber;
import codel.as.domain.Address;
import codel.as.domain.Contact;
import codel.as.domain.ContactGroup;
import codel.as.domain.Entreprise;
import codel.as.domain.PhoneNumber;
import codel.as.util.ApplicationContextUtils;


// FIXME Try togenetic
@Repository
@Transactional
@SuppressWarnings({"rawtypes", "unchecked"})
public class DAOContact extends HibernateDaoSupport implements IDAOContact {

	// http://stackoverflow.com/questions/8977121/advantages-of-using-hibernate-callback

	public List searchContact(final String fname, final String lname,
			final String email, final Address address, final String home,
			final String office, final String mobile) {
		return (List) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						try {
							// ¤hib:crit
							//FIXME extract helper method
							Criteria criteria = session
									.createCriteria(Contact.class);
							if (!fname.isEmpty()) {
								criteria.add(Restrictions.like("firstname",
										fname, MatchMode.ANYWHERE));
							}
							if (!lname.isEmpty()) {
								criteria.add(Restrictions.like("lastname",
										lname, MatchMode.ANYWHERE));
							}
							if (!email.isEmpty()) {
								criteria.add(Restrictions.like("email", email,
										MatchMode.ANYWHERE));
							}
							if (!address.getStreet().isEmpty()) {
								criteria.add(Restrictions.like(
										"address.street", address.getStreet(),
										MatchMode.ANYWHERE));
							}
							if (!address.getZip().isEmpty()) {
								criteria.add(Restrictions.like("address.zip",
										address.getZip(), MatchMode.ANYWHERE));
							}
							if (!address.getCity().isEmpty()) {
								criteria.add(Restrictions.like("address.city",
										address.getCity(), MatchMode.ANYWHERE));
							}
							if (!address.getCountry().isEmpty()) {
								criteria.add(Restrictions.like(
										"address.country",
										address.getCountry(),
										MatchMode.ANYWHERE));
							}

							List contacts = criteria.list();

							if (home.isEmpty() && office.isEmpty()
									&& mobile.isEmpty()) {
								return contacts;
							}

							List toRemove = new ArrayList();

							ApplicationContext context = ApplicationContextUtils
									.getApplicationContext();
							IDAOPhoneNumber daoP = (IDAOPhoneNumber) context
									.getBean("DAOPhoneNumber");

							for (int i = 0; i < contacts.size(); i++) {
								Contact c = (Contact) contacts.get(i);

								List pns = daoP.getPhoneNumbersByIdContact(c
										.getId());
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
						}
					}
				});
	}

	public Object[] getContactById(String id) {
		try {
			// ¤hib:sql
			List contacts = getHibernateTemplate().find(
					"select c, a from Contact c, Address a where c.id = " + id
							+ " and c.address= a");
			if ((contacts != null) && (!contacts.isEmpty())) {
				return (Object[]) contacts.get(0);
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List getAllContacts() {
		return (List) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						try {
							// ¤hib:hql
							Query query = session
									.createQuery("from Contact c left join fetch c.address address");
							List contacts = query.setCacheable(true).list();
							return contacts;
						} catch (Exception e) {
							e.printStackTrace();
							return null;
						}
					}
				});
	}

	public List getContactGroupByIdContact(String idContact) {
		try {
			long idNum = Integer.parseInt(idContact);
			Contact c = (Contact) getHibernateTemplate().get(Contact.class,
					idNum);
			// FIXME??

			List contactGroup = getHibernateTemplate().find(
					"select elements(c.books) from Contact c where c.id = "
							+ idContact);

			return contactGroup;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("================================ idContact = "
					+ idContact
					+ "======================================================");
			return null;
		}
	}

	//FIXME Kill??? with?
	public boolean generateContacts() {
		try {
			List<Contact> contacts = new ArrayList<Contact>();
			List<Address> addresses = new ArrayList<Address>();
			List<PhoneNumber> phoneNumbers = new ArrayList<PhoneNumber>();

			contacts.add((Contact) ApplicationContextUtils
					.getApplicationContext().getBean("ContactExp1"));
			contacts.add((Contact) ApplicationContextUtils
					.getApplicationContext().getBean("EntrepriseExp1"));
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
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

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
				PhoneNumber p = (PhoneNumber) ApplicationContextUtils
						.getApplicationContext().getBean("PhoneNumber");
				p.setPhoneKind(kind);
				p.setPhoneNumber(number);
				p.setContact(contact);
				getHibernateTemplate().save(p);
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

	@Transactional(readOnly = false)
	public boolean addContact(String fname, String lname, String email,
			Address address, Set<PhoneNumber> profiles, int numSiret) {
		
		Contact c;
		if(numSiret <= 0){
			c = new Contact();
		} else {
			c = new Entreprise();
			(( Entreprise )c).setNumSiret(numSiret);
		}
		
		c.setFirstname(fname);
		c.setLastname(lname);
		c.setEmail(email);
		c.setAddress(address);
		c.setProfiles(profiles);
		getHibernateTemplate().setCheckWriteOperations(false);
		if(numSiret <= 0){
			getHibernateTemplate().save(c);
		}else{
			getHibernateTemplate().save((( Entreprise )c));
		}
		
		try {
			
			
			/*
			if (numSiret <= 0) {
				c = (Contact) ApplicationContextUtils.getApplicationContext()
						.getBean("ContactConstrWithArgs");
			} else {
				c = (Entreprise) ApplicationContextUtils
						.getApplicationContext().getBean("Entreprise");
				((Entreprise) c).setNumSiret(numSiret);
			}

			if (address != null) {
				getHibernateTemplate().save(address);
			}

			c.setFirstname(fname);
			c.setLastname(lname);
			c.setEmail(email);
			c.setAddress(address);
			getHibernateTemplate().save(c);

			if (profiles != null) {
				for (PhoneNumber profile : profiles) {
					profile.setContact(c);
					c.getProfiles().add(profile);
					getHibernateTemplate().save(profile);
				}
			}
			*/

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteContact(long id) {
		long idNum = id;

		try {
			Contact c = (Contact) getHibernateTemplate().get(Contact.class,
					idNum);
			c.getProfiles().clear();

			ApplicationContext context = ApplicationContextUtils
					.getApplicationContext();
			IDAOContactGroup daoContactGroup = (IDAOContactGroup) context
					.getBean("DAOContactGroup");
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

	@Override
	public Contact getContact(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean modifyContact(Contact c, String fname, String lname,
			String email, String street, String zip, String city,
			String country, String home, String office, String mobile,
			int siretnum) {
		try {
			System.out.println("version prev : " + c.getVersion());

			c.setFirstname(fname);
			c.setLastname(lname);
			c.setEmail(email);
			c.getAddress().setStreet(street);
			c.getAddress().setZip(zip);
			c.getAddress().setCity(city);
			c.getAddress().setCountry(country);
			checkAndAdd("home", home, c, c.getProfiles());
			checkAndAdd("office", office, c, c.getProfiles());
			checkAndAdd("mobile", mobile, c, c.getProfiles());

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

}
