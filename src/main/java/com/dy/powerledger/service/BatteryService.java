package com.dy.powerledger.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dy.powerledger.dto.BatteryDto;
import com.dy.powerledger.dto.BatteryListDto;
import com.dy.powerledger.model.Battery;
import com.dy.powerledger.repository.BatteryRepository;

@Service
public class BatteryService {

	
	@Autowired 
	BatteryRepository batteryRepository;
	 
	
	public BatteryListDto listBatteries(Integer postalcodeFrom,Integer postalcodeTo) {
		BatteryListDto batteryListDto = new BatteryListDto();
		List<BatteryDto> batteriesList = new ArrayList<BatteryDto>(); 
		
		batteryRepository.findByPostalCodeRange(postalcodeFrom,postalcodeTo).forEach(battery -> batteriesList.add(new BatteryDto(battery)));  
		
		// set battery list
		batteryListDto.setBetteryList(batteriesList);
		
		// calculate total watt capacity from list
		long totalWatt = batteriesList.stream().filter(o -> o.getWatt()>0).mapToLong(o -> o.getWatt()).sum();
		batteryListDto.setTotalWatt(totalWatt);
		
		// calculate average watt capacity from list
		float avgWatt = totalWatt/batteriesList.size();
		batteryListDto.setAvgWatt(avgWatt);
		
		return batteryListDto;
	}


	public String saveBatteries(List<BatteryDto> batteryDtoList) {
		//List<Battery> batteriesList = batteryDtoList.stream().filter(o).mapToLong(o -> o.getWatt());
		List<Battery> batteriesList = new ArrayList<>();
		//
		batteryDtoList.forEach(battery -> {
			Battery tempBattery = new Battery();
			BeanUtils.copyProperties(battery,tempBattery);
			batteriesList.add(tempBattery);
			});
		System.out.println("List of Battery to be saved ::"+batteryDtoList);
		batteryRepository.saveAll(batteriesList);
		
		return "Sucsess";
	}
}
