package com.demo.springredis.pojo;

import com.alibaba.fastjson.annotation.JSONField;

import java.math.BigDecimal;

public class GoodsIInfoDTO {

    @JSONField(name = "id")
    private Integer id;

    @JSONField(name = "good_name")
    private String goodName;

    @JSONField(name = "price")
    private BigDecimal price;

    @JSONField(name = "amount")
    private Integer amount;

    @JSONField(name = "archive")
    private int archive;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public int getArchive() {
        return archive;
    }

    public void setArchive(int archive) {
        this.archive = archive;
    }
}
