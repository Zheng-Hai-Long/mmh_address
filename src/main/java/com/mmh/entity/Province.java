package com.mmh.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "gb_prv")
public class Province {

    /**
     * 省份
     */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer PRV_NUM_ID;

    /**
     * 省份简码
     */
    private String PRV_SIM_NO;

    /**
     * 省份名称
     */
    private String PRV_NAME;

    /**
     * 省份简称
     */
    private String PRV_SIM_NAME;

    /**
     *
     * 默认值：0
     */
    private Integer DEL_STATE;

    /**
     * 排序
     * 默认值：0
     */
    private Integer SORT;

    /**
     * 省原来的名称
     */
    private String originalProvinceName;

    public String getOriginalProvinceName() {
        return originalProvinceName;
    }

    public void setOriginalProvinceName(String originalProvinceName) {
        this.originalProvinceName = originalProvinceName;
    }

    public Integer getPRV_NUM_ID() {
        return PRV_NUM_ID;
    }

    public void setPRV_NUM_ID(Integer PRV_NUM_ID) {
        this.PRV_NUM_ID = PRV_NUM_ID;
    }

    public String getPRV_SIM_NO() {
        return PRV_SIM_NO;
    }

    public void setPRV_SIM_NO(String PRV_SIM_NO) {
        this.PRV_SIM_NO = PRV_SIM_NO;
    }

    public String getPRV_NAME() {
        return PRV_NAME;
    }

    public void setPRV_NAME(String PRV_NAME) {
        this.PRV_NAME = PRV_NAME;
    }

    public String getPRV_SIM_NAME() {
        return PRV_SIM_NAME;
    }

    public void setPRV_SIM_NAME(String PRV_SIM_NAME) {
        this.PRV_SIM_NAME = PRV_SIM_NAME;
    }

    public Integer getDEL_STATE() {
        return DEL_STATE;
    }

    public void setDEL_STATE(Integer DEL_STATE) {
        this.DEL_STATE = DEL_STATE;
    }

    public Integer getSORT() {
        return SORT;
    }

    public void setSORT(Integer SORT) {
        this.SORT = SORT;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Province{");
        sb.append("PRV_NUM_ID=").append(PRV_NUM_ID);
        sb.append(", PRV_SIM_NO='").append(PRV_SIM_NO).append('\'');
        sb.append(", PRV_NAME='").append(PRV_NAME).append('\'');
        sb.append(", PRV_SIM_NAME='").append(PRV_SIM_NAME).append('\'');
        sb.append(", DEL_STATE=").append(DEL_STATE);
        sb.append(", SORT=").append(SORT);
        sb.append(", originalProvinceName='").append(originalProvinceName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
