package com.academics.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.academics.models.Course;
import com.academics.services.CourseService;

/**
 * @author Amir
 *
 */

@RestController
@RequestMapping(value = "/courses")
public class CourseController {
	
	@Autowired
	private CourseService courseService;

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Course> getAllCourses() {
    	return courseService.getAllCourses();
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Course getCourseById(@PathVariable Long id) {
    	return courseService.getCourseById(id);
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public void addCourse(@RequestBody Course course) {
    	courseService.addCourse(course);
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void updateCourse(@PathVariable Long id, @RequestBody Course course) {
    	courseService.updateCourse(id, course);
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteCourse(@PathVariable Long id) {
    	courseService.deleteCourse(id);
    }
    
    @RequestMapping(value = "/print/{id}", method = RequestMethod.GET)
    public String printCourse(@PathVariable Long id) {
    	return courseService.printCourse(id);
    }
}

