package com.dy.powerledger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.dy.powerledger.dto.BatteryDto;
import com.dy.powerledger.dto.BatteryListDto;
import com.dy.powerledger.model.Battery;
import com.dy.powerledger.repository.BatteryRepository;
import com.dy.powerledger.service.BatteryService;
import com.dy.powerledger.service.BatteryServiceImpl;

/**
 * This class is used to test save/get list of batteries
 */
@ExtendWith(MockitoExtension.class)
public class BatteryServiceImplTest{

	
	private BatteryService batteryService;

	@Mock
	BatteryRepository batteryRepository;
	
	@BeforeEach
	public void init() {
		batteryService = new BatteryServiceImpl(batteryRepository);
	}
	
	@Test
	public void testListBatteries()
	{
		List<Battery> list = new ArrayList<>();
		
		Battery batteryOne = new Battery(1001,"Syska",420,302033);
		Battery batteryTwo = new Battery(1002,"Luminious",235,302032);
		Battery batteryThree = new Battery(1003,"Usha",310,302031);
		Battery batteryFour = new Battery(1004,"Philips",310,302040);
		
	
		list.add(batteryOne);
		list.add(batteryTwo);
		list.add(batteryThree);
		list.add(batteryFour);

		when(batteryRepository.findByPostalCodeRange(235,420)).thenReturn(list);

		//test
		BatteryListDto batteryListDto = batteryService.listBatteries(235,420);

		assertEquals(4, batteryListDto.getBetteryList().size());
		
		assertEquals("Syska", batteryListDto.getBetteryList().get(0).getName());
		assertEquals(420, batteryListDto.getBetteryList().get(0).getWatt());
		assertEquals(302033, batteryListDto.getBetteryList().get(0).getPostalcode());
	}
	
	
	@Test
	public void saveListBatteries()
	{
		when(batteryRepository.saveAll(Mockito.anyList())).thenReturn(Mockito.anyList());

		List<BatteryDto> batteriesDtoList = new ArrayList<>();
		BatteryDto batteryDtoOne = new BatteryDto(0,"new one",2121,245);
		batteriesDtoList.add(batteryDtoOne);
		
		boolean result = batteryService.saveBatteries(batteriesDtoList);

		assertTrue(result);
		
	}
	
}
