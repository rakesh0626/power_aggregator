package com.dy.powerledger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.dy.powerledger.controller.BatteryController;
import com.dy.powerledger.dto.BatteryDto;

@SpringBootTest
class PowerledgerAgreegatorServiceApplicationTests {

	@Autowired
	private BatteryController controller;
	
	@Test
	void contextLoads() {
		assertThat(controller).isNotNull();
	}
	
	@Test
	void saveListBatteries() {
		List<BatteryDto> batteriesDtoList = new ArrayList<>();
		BatteryDto batteryDtoOne = new BatteryDto(0,"new one",2121,245);
		batteriesDtoList.add(batteryDtoOne);
		String result = controller.saveBatteries(batteriesDtoList);
		assertEquals(result,"Sucess");
		assertThat(controller.listBatteries(null, null).getBetteryList().size()).isEqualTo(5);
	}

}
