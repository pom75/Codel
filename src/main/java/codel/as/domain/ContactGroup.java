package codel.as.domain;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class ContactGroup {
	@Id
	@GeneratedValue
	private long groupId;
	private String groupName;
	@ManyToMany()
	private Set<Contact> contacts;

	public ContactGroup() {
		this.contacts = new HashSet<Contact>();
	}
	
	public ContactGroup(long groupId, String groupName, Set<Contact> contacts) {
		this.groupId = groupId;
		this.groupName = groupName;
		this.contacts = contacts;
	}

	public long getGroupId() {
		return groupId;
	}

	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
	public Set<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(Set<Contact> contacts) {
		this.contacts = contacts;
	}	
}