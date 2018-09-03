package com.users.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.users.models.FacultyMember;

/**
 * @author Amir
 *
 */

@RepositoryRestResource(collectionResourceRel = "facultyrepo", path = "facultyrepo")
public interface FacultyMemberRepository extends JpaRepository<FacultyMember, Long> {

}
