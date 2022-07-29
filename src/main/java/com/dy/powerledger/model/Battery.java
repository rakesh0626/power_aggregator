package com.dy.powerledger.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table  
public class Battery {
	
	@Id 
	@Column
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int code;
	
	@Column
	private String name;
	
	@Column
	private long watt;
	
	@Column
	private int postalcode;
	
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
