package com.locations.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.locations.models.Location;
import com.locations.services.LocationService;

/**
 * @author Amir
 *
 */

@RestController
@RequestMapping(value = "/locations")
public class LocationController {
	
	@Autowired
	private LocationService locationService;

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Location> getAllLocations() {
    	return locationService.getAllLocations();
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Location getLocationById(@PathVariable Long id) {
    	return locationService.getLocationById(id);
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public void addLocation(@RequestBody Location location) {
    	locationService.addLocation(location);
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void updateLocation(@PathVariable Long id, @RequestBody Location location) {
    	locationService.updateLocation(id, location);
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteLocation(@PathVariable Long id) {
    	locationService.deleteLocation(id);
    }
    
    @RequestMapping(value = "/print/{id}", method = RequestMethod.GET)
    public String printLocation(@PathVariable Long id) {
    	return locationService.printLocation(id);
    }
}
