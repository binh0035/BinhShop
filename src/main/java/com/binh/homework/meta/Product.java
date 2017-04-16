package com.binh.homework.meta;

/**
 * Created by binh on 2017/4/12.
 */
public class Product {
    private int id;
    private String title;
    private String summary;
    private String detail;
    private String image;
    private long price;

    public Product(){

    }
    public Product(String title, String summary, String detail, String image, long price) {
        this.title = title;
        this.summary = summary;
        this.detail = detail;
        this.image = image;
        this.price = price;
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
}
