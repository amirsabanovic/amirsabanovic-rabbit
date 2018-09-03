/**
 * 
 */
package com.academics.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * @author Amir
 *
 */

@Entity
public class Course {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	private String abbreviation;
	private String description;
	private int yearOfStudy;
	
	@ManyToOne
	private Department department;

	public Course() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Course(String name, String abbreviation, String description, int yearOfStudy,
			Department department) {
		super();
		this.name = name;
		this.abbreviation = abbreviation;
		this.description = description;
		this.yearOfStudy = yearOfStudy;
		this.department = department;
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

	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getYearOfStudy() {
		return yearOfStudy;
	}

	public void setYearOfStudy(int yearOfStudy) {
		this.yearOfStudy = yearOfStudy;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return getId() + "*" + getName() + "*" + getAbbreviation()
				+ "*" + getDescription() + "*" + getYearOfStudy()
				+ "*" + getDepartment().getId();
	}
}
