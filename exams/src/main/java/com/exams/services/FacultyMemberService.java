package com.exams.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exams.models.FacultyMember;
import com.exams.repositories.FacultyMemberRepository;

/**
 * @author Amir
 *
 */
@Service
@Transactional
public class FacultyMemberService {

	@Autowired
	private FacultyMemberRepository facultyMemberRepository;

	public List<FacultyMember> getAllFacultyMembers() {
		List<FacultyMember> facultyMembers = new ArrayList<>();
		facultyMemberRepository.findAll().forEach(facultyMembers::add);
		return facultyMembers;
	}

	public FacultyMember getFacultyMemberById(Long id) {
		return facultyMemberRepository.getOne(id);
	}

	public void addFacultyMember(FacultyMember facultyMember) {
		facultyMemberRepository.save(facultyMember);
	}
	
	@RabbitListener(queues = "post-faculty-member")
	public void addFacultyMemberFromOutside(String facultyMemberInMessage) {
        
		String[] fields = facultyMemberInMessage.split(" ");
        FacultyMember facultyMember = new FacultyMember();
        facultyMember.setTitle(fields[1]);
        facultyMember.setFirstName(fields[2]);
        facultyMember.setLastName(fields[3]);
        facultyMember.setEmailAddress(fields[4]);
        this.addFacultyMember(facultyMember);
    }

	public void updateFacultyMember(Long id, FacultyMember facultyMember) {
		FacultyMember facultyMemberToBeUpdated = facultyMemberRepository.getOne(id);
	
		facultyMemberToBeUpdated.setTitle(facultyMember.getTitle());
		facultyMemberToBeUpdated.setFirstName(facultyMember.getFirstName());
		facultyMemberToBeUpdated.setLastName(facultyMember.getLastName());
		facultyMemberToBeUpdated.setEmailAddress(facultyMember.getEmailAddress());
		
		facultyMemberRepository.save(facultyMemberToBeUpdated);
	}
	
	@RabbitListener(queues = "put-faculty-member")
	public void updateFacultyMemberFromOutside(String facultyMemberInMessage) {
        
        String[] fields = facultyMemberInMessage.split(" ");
        Long id = Long.parseLong(fields[0]);
        FacultyMember facultyMemberToBeUpdated = facultyMemberRepository.getOne(id);
        facultyMemberToBeUpdated.setTitle(fields[1]);
        facultyMemberToBeUpdated.setFirstName(fields[2]);
        facultyMemberToBeUpdated.setLastName(fields[3]);
        facultyMemberToBeUpdated.setEmailAddress(fields[4]);
    }

	public void deleteFacultyMember(Long id) {
		facultyMemberRepository.deleteById(id);
	}
	
	@RabbitListener(queues = "delete-faculty-member")
	public void deleteFacultyMemberFromOutside(Long idInMessage) {
		this.deleteFacultyMember(idInMessage);
	}
	
	public String printFacultyMember(Long id) {
		return facultyMemberRepository.getOne(id).toString();
	}	
}