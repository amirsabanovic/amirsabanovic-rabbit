package com.exams.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exams.models.Department;
import com.exams.repositories.DepartmentRepository;
import com.exams.repositories.FacultyMemberRepository;

/**
 * @author Amir
 *
 */
@Service
@Transactional
public class DepartmentService {

	@Autowired
	private DepartmentRepository departmentRepository;
	
	@Autowired
	private FacultyMemberRepository facultyMemberRepository;

	public List<Department> getAllDepartments() {
		List<Department> departments = new ArrayList<>();
		departmentRepository.findAll().forEach(departments::add);
		return departments;
	}

	public Department getDepartmentById(Long id) {
		return departmentRepository.getOne(id);
	}

	public void addDepartment(Department department) {
		departmentRepository.save(department);
	}
	
	@RabbitListener(queues = "post-department")
	public void addDepartmentFromOutside(String departmentInMessage) {
		System.out.println("Received: <" + departmentInMessage + ">");
		
		String[] fields = departmentInMessage.split("\\*");
        Department department = new Department();
        department.setName(fields[1]);
        department.setDescription(fields[2]);
        department.setHeadOfDepartment(facultyMemberRepository.getOne(Long.parseLong(fields[3])));
        
        this.addDepartment(department);
    }

	public void updateDepartment(Long id, Department department) {
		Department departmentToBeUpdated = departmentRepository.getOne(id);
	
		departmentToBeUpdated.setName(department.getName());
		departmentToBeUpdated.setDescription(department.getDescription());
		departmentToBeUpdated.setHeadOfDepartment(department.getHeadOfDepartment());
		
		departmentRepository.save(departmentToBeUpdated);
	}
	
	@RabbitListener(queues = "put-department")
	public void updateDepartmentFromOutside(String departmentInMessage) {
        
        String[] fields = departmentInMessage.split("\\*");
        Long id = Long.parseLong(fields[0]);
        Department departmentToBeUpdated = departmentRepository.getOne(id);
        departmentToBeUpdated.setName(fields[1]);
        departmentToBeUpdated.setDescription(fields[2]);
        departmentToBeUpdated.setHeadOfDepartment(facultyMemberRepository.getOne(Long.parseLong(fields[3])));
    }

	public void deleteDepartment(Long id) {
		departmentRepository.deleteById(id);
	}
	
	@RabbitListener(queues = "delete-department")
	public void deleteDepartmentFromOutside(Long idInMessage) {
		this.deleteDepartment(idInMessage);
	}
	
	public String printDepartment(Long id) {
		return departmentRepository.getOne(id).toString();
	}	
}