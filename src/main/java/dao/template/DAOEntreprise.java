package dao.template;

import java.util.List;

import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import dao.IDAOEntreprise;
import domain.Entreprise;

public class DAOEntreprise extends HibernateDaoSupport implements
		IDAOEntreprise {

	@SuppressWarnings("rawtypes")
	public Entreprise getEntrepriseByIdContact(long idContact) {
		try {
			List entreprises = getHibernateTemplate().find(
					"from Entreprise e where e.id = " + idContact);
			if (entreprises.isEmpty()) {
				return null;
			}
			return ((Entreprise) entreprises.get(0));
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

}
