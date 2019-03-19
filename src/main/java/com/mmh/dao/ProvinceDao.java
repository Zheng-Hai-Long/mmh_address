package com.mmh.dao;

import com.mmh.dto.ProvinceDTO;
import com.mmh.entity.Province;

import java.util.ArrayList;

public interface ProvinceDao {

    /**
     * 根据省的ID查询省的信息
     * @param PRV_NUM_ID
     * @return
     */
    Province queryById(Integer PRV_NUM_ID);

    /**
     * 根据省的ID与区的名称查询省的信息
     * @param param
     * @return
     */
    Province queryByParam(ProvinceDTO param);

    ArrayList<Province> queryAll();

}
