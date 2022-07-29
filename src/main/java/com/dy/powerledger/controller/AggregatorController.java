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

@RestController
@RequestMapping(value = "/battery")
public class AggregatorController {

	private static final Logger logger = LogManager.getLogger(AggregatorController.class);
	
	@Autowired
	private BatteryService batteryService;
	
	@GetMapping(value = "/listBatteries", produces = MediaType.APPLICATION_JSON_VALUE)
	public BatteryListDto listBatteries(@RequestParam(name ="postalcode_from",required=false) Integer postalcodeFrom,
			@RequestParam(name ="postalcode_to",required=false) Integer postalcodeTo) {
		logger.info("inside listBatteries apis");
		return batteryService.listBatteries(postalcodeFrom,postalcodeTo);
	}
	
	@PostMapping(value = "/saveBatteries", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String newEmployee(@RequestBody List<BatteryDto> batteryList) {
		return batteryService.saveBatteries(batteryList);
	}
}
