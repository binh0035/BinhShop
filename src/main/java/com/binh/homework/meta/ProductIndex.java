package com.binh.homework.meta;

/**
 * Created by binh on 2017/3/23.
 */
public class ProductIndex {
    private int id;
    private String title;
    private String image;
    private long price;
    private boolean isBuy;
    private boolean isSell;

    public ProductIndex(Product product) {
        this.id = product.getId();
        this.title = product.getTitle();
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
