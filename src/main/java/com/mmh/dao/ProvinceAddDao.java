package com.mmh.dao;

import com.mmh.dto.ProvinceDTO;
import com.mmh.entity.Province;

public interface ProvinceAddDao {

    /**
     * 向新表插入要更新的省信息
     * @param data
     * @return
     */
    int insert(Province data);

}
