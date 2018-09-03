package com.exams.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exams.models.Course;
import com.exams.repositories.CourseRepository;
import com.exams.repositories.DepartmentRepository;

/**
 * @author Amir
 *
 */
@Service
@Transactional
public class CourseService {
	
	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private DepartmentRepository departmentRepository;

	public List<Course> getAllCourses() {
		List<Course> courses = new ArrayList<>();
		courseRepository.findAll().forEach(courses::add);
		return courses;
	}

	public Course getCourseById(Long id) {
		return courseRepository.getOne(id);
	}

	public void addCourse(Course course) {
		courseRepository.save(course);
	}
	
	@RabbitListener(queues = "post-course")
	public void addCourseFromOutside(String courseInMessage) {
		System.out.println("Received: <" + courseInMessage + ">");
		
		String[] fields = courseInMessage.split("\\*");
        Course course = new Course();
        course.setName(fields[1]);
        course.setAbbreviation(fields[2]);
        course.setDescription(fields[3]);
        course.setYearOfStudy(Integer.parseInt(fields[4]));
        course.setDepartment(departmentRepository.getOne(Long.parseLong(fields[5])));
        
        this.addCourse(course);
    }

	public void updateCourse(Long id, Course course) {
		Course courseToBeUpdated = courseRepository.getOne(id);
	
		courseToBeUpdated.setName(course.getName());
		courseToBeUpdated.setAbbreviation(course.getAbbreviation());
		courseToBeUpdated.setDescription(course.getDescription());
		courseToBeUpdated.setYearOfStudy(course.getYearOfStudy());
		courseToBeUpdated.setDepartment(course.getDepartment());
		
		courseRepository.save(courseToBeUpdated);
	}
	
	@RabbitListener(queues = "put-course")
	public void updateCourseFromOutside(String courseInMessage) {
        
        String[] fields = courseInMessage.split("\\*");
        Long id = Long.parseLong(fields[0]);
        Course courseToBeUpdated = courseRepository.getOne(id);
        courseToBeUpdated.setName(fields[1]);
        courseToBeUpdated.setAbbreviation(fields[2]);
        courseToBeUpdated.setDescription(fields[3]);
        courseToBeUpdated.setYearOfStudy(Integer.parseInt(fields[4]));
        courseToBeUpdated.setDepartment(departmentRepository.getOne(Long.parseLong(fields[5])));
    }

	public void deleteCourse(Long id) {
		courseRepository.deleteById(id);
	}
	
	@RabbitListener(queues = "delete-course")
	public void deleteCourseFromOutside(Long idInMessage) {
		this.deleteCourse(idInMessage);
	}
	
	public String printCourse(Long id) {
		return courseRepository.getOne(id).toString();
	}
}
