package cn.cxh.sell.controller;

import cn.cxh.sell.enttry.SellInfo;
import cn.cxh.sell.service.SellInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.Map;

@Controller
@RequestMapping("/service")
public class Login {
    @Autowired
    private SellInfoService sellInfoService;
    @GetMapping("/login")
    public String login(Map map){
        map.put("msg","欢迎登陆");
        return "login";
    }

    @PostMapping("/login")
    public String login(SellInfo sellInfo,Map map){
            if(sellInfoService.cheackLogin(sellInfo)){
                //map.put("title","后台管理系统");
                return "admin/index";
            }
            return "login";
    }

    @GetMapping("/index")
    public String index(Map map){
        map.put("title","首页");
        return "admin/index";
    }
}
