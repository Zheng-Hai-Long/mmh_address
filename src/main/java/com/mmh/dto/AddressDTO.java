package com.mmh.dto;

import com.mmh.entity.City;
import com.mmh.entity.CityArea;
import com.mmh.entity.Province;

import java.io.Serializable;
import java.util.ArrayList;

public class AddressDTO implements Serializable {

    public AddressDTO(ArrayList<Province> provinceUpdateArrayList, ArrayList<Province> provinceDelArrayList, ArrayList<Province> provinceAddArrayList, ArrayList<City> cityUpdateArrayList, ArrayList<City> cityDelArrayList, ArrayList<City> cityAddArrayList, ArrayList<CityArea> cityAreaUpdateArrayList, ArrayList<CityArea> cityAreaDelArrayList, ArrayList<CityArea> cityAreaAddArrayList) {
        this.provinceUpdateArrayList = provinceUpdateArrayList;
        this.provinceDelArrayList = provinceDelArrayList;
        this.provinceAddArrayList = provinceAddArrayList;
        this.cityUpdateArrayList = cityUpdateArrayList;
        this.cityDelArrayList = cityDelArrayList;
        this.cityAddArrayList = cityAddArrayList;
        this.cityAreaUpdateArrayList = cityAreaUpdateArrayList;
        this.cityAreaDelArrayList = cityAreaDelArrayList;
        this.cityAreaAddArrayList = cityAreaAddArrayList;
    }

    public AddressDTO(){

    }

    /*ArrayList<Province> provinceList;

    ArrayList<City> cityList;

    ArrayList<CityArea> cityAreaList;*/

    //需更新的省
    ArrayList<Province> provinceUpdateArrayList;

    //需删除的省
    ArrayList<Province> provinceDelArrayList;

    //需新增的省
    ArrayList<Province> provinceAddArrayList;

    //需更新的市
    ArrayList<City> cityUpdateArrayList;

    //需删除的市
    ArrayList<City> cityDelArrayList;

    //需新增的市
    ArrayList<City> cityAddArrayList;

    //需更新的区县
    ArrayList<CityArea> cityAreaUpdateArrayList;

    //需删除的区县
    ArrayList<CityArea> cityAreaDelArrayList;

    //需新增的区县
    ArrayList<CityArea> cityAreaAddArrayList;

    public ArrayList<Province> getProvinceUpdateArrayList() {
        return provinceUpdateArrayList;
    }

    public void setProvinceUpdateArrayList(ArrayList<Province> provinceUpdateArrayList) {
        this.provinceUpdateArrayList = provinceUpdateArrayList;
    }

    public ArrayList<Province> getProvinceDelArrayList() {
        return provinceDelArrayList;
    }

    public void setProvinceDelArrayList(ArrayList<Province> provinceDelArrayList) {
        this.provinceDelArrayList = provinceDelArrayList;
    }

    public ArrayList<Province> getProvinceAddArrayList() {
        return provinceAddArrayList;
    }

    public void setProvinceAddArrayList(ArrayList<Province> provinceAddArrayList) {
        this.provinceAddArrayList = provinceAddArrayList;
    }

    public ArrayList<City> getCityUpdateArrayList() {
        return cityUpdateArrayList;
    }

    public void setCityUpdateArrayList(ArrayList<City> cityUpdateArrayList) {
        this.cityUpdateArrayList = cityUpdateArrayList;
    }

    public ArrayList<City> getCityDelArrayList() {
        return cityDelArrayList;
    }

    public void setCityDelArrayList(ArrayList<City> cityDelArrayList) {
        this.cityDelArrayList = cityDelArrayList;
    }

    public ArrayList<City> getCityAddArrayList() {
        return cityAddArrayList;
    }

    public void setCityAddArrayList(ArrayList<City> cityAddArrayList) {
        this.cityAddArrayList = cityAddArrayList;
    }

    public ArrayList<CityArea> getCityAreaUpdateArrayList() {
        return cityAreaUpdateArrayList;
    }

    public void setCityAreaUpdateArrayList(ArrayList<CityArea> cityAreaUpdateArrayList) {
        this.cityAreaUpdateArrayList = cityAreaUpdateArrayList;
    }

    public ArrayList<CityArea> getCityAreaDelArrayList() {
        return cityAreaDelArrayList;
    }

    public void setCityAreaDelArrayList(ArrayList<CityArea> cityAreaDelArrayList) {
        this.cityAreaDelArrayList = cityAreaDelArrayList;
    }

    public ArrayList<CityArea> getCityAreaAddArrayList() {
        return cityAreaAddArrayList;
    }

    public void setCityAreaAddArrayList(ArrayList<CityArea> cityAreaAddArrayList) {
        this.cityAreaAddArrayList = cityAreaAddArrayList;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("AddressDTO{");
        sb.append("provinceUpdateArrayList=").append(provinceUpdateArrayList);
        sb.append(", provinceDelArrayList=").append(provinceDelArrayList);
        sb.append(", provinceAddArrayList=").append(provinceAddArrayList);
        sb.append(", cityUpdateArrayList=").append(cityUpdateArrayList);
        sb.append(", cityDelArrayList=").append(cityDelArrayList);
        sb.append(", cityAddArrayList=").append(cityAddArrayList);
        sb.append(", cityAreaUpdateArrayList=").append(cityAreaUpdateArrayList);
        sb.append(", cityAreaDelArrayList=").append(cityAreaDelArrayList);
        sb.append(", cityAreaAddArrayList=").append(cityAreaAddArrayList);
        sb.append('}');
        return sb.toString();
    }
}
