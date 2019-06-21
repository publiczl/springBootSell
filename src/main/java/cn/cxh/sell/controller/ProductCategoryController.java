package cn.cxh.sell.controller;

import cn.cxh.sell.enttry.ProductCategory;
import cn.cxh.sell.service.ProductCategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Controller
public class ProductCategoryController {
    @Resource
    private ProductCategoryService productCategoryService;

    @GetMapping("/productCategory/list")
    public String list(){
           return "admin/shoptype/list";
    }
    @GetMapping("/productCategory/add")
    public String add(){
        return "admin/shoptype/add";
    }

    @PostMapping("/productCategory/save")
    public String save(ProductCategory productCategory){
        //SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        if(productCategory.getCategoryId()!=null){ //提交有ID代表更新
            productCategory.setUpdateTime(new Date());
            productCategoryService.updateById(productCategory);


        }else {
            productCategory.setCreateTime(new Date());
            productCategoryService.addProductCategory(productCategory);
        }


        return "redirect:/productCategory/list";
    }

    @GetMapping("/productCategory/edit/{id}")
    public String edit(@PathVariable int id, Model model){
        System.out.println(id);
        model.addAttribute("category",productCategoryService.getById(id));
      //map.put("category", "qq");
        return "admin/shoptype/edit";
    }
    @GetMapping("/productCategory/del/{id}")
    public String del(@PathVariable int id){
        if(productCategoryService.delById(id)){
            return "redirect:/productCategory/list";
        }else {
            return "redirect:/productCategory/edit/"+id;
        }

    }




}
