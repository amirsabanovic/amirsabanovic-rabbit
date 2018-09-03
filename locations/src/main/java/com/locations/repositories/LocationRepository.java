package com.locations.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.locations.models.Location;

/**
 * @author Amir
 *
 */

@RepositoryRestResource(collectionResourceRel = "locationsrepo", path = "locationsrepo")
public interface LocationRepository extends JpaRepository<Location, Long> {

}
