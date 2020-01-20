package com.demo.springredis;


import com.demo.springredis.mapper.GoodsInfoMapper;
import com.demo.springredis.pojo.GoodsIInfoDTO;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 *不加锁
 */
public class TestNoRedisDemo {


    public static void main(String[] args) {

        //main 方法 是静态的，goodsInfoMapper 不能注解注入，以下方式获取
        SpringApplication application = new SpringApplication(SpringRedisApplication.class);
        ConfigurableApplicationContext context = application.run(args);
        GoodsInfoMapper goodsInfoMapper = context.getBean(GoodsInfoMapper.class);

        //amount 的初始值 为 100，模拟20个人同时 购买，不加并发控制如下
        for (int i=0;i<20;i++) {
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
/*      正常结果应该是剩下 80 个， 但是由于并发没有控制,最后amount 还有 98 个
        控制台打印结果：
        线程: : 查询到的amount: 100
        线程: : 查询到的amount: 100
        线程: : 查询到的amount: 100
        线程: : 查询到的amount: 100
        线程: : 查询到的amount: 100
        线程: : 更新成功
        线程: : 更新成功
        线程: : 查询到的amount: 100
        线程: : 查询到的amount: 100
        线程: : 查询到的amount: 100
        线程: : 更新成功
        线程: : 查询到的amount: 99
        线程: : 查询到的amount: 99
        线程: : 查询到的amount: 100
        线程: : 查询到的amount: 99
        线程: : 查询到的amount: 99
        线程: : 更新成功
        线程: : 更新成功
        线程: : 更新成功
        线程: : 更新成功
        线程: : 更新成功
        线程: : 更新成功
        线程: : 更新成功
        线程: : 更新成功
        线程: : 更新成功
        线程: : 更新成功
        线程: : 查询到的amount: 99
        线程: : 查询到的amount: 99
        线程: : 查询到的amount: 99
        线程: : 查询到的amount: 99
        线程: : 更新成功
        线程: : 查询到的amount: 99
        线程: : 查询到的amount: 99
        线程: : 查询到的amount: 99
        线程: : 更新成功
        线程: : 更新成功
        线程: : 更新成功
        线程: : 更新成功
        线程: : 更新成功
        线程: : 更新成功*/

    }
}
