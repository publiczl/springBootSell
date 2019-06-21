package cn.cxh.sell.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/service1/")
public class ServiceController {

    @GetMapping("index")
    public void tt(){
        System.out.println("asda");
    }
}
