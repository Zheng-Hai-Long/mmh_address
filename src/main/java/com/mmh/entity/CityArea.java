package com.mmh.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

public class CityArea {

    /**
     * 省份
     */
    private Integer PRV_NUM_ID;

    /**
     * 地/市
     */
    private Integer CITY_NUM_ID;

    /**
     * 县(区)
     */
    private Integer CITY_AREA_NUM_ID;

    /**
     * 县(区)简码
     */
    private String CITY_AREA_SIM_NO;

    /**
     * 县(区)名称
     */
    private String CITY_AREA_NAME;

    /**
     * 分公司主键
     */
    private Integer DIV_NUM_ID;

    /**
     *
     * 默认值：0
     */
    private Integer DEL_STATE;

    /**
     * 区县原来的名称
     */
    private String originalCityAreaName;

    public String getOriginalCityAreaName() {
        return originalCityAreaName;
    }

    public void setOriginalCityAreaName(String originalCityAreaName) {
        this.originalCityAreaName = originalCityAreaName;
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

    public Integer getCITY_AREA_NUM_ID() {
        return CITY_AREA_NUM_ID;
    }

    public void setCITY_AREA_NUM_ID(Integer CITY_AREA_NUM_ID) {
        this.CITY_AREA_NUM_ID = CITY_AREA_NUM_ID;
    }

    public String getCITY_AREA_SIM_NO() {
        return CITY_AREA_SIM_NO;
    }

    public void setCITY_AREA_SIM_NO(String CITY_AREA_SIM_NO) {
        this.CITY_AREA_SIM_NO = CITY_AREA_SIM_NO;
    }

    public String getCITY_AREA_NAME() {
        return CITY_AREA_NAME;
    }

    public void setCITY_AREA_NAME(String CITY_AREA_NAME) {
        this.CITY_AREA_NAME = CITY_AREA_NAME;
    }

    public Integer getDIV_NUM_ID() {
        return DIV_NUM_ID;
    }

    public void setDIV_NUM_ID(Integer DIV_NUM_ID) {
        this.DIV_NUM_ID = DIV_NUM_ID;
    }

    public Integer getDEL_STATE() {
        return DEL_STATE;
    }

    public void setDEL_STATE(Integer DEL_STATE) {
        this.DEL_STATE = DEL_STATE;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("CityArea{");
        sb.append("PRV_NUM_ID=").append(PRV_NUM_ID);
        sb.append(", CITY_NUM_ID=").append(CITY_NUM_ID);
        sb.append(", CITY_AREA_NUM_ID=").append(CITY_AREA_NUM_ID);
        sb.append(", CITY_AREA_SIM_NO='").append(CITY_AREA_SIM_NO).append('\'');
        sb.append(", CITY_AREA_NAME='").append(CITY_AREA_NAME).append('\'');
        sb.append(", DIV_NUM_ID=").append(DIV_NUM_ID);
        sb.append(", DEL_STATE=").append(DEL_STATE);
        sb.append(", originalCityAreaName='").append(originalCityAreaName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
