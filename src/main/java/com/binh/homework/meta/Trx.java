package com.binh.homework.meta;

/**
 * Created by binh on 2017/4/9.
 */
public class Trx {
    private int id;
    private int contentId;
    private int personId;
    private int price;
    private long time;

    @Override
    public boolean equals(Object obj) {
        boolean result = false;
        if (obj != null) {
            Trx trx = (Trx) obj;
            if (trx.getId() == id && trx.getContentId() == contentId && trx.getPersonId() == personId && trx.getPrice() == price && trx.getTime() == time) {
                result = true;
            }
        }
        return result;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getContentId() {
        return contentId;
    }

    public void setContentId(int contentId) {
        this.contentId = contentId;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int prince) {
        this.price = prince;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
