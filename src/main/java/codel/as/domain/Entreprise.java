package codel.as.domain;

import java.util.Set;

public class Entreprise extends Contact {
	private int numSiret;

	public Entreprise() {
	}

	
	public Entreprise(String firstname, String lastname, String email,
			Address address, int numSiret) {
		super(firstname, lastname, email, address);
		this.numSiret = numSiret;
	}



	public Entreprise(String firstname, String lastname, String email,
			Address address, Set<PhoneNumber> profiles, int numSiret) {
		super(firstname, lastname, email, address,profiles);
		this.numSiret = numSiret;
	}


	public int getNumSiret() {
		return numSiret;
	}

	public void setNumSiret(int numSiret) {
		this.numSiret = numSiret;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + numSiret;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Entreprise other = (Entreprise) obj;
		if (numSiret != other.numSiret)
			return false;
		return true;
	}



	@Override
	public String toString() {
		return "Entreprise [numSiret=" + numSiret + ", id=" + id + ", version="
				+ version + ", firstname=" + firstname + ", lastname="
				+ lastname + ", email=" + email + ", address=" + address
				+ ", profiles=" + profiles + ", books=" + books + "]";
	}

	
	
}
