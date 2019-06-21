package cn.cxh.sell.controller;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/weixin/")
@Slf4j
public class WeixinController {
        @GetMapping("auth")
        public void auth(@RequestParam("code") String code){
            log.info("进入auth方法.......");
            log.info("code={}",code);

            String url="https://api.weixin.qq.com/sns/oauth2/access_token?"
                    +"appid=wxdcf91e9f784bfad0&secret=c467c06d98a2447f5ddef6ca8f47f268&code="+ code +"&grant_type=authorization_code";
            RestTemplate restTemplate=new RestTemplate();
            String response=restTemplate.getForObject(url,String.class);
            log.info("response={}",response);
        }


}
