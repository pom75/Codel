package dao.template;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import dao.IDAOAddress;
import domain.Address;

public class DAOAddress extends HibernateDaoSupport implements IDAOAddress {
	
	@SuppressWarnings({"rawtypes"})
	public Address getAddressById(final long id){	
		//FIXME see genetic for hibernate callbacl
		return (Address)getHibernateTemplate().executeFind(new HibernateCallback<Object>(){
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
