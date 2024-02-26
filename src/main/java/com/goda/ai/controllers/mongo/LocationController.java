package com.goda.ai.controllers.mongo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.goda.ai.service.mongo.LocationService;

@Controller
@RequestMapping("/api/location")
public class LocationController {

    @Autowired
    private LocationService locationService;

    @GetMapping("/near")
    public ResponseEntity<?> getAllLocations(
            @RequestParam double longitude,
            @RequestParam double latitude,
            @RequestParam int distance) throws RuntimeException {
        return ResponseEntity.ok(locationService.findLocationsWithin(longitude, latitude, distance));
    }
}
