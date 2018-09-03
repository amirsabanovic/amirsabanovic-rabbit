package com.exams.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exams.models.Location;
import com.exams.repositories.LocationRepository;

/**
 * @author Amir
 *
 */
@Service
@Transactional
public class LocationService {
	
	@Autowired
	private LocationRepository locationRepository;

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
	}
	
	@RabbitListener(queues = "post-location")
	public void addLocationFromOutside(String locationInMessage) {
        
		String[] fields = locationInMessage.split("\\*");
        Location location = new Location();
        location.setBuilding(fields[1]);
        location.setAddress(fields[2]);
        location.setClassroom(fields[3]);
        this.addLocation(location);
    }

	public void updateLocation(Long id, Location location) {
		Location locationToBeUpdated = locationRepository.getOne(id);
	
		locationToBeUpdated.setBuilding(location.getBuilding());
		locationToBeUpdated.setAddress(location.getAddress());
		locationToBeUpdated.setClassroom(location.getClassroom());
		
		locationRepository.save(locationToBeUpdated);
	}
	
	@RabbitListener(queues = "put-location")
	public void updateLocationFromOutside(String locationInMessage) {
        
        String[] fields = locationInMessage.split("\\*");
        Long id = Long.parseLong(fields[0]);
        Location locationToBeUpdated = locationRepository.getOne(id);
        locationToBeUpdated.setBuilding(fields[1]);
		locationToBeUpdated.setAddress(fields[2]);
		locationToBeUpdated.setClassroom(fields[3]);
    }

	public void deleteLocation(Long id) {
		locationRepository.deleteById(id);
	}
	
	@RabbitListener(queues = "delete-location")
	public void deleteLocationFromOutside(Long idInMessage) {
		this.deleteLocation(idInMessage);
	}
	
	public String printLocation(Long id) {
		return locationRepository.getOne(id).toString();
	}
	
}
