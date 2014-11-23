package codel.as.dao.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import codel.as.dao.IDAOAddress;
import codel.as.domain.Address;
import codel.as.util.HibernateUtil;

public class DAOAddress implements IDAOAddress {

	@Override
	public Address getAddressById(long id) {
		Session session = HibernateUtil.getSession();

		try {
			// Query query =
			// session.createQuery("select a from Address a where a.id = " +
			// id);
			// List addresses = query.list();

			List addresses = session.createCriteria(Address.class)
					.add(Restrictions.idEq(id)).list();

			if ((addresses != null) && (addresses.size() != 0)) {
				return (Address) addresses.get(0);
			}
			return null;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			session.close();
		}
	}
}
