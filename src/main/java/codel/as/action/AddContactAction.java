package codel.as.action;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.opensymphony.xwork2.ModelDriven;

import codel.as.domain.Address;
import codel.as.domain.PhoneNumber;
import codel.as.service.ContactService;
import codel.as.service.LoginService;
import codel.as.util.PathUtils;

public class AddContactAction extends ContactAction {
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
	private String siretNum;

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

	public String getSiretNum() {
		return siretNum;
	}

	public void setSiretNum(String siretNum) {
		this.siretNum = siretNum;
	}

	// all struts logic here
	public String execute() {
		if (fname.isEmpty() || lname.isEmpty() || email.isEmpty()
				|| StringUtils.isNumericSpace(siretNum)) {
			return "ERROR";
		} else {
			// FIXME PAs eu le temps d'extraire addresse.
			
			int numSiret = (siretNum == null) ? -1 : Integer.valueOf(siretNum);
			Address address = (street.isEmpty() && zip.isEmpty()
					&& city.isEmpty() && country.isEmpty()) ? null
					: new Address(street, city, zip, country);
			
			
			// FIXME: tu devrais extraire une méthode utils dans Phone nombre:
			// createSet, qui te prends les trois chaine (et ignore les nuls) 
			Set<PhoneNumber> profiles;
			if (homeNum.isEmpty() && officeNum.isEmpty() && mobileNum.isEmpty()) {
				profiles = null;
			} else {
				profiles = new HashSet<PhoneNumber>();
				if (!homeNum.isEmpty())
					profiles.add(PhoneNumber.newHome(homeNum));

				if (!officeNum.isEmpty())
					profiles.add(PhoneNumber.newHome(officeNum));

				if (!mobileNum.isEmpty())
					profiles.add(PhoneNumber.newHome(mobileNum));
			}

			CS.addContact(fname, lname, email, address, profiles, numSiret);

			return "SUCCESS";
		}

	}
}
