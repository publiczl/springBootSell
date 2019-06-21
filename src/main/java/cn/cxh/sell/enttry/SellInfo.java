package cn.cxh.sell.enttry;

import lombok.Data;
import java.io.Serializable;

@Data
public class SellInfo implements Serializable {
    private String sellerId;
    private String userName;
    private String password;
    private String openId;
}
