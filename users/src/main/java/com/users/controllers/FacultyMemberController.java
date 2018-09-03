package com.users.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.users.models.FacultyMember;
import com.users.services.FacultyMemberService;

/**
 * @author Amir
 *
 */

@RestController
@RequestMapping(value = "/faculty")
public class FacultyMemberController {
	
	@Autowired
	private FacultyMemberService facultyMemberService;

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<FacultyMember> getAllFacultyMembers() {
    	return facultyMemberService.getAllFacultyMembers();
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public FacultyMember getFacultyMemberById(@PathVariable Long id) {
    	return facultyMemberService.getFacultyMemberById(id);
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public void addFacultyMember(@RequestBody FacultyMember facultyMember) {
    	facultyMemberService.addFacultyMember(facultyMember);
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void updateFacultyMember(@PathVariable Long id, @RequestBody FacultyMember facultyMember) {
    	facultyMemberService.updateFacultyMember(id, facultyMember);
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteFacultyMember(@PathVariable Long id) {
    	facultyMemberService.deleteFacultyMember(id);
    }
    
    @RequestMapping(value = "/print/{id}", method = RequestMethod.GET)
    public String printFacultyMember(@PathVariable Long id) {
    	return facultyMemberService.printFacultyMember(id);
    }
}

