/**
 * 
 */
package com.exams.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Amir
 *
 */

@Entity
public class Location {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private String building;
	private String address;
	private String classroom;

	public Location() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Location(String building, String address, String classroom) {
		super();
		this.building = building;
		this.address = address;
		this.classroom = classroom;
	}

	public Long getId() {
		return id;
	}

	public String getBuilding() {
		return building;
	}

	public void setBuilding(String building) {
		this.building = building;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getClassroom() {
		return classroom;
	}

	public void setClassroom(String classroom) {
		this.classroom = classroom;
	}

	@Override
	public String toString() {
		return "Location: " + getId() + " " + getBuilding() + ", " + getAddress()
				+ "; Classroom: " + getClassroom();
	}
}
