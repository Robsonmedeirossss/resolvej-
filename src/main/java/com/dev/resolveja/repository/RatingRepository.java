package com.dev.resolveja.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.resolveja.domain.Rating;

public interface RatingRepository extends JpaRepository<Rating, Long>{
    
}
