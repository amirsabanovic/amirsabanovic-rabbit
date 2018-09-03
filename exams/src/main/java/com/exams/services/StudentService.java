package com.exams.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exams.models.Student;
import com.exams.repositories.StudentRepository;

/**
 * @author Amir
 *
 */

@Service
@Transactional
public class StudentService {

	@Autowired
	private StudentRepository studentRepository;

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
	}
	
	@RabbitListener(queues = "post-student")
	public void addStudentFromOutside(String studentInMessage) {
        
		String[] fields = studentInMessage.split(" ");
        Student student = new Student();
        student.setFirstName(fields[1]);
        student.setLastName(fields[2]);
        student.setEmailAddress(fields[3]);
        this.addStudent(student);
    }

	public void updateStudent(Long id, Student student) {
		Student studentToBeUpdated = studentRepository.getOne(id);
	
		studentToBeUpdated.setFirstName(student.getFirstName());
		studentToBeUpdated.setLastName(student.getLastName());
		studentToBeUpdated.setEmailAddress(student.getEmailAddress());
		
		studentRepository.save(studentToBeUpdated);
	}
	
	@RabbitListener(queues = "put-student")
	public void updateStudentFromOutside(String studentInMessage) {
        
        String[] fields = studentInMessage.split(" ");
        Long id = Long.parseLong(fields[0]);
        Student studentToBeUpdated = studentRepository.getOne(id);
        studentToBeUpdated.setFirstName(fields[1]);
		studentToBeUpdated.setLastName(fields[2]);
		studentToBeUpdated.setEmailAddress(fields[3]);
    }

	public void deleteStudent(Long id) {
		studentRepository.deleteById(id);
	}
	
	@RabbitListener(queues = "delete-student")
	public void deleteStudentFromOutside(Long idInMessage) {
		this.deleteStudent(idInMessage);
	}
	
	public String printStudent(Long id) {
		return studentRepository.getOne(id).toString();
	}
	
}
