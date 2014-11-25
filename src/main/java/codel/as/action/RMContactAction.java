package codel.as.action;

public class RMContactAction extends ContactAction {

	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	// all struts logic here

	public String execute() {
		try {
			CS.deleteContact(Long.parseLong(id));
			// better handling exceptino
			// FIXME Affichier message..
			return "SUCCESS";
		} catch (Exception e) {
			return "ERROR";
		}

	}

}
