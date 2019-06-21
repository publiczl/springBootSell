package cn.cxh.sell.service;

import cn.cxh.sell.dao.ProductCategoryDao;
import cn.cxh.sell.enttry.ProductCategory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProductCategoryService {
    @Resource
    private ProductCategoryDao productCategoryDao;

   public List<ProductCategory> getAll(){
        return productCategoryDao.findAll();
    }

    public ProductCategory getById(int id){
       return productCategoryDao.findByid(id);
    }

    public  boolean addProductCategory(ProductCategory productCategory){
        return productCategoryDao.add(productCategory)>0;
    }
   public boolean updateById(ProductCategory productCategory){
        return productCategoryDao.update(productCategory)>0;
    }
    public boolean delById(int id){
        return productCategoryDao.delByid(id)>0;
    }




}
