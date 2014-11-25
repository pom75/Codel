package codel.as.action;

import java.util.List;

import codel.as.domain.Contact;

public class AllGetContactAction extends ContactAction {
	private String html = "";
	
	
	
	// all struts logic here
		public String getHtml() {
		return html;
	}



	public void setHtml(String html) {
		this.html = html;
	}



		public String execute() {
			List<Contact> list = CS.getAllContacts();
			
			for(int i = 0; i<list.size();i++){
				html += "<p>Id : "+list.get(i).getId()+ "<p>";
				html += "<p>Nom : "+list.get(i).getLastname()+ "<p>";	
				html += "<p>Prenom : "+list.get(i).getFirstname()+ "<p>";
				html += "<p>Email : "+list.get(i).getEmail()+ "<p>";
				html += "<p>Street : "+list.get(i).getAddress().getStreet()+ "<p>";
				html += "<p>City : "+list.get(i).getAddress().getCity()+ "<p>";
				html += "<p>Zip : "+list.get(i).getAddress().getZip()+ "<p>";
				html += "<p>Country : "+list.get(i).getAddress().getCountry()+ "<p>";
				html += "<br><br><br>";		
			}
			
				return "SUCCESS";
			}

}
