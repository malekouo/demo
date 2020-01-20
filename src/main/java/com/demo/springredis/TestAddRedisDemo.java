package com.demo.springredis;


import com.demo.springredis.mapper.GoodsInfoMapper;
import com.demo.springredis.pojo.GoodsIInfoDTO;
import com.demo.springredis.service.GoodsInfoService;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * 加上redis锁
 */
public class TestAddRedisDemo {


    public static void main(String[] args) {

        //main 方法 是静态的，goodsInfoMapper 不能注解注入，以下方式获取
        SpringApplication application = new SpringApplication(SpringRedisApplication.class);
        ConfigurableApplicationContext context = application.run(args);
        GoodsInfoMapper goodsInfoMapper = context.getBean(GoodsInfoMapper.class);
        GoodsInfoService goodsInfoService = context.getBean(GoodsInfoService.class);

        //amount 的初始值 为 100，模拟200个人同时发请求操作同一条记录，加了redis后，在同一时间，只有一条成功，
        for (int i = 0; i < 200; i++) {
            Thread thread = new Thread() {
                @Override
                public void run() {
                    super.run();
                    int result = goodsInfoService.updateGoodsIInfoAddRedis(new GoodsIInfoDTO());
                    if (result == 0) {
                        System.out.println("线程购买失败");
                    }else{
                        System.out.println("线程购买成功");
                    }

                }
            };
            thread.start();
        }
/*      控制台打印结果：
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        线程购买失败
        查询到的商品数量为1000
        线程购买成功*/

    }
}
