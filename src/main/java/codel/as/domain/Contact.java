package codel.as.domain;

import java.util.HashSet;
import java.util.Set;

// LATER: JPA annotate it.
public class Contact {

	protected long id;
	protected int version;
	
	protected String firstname;
	protected String lastname;
	protected String email;
	protected Address address;
	protected Set<PhoneNumber> profiles;
	// FIXME HASHSET
	protected Set<ContactGroup> books;

	public int getVersion() {
		return version;
	}

	public Contact(String firstname, String lastname, String email,
			Address address, Set<PhoneNumber> profiles, Set<ContactGroup> books) {
		this(firstname, lastname, email, address, profiles);
		this.books = books == null?new HashSet<ContactGroup>(): books;
	}

	public Contact(String firstname, String lastname, String email,
			Address address, Set<PhoneNumber> profiles) {
		this(firstname, lastname, email, address);
		this.profiles = profiles == null?  new HashSet<PhoneNumber>():profiles;
	}

	public Contact(String firstname, String lastname, String email,
			Address address) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.address = address;
		this.profiles = new HashSet<PhoneNumber>();
		this.books = new HashSet<ContactGroup>();
	}

	public Contact() {
		this.profiles = new HashSet<PhoneNumber>();
		this.books = new HashSet<ContactGroup>();
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((books == null) ? 0 : books.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result
				+ ((firstname == null) ? 0 : firstname.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result
				+ ((lastname == null) ? 0 : lastname.hashCode());
		result = prime * result
				+ ((profiles == null) ? 0 : profiles.hashCode());
		result = prime * result + version;
		return result;
	}

	@Override
	public String toString() {
		return "Contact [id=" + id + ", version=" + version + ", firstname="
				+ firstname + ", lastname=" + lastname + ", email=" + email
				+ ", address=" + address + ", profiles=" + profiles
				+ ", books=" + books + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contact other = (Contact) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (books == null) {
			if (other.books != null)
				return false;
		} else if (!books.equals(other.books))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstname == null) {
			if (other.firstname != null)
				return false;
		} else if (!firstname.equals(other.firstname))
			return false;
		if (id != other.id)
			return false;
		if (lastname == null) {
			if (other.lastname != null)
				return false;
		} else if (!lastname.equals(other.lastname))
			return false;

		return true;
	}

	//FIXME check
	public String toHtmlFragment() {
		// Ugly mais isolé compris....
		return 	 "<p>Nom : "+getLastname()+ "</p>"+	
				"<p>Prenom : "+getFirstname()+ "</p>"+
				"<p>Email : "+getEmail()+ "</p>"+
				"<p>City : "+getAddress().getCity()+ "</p>"+
				"<p>Zip : "+getAddress().getZip()+ "</p>"+
				"<p>Country : "+getAddress().getCountry()+ "<p/><br/>";	
		
		
	}
}