package com.mmh.dto;


import java.io.Serializable;

public class ProvinceDTO implements Serializable {

    private Integer code;

    private String addressName;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ProvinceDTO{");
        sb.append("code=").append(code);
        sb.append(", addressName='").append(addressName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
