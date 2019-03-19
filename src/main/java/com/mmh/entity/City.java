package com.mmh.entity;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

public class City {

    /**
     * 省份
     */
    private Integer PRV_NUM_ID;

    /**
     * 城市
     */
    private Integer CITY_NUM_ID;

    /**
     * 城市简码
     */
    private String CITY_SIM_NO;

    /**
     * 城市名称
     */
    private String CITY_NAME;

    /**
     * 城市全称字母简拼
     */
    private String CITY_SIM_NAME;

    /**
     *
     * 默认值：0
     */
    private Integer DEL_STATE;

    /**
     * 市原来的名称
     */
    private String originalCityName;

    public String getOriginalCityName() {
        return originalCityName;
    }

    public void setOriginalCityName(String originalCityName) {
        this.originalCityName = originalCityName;
    }

    public Integer getPRV_NUM_ID() {
        return PRV_NUM_ID;
    }

    public void setPRV_NUM_ID(Integer PRV_NUM_ID) {
        this.PRV_NUM_ID = PRV_NUM_ID;
    }

    public Integer getCITY_NUM_ID() {
        return CITY_NUM_ID;
    }

    public void setCITY_NUM_ID(Integer CITY_NUM_ID) {
        this.CITY_NUM_ID = CITY_NUM_ID;
    }

    public String getCITY_SIM_NO() {
        return CITY_SIM_NO;
    }

    public void setCITY_SIM_NO(String CITY_SIM_NO) {
        this.CITY_SIM_NO = CITY_SIM_NO;
    }

    public String getCITY_NAME() {
        return CITY_NAME;
    }

    public void setCITY_NAME(String CITY_NAME) {
        this.CITY_NAME = CITY_NAME;
    }

    public String getCITY_SIM_NAME() {
        return CITY_SIM_NAME;
    }

    public void setCITY_SIM_NAME(String CITY_SIM_NAME) {
        this.CITY_SIM_NAME = CITY_SIM_NAME;
    }

    public Integer getDEL_STATE() {
        return DEL_STATE;
    }

    public void setDEL_STATE(Integer DEL_STATE) {
        this.DEL_STATE = DEL_STATE;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("City{");
        sb.append("PRV_NUM_ID=").append(PRV_NUM_ID);
        sb.append(", CITY_NUM_ID=").append(CITY_NUM_ID);
        sb.append(", CITY_SIM_NO='").append(CITY_SIM_NO).append('\'');
        sb.append(", CITY_NAME='").append(CITY_NAME).append('\'');
        sb.append(", CITY_SIM_NAME='").append(CITY_SIM_NAME).append('\'');
        sb.append(", DEL_STATE=").append(DEL_STATE);
        sb.append(", originalCityName='").append(originalCityName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
