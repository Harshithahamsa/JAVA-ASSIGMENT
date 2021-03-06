package com.te.base.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.te.base.dto.Admin;
import com.te.base.dto.AdminResponse;
import com.te.base.dto.CarDetails;
import com.te.base.jwt.JwtUtil;
import com.te.base.model.AuthenticationRequest;
import com.te.base.model.AuthenticationResponse;
import com.te.base.service.CarService;


@RestController
@CrossOrigin(origins = "*")
public class CarController {

	@Autowired
	private CarService service;
	@Autowired
	private AuthenticationManager authenticateManager;
	@Autowired
	private JwtUtil jwtTokenUtil;

	
	
	@PostMapping("/adminRegister")
	public ResponseEntity<?> createRegistration(@RequestBody Admin admin) {
		Admin createRegistration = service.createRegistration(admin);
		if (createRegistration!=null) {
			authenticateManager.authenticate(new UsernamePasswordAuthenticationToken(createRegistration.getUsername(), createRegistration.getPassword()));
			
			UserDetails userDetails = service.loadUserByUsername(createRegistration.getUsername());
			String jwtToken = jwtTokenUtil.generateToken(userDetails);
			
			return ResponseEntity.ok(new AdminResponse(false, "Success", jwtToken));
		}else {
			return ResponseEntity.ok(new AdminResponse(true, "Username Already Exists ", null));
		}
		
		
	}
	
	@PostMapping("/adminreg")
	public ResponseEntity<?> register(@RequestBody Admin admin){
		try {
			Admin registration = service.createRegistration(admin);
			return new ResponseEntity<String>("Registration successful",HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("something went worng",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
	}
	
	@PostMapping("/adminloginauthenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
			throws Exception {
		try {
			authenticateManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or Password", e);

		}
		final UserDetails userDetails = service.loadUserByUsername(authenticationRequest.getUsername());

		final String jwt = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new AuthenticationResponse(jwt));

	}

	
	
	

	@GetMapping({"/carDetail"})
	public ResponseEntity<?> cardetails() {
		try {
			List<CarDetails> carDetails = service.CarDetails();
			return new ResponseEntity<List<CarDetails>>(carDetails, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Something Went Wrong !", HttpStatus.INTERNAL_SERVER_ERROR);

		}

	}
	
	
	@PostMapping( "/add")
	public ResponseEntity<?> addDetails(@RequestBody CarDetails car,HttpServletRequest request) {
		try {
			CarDetails addCars = service.addCar(car,request);
			return new ResponseEntity<String>("Data is Inserted Successfully!",HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<String>("Something Went Wrong !",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@DeleteMapping(path = "/delete/{carId}")
	public ResponseEntity<?> deleteDetails(@PathVariable int carId) {
		try {
			service.deleteCar(carId);
			return new ResponseEntity<String>("Car Details is Deleted",HttpStatus.OK);
		} catch (Exception e) {
			
			return new ResponseEntity<String>("Something went Wrong ", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping(path="/update/{carId}")
	public ResponseEntity<?> updateDetails(@PathVariable int carId,@RequestBody CarDetails car) {
		try {
			service.updateCar(carId, car);
			return new ResponseEntity<String>("car Details Upadated", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	
	
	
	
}
