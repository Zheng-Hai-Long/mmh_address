package com.mmh.dao;

import com.mmh.dto.ProvinceDTO;
import com.mmh.entity.City;

public interface CityAddDao {

    /**
     * 向新表插入要更新的市信息
     * @param data
     * @return
     */
    int insert(City data);

}
