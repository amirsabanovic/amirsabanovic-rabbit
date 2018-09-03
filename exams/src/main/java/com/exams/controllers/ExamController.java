package com.exams.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.exams.models.Exam;
import com.exams.services.ExamService;

/**
 * @author Amir
 *
 */

@RestController
@RequestMapping(value = "/exams")
public class ExamController {
	
	@Autowired
	private ExamService examService;

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Exam> getAllExams() {
    	return examService.getAllExams();
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Exam getExamById(@PathVariable Long id) {
    	return examService.getExamById(id);
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public void addExam(@RequestBody Exam exam) {
    	examService.addExam(exam);
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void updateExam(@PathVariable Long id, @RequestBody Exam exam) {
    	examService.updateExam(id, exam);
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteExam(@PathVariable Long id) {
    	examService.deleteExam(id);
    }
    
    @RequestMapping(value = "/print/{id}", method = RequestMethod.GET)
    public String printExam(@PathVariable Long id) {
    	return examService.printExam(id);
    }
}

