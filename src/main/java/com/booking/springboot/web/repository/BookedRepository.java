package com.booking.springboot.web.repository;

import org.springframework.data.repository.CrudRepository;

import com.booking.springboot.web.model.Booked;

public interface BookedRepository extends CrudRepository<Booked, Integer>{

}

