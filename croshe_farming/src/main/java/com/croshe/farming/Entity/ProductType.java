package com.croshe.farming.Entity;

import java.io.Serializable;

/**
 * Created by ZPA on 2017/7/5.
 */
/*
"typeTop":0,
                "typeType":0,
                "typeName":"叶菜类",
                "productTypeLayerCode":"0000000029",
                "parentId":28,
                "typeSubtitle":null,
                "typeDateTime":"2017-06-16 13:33:47",
                "typeImage":null,
                "typeClass":5,
                "typeId":29
 */
public class ProductType implements Serializable {
    String typeClass;

    public String getTypeClass() {
        return typeClass;
    }

    public void setTypeClass(String typeClass) {
        this.typeClass = typeClass;
    }
}
