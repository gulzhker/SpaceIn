package model;

import java.util.ArrayList;

public class Flight {
	private String FlightID;
	private String startTime;
	private String arrivalTime;
	private String startCity;
	private String arrivalCity;
	private String departureDate;
	private int price;
	private int currentPassenger;
	private int seatCapacity;
	private String flightStatus;
	private ArrayList<Integer> passengers;
	public String getFlightStatus() {
		return flightStatus;
	}
	public void setFlightStatus(String flightStatus) {
		this.flightStatus = flightStatus;
	}
	public ArrayList<Integer> getPassengers() {
		return passengers;
	}
	public void setPassengers(ArrayList<Integer> passengers) {
		this.passengers = passengers;
	}
	public String getFlightID() {
		return FlightID;
	}
	public void setFlightID(String flightID) {
		FlightID = flightID;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	public String getStartCity() {
		return startCity;
	}
	public void setStartCity(String startCity) {
		this.startCity = startCity;
	}
	public String getArrivalCity() {
		return arrivalCity;
	}
	public void setArrivalCity(String arrivalCity) {
		this.arrivalCity = arrivalCity;
	}
	public String getDepartureDate() {
		return departureDate;
	}
	public void setDepartureDate(String departureDate) {
		this.departureDate = departureDate;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getCurrentPassenger() {
		return currentPassenger;
	}
	public void setCurrentPassenger(int currentPassenger) {
		this.currentPassenger = currentPassenger;
	}
	public int getSeatCapacity() {
		return seatCapacity;
	}
	public void setSeatCapacity(int seatCapacity) {
		this.seatCapacity = seatCapacity;
	}
}
