package com.binh.homework.meta;

import java.math.BigInteger;

/**
 * Created by binh on 2017/4/9.
 */
public class Content {

    private int id;
    private long price;
    private String title;
    private byte[] icon;
    private String abstracts;
    private byte[] text;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long prince) {
        this.price = prince;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public byte[] getIcon() {
        return icon;
    }

    public void setIcon(byte[] icon) {
        this.icon = icon;
    }

    public String getAbstract() {
        return abstracts;
    }

    public void setAbstract(String abstracts) {
        this.abstracts = abstracts;
    }

    public byte[] getText() {
        return text;
    }

    public void setText(byte[] text) {
        this.text = text;
    }
}
