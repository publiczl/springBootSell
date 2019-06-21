package cn.cxh.sell.enttry;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
@Data
public class ProductCategory implements Serializable {
    /**类目id.**/
    private Integer categoryId;
    /**类目名字**/
    private String categoryName;
    /** 类目编号**/
    private Integer categoryType;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date updateTime;

}
