package com.te.base.dto;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Entity
@Data
public class CarDetails implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int carId;
	private String carName;
	private String carCompany;
	private String carFuelType;
	private String carPowerSteering;
	private String carBreakSystem;
	private Double carShowroomPrice;
	private Double carOnroadPrice;
	private Double carMileage;
	private int carSeatingCapacity;
	private int carEngineCapacity;
	private String carGearType;
	
	
	
	@ManyToOne()
	@JoinColumn(referencedColumnName ="id")
	private Admin admin;
	
	
	
	public Admin getAdmin() {
		return admin;
	}
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	public int getCarId() {
		return carId;
	}
	public void setCarId(int carId) {
		this.carId = carId;
	}
	public String getCarName() {
		return carName;
	}
	public void setCarName(String carName) {
		this.carName = carName;
	}
	public String getCarCompany() {
		return carCompany;
	}
	public void setCarCompany(String carCompany) {
		this.carCompany = carCompany;
	}
	public String getCarFuelType() {
		return carFuelType;
	}
	public void setCarFuelType(String carFuelType) {
		this.carFuelType = carFuelType;
	}
	public String getCarPowerSteering() {
		return carPowerSteering;
	}
	public void setCarPowerSteering(String carPowerSteering) {
		this.carPowerSteering = carPowerSteering;
	}
	public String getCarBreakSystem() {
		return carBreakSystem;
	}
	public void setCarBreakSystem(String carBreakSystem) {
		this.carBreakSystem = carBreakSystem;
	}
	public Double getCarShowroomPrice() {
		return carShowroomPrice;
	}
	public void setCarShowroomPrice(Double carShowroomPrice) {
		this.carShowroomPrice = carShowroomPrice;
	}
	public Double getCarOnroadPrice() {
		return carOnroadPrice;
	}
	public void setCarOnroadPrice(Double carOnroadPrice) {
		this.carOnroadPrice = carOnroadPrice;
	}
	public Double getCarMileage() {
		return carMileage;
	}
	public void setCarMileage(Double carMileage) {
		this.carMileage = carMileage;
	}
	public int getCarSeatingCapacity() {
		return carSeatingCapacity;
	}
	public void setCarSeatingCapacity(int carSeatingCapacity) {
		this.carSeatingCapacity = carSeatingCapacity;
	}
	public int getCarEngineCapacity() {
		return carEngineCapacity;
	}
	public void setCarEngineCapacity(int carEngineCapacity) {
		this.carEngineCapacity = carEngineCapacity;
	}
	public String getCarGearType() {
		return carGearType;
	}
	public void setCarGearType(String carGearType) {
		this.carGearType = carGearType;
	}
	
	
	
}
