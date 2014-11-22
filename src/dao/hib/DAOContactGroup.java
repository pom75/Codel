package dao.hib;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.NonUniqueObjectException;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import util.ApplicationContextUtils;
import dao.IDAOContact;
import dao.IDAOContactGroup;
import domain.Contact;
import domain.ContactGroup;

public class DAOContactGroup extends HibernateDaoSupport implements IDAOContactGroup {
	public boolean createContactGroup(String name , String idContact){		
		try{
			
			ContactGroup c = (ContactGroup)ApplicationContextUtils.getApplicationContext().getBean("ContactGroup");
			c.setGroupName(name);
			
			long idNum = Integer.parseInt(idContact);
			Contact contact = (Contact)getHibernateTemplate().get(Contact.class, idNum);

			if(contact == null){
				System.out.println("Contact " + idContact + " not found");
				return false;
			}

			c.getContacts().add(contact);
			contact.getBooks().add(c);
			getHibernateTemplate().save(c);
			System.out.println("Id Contact = " + idNum + " => " + contact.getId());

			return true;
		} catch(Exception e){
			System.err.println(e.getMessage());
			return false;
		}
	}

	public List getAllContactGroup(){
		try{
			List contactGroup = getHibernateTemplate().find("select c from ContactGroup c ");
			return contactGroup;
		} catch(Exception e){
			System.out.println(e.getMessage());
			return null;
		}
	}

	public ContactGroup getContactGroupById(String id){
		try{
			List contactsGroup = getHibernateTemplate().find("select c from ContactGroup c where c.id = " + id);
			if((contactsGroup != null) && (contactsGroup.size() != 0)){
				return (ContactGroup) contactsGroup.get(0);
			}
			return null;
		} catch(Exception e){
			System.out.println(e.getMessage());
			return null;
		}	
	}

	public boolean addContact(String[] contacts, String idContactGroup){		
		try{
			long idNum = Integer.parseInt(idContactGroup);
			ContactGroup c = (ContactGroup)getHibernateTemplate().get(ContactGroup.class, idNum);
			if(c == null){
				System.out.println("Group contact " + idContactGroup + " not found");
				return false;
			}

			ApplicationContext context = ApplicationContextUtils.getApplicationContext();
			IDAOContact daoContact = (IDAOContact)context.getBean("DAOContact");

			if(contacts != null){
				for(String idContact : contacts){	
					try{
						long id = Integer.parseInt(idContact);
						Contact contact = (Contact) getHibernateTemplate().get(Contact.class, id);
						if(contact == null){
							System.out.println("Cannot find the contact " + idContact);
							return false;
						}
						contact.getBooks().add(c);
						c.getContacts().add(contact);
						System.out.println("Dans le foreach DAOCOntactGroup methode addContact idContact = " + idContact);
					} catch(NonUniqueObjectException e) {}
				}
				System.out.println("Add contact termine !!!");
				getHibernateTemplate().update(c);
			}
			return true;
		} catch(Exception e){
			System.out.println("========================================= Erreur DAOContactGroup AddContact ============================================");
			e.printStackTrace();
			return false;
		}	
	}

