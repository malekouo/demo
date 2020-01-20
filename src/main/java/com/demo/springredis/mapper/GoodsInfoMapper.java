package com.demo.springredis.mapper;

import com.demo.springredis.pojo.GoodsIInfoDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GoodsInfoMapper {

    GoodsIInfoDTO getGoodsIInfo(int id);

    int updateGoodsIInfo(GoodsIInfoDTO goodsIInfoDTO);
}
