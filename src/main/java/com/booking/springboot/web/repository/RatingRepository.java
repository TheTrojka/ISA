package com.booking.springboot.web.repository;

import org.springframework.data.repository.CrudRepository;

import com.booking.springboot.web.model.Rating;

public interface RatingRepository extends CrudRepository<Rating, Integer>{

}
