package com.dy.powerledger.service;

import java.util.List;

import com.dy.powerledger.dto.BatteryDto;
import com.dy.powerledger.dto.BatteryListDto;

public interface  BatteryService {

	public BatteryListDto listBatteries(Integer postalcodeFrom,Integer postalcodeTo);
	public boolean saveBatteries(List<BatteryDto> batteryDtoList);
}
