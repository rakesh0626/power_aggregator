package com.dy.powerledger.util;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.util.StringUtils;

import com.dy.powerledger.dto.BatteryDto;

public class ValidationUtil {

	/**
	 * This method is used to validate the input battery data for their properties emptiness, ,means all attributes are mandatory other than code
	 * @param batteryDtoList
	 * @return boolean
	*/
	public static boolean isValidBatteryDetail(List<BatteryDto> batteryDtoList) {
		
		 List<BatteryDto> validatedDtolist = batteryDtoList.stream().filter(o -> {
								 if(!StringUtils.hasText(o.getName()) || (o.getWatt()==0) || (o.getPostalcode()==0)) { 
									 return false; 
								 } else { 
									 return true; 
								 }
		 					}).collect(Collectors.toList());
		 
		if(validatedDtolist.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	
}
