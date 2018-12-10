package com.croshe.farming.Entity;

import com.croshe.crosheandroidbase.util.StringUtils;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/6/7.
 */

public class StandInfo implements Serializable{
//          "standardId":2,
//           "standardDateTime":"2017-06-01 19:43:37",
//           "standardSubtitle":"1袋30粒",
//           "plantArea":null,
//           "standardPrice":30,
//           "useArea":null,
//           "targetId":2,
//           "standardName":"番茄 30粒/袋",
//           "standardUnit":"袋",
//           "targetType":1
    private String standardId;
    private String standardDateTime;
    private String standardSubtitle;
    private String plantArea;
    private String standardPrice;
    private String useArea;
    private String targetId;
    private String standardName;
    private String standardUnit;
    private String targetType;

    public String getStandardId() {
        return standardId;
    }

    public void setStandardId(String standardId) {
        this.standardId = standardId;
    }

    public String getStandardDateTime() {
        return standardDateTime;
    }

    public void setStandardDateTime(String standardDateTime) {
        this.standardDateTime = standardDateTime;
    }

    public String getStandardSubtitle() {
        return standardSubtitle;
    }

    public void setStandardSubtitle(String standardSubtitle) {
        this.standardSubtitle = standardSubtitle;
    }

    public String getPlantArea() {
        return plantArea;
    }

    public void setPlantArea(String plantArea) {
        this.plantArea = plantArea;
    }

    public String getStandardPrice() {
        return standardPrice;
    }

    public void setStandardPrice(String standardPrice) {
        this.standardPrice = standardPrice;
    }

    public String getUseArea() {
        return useArea;
    }

    public void setUseArea(String useArea) {
        this.useArea = useArea;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public String getStandardName() {
//        if(!StringUtils.isEmpty(standardName)){
//            return "";
//        }
        return standardName;
    }

    public void setStandardName(String standardName) {
        this.standardName = standardName;
    }

    public String getStandardUnit() {
        return standardUnit;
    }

    public void setStandardUnit(String standardUnit) {
        this.standardUnit = standardUnit;
    }

    public String getTargetType() {
        return targetType;
    }

    public void setTargetType(String targetType) {
        this.targetType = targetType;
    }
}
