package com.users.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.users.models.FacultyMember;
import com.users.repositories.FacultyMemberRepository;

/**
 * @author Amir
 *
 */
@Service
public class FacultyMemberService {

	@Autowired
	private FacultyMemberRepository facultyMemberRepository;
	
	@Autowired
	private RabbitTemplate rabbitTemplate;

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
		
		rabbitTemplate.convertAndSend("users-direct-exchange", "post.faculty.member", facultyMember.toString());
		rabbitTemplate.convertAndSend("users-direct-exchange", "post.faculty.member.academics", facultyMember.toString());
	}

	public void updateFacultyMember(Long id, FacultyMember facultyMember) {
		FacultyMember facultyMemberToBeUpdated = facultyMemberRepository.getOne(id);
	
		facultyMemberToBeUpdated.setTitle(facultyMember.getTitle());
		facultyMemberToBeUpdated.setFirstName(facultyMember.getFirstName());
		facultyMemberToBeUpdated.setLastName(facultyMember.getLastName());
		facultyMemberToBeUpdated.setEmailAddress(facultyMember.getEmailAddress());
		
		facultyMemberRepository.save(facultyMemberToBeUpdated);
		
		rabbitTemplate.convertAndSend("users-direct-exchange", "put.faculty.member", facultyMemberToBeUpdated.toString());
		rabbitTemplate.convertAndSend("users-direct-exchange", "put.faculty.member.academics", facultyMemberToBeUpdated.toString());
	}

	public void deleteFacultyMember(Long id) {
		facultyMemberRepository.deleteById(id);
		
		rabbitTemplate.convertAndSend("users-direct-exchange", "delete.faculty.member", id);
		rabbitTemplate.convertAndSend("users-direct-exchange", "delete.faculty.member.academics", id);
	}
	
	public String printFacultyMember(Long id) {
		return facultyMemberRepository.getOne(id).toString();
	}
	
}
