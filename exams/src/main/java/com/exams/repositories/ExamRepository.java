package com.exams.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.exams.models.Exam;

/**
 * @author Amir
 *
 */

@RepositoryRestResource(collectionResourceRel = "examssrepo", path = "examsrepo")
public interface ExamRepository extends JpaRepository<Exam, Long> {

}
