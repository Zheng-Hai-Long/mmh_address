package com.mmh.dao;

import com.mmh.dto.ProvinceDTO;
import com.mmh.entity.CityArea;

public interface CityAreaAddDao {

    /**
     * 向新表插入要更新的区信息
     * @param data
     * @return
     */
    int insert(CityArea data);

}
