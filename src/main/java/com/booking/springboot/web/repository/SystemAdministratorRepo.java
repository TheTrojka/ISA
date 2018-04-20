package com.booking.springboot.web.repository;

import org.springframework.data.repository.CrudRepository;

import com.booking.springboot.web.users.model.AdministratorFanZone;
import com.booking.springboot.web.users.model.SystemAdministrator;

public interface SystemAdministratorRepo extends CrudRepository<SystemAdministrator, Integer> {
	public SystemAdministrator findById(int id);
	public void deleteById(int id);
}
