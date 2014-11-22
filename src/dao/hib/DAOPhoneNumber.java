package dao.hib;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import dao.IDAOPhoneNumber;
import domain.PhoneNumber;

public class DAOPhoneNumber extends HibernateDaoSupport implements IDAOPhoneNumber {
	public List getPhoneNumbersByIdContact(long idContact){
		try{
			List contacts = getHibernateTemplate().find("from PhoneNumber p where p.contact.id = " + idContact);
			if(contacts.size() <= 0){
				return null;
			}
			return contacts;
		} catch(Exception e){
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public boolean deletePhoneNumber(long id){
		try{
			PhoneNumber p = (PhoneNumber)getHibernateTemplate().get(PhoneNumber.class, id);
			getHibernateTemplate().delete(p);

			return true;
		} catch(Exception e){
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	
	
	
	
	/*
	 * code before "spring integration with hibernate"
	 */
	/*private Session myGetSession(){
		Session session = null;
		try{
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
		} catch(Exception e){
			System.out.println(e.getMessage());
		}
		return session;
	}
	
	public List getPhoneNumbersByIdContact(long idContact){
		Session session = myGetSession();

		try{
			Query query = session.createQuery("from PhoneNumber p where p.contact.id = " + idContact);
			List contacts = query.list();
			if(contacts.size() <= 0){
				return null;
			}
			return contacts;
		} catch(Exception e){
			System.out.println(e.getMessage());
			return null;
		} finally {
			session.close();
		}
	}
	
	public boolean deletePhoneNumber(long id){
		Session session = myGetSession();
		Transaction tx = null;

		try{
			tx = session.beginTransaction();
			PhoneNumber p = (PhoneNumber)session.get(PhoneNumber.class, id);
			session.delete(p);

			tx.commit();

			return true;
		} catch(Exception e){
			System.out.println(e.getMessage());
			if(tx != null)
				tx.rollback();
			return false;
		} finally {
			session.close();
		}	
	} */
}
