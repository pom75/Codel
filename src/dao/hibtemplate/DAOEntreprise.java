package dao.hibtemplate;

import java.util.List;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import dao.IDAOEntreprise;
import domain.Entreprise;

public class DAOEntreprise extends HibernateDaoSupport implements
		IDAOEntreprise {
	public Entreprise getEntrepriseByIdContact(long idContact) {
		try {
			List<Entreprise> entreprises = getHibernateTemplate().find(
					"from Entreprise e where e.id = " + idContact);
			if (entreprises.size() <= 0) {
				return null;
			}
			return ((Entreprise) entreprises.get(0));
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

}
