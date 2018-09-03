/**
 * 
 */
package com.exams.models;

import java.util.Date;

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
public class Exam {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private Date dateTime;
	
	@ManyToOne
	private Student student;
	
	@ManyToOne
	private Course course;
	
	@ManyToOne
	private FacultyMember facultyMember;
	
	@ManyToOne
	private Location location;

	public Exam() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Exam(Date dateTime, Student student, Course course, FacultyMember facultyMember, Location location) {
		super();
		this.dateTime = dateTime;
		this.student = student;
		this.course = course;
		this.facultyMember = facultyMember;
		this.location = location;
	}

	public Long getId() {
		return id;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public FacultyMember getFacultyMember() {
		return facultyMember;
	}

	public void setFacultyMember(FacultyMember facultyMember) {
		this.facultyMember = facultyMember;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
	
}
