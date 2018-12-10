package com.croshe.farming.Entity;

/**
 * Created by Administrator on 2017/6/9.
 */

public class TypeClassEnumInfo {
    private String id;
    private String text;
    private boolean isselect;

    public boolean isselect() {
        return isselect;
    }

    public void setIsselect(boolean isselect) {
        this.isselect = isselect;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
