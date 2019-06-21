package cn.cxh.sell.dao;

import cn.cxh.sell.enttry.SellInfo;
import org.apache.ibatis.annotations.*;


import java.util.List;
public interface SellInfoDao {

//        定义结果集
        @Results(id = "sellInfoMap",value = {
                @Result(property = "sellerId",  column = "seller_id"),
                @Result(property = "userName", column = "user_name"),
                @Result(property = "password", column = "password"),
        })
        @Select("select * from sell_info")
        List<SellInfo> findAll();

        @Select("select * from sell_info where seller_id=#{id}")
        @ResultMap("sellInfoMap")
        SellInfo findById(String id);

        @Select("select * from sell_info where user_name=#{userNme}")
        SellInfo findByUserName(String userNme);

        @Insert("insert into sell_info(seller_id,user_name,password) values(#{sellerId},#{userName},#{password})")
        int add(SellInfo sellInfo);


}
