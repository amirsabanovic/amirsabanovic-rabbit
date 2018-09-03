package com.academics.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.academics.models.Course;
import com.academics.repositories.CourseRepository;

/**
 * @author Amir
 *
 */
@Service
public class CourseService {
	
	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private RabbitTemplate rabbitTemplate;

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
		
		rabbitTemplate.convertAndSend("academics-direct-exchange", "post.course", course.toString());
	}

	public void updateCourse(Long id, Course course) {
		Course courseToBeUpdated = courseRepository.getOne(id);
	
		courseToBeUpdated.setName(course.getName());
		courseToBeUpdated.setAbbreviation(course.getAbbreviation());
		courseToBeUpdated.setDescription(course.getDescription());
		courseToBeUpdated.setYearOfStudy(course.getYearOfStudy());
		courseToBeUpdated.setDepartment(course.getDepartment());
		
		courseRepository.save(courseToBeUpdated);
		
		rabbitTemplate.convertAndSend("academics-direct-exchange", "put.course", courseToBeUpdated.toString());
	}

	public void deleteCourse(Long id) {
		courseRepository.deleteById(id);
		
		rabbitTemplate.convertAndSend("academics-direct-exchange", "delete.course", id);
	}
	
	public String printCourse(Long id) {
		return courseRepository.getOne(id).toString();
	}
	
}
