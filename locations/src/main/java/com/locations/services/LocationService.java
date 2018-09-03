package com.locations.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.locations.models.Location;
import com.locations.repositories.LocationRepository;

/**
 * @author Amir
 *
 */
@Service
public class LocationService {
	
	@Autowired
	private LocationRepository locationRepository;
	
	@Autowired
	private RabbitTemplate rabbitTemplate;

	public List<Location> getAllLocations() {
		List<Location> locations = new ArrayList<>();
		locationRepository.findAll().forEach(locations::add);
		return locations;
	}

	public Location getLocationById(Long id) {
		return locationRepository.getOne(id);
	}

	public void addLocation(Location location) {
		locationRepository.save(location);
		
		rabbitTemplate.convertAndSend("locations-direct-exchange", "post.location", location.toString());
	}

	public void updateLocation(Long id, Location location) {
		Location locationToBeUpdated = locationRepository.getOne(id);
	
		locationToBeUpdated.setBuilding(location.getBuilding());
		locationToBeUpdated.setAddress(location.getAddress());
		locationToBeUpdated.setClassroom(location.getClassroom());
		
		locationRepository.save(locationToBeUpdated);
		
		rabbitTemplate.convertAndSend("locations-direct-exchange", "put.location", locationToBeUpdated.toString());
	}

	public void deleteLocation(Long id) {
		locationRepository.deleteById(id);
		
		rabbitTemplate.convertAndSend("locations-direct-exchange", "delete.location", id);
	}
	
	public String printLocation(Long id) {
		return locationRepository.getOne(id).toString();
	}
	
}
