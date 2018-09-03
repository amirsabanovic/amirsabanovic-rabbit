package com.exams.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.exams.models.Department;

/**
 * @author Amir
 *
 */

@RepositoryRestResource(collectionResourceRel = "departmentsrepo", path = "departmentsrepo")
public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
