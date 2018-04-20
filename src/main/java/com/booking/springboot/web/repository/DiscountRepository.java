package com.booking.springboot.web.repository;

import org.springframework.data.repository.CrudRepository;

import com.booking.springboot.web.model.Discounted;

public interface DiscountRepository extends CrudRepository<Discounted, Integer>{

}
