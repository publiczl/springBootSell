package cn.cxh.sell.controller;

import cn.cxh.sell.enttry.SellInfo;
import cn.cxh.sell.service.SellInfoService;
import cn.cxh.sell.service.WebSocketService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mysql.cj.xdevapi.JsonArray;
import com.mysql.cj.xdevapi.JsonString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.UUID;

@Controller
public class UserController {
    @Autowired
    private SellInfoService sellInfoService;
    @Autowired
    private WebSocketService webSocketService;
    @GetMapping("/user/list")
    public String list(@RequestParam(required = false,defaultValue = "1") int page,
                       @RequestParam(required = false,defaultValue = "3") int pageSize, Map map){
        PageHelper.startPage(page,pageSize);
        PageInfo pageInfo=new PageInfo(sellInfoService.getAll());

        map.put("list",pageInfo.getList()); //分页数据
        map.put("prePage",pageInfo.getPrePage()); //上一页
        map.put("nextPage",pageInfo.getNextPage());//下一页
        map.put("pageContent",pageInfo.getPages()); //总的分页数
        map.put("pages", pageInfo.getNavigatepageNums());//分页数组
        map.put("page", pageInfo.getPageNum());//当前页码

        return "admin/user/list";
    }

    @GetMapping("/user/add")
    public String add(){
        return "admin/user/add";
    }
    @PostMapping("/user/add")
    public String save(SellInfo sellInfo){
        sellInfo.setSellerId(UUID.randomUUID().toString());
      if(sellInfoService.addSellInfo(sellInfo)){
          return "redirect:/user/list";
      }
      return "redirect:/user/add";
    }

    @GetMapping("/gm")
    public void  buy(){

        //下了订单
        webSocketService.sendMessage("谢谢你购买");
    }






}
