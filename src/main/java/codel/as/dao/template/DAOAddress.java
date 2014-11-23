package codel.as.dao.template;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import codel.as.dao.IDAOAddress;
import codel.as.domain.Address;

public class DAOAddress extends HibernateDaoSupport implements IDAOAddress {
	
	@SuppressWarnings({"rawtypes", "unchecked"})
	public Address getAddressById(final long id){	
		//FIXME see genetic for hibernate callbacl
		//FIXME Try Lambda:::. (once test there)
		return (Address)getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException{
				try{	
					// ¤hib:crit
					List addresses = session.createCriteria(Address.class).add(Restrictions.idEq(id)).list();

					if((addresses != null) && (! addresses.isEmpty())){
						return (Address) addresses.get(0);
					}
					return null;
				} catch(Exception e){
					System.out.println(e.getMessage());
					return null;
				}
			}
		});
	}

}
