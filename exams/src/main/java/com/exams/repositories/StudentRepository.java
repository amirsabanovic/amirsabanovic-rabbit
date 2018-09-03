package com.exams.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.exams.models.Student;

/**
 * @author Amir
 *
 */

@RepositoryRestResource(collectionResourceRel = "studentsrepo", path = "studentsrepo")
public interface StudentRepository extends JpaRepository<Student, Long> {

}
