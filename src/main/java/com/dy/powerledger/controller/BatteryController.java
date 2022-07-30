package com.dy.powerledger.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dy.powerledger.dto.BatteryDto;
import com.dy.powerledger.dto.BatteryListDto;
import com.dy.powerledger.service.BatteryService;
import com.dy.powerledger.util.ValidationUtil;


@RestController
@RequestMapping(value = "/batteries")
public class BatteryController {

	private static final Logger logger = LogManager.getLogger(BatteryController.class);
	
	@Autowired
	private BatteryService batteryService;
	
	@GetMapping(value = "/listBatteries", produces = MediaType.APPLICATION_JSON_VALUE)
	public BatteryListDto listBatteries(@RequestParam(name ="postalcode_from",required=false) Integer postalcodeFrom,
			@RequestParam(name ="postalcode_to",required=false) Integer postalcodeTo) {
		
		logger.info("Calling listBatteries api with parameters postalcode_from="+postalcodeFrom+" and postalcodeTo="+postalcodeTo);
		
		return batteryService.listBatteries(postalcodeFrom,postalcodeTo);
	}
	
	@PostMapping(value = "/saveBatteries", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String saveBatteries(@RequestBody List<BatteryDto> batteryDtoList) {
		
		logger.info("Calling saveBatteries api with request body as"+batteryDtoList.toString());
		
		if (batteryDtoList == null || batteryDtoList.isEmpty()) {
			return "Failed to save battery list as batteryList parameter is empty";
		} 
		
		// validate battery detail
		if(!ValidationUtil.isValidBatteryDetail(batteryDtoList)) {
			return "name, postalcode and watt fileds are mandatory in the battery list.";
		} 

		return batteryService.saveBatteries(batteryDtoList)?"Sucess":"Failure";
		
	}
}
