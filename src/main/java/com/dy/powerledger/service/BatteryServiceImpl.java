package com.dy.powerledger.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dy.powerledger.dto.BatteryDto;
import com.dy.powerledger.dto.BatteryListDto;
import com.dy.powerledger.model.Battery;
import com.dy.powerledger.repository.BatteryRepository;

/**
 * This class is used to save/get list of batteries
 */
@Service
public class BatteryServiceImpl implements BatteryService{

	private static final Logger logger = LogManager.getLogger(BatteryServiceImpl.class);
	
	private BatteryRepository batteryRepository;
	
	public BatteryServiceImpl(BatteryRepository batteryRepository) {
		super();
		this.batteryRepository = batteryRepository;
	}

	/**
	 * This method is used to get list of batteries
	 */
	@Override
	@Transactional
	public BatteryListDto listBatteries(Integer postalcodeFrom,Integer postalcodeTo) {
		BatteryListDto batteryListDto = new BatteryListDto();
		List<BatteryDto> batteriesList = new ArrayList<BatteryDto>(); 
		
		try {
			
			logger.info("Calling listBatteries service with parameters postalcode_from="+postalcodeFrom+" and postalcodeTo="+postalcodeTo);
			
			batteryRepository.findByPostalCodeRange(postalcodeFrom,postalcodeTo)
							 .forEach(battery -> batteriesList.add(new BatteryDto(battery)));  
			
			logger.info("batteries list returned from battery repository is :"+batteriesList.toString());
			
			// set battery list
			batteryListDto.setBetteryList(batteriesList);

			// set total number of batteries
			batteryListDto.setTotalBatteries(batteriesList.size());
			
			// set total watt of batteries
			batteryListDto.setTotalWatt(calculateTotalWatt(batteriesList));
			
			// calculate average watt capacity from list
			float avgWatt = batteriesList.size() > 0 ? batteryListDto.getTotalWatt()/batteriesList.size() : 0;
			batteryListDto.setAvgWatt(avgWatt);
			
		} catch(Exception e) {
			// we may send email to this event to all dev team members or save this log to database using Log42 appenders
			logger.error("Error in listBatteries method of batteryservice class",e);
			throw e;
		}
		return batteryListDto;
	}

	/**
	 * This method is used to save list of batteries
	 */
	@Override
	@Transactional
	public boolean saveBatteries(List<BatteryDto> batteryDtoList) {
		try {
			logger.info("Calling saveBatteries service with batteryDtoList :"+batteryDtoList);
			
			List<Battery> batteriesList = copyBatteryDto(batteryDtoList);
			
			batteryRepository.saveAll(batteriesList);
			
			logger.info("List of Battery saved sucessfully.");
			
			return true;
		} catch(Exception e) {
			// we may send email to this event to all dev team members or save this log to database using Log42 appenders
			logger.error("Error in saveBatteries method of batteryservice class",e);
			throw e;
		}
	}
	
	/**
	 * This method copies Battery DTO list to Battery List
	 * @param List<BatteryDto>
	 * @return List<Battery>
	*/
	private List<Battery> copyBatteryDto(List<BatteryDto> batteryDtoList ) {
		List<Battery> batteriesList = new ArrayList<>();
		
		batteryDtoList.forEach(battery -> {
							Battery tempBattery = new Battery();
							BeanUtils.copyProperties(battery,tempBattery);
							batteriesList.add(tempBattery);
						});
		return batteriesList;
	}

	/**
	 * calculate total watt capacity from list
	 * @return long
	*/
	private long calculateTotalWatt(List<BatteryDto> batteriesList) {
		
		if(batteriesList!=null) {
			long totalWatt = batteriesList.stream()
										  .filter(o -> o.getWatt()>0)
										  .mapToLong(o -> o.getWatt())
											  .sum();
			return totalWatt;
		} else {
			return 0;
		}
	}
}
