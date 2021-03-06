package com.te.base.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.te.base.dao.AdminDao;
import com.te.base.dao.CarDao;
import com.te.base.dto.Admin;
import com.te.base.dto.CarDetails;
import com.te.base.dto.MyAdmin;
import com.te.base.jwt.JwtUtil;



@Service
public class CarService implements UserDetailsService{
	@Autowired
	private AdminDao adminDao;
	
	@Autowired
	private CarDao carDao;
	
	@Autowired
    private JwtUtil jwtUtil;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Admin adminName = adminDao.findByUsername(username);
		if (adminName!=null) {
			
			return new MyAdmin(adminName);
		}
		throw new UsernameNotFoundException("Admin is Not Found! ");
	}
	
	
	public Admin createRegistration(Admin admin) {
		return adminDao.save(admin);
	}
	
	
	public CarDetails addCar(CarDetails car,HttpServletRequest request) {
		final String authorizationHeader = request.getHeader("Authorization");
		String jwt = authorizationHeader.substring(7);
		String userName = jwtUtil.extractUsername(jwt);
		Admin findByUsername = adminDao.findByUsername(userName);
		car.setAdmin(findByUsername);
		return carDao.save(car);
		
	}
	
	public void deleteCar(int carId) {
		 CarDetails findByCarId = carDao.findByCarId(carId);
		 
			 carDao.delete(findByCarId);
			 
		}
	
	public CarDetails updateCar(int carId, CarDetails car) {
	  
		CarDetails details = carDao.findByCarId(carId);
		return carDao.save(details);
	
	
	}
	
	public List<CarDetails> CarDetails() {
		return  (List<CarDetails>) carDao.findAll();
	}
		
	
	
	}
	