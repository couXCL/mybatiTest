package com.xxx.mybatis.dto;

import com.xxx.mybatis.entity.Goods;
//DTO,意思是Data transfer Object---数据传输对象
public class GoodsDTO {
    private Goods goods = new Goods();
    private String categoryName;
    private String test;

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }
}
