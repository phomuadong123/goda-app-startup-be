package com.goda.ai.repository.mongo;

import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.repository.Query;
import java.util.List;

import com.goda.ai.model.mongo.Location;

@Repository
public interface LocationRepository extends MongoRepository<Location, String>{
    @Query("{\n" + //
            "  \"location.coordinates\": {\n" + //
            "    $near: {\n" + //
            "      $geometry: {\n" + //
            "         type: \"Point\",\n" + //
            "         coordinates: [?0, ?1]\n" + //
            "      },\n" + //
            "      $maxDistance: ?2\n" + //
            "    }\n" + //
            "  }\n" + //
            "}")
    List<Location> findByLocationNear(double longitude, double latitude, int distance);
}
