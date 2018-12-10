package com.croshe.farming.Entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/7/25.
 */

public class MyFramEntity implements Serializable {
    /**
     * detailsStateStr : 生长中
     * userFarmId : 16
     * img : attachments/merchantTBHYZGZ/d06992ebe7d3af1b3d4c7f6f6181d261.jpg
     * state : 2
     * userFarmImages : [{fileType=.jpeg, thumbPath=null, filePath=attachments/productImages/a2106f846196f736dbbaefabcf38a4f2.jpg, lock=false, lng=null, fileContent=, fileSize=0.0, duration=0.0, file=null, fileName=羊_1.jpg, fileDateTime=2017-07-18 17:57:30, lat=null, key=null, id=extModel5175-1}]
     * userId : 7
     * useArea : 25
     * detailsTime : 2017-07-25 15:00:51
     * spreadDay : 0
     * useSumArea : 25
     * liveCycle : 70
     * standardId : 59
     * landTypeStr : 养殖
     * sprayDay : 0
     * liveState : null
     * dieDay : 70
     * productionDay : 70
     * productId : 65
     * landType : 1
     * productCount : 1
     * detailsId : 51
     * detailsState : 0
     * harvestType : 1
     * stateStr : 已养殖
     * detailsVideos : null
     * productName : 山羊幼崽
     */

    private String detailsStateStr;
    private int userFarmId;
    private String img;
    private int state;
    private String userFarmImages;
    private int userId;
    private int useArea;
    private String detailsTime;
    private int spreadDay;
    private String useSumArea;
    private String liveCycle;
    private int standardId;
    private String landTypeStr;
    private int sprayDay;
    private Object liveState;
    private int dieDay;
    private int productionDay;
    private int productId;
    private int landType;
    private String productCount;
    private String detailsId;
    private int detailsState;
    private int harvestType;
    private String stateStr;
    private Object detailsVideos;
    private String productName;
    private LandEntity land;
    private String userFarmArea;

    public String getUserFarmArea() {
        return userFarmArea;
    }

    public void setUserFarmArea(String userFarmArea) {
        this.userFarmArea = userFarmArea;
    }

    public LandEntity getLand() {
        return land;
    }

    public void setLand(LandEntity land) {
        this.land = land;
    }

    public String getDetailsStateStr() {
        return detailsStateStr;
    }

    public void setDetailsStateStr(String detailsStateStr) {
        this.detailsStateStr = detailsStateStr;
    }

    public int getUserFarmId() {
        return userFarmId;
    }

    public void setUserFarmId(int userFarmId) {
        this.userFarmId = userFarmId;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getUserFarmImages() {
        return userFarmImages;
    }

    public void setUserFarmImages(String userFarmImages) {
        this.userFarmImages = userFarmImages;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUseArea() {
        return useArea;
    }

    public void setUseArea(int useArea) {
        this.useArea = useArea;
    }

    public String getDetailsTime() {
        return detailsTime;
    }

    public void setDetailsTime(String detailsTime) {
        this.detailsTime = detailsTime;
    }

    public int getSpreadDay() {
        return spreadDay;
    }

    public void setSpreadDay(int spreadDay) {
        this.spreadDay = spreadDay;
    }

    public String getUseSumArea() {
        return useSumArea;
    }

    public void setUseSumArea(String useSumArea) {
        this.useSumArea = useSumArea;
    }

    public String getLiveCycle() {
        return liveCycle;
    }

    public void setLiveCycle(String liveCycle) {
        this.liveCycle = liveCycle;
    }

    public int getStandardId() {
        return standardId;
    }

    public void setStandardId(int standardId) {
        this.standardId = standardId;
    }

    public String getLandTypeStr() {
        return landTypeStr;
    }

    public void setLandTypeStr(String landTypeStr) {
        this.landTypeStr = landTypeStr;
    }

    public int getSprayDay() {
        return sprayDay;
    }

    public void setSprayDay(int sprayDay) {
        this.sprayDay = sprayDay;
    }

    public Object getLiveState() {
        return liveState;
    }

    public void setLiveState(Object liveState) {
        this.liveState = liveState;
    }

    public int getDieDay() {
        return dieDay;
    }

    public void setDieDay(int dieDay) {
        this.dieDay = dieDay;
    }

    public int getProductionDay() {
        return productionDay;
    }

    public void setProductionDay(int productionDay) {
        this.productionDay = productionDay;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getLandType() {
        return landType;
    }

    public void setLandType(int landType) {
        this.landType = landType;
    }

    public String getProductCount() {
        return productCount;
    }

    public void setProductCount(String productCount) {
        this.productCount = productCount;
    }

    public String getDetailsId() {
        return detailsId;
    }

    public void setDetailsId(String detailsId) {
        this.detailsId = detailsId;
    }

    public int getDetailsState() {
        return detailsState;
    }

    public void setDetailsState(int detailsState) {
        this.detailsState = detailsState;
    }

    public int getHarvestType() {
        return harvestType;
    }

    public void setHarvestType(int harvestType) {
        this.harvestType = harvestType;
    }

    public String getStateStr() {
        return stateStr;
    }

    public void setStateStr(String stateStr) {
        this.stateStr = stateStr;
    }

    public Object getDetailsVideos() {
        return detailsVideos;
    }

    public void setDetailsVideos(Object detailsVideos) {
        this.detailsVideos = detailsVideos;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
