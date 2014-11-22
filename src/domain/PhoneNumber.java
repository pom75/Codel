package domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class PhoneNumber {
	@Id
	@GeneratedValue
	private long id;
	private String phoneKind;
	private String phoneNumber;
	@ManyToOne
	private Contact contact;
	
	public PhoneNumber() {}
	
	public PhoneNumber(long id, String phoneKind, String phoneNumber, Contact contact) {
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
	public Contact getContact(){
		return contact;
	}
	public void setContact(Contact contact){
		this.contact = contact;
	}
}