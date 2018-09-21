package com.group.integrate;

import com.group.integrate.service.ExcelService;
import jxl.read.biff.BiffException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IntegrateApplicationTests {
	@Autowired
	ExcelService excelService;

	@Test
	public void contextLoads() {
		try {
//			excelService.generateExecl();
			excelService.updateExexl();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testMap(){
		Map<String , String> hashMap = new HashMap();
		hashMap.put("tang","xinjing");
		hashMap.put("mao","zhuxi");
		hashMap.put("xi","jinping");
		hashMap.put("liu","shaoqi");
		hashMap.get("tang");

	}

}
