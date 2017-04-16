package com.binh.homework.meta;

/**
 * Created by binh on 2017/4/12.
 */
public class ProductShow {

    private int id;
    private String title;
    private String summary;
    private String detail;
    private String image;
    private long price;
    private int buyPrice;
    private int buyNum;
    private int saleNum;
    private boolean isBuy;
    private boolean isSell;

    public ProductShow(Product product) {
        this.id = product.getId();
        this.title = product.getTitle();
        this.summary = product.getSummary();
        this.detail = product.getDetail();
        this.image = product.getImage();
        this.price = product.getPrice();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public int getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(int buyPrice) {
        this.buyPrice = buyPrice;
    }

    public int getBuyNum() {
        return buyNum;
    }

    public void setBuyNum(int buyNum) {
        this.buyNum = buyNum;
    }

    public int getSaleNum() {
        return saleNum;
    }

    public void setSaleNum(int saleNum) {
        this.saleNum = saleNum;
    }

    public boolean getIsBuy() {
        return isBuy;
    }

    public void setIsBuy(boolean buy) {
        isBuy = buy;
    }

    public boolean getIsSell() {
        return isSell;
    }

    public void setIsSell(boolean sell) {
        isSell = sell;
    }
}
