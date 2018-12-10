package com.croshe.farming.Entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/7/7.
 */

public class StandardNameEntity implements Serializable {

    /**
     * name : 份
     * type : 份
     * id : extModel1270-1
     */

    private String name;
    private String type;
    private String id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
