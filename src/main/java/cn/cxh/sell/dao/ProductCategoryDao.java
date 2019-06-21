package cn.cxh.sell.dao;

import cn.cxh.sell.enttry.ProductCategory;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.Alias;

import java.util.List;

public interface ProductCategoryDao {
    @Results(id="productCategoryMap",value = {
            @Result(property = "categoryId",column = "category_id"),
            @Result(property = "categoryName",column = "category_name"),
            @Result(property = "categoryType",column = "category_type"),
//            @Result(property = "createTime",column = "create_time"),
//            @Result(property = "updateTime",column = "update_time")
})
    @Select("select * from product_category")
    List<ProductCategory> findAll();

    @Select("select * from product_category where category_id=#{id}")
    @ResultMap(value = "productCategoryMap")
    ProductCategory findByid(int id);

    @Insert("insert into product_category(category_name,category_type,create_time) " +
            "values (#{categoryName},#{categoryType},#{createTime})"
    )
    int add(ProductCategory productCategory);
    @Update(
            "update product_category set category_name=#{categoryName} where category_id=#{categoryId}"
    )
    int update(ProductCategory productCategory);
    @Delete("delete from product_category where category_id=#{id}")
    int delByid(int id);
}
