/**
 * 
 */
package com.academics.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Amir
 *
 */

@Entity
public class FacultyMember {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private String title;
	private String firstName;
	private String lastName;
	private String emailAddress;
	
	public FacultyMember() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FacultyMember(String title, String firstName, String lastName, String emailAddress) {
		super();
		this.title = title;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	@Override
	public String toString() {
		return "FacultyMember: " + getId() + " " + getTitle() + " " + getFirstName()
				+ " " + getLastName() + " [" + getEmailAddress() + "]";
	}
}
