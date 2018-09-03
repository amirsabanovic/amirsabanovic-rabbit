package com.academics.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.academics.models.Department;
import com.academics.services.DepartmentService;

/**
 * @author Amir
 *
 */

@RestController
@RequestMapping(value = "/departments")
public class DepartmentController {
	
	@Autowired
	private DepartmentService departmentService;

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Department> getAllDepartments() {
    	return departmentService.getAllDepartments();
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Department getDepartmentById(@PathVariable Long id) {
    	return departmentService.getDepartmentById(id);
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public void addDepartment(@RequestBody Department department) {
    	departmentService.addDepartment(department);
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void updateDepartment(@PathVariable Long id, @RequestBody Department department) {
    	departmentService.updateDepartment(id, department);
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteDepartment(@PathVariable Long id) {
    	departmentService.deleteDepartment(id);
    }
    
    @RequestMapping(value = "/print/{id}", method = RequestMethod.GET)
    public String printDepartment(@PathVariable Long id) {
    	return departmentService.printDepartment(id);
    }
}

