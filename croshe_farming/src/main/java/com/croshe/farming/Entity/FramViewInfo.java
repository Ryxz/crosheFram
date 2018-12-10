package com.croshe.farming.Entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/6/3.
 */

public class FramViewInfo  implements Serializable {
//`farmId` int(11) NOT NULL AUTO_INCREMENT,
//  `farmName` varchar(255) DEFAULT NULL COMMENT '农场名称',
//            `farmImages` text COMMENT '滚动图片',
//            `farmAddress` varchar(255) DEFAULT NULL COMMENT '农场地址',
//            `farmLng` varchar(255) DEFAULT NULL COMMENT '经度',
//            `farmLat` varchar(255) DEFAULT NULL COMMENT '纬度',
//            `farmPhone` varchar(255) DEFAULT NULL COMMENT '电话',
//            `farmDetails` text COMMENT '介绍',
//            `farmTags` varchar(255) DEFAULT NULL COMMENT '特色',
//            `farmTime` datetime DEFAULT NULL COMMENT '录入时间',


    private String farmLng;
    private List<ImgInfo> farmImages;
    private String farmDetails;
    private String farmId;
    private String farmPhone;
    private String farmLat;
    private String farmTime;
    private String farmName;
    private String farmAddress;
    private String farmTags;

    public String getFarmLng() {
        return farmLng;
    }

    public void setFarmLng(String farmLng) {
        this.farmLng = farmLng;
    }

    public List<ImgInfo> getFarmImages() {
        return farmImages;
    }

    public void setFarmImages(List<ImgInfo> farmImages) {
        this.farmImages = farmImages;
    }

    public String getFarmDetails() {
        return farmDetails;
    }

    public void setFarmDetails(String farmDetails) {
        this.farmDetails = farmDetails;
    }

    public String getFarmId() {
        return farmId;
    }

    public void setFarmId(String farmId) {
        this.farmId = farmId;
    }

    public String getFarmPhone() {
        return farmPhone;
    }

    public void setFarmPhone(String farmPhone) {
        this.farmPhone = farmPhone;
    }

    public String getFarmLat() {
        return farmLat;
    }

    public void setFarmLat(String farmLat) {
        this.farmLat = farmLat;
    }

    public String getFarmTime() {
        return farmTime;
    }

    public void setFarmTime(String farmTime) {
        this.farmTime = farmTime;
    }

    public String getFarmName() {
        return farmName;
    }

    public void setFarmName(String farmName) {
        this.farmName = farmName;
    }

    public String getFarmAddress() {
        return farmAddress;
    }

    public void setFarmAddress(String farmAddress) {
        this.farmAddress = farmAddress;
    }

    public String getFarmTags() {
        return farmTags;
    }

    public void setFarmTags(String farmTags) {
        this.farmTags = farmTags;
    }
}
