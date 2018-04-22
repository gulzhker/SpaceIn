package model;

import java.util.ArrayList;

public class Passenger {
	private int passengerID;
	private String realName;
	private String identityID;
	private String password;
	private ArrayList orderList;
	public int getPassengerID() {
		return passengerID;
	}
	public void setPassengerID(int passengerID) {
		this.passengerID = passengerID;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getIdentityID() {
		return identityID;
	}
	public void setIdentityID(String identityID) {
		this.identityID = identityID;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public ArrayList getOrderList() {
		return orderList;
	}
	public void setOrderList(ArrayList orderList) {
		this.orderList = orderList;
	}
	
}
