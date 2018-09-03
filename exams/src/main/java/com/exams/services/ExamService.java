package com.exams.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exams.models.Exam;
import com.exams.repositories.ExamRepository;

/**
 * @author Amir
 *
 */
@Service
public class ExamService {
	
	@Autowired
	private ExamRepository examRepository;

	public List<Exam> getAllExams() {
		List<Exam> exams = new ArrayList<>();
		examRepository.findAll().forEach(exams::add);
		return exams;
	}

	public Exam getExamById(Long id) {
		return examRepository.getOne(id);
	}

	public void addExam(Exam exam) {
		examRepository.save(exam);
	}

	public void updateExam(Long id, Exam exam) {
		Exam examToBeUpdated = examRepository.getOne(id);
	
		examToBeUpdated.setDateTime(exam.getDateTime());
		examToBeUpdated.setStudent(exam.getStudent());
		examToBeUpdated.setCourse(exam.getCourse());
		examToBeUpdated.setFacultyMember(exam.getFacultyMember());
		examToBeUpdated.setLocation(exam.getLocation());
		
		examRepository.save(examToBeUpdated);
	}

	public void deleteExam(Long id) {
		examRepository.deleteById(id);
	}
	
	public String printExam(Long id) {
		return examRepository.getOne(id).toString();
	}
}
