package codel.as.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import codel.as.dao.IDAOContact;
import codel.as.domain.Address;
import codel.as.domain.Contact;
import codel.as.domain.PhoneNumber;
import codel.as.util.Messages;


/**
 * OLD do not respect contract anymore
 * Should be killed
 *
 */
abstract public class DAOContact// implements IDAOContact
{

	public DAOContact(){
	}


	/**
	 * Suppresion d'un contact a partir de son identifiant
	 * @param id
	 * @return vrai si la suppression a bien ete effectuee
	 */
	public boolean deleteContact(long id){
		Connection con = null;
		try{
			Class.forName(Messages.getString("driver")); 
			con = DriverManager.getConnection(Messages.getString("database"), Messages.getString("username"), Messages.getString("password")); 
			Statement stmt = con.createStatement();
			String request = "DELETE FROM contacts WHERE id = "+id; 
			stmt.executeUpdate(request);
			stmt.close();
			con.close();
			
		} catch( Exception e ){
			e.printStackTrace();
			return false;
		}

		return true;
	}

	/**
	 * Recuperation d'un contact a partir de son identifiant
	 * @param id
	 * @return
	 */
	public Contact getContactById(long id){
		ResultSet rec = null;
		Contact contact = new Contact();
		Connection con = null;
		try{
			Class.forName(Messages.getString("driver")); 
			con = DriverManager.getConnection(Messages.getString("database"), Messages.getString("username"), Messages.getString("password")); 
			Statement stmt = con.createStatement();
			rec = stmt.executeQuery("SELECT * FROM contacts WHERE id = "+id); 

			while (rec.next()) {
				contact.setId(Long.parseLong(rec.getString("id"))); 
				contact.setFirstname(rec.getString("firstname")); 
				contact.setLastname(rec.getString("lastname")); 
				contact.setEmail(rec.getString("email")); 
			}

			stmt.close();
			rec.close();
			con.close();

		} catch( Exception e ){
			e.printStackTrace();
		}
		return contact;
	}

	/**
	 * Methode qui modifie les coordonees d'un contact
	 * @param id
	 * @param firstname
	 * @param alstname
	 * @param email
	 * @return
	 */
	public boolean modifyContact(long id, String firstname, String lastname, String email){
		boolean success = false;
		Connection con = null;
		try{
			Class.forName(Messages.getString("driver")); 
			con = DriverManager.getConnection(Messages.getString("database"), Messages.getString("username"), Messages.getString("password")); 
			Statement stmt = con.createStatement();
			String sqlFirstName = "UPDATE contacts SET firstname = "+"'"+firstname+"'"+" WHERE id = "+id ; 
			String sqlLastName = "UPDATE contacts SET lastname = "+"'"+lastname+"'"+" WHERE id = "+id ; 
			String sqlEmail = "UPDATE contacts SET email = "+"'"+email+"'"+" WHERE id = "+id ; 

			if(firstname != "")stmt.executeUpdate(sqlFirstName); 
			if(lastname != "")stmt.executeUpdate(sqlLastName); 
			if(email != "")stmt.executeUpdate(sqlEmail); 

			success = true;
			stmt.close();
			con.close();

		} catch( Exception e ){
			e.printStackTrace();
		}
		return success;
	}

	/**
	 * Renvoit la liste des contacts correspondant au prenom firstname
	 * @param firstname
	 * @return
	 */
	public ArrayList<Contact> getContactByFirstName(String firstname){

		ArrayList<Contact> contacts = new ArrayList<Contact>();

		ResultSet rec = null;
		Connection con = null;
		try{
			Class.forName(Messages.getString("driver")); 
			con = DriverManager.getConnection(Messages.getString("database"), Messages.getString("username"), Messages.getString("password")); 
			Statement stmt = con.createStatement();
			rec = stmt.executeQuery("SELECT * FROM contacts WHERE firstname = "+"'"+firstname+"'"); 

			while (rec.next()) {
				Contact contact = new Contact();
				contact.setId(Long.parseLong(rec.getString("id"))); 
				contact.setFirstname(rec.getString("firstname"));
				contact.setLastname(rec.getString("lastname"));
				contact.setEmail(rec.getString("email")); 
				
				contacts.add(contact);
			}

			stmt.close();
			rec.close();
			con.close();

		} catch( Exception e ){
			e.printStackTrace();
		}
		return contacts;
	}


	/**
	 * Renvoit la liste des contacts correspondant au nom lastname
	 * @param lastname
	 * @return
	 */
	public ArrayList<Contact> getContactByLastName(String lastname){

		ArrayList<Contact> contacts = new ArrayList<Contact>();

		ResultSet rec = null;
		Connection con = null;
		try{
			Class.forName(Messages.getString("driver")); 
			con = DriverManager.getConnection(Messages.getString("database"), Messages.getString("username"), Messages.getString("password")); 
			Statement stmt = con.createStatement();
			rec = stmt.executeQuery("SELECT * FROM contacts WHERE lastname = "+"'"+lastname+"'"); 

			while (rec.next()) {
				Contact contact = new Contact();
				contact.setId(Long.parseLong(rec.getString("id"))); 
				contact.setFirstname(rec.getString("firstname")); 
				contact.setLastname(rec.getString("lastname")); 
				contact.setEmail(rec.getString("email")); 
				contacts.add(contact);
			}

			stmt.close();
			rec.close();
			con.close();

		} catch( Exception e ){
			e.printStackTrace();
		}
		return contacts;
	}

	/**
	 * Renvoit la liste des contacts correspondant a l'email email
	 * @param email
	 * @return
	 */
	public ArrayList<Contact> getContactByEmail(String email){
		ArrayList<Contact> contacts = new ArrayList<Contact>();

		ResultSet rec = null;
		Connection con = null;
		try{
			Class.forName(Messages.getString("driver")); 
			con = DriverManager.getConnection(Messages.getString("database"), Messages.getString("username"), Messages.getString("password")); 
			Statement stmt = con.createStatement();
			rec = stmt.executeQuery("SELECT * FROM contacts WHERE email = "+"'"+email+"'"); 

			while (rec.next()) {
				Contact contact = new Contact();
				contact.setId(Long.parseLong(rec.getString("id")));
				contact.setFirstname(rec.getString("firstname"));
				contact.setLastname(rec.getString("lastname")); 
				contact.setEmail(rec.getString("email"));
				contacts.add(contact);
			}

			stmt.close();
			rec.close();
			con.close();

		} catch( Exception e ){
			e.printStackTrace();
		}
		return contacts;
	}



	public boolean addContact(String fname, String lname, String email) {
		Contact contact = new Contact();
		contact.setFirstname(fname);
		contact.setLastname(lname);
		contact.setEmail(email);
		
		Connection con = null;
		try{
			Class.forName(Messages.getString("driver")); 
			con = DriverManager.getConnection(Messages.getString("database"), Messages.getString("username"), Messages.getString("password")); 
			Statement stmt = con.createStatement();
			String request = "INSERT INTO contacts(firstname,lastname,email) VALUES('"+fname+"','"+lname+"','"+email+"')";
			stmt.executeUpdate(request);
			stmt.close();   	
			con.close();
			
			return true;
		} catch( Exception e ){
			e.printStackTrace();
			return false;
		}
	}

}