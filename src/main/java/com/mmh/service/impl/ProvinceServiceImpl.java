package com.mmh.service.impl;

import com.mmh.dao.*;
import com.mmh.dto.AddressDTO;
import com.mmh.dto.ProvinceDTO;
import com.mmh.entity.City;
import com.mmh.entity.CityArea;
import com.mmh.entity.Province;
import com.mmh.service.ProvinceService;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.VerticalAlignment;
import jxl.write.*;
import jxl.write.biff.JxlWriteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ProvinceServiceImpl implements ProvinceService {

    @Autowired
    private ProvinceDao provinceDao;

    @Autowired
    private CityDao cityDao;

    @Autowired
    private CityAreaDao cityAreaDao;

    @Autowired
    private ProvinceAddDao provinceAddDao;

    @Autowired
    private CityAddDao cityAddDao;

    @Autowired
    private CityAreaAddDao cityAreaAddDao;

    @Override
    public Province queryById(Integer PRV_NUM_ID) {
        return provinceDao.queryById(PRV_NUM_ID);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AddressDTO queryNotMatch(ArrayList<ProvinceDTO> provinceDTOList) throws Exception {

        //需更新的省
        ArrayList<Province> provinceUpdateArrayList = new ArrayList<>();
        //需删除的省
        ArrayList<Province> provinceDelArrayList = new ArrayList<>();
        //需新增的省
        ArrayList<Province> provinceAddArrayList = new ArrayList<>();

        //需更新的市
        ArrayList<City> cityUpdateArrayList = new ArrayList<>();
        //需删除的市
        ArrayList<City> cityDelArrayList = new ArrayList<>();
        //需新增的市
        ArrayList<City> cityAddArrayList = new ArrayList<>();

        //需更新的区县
        ArrayList<CityArea> cityAreaUpdateArrayList = new ArrayList<>();
        //需删除的区县
        ArrayList<CityArea> cityAreaDelArrayList = new ArrayList<>();
        //需新增的区县
        ArrayList<CityArea> cityAreaAddArrayList = new ArrayList<>();

        //查询所有的省数据，与最新数据对比，找出过期的省数据
        List<Province> provinceDaoAll = provinceDao.queryAll();
        for (Province province : provinceDaoAll) {
            boolean res = false;
            for (ProvinceDTO provinceDTO2 : provinceDTOList) {
                if(!province.getPRV_NUM_ID().equals(provinceDTO2.getCode())){
                    res = true;
                }
            }
            if(!res){
                provinceDelArrayList.add(province);
            }
        }

        //查询所有的市数据，与最新数据对比，找出过期的市数据
        ArrayList<City> cityAll = cityDao.queryAll();
        for (City city : cityAll) {
            boolean res = false;
            for (ProvinceDTO provinceDTO2 : provinceDTOList) {
                if(!city.getCITY_NUM_ID().equals(provinceDTO2.getCode())){
                    res = true;
                }
            }
            if(!res){
                cityDelArrayList.add(city);
            }
        }

        //查询所有的区数据，与最新数据对比，找出过期的区数据
        ArrayList<CityArea> cityAreaAll = cityAreaDao.queryAll();
        for (CityArea cityArea : cityAreaAll) {
            boolean res = false;
            for (ProvinceDTO provinceDTO2 : provinceDTOList) {
                if(!cityArea.getCITY_AREA_NUM_ID().equals(provinceDTO2.getCode())){
                    res = true;
                }
            }
            if(!res){
                cityAreaDelArrayList.add(cityArea);
            }
        }


        for (int i=0;i<provinceDTOList.size();i++) {
            ProvinceDTO dto = provinceDTOList.get(i);
            ProvinceDTO provinceDTO = provinceDTOList.get(i);
            if(provinceDTO.getCode() != null){
                Province provinceD = provinceDao.queryById(provinceDTO.getCode());
                if(provinceD != null){//数据库省表中有值
                    if(!provinceDTO.getAddressName().replace("省","").replace("市", "")
                            .equals(provinceD.getPRV_NAME())
                            && "内蒙古自治区".equals(provinceDTO.getAddressName())
                            && "广西壮族自治区".equals(provinceDTO.getAddressName())
                            && "西藏自治区".equals(provinceDTO.getAddressName())
                            && "宁夏回族自治区".equals(provinceDTO.getAddressName())
                            && "新疆维吾尔自治区".equals(provinceDTO.getAddressName())
                            && "香港特别行政区".equals(provinceDTO.getAddressName())
                            && "澳门特别行政区".equals(provinceDTO.getAddressName())){//数据需要更新
                        provinceD.setOriginalProvinceName(provinceD.getPRV_NAME());
                        provinceD.setPRV_NAME(provinceDTO.getAddressName());
                        provinceUpdateArrayList.add(provinceD);
                        /*int res = provinceAddDao.insert(provinceD);
                        if (res <= 0) {
                            throw new Exception();
                        }*/
                    }

                }else{//数据库中无值
                    //查询市表中是否有数据
                    City cityD = cityDao.queryById(provinceDTO.getCode());
                    if(cityD != null){//数据库市表中有值
                        if(!provinceDTO.getAddressName().equals(cityD.getCITY_NAME())){
                            CityArea cityAreaD = cityAreaDao.queryById(dto.getCode());
                            if(cityAreaD != null && cityAreaD.getCITY_NUM_ID() != null && cityAreaD.getCITY_AREA_NUM_ID() != null
                                    && cityAreaD.getCITY_AREA_NUM_ID().equals(cityAreaD.getCITY_NUM_ID())){
                                cityAreaD.setOriginalCityAreaName(cityAreaD.getCITY_AREA_NAME());
                                cityAreaD.setCITY_AREA_NAME(dto.getAddressName());
                                cityAreaUpdateArrayList.add(cityAreaD);
                            }
                            cityD.setOriginalCityName(cityD.getCITY_NAME());
                            cityD.setCITY_NAME(dto.getAddressName());
                            cityUpdateArrayList.add(cityD);
                           /* int res = cityAddDao.insert(cityD);
                            if (res <= 0) {
                                throw new Exception();
                            }*/
                        }
                    }else{
                        CityArea cityAreaD = cityAreaDao.queryById(dto.getCode());
                        if(cityAreaD != null){//数据库中区表有值
                            if(!provinceDTO.getAddressName().equals(cityAreaD.getCITY_AREA_NAME())){
                                //将不匹配的区数据放入实体
                                cityAreaD.setOriginalCityAreaName(cityAreaD.getCITY_AREA_NAME());
                                cityAreaD.setCITY_AREA_NAME(dto.getAddressName());
                                cityAreaUpdateArrayList.add(cityAreaD);
                                /*int res = cityAreaAddDao.insert(cityAreaD);
                                if (res <= 0) {
                                    throw new Exception();
                                }*/
                            }
                        }else{
                            //System.out.println(provinceDTO);
                            //将新增的数据放入实体中
                            CityArea cityArea = new CityArea();
                            cityArea.setPRV_NUM_ID(Integer.valueOf(provinceDTO.getCode().toString().substring(0,2) + "0000"));
                            cityArea.setCITY_NUM_ID(Integer.valueOf(provinceDTO.getCode().toString().substring(0,4) + "00"));
                            cityArea.setCITY_AREA_NUM_ID(provinceDTO.getCode());

                            CityArea c =  cityAreaDao.queryOneByProvinceId(cityArea.getPRV_NUM_ID());
                            if(c != null && c.getCITY_AREA_SIM_NO() != null){
                                cityArea.setCITY_AREA_SIM_NO(c.getCITY_AREA_SIM_NO());
                            }
                            if(c != null && c.getDIV_NUM_ID() != null){
                                cityArea.setDIV_NUM_ID(c.getDIV_NUM_ID());
                            }

                            cityArea.setCITY_AREA_NAME(provinceDTO.getAddressName());
                            if(cityArea.getCITY_AREA_NAME().contains("省")){
                                Province p = new Province();
                                p.setPRV_NUM_ID(provinceDTO.getCode());
                                p.setPRV_NAME(provinceDTO.getAddressName().replace("省", ""));
                                provinceAddArrayList.add(p);

                            }else if(cityArea.getCITY_AREA_NAME().contains("市")){
                                cityAreaAddArrayList.add(cityArea);

                                City city = new City();
                                city.setPRV_NUM_ID(cityArea.getPRV_NUM_ID());
                                city.setCITY_NUM_ID(provinceDTO.getCode());
                                city.setCITY_NAME(provinceDTO.getAddressName());
                                cityAddArrayList.add(city);
                            }else{
                                cityAreaAddArrayList.add(cityArea);
                            }
                        }
                    }
                }
            }
        }
        toExcel(new AddressDTO(provinceUpdateArrayList, provinceDelArrayList, provinceAddArrayList, cityUpdateArrayList, cityDelArrayList, cityAddArrayList, cityAreaUpdateArrayList, cityAreaDelArrayList, cityAreaAddArrayList));
        return new AddressDTO(provinceUpdateArrayList, provinceDelArrayList, provinceAddArrayList, cityUpdateArrayList, cityDelArrayList, cityAddArrayList, cityAreaUpdateArrayList, cityAreaDelArrayList, cityAreaAddArrayList);
    }

    public void toExcel(AddressDTO dto) {
        //这里为导出文件存放的路径
        String filePath ="C:/Users/Jack/Desktop/" + UUID.randomUUID() + "/";
        //加入一个uuid随机数是因为
        //每次导出的时候，如果文件存在了，会将其覆盖掉，这里是保存所有的文件
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdirs();
        }

        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        // 给要导出的文件起名为 "测试导出数据表_时间.xls"
        String filePath2 = filePath + "export_province_city_area" + "_" + fmt.format(new Date()) + ".xls";
        WritableWorkbook wb = null;
        try {
            File file2 = new File(filePath2);
            if (!file2.exists()) {//不存在，创建
                file2.createNewFile();
            }
            wb = Workbook.createWorkbook(file2);//创建xls表格文件

            // 表头显示
            WritableCellFormat wcf = new WritableCellFormat();
            wcf.setAlignment(Alignment.CENTRE);// 水平居中
            wcf.setWrap(true);
            wcf.setVerticalAlignment(VerticalAlignment.CENTRE);// 垂直居中
            wcf.setFont(new WritableFont(WritableFont.TIMES,13, WritableFont.BOLD));// 表头字体 加粗 13号
            wcf.setBackground(jxl.format.Colour.PERIWINKLE);
            // 内容显示
            WritableCellFormat wcf2 = new WritableCellFormat();
            wcf2.setWrap(true);//设置单元格可以换行
            wcf2.setAlignment(Alignment.CENTRE);//水平居中
            wcf2.setVerticalAlignment(VerticalAlignment.CENTRE);// 垂直居中
            wcf2.setFont( new WritableFont(WritableFont.TIMES,11));// 内容字体 11号

            //导出的xls的第一页，第二页就是0换成1，“sheet1”，也可以修改为自己想要的显示的内容
            //需要修改的省
            WritableSheet updateProvinceWs = wb.createSheet("需要修改的省", 0);
            //需要删除的省
            WritableSheet delProvinceWs = wb.createSheet("需要删除的省", 1);
            //需要新增的省
            WritableSheet addProvinceWs = wb.createSheet("需要新增的省", 2);

            //需要修改的市
            WritableSheet updateCityWs = wb.createSheet("需要修改的市", 3);
            //需要删除的市
            WritableSheet delCityWs = wb.createSheet("需要删除的市", 4);
            //需要新增的市
            WritableSheet addCityWs = wb.createSheet("需要新增的市", 5);

            //需要修改的区
            WritableSheet updateCityAreaWs = wb.createSheet("需要修改的区", 6);
            //需要删除的区
            WritableSheet delCityAreaWs = wb.createSheet("需要删除的区", 7);
            //需要新增的区
            WritableSheet addCityAreaWs = wb.createSheet("需要新增的区", 8);


            //将要更新的省数据写入Excel
            updateProvinceWs.addCell(new Label(0,0, "省ID（PRV_NUM_ID）"));//代表着表格中第一列的第一行显示查询结果几个字
            updateProvinceWs.addCell(new Label(1,0, "更新城市名称（CITY_NAME）"));
            updateProvinceWs.addCell(new Label(2,0, "原名称"));

            int k =1 ;//从第二行开始写入数据

            ArrayList<Province> provinceUpdateArrayList = dto.getProvinceUpdateArrayList();
            if(!CollectionUtils.isEmpty(provinceUpdateArrayList)){
                for (int i = 0; i < provinceUpdateArrayList.size(); i++) {
                    updateProvinceWs.addCell(new Label(0, k, provinceUpdateArrayList.get(i).getPRV_NUM_ID()+"", wcf2));
                    updateProvinceWs.addCell(new Label(1, k, provinceUpdateArrayList.get(i).getPRV_NAME(), wcf2));
                    updateProvinceWs.addCell(new Label(2, k, provinceUpdateArrayList.get(i).getOriginalProvinceName(), wcf2));
                    k++;
                }
            }

            //将要删除的省数据放入Excel中
            delProvinceWs.addCell(new Label(0,0, "省ID（PRV_NUM_ID）"));
            delProvinceWs.addCell(new Label(1,0, "城市名称（CITY_NAME）"));

            int j =1 ;//从第二行开始写入数据

            ArrayList<Province> provinceDelArrayList = dto.getProvinceDelArrayList();
            if(!CollectionUtils.isEmpty(provinceDelArrayList)){
                for (int i = 0; i < provinceDelArrayList.size(); i++) {
                    delProvinceWs.addCell(new Label(0, j, provinceDelArrayList.get(i).getPRV_NUM_ID()+"", wcf2));
                    delProvinceWs.addCell(new Label(1, j, provinceDelArrayList.get(i).getPRV_NAME(), wcf2));
                    j++;
                }
            }

            //将要新增的省数据放入Excel中
            addProvinceWs.addCell(new Label(0,0, "省ID（PRV_NUM_ID）"));
            addProvinceWs.addCell(new Label(1,0, "城市名称（CITY_NAME）"));

            j =1 ;//从第二行开始写入数据

            ArrayList<Province> provinceAddArrayList = dto.getProvinceAddArrayList();
            if(!CollectionUtils.isEmpty(provinceAddArrayList)){
                for (int i = 0; i < provinceAddArrayList.size(); i++) {
                    addProvinceWs.addCell(new Label(0, j, provinceAddArrayList.get(i).getPRV_NUM_ID()+"", wcf2));
                    addProvinceWs.addCell(new Label(1, j, provinceAddArrayList.get(i).getPRV_NAME(), wcf2));
                    j++;
                }
            }


            //将要修改的市信息放入到Excel中
            updateCityWs.addCell(new Label(0,0, "省ID（PRV_NUM_ID）"));
            updateCityWs.addCell(new Label(1,0, "市ID（CITY_NUM_ID）"));
            updateCityWs.addCell(new Label(2,0, "更新城市名称（CITY_NAME）"));
            updateCityWs.addCell(new Label(3,0, "原名称"));

            int l =1 ;//从第二行开始写入数据

            ArrayList<City> cityUpdateArrayList = dto.getCityUpdateArrayList();
            if(!CollectionUtils.isEmpty(cityUpdateArrayList)){
                for (int i = 0; i < cityUpdateArrayList.size(); i++) {
                    updateCityWs.addCell(new Label(0, l, cityUpdateArrayList.get(i).getPRV_NUM_ID()+"", wcf2));
                    updateCityWs.addCell(new Label(1, l, cityUpdateArrayList.get(i).getCITY_NUM_ID()+"", wcf2));
                    updateCityWs.addCell(new Label(2, l, cityUpdateArrayList.get(i).getCITY_NAME(), wcf2));
                    updateCityWs.addCell(new Label(3, l, cityUpdateArrayList.get(i).getOriginalCityName(), wcf2));
                    l++;
                }
            }

            //将要删除的市信息放入到Excel中
            delCityWs.addCell(new Label(0,0, "省ID（PRV_NUM_ID）"));
            delCityWs.addCell(new Label(1,0, "市ID（CITY_NUM_ID）"));
            delCityWs.addCell(new Label(2,0, "城市名称（CITY_NAME）"));

            int m =1 ;//从第二行开始写入数据

            ArrayList<City> cityDelArrayList = dto.getCityDelArrayList();
            if(!CollectionUtils.isEmpty(cityDelArrayList)){
                for (int i = 0; i < cityDelArrayList.size(); i++) {
                    delCityWs.addCell(new Label(0, m, cityDelArrayList.get(i).getPRV_NUM_ID()+"", wcf2));
                    delCityWs.addCell(new Label(1, m, cityDelArrayList.get(i).getCITY_NUM_ID()+"", wcf2));
                    delCityWs.addCell(new Label(2, m, cityDelArrayList.get(i).getCITY_NAME(), wcf2));
                    m++;
                }
            }

            //将要新增的市信息放入到Excel中
            addCityWs.addCell(new Label(0,0, "省ID（PRV_NUM_ID）"));
            addCityWs.addCell(new Label(1,0, "市ID（CITY_NUM_ID）"));
            addCityWs.addCell(new Label(2,0, "城市名称（CITY_NAME）"));

            m =1 ;//从第二行开始写入数据

            ArrayList<City> cityAddArrayList = dto.getCityAddArrayList();
            if(!CollectionUtils.isEmpty(cityAddArrayList)){
                for (int i = 0; i < cityAddArrayList.size(); i++) {
                    addCityWs.addCell(new Label(0, m, cityAddArrayList.get(i).getPRV_NUM_ID()+"", wcf2));
                    addCityWs.addCell(new Label(1, m, cityAddArrayList.get(i).getCITY_NUM_ID()+"", wcf2));
                    addCityWs.addCell(new Label(2, m, cityAddArrayList.get(i).getCITY_NAME(), wcf2));
                    m++;
                }
            }


            //将要更新的区县信息放入到Excel中
            updateCityAreaWs.addCell(new Label(0,0, "省ID（PRV_NUM_ID）"));
            updateCityAreaWs.addCell(new Label(1,0, "市ID（CITY_NUM_ID）"));
            updateCityAreaWs.addCell(new Label(2,0, "区ID（CITY_AREA_NUM_ID）"));
            updateCityAreaWs.addCell(new Label(3,0, "更新县(区)名称（CITY_AREA_NAME）"));
            updateCityAreaWs.addCell(new Label(4,0, "原名称"));

            int o =1 ;//从第二行开始写入数据

            ArrayList<CityArea> cityAreaUpdateArrayList = dto.getCityAreaUpdateArrayList();
            if(!CollectionUtils.isEmpty(cityAreaUpdateArrayList)){
                for (int i = 0; i < cityAreaUpdateArrayList.size(); i++) {
                    updateCityAreaWs.addCell(new Label(0, o, cityAreaUpdateArrayList.get(i).getPRV_NUM_ID()+"", wcf2));
                    updateCityAreaWs.addCell(new Label(1, o, cityAreaUpdateArrayList.get(i).getCITY_NUM_ID()+"", wcf2));
                    updateCityAreaWs.addCell(new Label(2, o, cityAreaUpdateArrayList.get(i).getCITY_AREA_NUM_ID()+"", wcf2));
                    updateCityAreaWs.addCell(new Label(3, o, cityAreaUpdateArrayList.get(i).getCITY_AREA_NAME(), wcf2));
                    updateCityAreaWs.addCell(new Label(4, o, cityAreaUpdateArrayList.get(i).getOriginalCityAreaName(), wcf2));
                    o++;
                }
            }

            //将要删除的区县信息放入到Excel中
            delCityAreaWs.addCell(new Label(0,0, "省ID（PRV_NUM_ID）"));
            delCityAreaWs.addCell(new Label(1,0, "市ID（CITY_NUM_ID）"));
            delCityAreaWs.addCell(new Label(2,0, "区ID（CITY_AREA_NUM_ID）"));
            delCityAreaWs.addCell(new Label(3,0, "县(区)名称（CITY_AREA_NAME）"));

            int p =1 ;//从第二行开始写入数据

            ArrayList<CityArea> cityAreaDelArrayList = dto.getCityAreaDelArrayList();
            if(!CollectionUtils.isEmpty(cityAreaDelArrayList)){
                for (int i = 0; i < cityAreaDelArrayList.size(); i++) {
                    delCityAreaWs.addCell(new Label(0, o, cityAreaDelArrayList.get(i).getPRV_NUM_ID()+"", wcf2));
                    delCityAreaWs.addCell(new Label(1, o, cityAreaDelArrayList.get(i).getCITY_NUM_ID()+"", wcf2));
                    delCityAreaWs.addCell(new Label(2, o, cityAreaDelArrayList.get(i).getCITY_AREA_NUM_ID()+"", wcf2));
                    delCityAreaWs.addCell(new Label(3, o, cityAreaDelArrayList.get(i).getCITY_AREA_NAME(), wcf2));
                    p++;
                }
            }

            //将要添加的省市区县信息放入到Excel中
            addCityAreaWs.addCell(new Label(0,0, "省ID（PRV_NUM_ID）"));
            addCityAreaWs.addCell(new Label(1,0, "市ID（CITY_NUM_ID）"));
            addCityAreaWs.addCell(new Label(2,0, "区ID（CITY_AREA_NUM_ID）"));
            addCityAreaWs.addCell(new Label(3,0, "县(区)简码（CITY_AREA_SIM_NO）"));
            addCityAreaWs.addCell(new Label(4,0, "县(区)名称（CITY_AREA_NAME）"));
            addCityAreaWs.addCell(new Label(5,0, "分公司主键（DIV_NUM_ID）"));

            int q =1 ;//从第二行开始写入数据

            ArrayList<CityArea> cityAreaAddArrayList = dto.getCityAreaAddArrayList();
            if(!CollectionUtils.isEmpty(cityAreaAddArrayList)){
                for (int i = 0; i < cityAreaAddArrayList.size(); i++) {
                    addCityAreaWs.addCell(new Label(0, q, cityAreaAddArrayList.get(i).getPRV_NUM_ID()+"", wcf2));
                    addCityAreaWs.addCell(new Label(1, q, cityAreaAddArrayList.get(i).getCITY_NUM_ID()+"", wcf2));
                    addCityAreaWs.addCell(new Label(2, q, cityAreaAddArrayList.get(i).getCITY_AREA_NUM_ID()+"", wcf2));
                    addCityAreaWs.addCell(new Label(3, q, cityAreaAddArrayList.get(i).getCITY_AREA_SIM_NO(), wcf2));
                    addCityAreaWs.addCell(new Label(4, q, cityAreaAddArrayList.get(i).getCITY_AREA_NAME(), wcf2));
                    addCityAreaWs.addCell(new Label(5, q, cityAreaAddArrayList.get(i).getDIV_NUM_ID()+"", wcf2));
                    q++;
                }
            }

            wb.write();//写入，到这里已经生成完成，可以在相应目录下找到刚才生成的文件
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JxlWriteException e) {
            e.printStackTrace();
        } catch (WriteException e) {
            e.printStackTrace();
        } finally {
            try {
                if (wb != null) {
                    wb.close();
                }
            } catch (WriteException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //这个是我们项目中，是把刚才生成的文件，响应到前台，进行下载、保存，可省略。
        //downLoadFile(filePath2);
    }
}
