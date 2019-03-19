package com.mmh.controller;

import com.mmh.dto.AddressDTO;
import com.mmh.dto.ProvinceDTO;
import com.mmh.entity.Province;
import com.mmh.service.ProvinceService;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Var;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import java.net.URL;
import java.util.ArrayList;

@RestController
@Slf4j
@RequestMapping("/province")
public class ProvinceController {

    @Autowired
    private ProvinceService provinceService;

    @GetMapping("/create/excel")
    public String createAddressExcel() throws IOException {

        File file = new File("C:/Users/Jack/Desktop/201902061009.html");
        Document doc = Jsoup.parse(file, "utf-8");
        //从链接去获取页面省市区信息，信息不全
        //Document doc= Jsoup.parse(new URL("http://www.mca.gov.cn/article/sj/xzqh/2018/201804-12/20181201301111.html"), 2000);

        ArrayList<ProvinceDTO> provinceDTOList = paseDocumentHtml(doc);
        try {
            AddressDTO addressDTO = provinceService.queryNotMatch(provinceDTOList);
            System.out.println(addressDTO);
        }catch (Exception e){
            e.printStackTrace();
            return "失败";
        }
        return "成功";
    }

    /**
     * 将Document对象转换成 省市区对象
     * @param doc
     * @return
     */
    public ArrayList<ProvinceDTO> paseDocumentHtml(Document doc){
        ArrayList<ProvinceDTO> provinceDTOList = new ArrayList<ProvinceDTO>();
        Elements tr = doc.getElementsByTag("tr");
        for (Element element : tr) {
            if("19".equals(element.attr("height"))){
                Elements td = element.getElementsByTag("td");
                ProvinceDTO provinceDTO = new ProvinceDTO();
                for (int i=1;i<td.size();i++) {
                    if(i == 1) {
                        //System.out.println(td.get(i).html());
                        if(td.get(i).html().contains("<span")){
                            provinceDTO.setCode(Integer.valueOf(td.get(i).getElementsByTag("span").get(0).html()));
                        }else{
                            provinceDTO.setCode(Integer.valueOf(td.get(i).html()));
                        }

                    }
                    if(i == 2){
                        //System.out.println(td.get(i).html());
                        if(td.get(i).html().contains("<span")){
                            String v = td.get(i).getElementsByTag("span").get(0).html();
                            if(v.contains("&nbsp;")){//嘉黎县<span style="mso-spacerun:yes">&nbsp;</span>
                                v = td.get(i).html().substring(0, td.get(i).html().indexOf("<span"));
                            }
                            provinceDTO.setAddressName(v);
                        }else{
                            provinceDTO.setAddressName(td.get(i).html());
                        }
                    }
                }
                provinceDTOList.add(provinceDTO);
            }
        }
        return provinceDTOList;
    }
}
