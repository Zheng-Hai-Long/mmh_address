package com.mmh.dao;

import com.mmh.dto.ProvinceDTO;
import com.mmh.entity.City;

import java.lang.reflect.Array;
import java.util.ArrayList;

public interface CityDao {

    /**
     * 根据市的ID查询市的信息
     * @param CITY_NUM_ID
     * @return
     */
    City queryById(Integer CITY_NUM_ID);

    /**
     * 根据市的ID与区的名称查询市的信息
     * @param param
     * @return
     */
    City queryByParam(ProvinceDTO param);

    ArrayList<City> queryAll();

}
