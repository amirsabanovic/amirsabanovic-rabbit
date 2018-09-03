package com.users.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.users.models.Student;
import com.users.repositories.StudentRepository;

/**
 * @author Amir
 *
 */
@Service
public class StudentService {

	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private RabbitTemplate rabbitTemplate;

	public List<Student> getAllStudents() {
		List<Student> students = new ArrayList<>();
		studentRepository.findAll().forEach(students::add);
		return students;
	}

	public Student getStudentById(Long id) {
		return studentRepository.getOne(id);
	}

	public void addStudent(Student student) {
		studentRepository.save(student);
		
		rabbitTemplate.convertAndSend("users-direct-exchange", "post.student", student.toString());
	}

	public void updateStudent(Long id, Student student) {
		Student studentToBeUpdated = studentRepository.getOne(id);
	
		studentToBeUpdated.setFirstName(student.getFirstName());
		studentToBeUpdated.setLastName(student.getLastName());
		studentToBeUpdated.setEmailAddress(student.getEmailAddress());
		
		studentRepository.save(studentToBeUpdated);
		
		rabbitTemplate.convertAndSend("users-direct-exchange", "put.student", studentToBeUpdated.toString());
	}

	public void deleteStudent(Long id) {
		studentRepository.deleteById(id);
		
		rabbitTemplate.convertAndSend("users-direct-exchange", "delete.student", id);
	}
	
	public String printStudent(Long id) {
		return studentRepository.getOne(id).toString();
	}
	
}
