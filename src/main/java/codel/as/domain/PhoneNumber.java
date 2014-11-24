package codel.as.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class PhoneNumber {

	public static final String HOME_CATEGORY = "home",
			MOBILE_CATEGORY = "mobile", WORK_CATEGORY = "work";

	@Id
	@GeneratedValue
	private long id;
	private String phoneKind;
	private String phoneNumber;

	@ManyToOne
	private Contact contact;

	@Override
	public String toString() {
		return "PhoneNumber [id=" + id + ", phoneKind=" + phoneKind
				+ ", phoneNumber=" + phoneNumber + ", contact=" + contact + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((contact == null) ? 0 : contact.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result
				+ ((phoneKind == null) ? 0 : phoneKind.hashCode());
		result = prime * result
				+ ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PhoneNumber other = (PhoneNumber) obj;
		if (contact == null) {
			if (other.contact != null)
				return false;
		} else if (!contact.equals(other.contact))
			return false;
		if (id != other.id)
			return false;
		if (phoneKind == null) {
			if (other.phoneKind != null)
				return false;
		} else if (!phoneKind.equals(other.phoneKind))
			return false;
		if (phoneNumber == null) {
			if (other.phoneNumber != null)
				return false;
		} else if (!phoneNumber.equals(other.phoneNumber))
			return false;
		return true;
	}

	public PhoneNumber() {
	}

	public PhoneNumber(long id, String phoneKind, String phoneNumber,
			Contact contact) {
		this.id = id;
		this.phoneKind = phoneKind;
		this.phoneNumber = phoneNumber;
		this.contact = contact;
	}

	public PhoneNumber(String phoneKind, String phoneNumber, Contact contact) {
		this.phoneKind = phoneKind;
		this.phoneNumber = phoneNumber;
		this.contact = contact;
	}

	public PhoneNumber(String phoneKind, String phoneNumber) {
		this.phoneKind = phoneKind;
		this.phoneNumber = phoneNumber;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPhoneKind() {
		return phoneKind;
	}

	public void setPhoneKind(String phoneKind) {
		this.phoneKind = phoneKind;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public static PhoneNumber newHome(String num) {
		PhoneNumber tmp = new PhoneNumber();
		tmp.setPhoneNumber(num);
		tmp.setPhoneNumber(HOME_CATEGORY);
		return tmp;
	}

	public static PhoneNumber newWork(String num) {
		PhoneNumber tmp = new PhoneNumber();
		tmp.setPhoneNumber(num);
		tmp.setPhoneNumber(WORK_CATEGORY);
		return tmp;
	}

	public static PhoneNumber newMobile(String num) {
		PhoneNumber tmp = new PhoneNumber();
		tmp.setPhoneNumber(num);
		tmp.setPhoneNumber(MOBILE_CATEGORY);
		return tmp;
	}
}
