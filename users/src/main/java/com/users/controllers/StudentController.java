package com.users.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.users.models.Student;
import com.users.services.StudentService;

/**
 * @author Amir
 *
 */

@RestController
@RequestMapping(value = "/students")
public class StudentController {
	
	@Autowired
	private StudentService studentService;

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Student> getAllStudents() {
    	return studentService.getAllStudents();
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Student getStudentById(@PathVariable Long id) {
    	return studentService.getStudentById(id);
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public void addStudent(@RequestBody Student student) {
    	studentService.addStudent(student);
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void updateStudent(@PathVariable Long id, @RequestBody Student student) {
    	studentService.updateStudent(id, student);
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteStudent(@PathVariable Long id) {
    	studentService.deleteStudent(id);
    }
    
    @RequestMapping(value = "/print/{id}", method = RequestMethod.GET)
    public String printStudent(@PathVariable Long id) {
    	return studentService.printStudent(id);
    }
}
