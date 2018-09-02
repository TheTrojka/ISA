package com.booking.springboot.web.repository;

import org.springframework.data.repository.CrudRepository;

import com.booking.springboot.web.model.Status;

public interface StatusRepository extends CrudRepository<Status, Integer>{

}
