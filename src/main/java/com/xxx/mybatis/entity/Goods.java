package com.xxx.mybatis.entity;

public class Goods {
    private Integer goodsId;
    private String title;
    private String subTitle;
    private Float originalCost;
    private Float currentCost;
    private Float discount;
    private Integer isFreeDelivery;
    private Integer categoryId;

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public Float getOriginalCost() {
        return originalCost;
    }

    public void setOriginalCost(Float originalCost) {
        this.originalCost = originalCost;
    }

    public Float getCurrentCost() {
        return currentCost;
    }

    public void setCurrentCost(Float currentCost) {
        this.currentCost = currentCost;
    }

    public Float getDiscount() {
        return discount;
    }

    public void setDiscount(Float discount) {
        this.discount = discount;
    }

    public Integer getIsFreeDelivery() {
        return isFreeDelivery;
    }

    public void setIsFreeDelivery(Integer isFreeDelivery) {
        this.isFreeDelivery = isFreeDelivery;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
}
