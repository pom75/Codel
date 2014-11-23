package codel.as.dao.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import codel.as.dao.IDAOEntreprise;
import codel.as.domain.Entreprise;
import codel.as.util.HibernateUtil;

public class DAOEntreprise implements IDAOEntreprise {

	public Entreprise getEntrepriseByIdContact(long idContact) {

		Session session = HibernateUtil.getSession();

		try {
			Query query = session.createQuery("from Entreprise e where e.id = "
					+ idContact);
			List entreprises = query.list();
			if (entreprises.size() <= 0) {
				return null;
			}
			return ((Entreprise) entreprises.get(0));
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			session.close();
		}
	}
}
