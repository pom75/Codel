package codel.as.dao.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import codel.as.dao.IDAOPhoneNumber;
import codel.as.domain.PhoneNumber;
import codel.as.util.HibernateUtil;

public class DAOPhoneNumber implements IDAOPhoneNumber {

	@Override
	public List getPhoneNumbersByIdContact(long idContact) {
		Session session = HibernateUtil.getSession();

		try {
			Query query = session
					.createQuery("from PhoneNumber p where p.contact.id = "
							+ idContact);
			List contacts = query.list();
			if (contacts.size() <= 0) {
				return null;
			}
			return contacts;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			session.close();
		}
	}

	@Override
	public boolean deletePhoneNumber(long id) {
		Session session = HibernateUtil.getSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			PhoneNumber p = (PhoneNumber) session.get(PhoneNumber.class, id);
			session.delete(p);

			tx.commit();

			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			if (tx != null)
				tx.rollback();
			return false;
		} finally {
			session.close();
		}
	}
}
