package dao.template;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import dao.IDAOPhoneNumber;
import domain.PhoneNumber;

public class DAOPhoneNumber extends HibernateDaoSupport implements
		IDAOPhoneNumber {
	public List getPhoneNumbersByIdContact(long idContact) {
		try {
			List contacts = getHibernateTemplate().find(
					"from PhoneNumber p where p.contact.id = " + idContact);
			if (contacts.size() <= 0) {
				return null;
			}
			return contacts;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	public boolean deletePhoneNumber(long id) {
		try {
			PhoneNumber p = (PhoneNumber) getHibernateTemplate().get(
					PhoneNumber.class, id);
			getHibernateTemplate().delete(p);

			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
}
