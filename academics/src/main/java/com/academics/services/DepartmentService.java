package com.academics.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.academics.models.Department;
import com.academics.repositories.DepartmentRepository;

/**
 * @author Amir
 *
 */
@Service
public class DepartmentService {

	@Autowired
	private DepartmentRepository departmentRepository;
	
	@Autowired
	private RabbitTemplate rabbitTemplate;

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
		
		rabbitTemplate.convertAndSend("academics-direct-exchange", "post.department", department.toString());
	}

	public void updateDepartment(Long id, Department department) {
		Department departmentToBeUpdated = departmentRepository.getOne(id);
	
		departmentToBeUpdated.setName(department.getName());
		departmentToBeUpdated.setDescription(department.getDescription());
		departmentToBeUpdated.setHeadOfDepartment(department.getHeadOfDepartment());
		
		departmentRepository.save(departmentToBeUpdated);
		
		rabbitTemplate.convertAndSend("academics-direct-exchange", "put.department", departmentToBeUpdated.toString());
	}

	public void deleteDepartment(Long id) {
		departmentRepository.deleteById(id);
		
		rabbitTemplate.convertAndSend("academics-direct-exchange", "delete.department", id);
	}
	
	public String printDepartment(Long id) {
		return departmentRepository.getOne(id).toString();
	}
	
}
