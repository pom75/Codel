package codel.as.action;

import codel.as.domain.Contact;

public class GetNameContactAction extends ContactAction{

	private String id;
	private String fname;
	private String lname;
	private String email;
	private String street;
	private String city;
	private String zip;
	private String country;
	private String mobileNum;
	private String officeNum;
	private String homeNum;
	private int siretNum;

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getMobileNum() {
		return mobileNum;
	}

	public void setMobileNum(String mobileNum) {
		this.mobileNum = mobileNum;
	}

	public String getOfficeNum() {
		return officeNum;
	}

	public void setOfficeNum(String officeNum) {
		this.officeNum = officeNum;
	}

	public String getHomeNum() {
		return homeNum;
	}

	public void setHomeNum(String homeNum) {
		this.homeNum = homeNum;
	}

	public int getSiretNum() {
		return siretNum;
	}

	public void setSiretNum(int siretNum) {
		this.siretNum = siretNum;
	}
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	// all struts logic here
	public String execute() {

		Contact c = CS.searchContactByName(fname, lname);

		if(c == null){
			return "ERROR";
		}else {
			fname = c.getFirstname();
			lname = c.getLastname();
			email = c.getEmail();
			street = c.getAddress().getStreet();
			city = c.getAddress().getCity();
			zip = c.getAddress().getZip();
			country = c.getAddress().getCountry();
			/* TODO How?
			 mobileNum 
			 officeNum 
			 homeNum;
			 */

			// FIXME Check entreprise
			//	 siretNum = ((Entreprise)c).getNumSiret(); // geter setter int may be bug?
			return "SUCCESS";
		}

	}

}
