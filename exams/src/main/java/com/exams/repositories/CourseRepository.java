package com.exams.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.exams.models.Course;

/**
 * @author Amir
 *
 */

@RepositoryRestResource(collectionResourceRel = "coursessrepo", path = "coursesrepo")
public interface CourseRepository extends JpaRepository<Course, Long> {

}
