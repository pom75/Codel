package codel.as.action;

import java.util.List;

import codel.as.domain.Contact;

// FIXME Pas comme ça qu'on doit faire. doit setter la liste, puis on construit dessus!!!!!!!
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

		StringBuffer sb = new StringBuffer("");
		// WAS UGGLY
		// TODO create tag..
		for (Contact c : list) {
			sb.append(c.toHtmlFragment());
		}

		html = sb.toString();

		return "SUCCESS";
	}

}
