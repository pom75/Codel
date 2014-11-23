package domain;

import java.util.HashSet;
import java.util.Set;


public class Contact {
	private long id;
	private int version;
	private String firstname;
	private String lastname;
	private String email;
	private Address address;
	private Set<PhoneNumber> profiles;
	private Set<ContactGroup> books;
	
	public int getVersion(){
		return version;
	}
	
	public Contact(long id, String firstname, String lastname, String email, Address address, Set<PhoneNumber> profiles, Set<ContactGroup> books){
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.address = address;
		this.profiles = profiles;
		this.books = books;
	}

	public Contact(long id, String firstname, String lastname, String email, Address address){
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.address = address;
		this.profiles = new HashSet<PhoneNumber>();
		this.books = new HashSet<ContactGroup>();
	}
	
	public Contact() {
		profiles = new HashSet<PhoneNumber>();
		books = new HashSet<ContactGroup>();
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public Set<PhoneNumber> getProfiles() {
		return profiles;
	}
	public void setProfiles(Set<PhoneNumber> profiles) {
		this.profiles = profiles;
	}
	
	public Set<ContactGroup> getBooks() {
		return books;
	}

	public void setBooks(Set<ContactGroup> books) {
		this.books = books;
	}
}