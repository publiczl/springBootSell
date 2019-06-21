package cn.cxh.sell.controller;


import cn.cxh.sell.dto.OrderDTO;
import cn.cxh.sell.enums.ResultEnum;
import cn.cxh.sell.exception.SellException;
import cn.cxh.sell.service.PayService;
import cn.cxh.sell.utils.KeyUtil;
import com.alibaba.fastjson.JSON;
import com.lly835.bestpay.model.PayResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Random;

/**
 * Created by SqMax on 2018/3/26.
 */
@Controller
@RequestMapping("/service")
@Slf4j
public class PayController {

//    @Autowired
//    private OrderService orderService;

    @Autowired
    private PayService payService;

    @GetMapping("/pay")
    public ModelAndView index(@RequestParam("openid") String openid,
                              @RequestParam("orderId") String orderId,
                              @RequestParam("returnUrl") String returnUrl,
                              Map<String,Object> map){
        log.info("openid={}",openid);
        //1.查询订单
//        String orderId="1234563";
//        OrderDTO orderDTO=orderService.findOne(orderId);
//        if(orderDTO==null){
//            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
//        }

        OrderDTO orderDTO=new OrderDTO();
        orderDTO.setOrderId(KeyUtil.genUniqueKey());
        orderDTO.setOrderAmount(BigDecimal.valueOf(12.98));
        //2.发起支付
        orderDTO.setBuyerOpenid(openid);
        PayResponse payResponse=payService.create(orderDTO);

        map.put("payResponse",payResponse);
        map.put("returnUrl","http://zmm.cqhuaye.cn/service");
        log.info("pay:{}",JSON.toJSONString(payResponse));
        return new ModelAndView("pay/create",map);
    }

    @GetMapping("/create")
    public ModelAndView create(@RequestParam("orderId") String orderId,
                               @RequestParam("returnUrl") String returnUrl,
                               Map<String,Object> map){
        //1.查询订单
      //  OrderDTO orderDTO=orderService.findOne(orderId);
//        if(orderDTO==null){
//            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
//        }
        OrderDTO orderDTO=new OrderDTO();
        orderDTO.setOrderId("123456789");
        orderDTO.setOrderAmount(BigDecimal.valueOf(11));
        //2.发起支付
        PayResponse payResponse=payService.create(orderDTO);

        map.put("payResponse",payResponse);
        map.put("returnUrl",returnUrl);

        return new ModelAndView("pay/create",map);
    }

    /**
     * 微信异步通知
     * @param notifyData
     */
    @PostMapping("/notify")
    public ModelAndView notify(@RequestBody String notifyData){

        log.info("notifyData:{}",notifyData);
        payService.notify(notifyData);

        //返回给微信处理结果
//        String string="";
        return new ModelAndView("pay/success");
    }



}
