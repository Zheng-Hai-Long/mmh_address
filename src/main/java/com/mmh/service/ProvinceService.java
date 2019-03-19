package com.mmh.service;

import com.mmh.dto.AddressDTO;
import com.mmh.dto.ProvinceDTO;
import com.mmh.entity.Province;

import java.util.ArrayList;

public interface ProvinceService {

    /**
     * 根据id获取省份信息
     * @param PRV_NUM_ID
     * @return
     */
    Province queryById(Integer PRV_NUM_ID);

    /**
     * 筛选出与数据库中不对应的省市区,并插入到数据库中
     * @param provinceDTOList
     * @return
     */
    AddressDTO queryNotMatch(ArrayList<ProvinceDTO> provinceDTOList) throws Exception;
}
