package com.mmh.dao;

import com.mmh.dto.ProvinceDTO;
import com.mmh.entity.CityArea;

import java.util.ArrayList;

public interface CityAreaDao {

    /**
     * 根据市的ID查询区的信息
     * @param CITY_AREA_NUM_ID
     * @return
     */
    CityArea queryById(Integer CITY_AREA_NUM_ID);

    /**
     * 根据区的ID与区的名称查询区的信息
     * @param param
     * @return
     */
    CityArea queryByParam(ProvinceDTO param);

    ArrayList<CityArea> queryAll();

    /**
     * 根据省ID查询一条区信息，主要为了取出（分公司主键（DIV_NUM_ID），县(区)简码（CITY_AREA_SIM_NO））
     * @param PRV_NUM_ID
     * @return
     */
    CityArea queryOneByProvinceId(Integer PRV_NUM_ID);

}
