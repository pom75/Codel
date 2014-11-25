package codel.as.action;

import java.util.List;

import codel.as.domain.Contact;

// FIXME Pas comme ça qu'on doit faire. doit setter la liste, puis on construit dessus!!!!!!!
public class ProperAllGetContactAction extends ContactAction {
	private int nbContact;
	private List<Contact> allContacts;

	// all struts logic here

	public String execute() {
		List<Contact> allContacts = CS.getAllContacts();
		nbContact = allContacts.size();
		return "SUCCESS";
	}

	public int getNbContact() {
		return nbContact;
	}

	public void setNbContact(int nbContact) {
		this.nbContact = nbContact;
	}

	public List<Contact> getAllContacts() {
		return allContacts;
	}

	public void setAllContacts(List<Contact> allContacts) {
		this.allContacts = allContacts;
	}
	
	

}
