package com.binh.homework.meta;

/**
 * Created by binh on 2017/4/9.
 */
public class Trx {
    private int id;
    private int contentId;
    private int personId;
    private int prince;
    private int time;

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

    public int getPrince() {
        return prince;
    }

    public void setPrince(int prince) {
        this.prince = prince;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
