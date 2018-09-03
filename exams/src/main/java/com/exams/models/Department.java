/**
 * 
 */
package com.exams.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * @author Amir
 *
 */

@Entity
public class Department {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	private String description;
	
	@OneToOne
	private FacultyMember headOfDepartment;

	public Department() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Department(String name, String description, FacultyMember headOfDepartment) {
		super();
		this.name = name;
		this.description = description;
		this.headOfDepartment = headOfDepartment;
	}

	public Long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public FacultyMember getHeadOfDepartment() {
		return headOfDepartment;
	}

	public void setHeadOfDepartment(FacultyMember headOfDepartment) {
		this.headOfDepartment = headOfDepartment;
	}

	@Override
	public String toString() {
		return "Department: " + getId() + " " + getName() + " (" + getDescription()
				+ ") headed by [" + getHeadOfDepartment().toString() + "]";
	}
}
