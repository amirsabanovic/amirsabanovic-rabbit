package com.exams.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.exams.models.Location;

/**
 * @author Amir
 *
 */

@RepositoryRestResource(collectionResourceRel = "locationsrepo", path = "locationsrepo")
public interface LocationRepository extends JpaRepository<Location, Long> {

}
