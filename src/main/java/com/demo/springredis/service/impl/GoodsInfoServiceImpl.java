package com.demo.springredis.service.impl;

import com.demo.springredis.common.util.RedisUtil;
import com.demo.springredis.mapper.GoodsInfoMapper;
import com.demo.springredis.pojo.GoodsIInfoDTO;
import com.demo.springredis.service.GoodsInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoodsInfoServiceImpl  implements GoodsInfoService {

    @Autowired
    private GoodsInfoMapper goodsInfoMapper;

    @Override
    public int updateGoodsIInfoAddRedis(GoodsIInfoDTO goodsIInfoDTO) {
        if(!RedisUtil.setnx("商品1","aa",5L)){
            //抛异常 eg: 点得太快了兄弟
            return 0;
        }
        GoodsIInfoDTO goodsIInfo = goodsInfoMapper.getGoodsIInfo(1);
        GoodsIInfoDTO goodsIInfoDTO1 = new GoodsIInfoDTO();
        goodsIInfoDTO1.setId(1);
        goodsIInfoDTO1.setAmount(goodsIInfo.getAmount()-1);
        System.out.println("查询到的商品数量为"+(goodsIInfo.getAmount()));
        int i = goodsInfoMapper.updateGoodsIInfo(goodsIInfoDTO1);
        RedisUtil.deleteKey("商品"+goodsIInfo.getId());

        return i;
    }
}
