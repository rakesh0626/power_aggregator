package com.dy.powerledger.dto;

import java.util.List;

public class BatteryListDto {
	private List<BatteryDto> betteryList;
	private float avgWatt;
	private long totalWatt;
	private long totalBatteries;
	public List<BatteryDto> getBetteryList() {
		return betteryList;
	}
	public void setBetteryList(List<BatteryDto> betteryList) {
		this.betteryList = betteryList;
	}
	public float getAvgWatt() {
		return avgWatt;
	}
	public void setAvgWatt(float avgWatt) {
		this.avgWatt = avgWatt;
	}
	public long getTotalWatt() {
		return totalWatt;
	}
	public void setTotalWatt(long totalWatt) {
		this.totalWatt = totalWatt;
	}
	public long getTotalBatteries() {
		return totalBatteries;
	}
	public void setTotalBatteries(long totalBatteries) {
		this.totalBatteries = totalBatteries;
	}
	
}
