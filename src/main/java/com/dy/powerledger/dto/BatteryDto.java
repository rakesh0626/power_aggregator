package com.dy.powerledger.dto;

import com.dy.powerledger.model.Battery;

public class BatteryDto {
	private int code;
	private String name;
	private long watt;
	private int postalcode;
	
	public BatteryDto() {
		
	}
	
	public BatteryDto(int code,String name,long watt,int postalcode) {
		this.code=code;
		this.name=name;
		this.watt=watt;
		this.postalcode=postalcode;
	}
	
	public BatteryDto(Battery battery) {
		this.code=battery.getCode();
		this.name=battery.getName();
		this.watt=battery.getWatt();
		this.postalcode=battery.getPostalcode();
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public long getWatt() {
		return watt;
	}
	public void setWatt(long watt) {
		this.watt = watt;
	}
	
	public int getPostalcode() {
		return postalcode;
	}
	public void setPostalcode(int postcode) {
		this.postalcode = postcode;
	}
}
