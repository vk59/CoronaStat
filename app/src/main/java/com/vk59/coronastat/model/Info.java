package com.vk59.coronastat.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Info {

    @SerializedName("ourid")
    @Expose
    private int ourid;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("source")
    @Expose
    private String source;

    public int getOurid() {
        return ourid;
    }

    public void setOurid(int ourid) {
        this.ourid = ourid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Override
    public String toString() {
        return "ourid: " + ourid + "\ntitle: " + title + "\ncode: " + code + "\nsource:" + source;
    }

}
