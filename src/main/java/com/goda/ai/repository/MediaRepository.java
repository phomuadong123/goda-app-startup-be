package com.goda.ai.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.goda.ai.model.Media;

@Repository
public interface MediaRepository extends JpaRepository<Media, Long>{
    
}
