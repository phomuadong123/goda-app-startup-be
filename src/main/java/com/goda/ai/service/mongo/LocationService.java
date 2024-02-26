package com.goda.ai.service.mongo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;
import java.util.List;

import com.goda.ai.model.mongo.Location;
import com.goda.ai.repository.mongo.LocationRepository;

@Service
public class LocationService {
    @Autowired
    private LocationRepository locationRepository;

    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    public List<Location> findLocationsWithin(double longitude, double latitude, int distance) {
        return locationRepository.findByLocationNear(longitude, latitude, distance*1000);
    }
}
