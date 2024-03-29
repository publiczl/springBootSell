package cn.cxh.sell.controller;


import cn.cxh.sell.config.ProjectUrlConfig;

import cn.cxh.sell.enums.ResultEnum;

import cn.cxh.sell.exception.SellException;
import cn.cxh.sell.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URLEncoder;


/**
 * Created by SqMax on 2018/3/23.
 */
@Controller
@RequestMapping("/wechat")
@Slf4j
public class WechatController {

    @Autowired
    private WxMpService wxMpService;

    @Autowired
    private WxMpService wxOpenService;

    @Autowired
    private ProjectUrlConfig projectUrlConfig;

    @GetMapping("/authorize")
    public String authorize(@RequestParam("returnUrl") String returnUrl){
//        WxMpService wxMpService=new WxMpServiceImpl();
        //1. 配置
        //2.调用方法
        String url=projectUrlConfig.getWechatMpAuthorize()+"/wechat/userInfo";
        String redirectUrl=wxMpService.oauth2buildAuthorizationUrl(url,WxConsts.OAUTH2_SCOPE_BASE, URLEncoder.encode(returnUrl));
        log.info("【微信网页授权】获取code,redirectUrl={}",redirectUrl);
        return "redirect:"+redirectUrl;//重定向到下面一个方法
    }
    @GetMapping("/userInfo")
    public String userInfo(@RequestParam("code") String code,
                         @RequestParam("state") String returnUrl){
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken=new WxMpOAuth2AccessToken();
        try {
            wxMpOAuth2AccessToken=wxMpService.oauth2getAccessToken(code);
        }catch (WxErrorException e){
            log.error("【微信网页授权】,{}",e);
            throw new SellException(ResultEnum.WECHAT_MP_ERROR.getCode(),e.getError().getErrorMsg());
        }
        String openId=wxMpOAuth2AccessToken.getOpenId();
        log.info("【微信网页授权】获取openid,returnUrl={}",returnUrl);
        return "redirect:"+ returnUrl+"?openid="+openId;

    }//以上两个方法是SDK方式微信网页授权的过程，
    // 访问http://sqmax.natapp1.cc/sell/wechat/authorize?returnUrl=http://www.imooc.com，
    //最终将会跳转到这个链接：http://www.imooc.com?openid={openid}

    //微信登陆
    @GetMapping("/qrAuthorize")
    public String qrAuthorize(@RequestParam("returnUrl") String returnUrl){
        String url=projectUrlConfig.getWechatOpenAuthorize()+"/wechat/userInfo";
        String redirectUrl=wxOpenService.buildQrConnectUrl(url,WxConsts.QRCONNECT_SCOPE_SNSAPI_LOGIN,URLEncoder.encode(returnUrl));
        return "redirect:"+redirectUrl;
    }
    @GetMapping("/qrUserInfo")
    public String qrUserInfo(@RequestParam("code") String code,
                             @RequestParam("state") String returnUrl){
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken=new WxMpOAuth2AccessToken();
        try{
            wxMpOAuth2AccessToken=wxOpenService.oauth2getAccessToken(code);
        }catch (WxErrorException e){
            log.error("【微信网页】{}",e);
            throw new SellException(ResultEnum.WECHAT_MP_ERROR.getCode(),e.getError().getErrorMsg());
        }
        log.info("wxMpOAuth2AccessToken={}", JsonUtil.toJson(wxMpOAuth2AccessToken));
        String openId=wxMpOAuth2AccessToken.getOpenId();
        return "redirect:"+returnUrl+"?openid="+openId;
    }

    /**
     * 处理微信服务器发来的get请求，进行签名的验证
     *
     * signature 微信端发来的签名
     * timestamp 微信端发来的时间戳
     * nonce     微信端发来的随机字符串
     * echostr   微信端发来的验证字符串
     */
    @GetMapping(value = "url")
    public String validate(@RequestParam(value = "signature") String signature,
                           @RequestParam(value = "timestamp") String timestamp,
                           @RequestParam(value = "nonce") String nonce,
                           @RequestParam(value = "echostr") String echostr) {

        return wxMpService.checkSignature(signature, timestamp, nonce) ? echostr : null;

    }

    @GetMapping("/getopenid")
    public String getOpenid(@RequestParam String openid, Model model){
        model.addAttribute("openid",openid);
        System.out.println("微信openId:"+openid);
        return  "pay/pay";
    }

    @GetMapping("/wxLogin")
    public String wxLogin(){

        return "wxlogin";
    }

}
