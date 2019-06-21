package cn.cxh.sell.enttry;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by SqMax on 2018/3/18.
 */

@Data
public class OrderDetail implements Serializable {

    private String detailId;

    /**订单id*/
    private String orderId;

    /**商品id*/
    private String productId;

    /**商品名称*/
    private String productName;

    /**商品单价*/
    private BigDecimal productPrice;

    /**商品数量*/
    private Integer productQuantity;

    /**商品小图*/
    private String productIcon;

}
