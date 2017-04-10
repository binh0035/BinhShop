package com.binh.homework.meta;

/**
 * Created by binh on 2017/3/23.
 */
public class Product {
    private int id;
    private String title;
    private String image;
    private long price;
    private boolean isBuy;
    private boolean isSell;

    public Product(int id, String title, String image, long price) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.price = price;
        isBuy = false;
        isSell = false;
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

    public boolean isBuy() {
        return isBuy;
    }

    public void setBuy(boolean buy) {
        isBuy = buy;
    }

    public boolean isSell() {
        return isSell;
    }

    public void setSell(boolean sell) {
        isSell = sell;
    }
}
