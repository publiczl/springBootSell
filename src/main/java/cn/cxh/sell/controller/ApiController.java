package cn.cxh.sell.controller;

import cn.cxh.sell.config.ProjectUrlConfig;
import cn.cxh.sell.enttry.ProductCategory;
import cn.cxh.sell.service.ProductCategoryService;
import cn.cxh.sell.service.SellInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ApiController {
    @Autowired
    private SellInfoService sellInfoService;
    @Autowired
    private ProductCategoryService productCategoryService;
    private   Map map =new HashMap();

    @Autowired
    private ProjectUrlConfig projectUrlConfig;


    @ResponseBody
    @GetMapping(value = "/user/data")
    public Object getData(){

        map.put("sell",sellInfoService.getAll());
        return map;
    }

    @ResponseBody
    @GetMapping("/productCategory/data")
    public Object getProductCategory(){

      map.put("data",productCategoryService.getAll());
      return map;

    }

    @GetMapping("/gt")
    public Object getP(){
//        System.out.println("ccx:"+pt.getSell());

        return projectUrlConfig;

    }

}
