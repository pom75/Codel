package codel.as.dao.template;

import java.util.Collections;
import java.util.List;

import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import codel.as.dao.IDAOPhoneNumber;
import codel.as.domain.PhoneNumber;

// TOKILL
// FIXME Maybe merge with contact??
@SuppressWarnings({"rawtypes"})
public class DAOPhoneNumber extends HibernateDaoSupport implements
		IDAOPhoneNumber {
	
	public List getPhoneNumbersByIdContact(long idContact) {
			List contacts = getHibernateTemplate().find(
					"from PhoneNumber p where p.contact.id = " + idContact);
			if (contacts.isEmpty()) {
				return Collections.EMPTY_LIST;
			}
			return contacts;
	}

	public boolean deletePhoneNumber(long id) {
			PhoneNumber p = (PhoneNumber) getHibernateTemplate().get(
					PhoneNumber.class, id);
			getHibernateTemplate().delete(p);

			return true;
	}
}
