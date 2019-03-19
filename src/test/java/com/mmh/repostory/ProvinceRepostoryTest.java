package com.mmh.repostory;

import com.mmh.dao.ProvinceDao;
import com.mmh.entity.Province;
import com.mmh.AddressApplicationTests;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ProvinceRepostoryTest extends AddressApplicationTests{

    /*@Autowired
    private ProvinceRepostory provinceRepostory;*/

    @Autowired
    private ProvinceDao provinceDao;

    @Test
    public void queryProvinceListTest(){
        Province province = provinceDao.queryById(1);
        Assert.assertTrue(province != null);
    }

}