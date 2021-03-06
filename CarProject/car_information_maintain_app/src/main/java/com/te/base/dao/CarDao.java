package com.te.base.dao;

import org.springframework.data.repository.CrudRepository;

import com.te.base.dto.CarDetails;

public interface CarDao extends CrudRepository<CarDetails, Integer>{

	public CarDetails findByCarId(int carId);
	
}
