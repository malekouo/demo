package com.demo.springredis;

import com.demo.springredis.mapper.GoodsInfoMapper;
import com.demo.springredis.pojo.GoodsIInfoDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class SpringRedisApplicationTests {

    @Autowired
    private GoodsInfoMapper goodsInfoMapper;
/*

    @Test
    void contextLoads() {
        GoodsIInfoDTO goodsIInfo = goodsInfoMapper.getGoodsIInfo(1);
        System.out.println("name:"+goodsIInfo.getGoodName());
    }
*/

    @Test
    public void testNo(){
        for(int i=1; i<=20;i++){
            new Thread(){
                public void run() {
                    GoodsIInfoDTO goodsIInfo = goodsInfoMapper.getGoodsIInfo(1);
                    System.out.println("线程: : 查询到的amount: "+goodsIInfo.getAmount());
                    GoodsIInfoDTO goodsIInfoDTO = new GoodsIInfoDTO();
                    goodsIInfoDTO.setId(1);
                    goodsIInfoDTO.setAmount(goodsIInfo.getAmount()-1);
                    goodsInfoMapper.updateGoodsIInfo(goodsIInfoDTO);
                    System.out.println("线程: "+": 更新成功");
                }
            }.start();
        }

    }
    @Test
    public void  test1(){
        Thread thread = new Thread() {
            @Override
            public void run() {
                super.run();
                GoodsIInfoDTO goodsIInfo = goodsInfoMapper.getGoodsIInfo(1);
                System.out.println("线程: : 查询到的amount: " + goodsIInfo.getAmount());
                GoodsIInfoDTO goodsIInfoDTO = new GoodsIInfoDTO();
                goodsIInfoDTO.setId(1);
                goodsIInfoDTO.setAmount(goodsIInfo.getAmount() - 1);
                goodsInfoMapper.updateGoodsIInfo(goodsIInfoDTO);
                System.out.println("线程: " + ": 更新成功");
            }
        };
        thread.start();


    }


}
