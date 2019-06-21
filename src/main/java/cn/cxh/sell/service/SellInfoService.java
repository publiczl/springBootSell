package cn.cxh.sell.service;

import cn.cxh.sell.dao.SellInfoDao;
import cn.cxh.sell.enttry.SellInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

@Service
public class SellInfoService {
    @Resource
    private SellInfoDao sellInfoDao;
    public List<SellInfo> getAll(){
        return sellInfoDao.findAll();
    }
    public SellInfo getSellInfoById(String id){
        return sellInfoDao.findById(id);
    }
    public boolean cheackLogin(SellInfo sellInfo){
        SellInfo restSellInfo=sellInfoDao.findByUserName(sellInfo.getUserName());
        if(Objects.nonNull(restSellInfo)){
                if(restSellInfo.getPassword().equals(sellInfo.getPassword())){
                    return  true;
                }
        }
        return false;
    }
    public boolean addSellInfo(SellInfo sellInfo){
      return   sellInfoDao.add(sellInfo)>0;
    }
}
