package com.mmh;

import com.mmh.dao.ProvinceDao;
import com.mmh.entity.Province;
import javafx.application.Application;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
//@SpringBootTest
//@SpringBootTest(classes = Application.class)

@ContextConfiguration(value={"classpath:mapper/*.xml"})
public class AddressApplicationTests {

	@Test
	public void contextLoads() {
	}

	/*@Autowired
	private ProvinceRepostory provinceRepostory;

	@Test
	public void queryProvinceListTest(){
		List<Province> byId = provinceRepostory.findAllById(Arrays.asList(1));
		Assert.assertTrue(byId.size() > 0);
	}*/

	@Resource
	private ProvinceDao provinceDao;

	@Test
	public void queryProvinceListTest(){
		Province province = provinceDao.queryById(1);
		Assert.assertTrue(province != null);
	}

}
