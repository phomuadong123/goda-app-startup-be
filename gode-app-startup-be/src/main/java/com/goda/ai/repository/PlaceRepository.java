package com.goda.ai.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.goda.ai.model.Place;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long>{

}