	public List getContactGroupByIdContactGroup(final String idContactGroup){
		return (List)getHibernateTemplate().executeFind(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException{
				try{
					//ContactGroup cg = new ContactGroup();
					ContactGroup cg = (ContactGroup)ApplicationContextUtils.getApplicationContext().getBean("ContactGroup");
					long id = Integer.parseInt(idContactGroup);
					
					
					System.out.println("cg.getGroupId() : " + cg.getGroupId());
					System.out.println("cg.getGroupName()" + cg.getGroupName());
					
					
					
					
					
					cg.setGroupId(id);
					List listContactGroup = session.createCriteria(ContactGroup.class).add(Example.create(cg)).list();
					for(Object g : listContactGroup){
						if(String.valueOf(((ContactGroup)g).getGroupId()).equals(idContactGroup)){
							cg = (ContactGroup)g;
							break;
						}
					}
					List contacts = new ArrayList(cg.getContacts());
					return contacts;
				} catch(Exception e){
					System.out.println(e.getMessage());
					return null;
				}	
			}
		});
	}
	
	public boolean deleteContactGroup(ContactGroup contactGroup){		
		try{
			getHibernateTemplate().delete(contactGroup);
			return true;
		} catch(Exception e){
			e.printStackTrace();
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

	public boolean createContactGroup(String name , String idContact){		
		Session session = myGetSession();
		Transaction tx = null;

		try{
			tx = session.beginTransaction();
			ContactGroup c = new ContactGroup();
			session.save(c);
			c.setGroupName(name);
			long idNum = Integer.parseInt(idContact);
			Contact contact = (Contact)session.get(Contact.class, idNum);

			if(contact == null){
				System.out.println("Contact " + idContact + " not found");
				return false;
			}
			contact.getBooks().add(c);
			System.out.println("Id Contact = " + idNum + " => " + contact.getId());

			tx.commit();			
			return true;
		} catch(Exception e){
			System.err.println(e.getMessage());
			if(tx != null)
				tx.rollback();
			return false;
		} finally {
			session.close();
		}	
	}

	public List getAllContactGroup(){
		Session session = myGetSession();

		try{
			Query query = session.createQuery(
					"select c from ContactGroup c ");
			List contactGroup = query.list();
			return contactGroup;
		} catch(Exception e){
			System.out.println(e.getMessage());
			return null;
		} finally {
			session.close();
		}	
	}

	public ContactGroup getContactGroupById(String id){
		Session session = myGetSession();

		try{
			Query query = session.createQuery("select c from ContactGroup c where c.id = " + id);
			List contactsGroup = query.list();
			if((contactsGroup != null) && (contactsGroup.size() != 0)){
				return (ContactGroup) contactsGroup.get(0);
			}
			return null;
		} catch(Exception e){
			System.out.println(e.getMessage());
			return null;
		} finally {
			session.close();
		}	
	}

	public boolean addContact(String[] contacts, String idContactGroup){		
		Session session = myGetSession();
		Transaction tx = null;

		try{
			long idNum = Integer.parseInt(idContactGroup);
			tx = session.beginTransaction();
			ContactGroup c = (ContactGroup)session.get(ContactGroup.class, idNum);
			if(c == null){
				System.out.println("Group contact " + idContactGroup + " not found");
				return false;
			}

			ApplicationContext context = ApplicationContextUtils.getApplicationContext();
			DAOContact daoContact = (DAOContact)context.getBean("DAOContact");

			if(contacts != null){
				for(String idContact : contacts){	
					try{
						//						Object[] result = daoContact.getContactById(idContact);
						//						Contact contact = (Contact) result[0];
						long id = Integer.parseInt(idContact);
						Contact contact = (Contact) session.load(Contact.class, id);
						contact.getBooks().add(c);
						c.getContacts().add(contact);
						System.out.println("Dans le foreach DAOCOntactGroup methode addContact idContact = " + idContact);
						session.save(c);
						//tx.commit();
					} catch(NonUniqueObjectException e) {}
				}
				tx.commit();
			}
			return true;
		} catch(Exception e){
			System.out.println("========================================= Erreur DAOContactGroup AddContact ============================================");
			e.printStackTrace();
			if(tx != null)
				tx.rollback();
			return false;
		} finally {
			System.out.println("Add contact termine !!!");
			session.close();
		}	


	}

	public List getContactGroupByIdContactGroup(String idContactGroup){
		Session session = myGetSession();

		try{
			Query query = session.createQuery(
					"select elements(c.contacts) from ContactGroup c where c.groupId = " + idContactGroup);
			//List contactGroup = query.list();
			ContactGroup cg = new ContactGroup();
			long id = Integer.parseInt(idContactGroup);
			cg.setGroupId(id);
			List listContactGroup = session.createCriteria(ContactGroup.class).add(Example.create(cg)).list();
			cg = (ContactGroup)listContactGroup.get(0);
			List contactGroup = new ArrayList(cg.getContacts());
			return contactGroup;
		} catch(Exception e){
			System.out.println(e.getMessage());
			return null;
		} finally {
			session.close();
		}	
	} */
}
