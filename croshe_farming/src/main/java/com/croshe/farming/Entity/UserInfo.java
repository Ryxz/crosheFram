package com.croshe.farming.Entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/5/31.
 */

public class UserInfo implements Serializable {
    private String payPassword;
    private UserSet userSet;
    private String startTime;
    private String endTime;

    private String landOutput;
    private String landArea;
    private String landMoney;
    private String farmId;
    private String landRentArea;
    private String landPrice;
    private String seedMoney;
    private String landLayerCode;
    private List<ImgInfo>  landImages;
    private String landCode;
    private String landName;
    private String landOtherPrice;
    private String feedMoney;
    private String manageMoney;
    private String landTags;
    private String liveState;
    private LandEntity land;
    private String dieDay;
    private String productId;
    private int useArea;
    private String detailsId;
    private String liveCycle;
    private String userFarmArea;
    private String rentArea;
    private String userFarmId;
    private int landType;
    private int landState;
    private String landId;
    private String userHeadImg;
    private String userDateTime;
    private String address;
    private String userCode;
    private String userId;
    private String userSex;
    private String userPassword;
    private String userEmail;
    private String userPhone;
    private String userGarde;
    private String userNickName;
    private String img;
    private String productName;
    private String userFarmDetails;
    private List<ImgInfo> userFarmImages;
    private List<FarmCostInfo> userFarmCost;
    private FarmLogInfo  farmLog;
    private List<CommentInfo>  comment;
    private String sprayDay;
    private String productionDay;
    private UserInfo userInfo;
    private int ifSign;
    private int state;
    private int productCount;
    private String detailsStateStr;
    private String useSumArea;
    private String sumMonthScore;

    public String getSumMonthScore() {
        return sumMonthScore;
    }

    public void setSumMonthScore(String sumMonthScore) {
        this.sumMonthScore = sumMonthScore;
    }

    public String getUseSumArea() {
        return useSumArea;
    }

    public void setUseSumArea(String useSumArea) {
        this.useSumArea = useSumArea;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public String getDetailsStateStr() {
        return detailsStateStr;
    }

    public void setDetailsStateStr(String detailsStateStr) {
        this.detailsStateStr = detailsStateStr;
    }

    public String getPayPassword() {
        return payPassword;
    }

    public void setPayPassword(String payPassword) {
        this.payPassword = payPassword;
    }

    public int getProductCount() {
        return productCount;
    }

    public void setProductCount(int productCount) {
        this.productCount = productCount;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getIfSign() {
        return ifSign;
    }

    public void setIfSign(int ifSign) {
        this.ifSign = ifSign;
    }

    public UserInfo getUserInfo() {
        if (null == userInfo) {
            userInfo = new UserInfo();
        }
        return userInfo;
    }
    public UserSet getUserSet() {
        return userSet;
    }

    public void setUserSet(UserSet userSet) {
        this.userSet = userSet;
    }

    public String getUserFarmDetails() {
        return userFarmDetails;
    }

    public void setUserFarmDetails(String userFarmDetails) {
        this.userFarmDetails = userFarmDetails;
    }

    public LandEntity getLand() {
        return land;
    }

    public void setLand(LandEntity land) {
        this.land = land;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getLandOutput() {
        return landOutput;
    }

    public void setLandOutput(String landOutput) {
        this.landOutput = landOutput;
    }

    public String getLandArea() {
        return landArea;
    }

    public void setLandArea(String landArea) {
        this.landArea = landArea;
    }

    public String getLandMoney() {
        return landMoney;
    }

    public void setLandMoney(String landMoney) {
        this.landMoney = landMoney;
    }

    public String getFarmId() {
        return farmId;
    }

    public void setFarmId(String farmId) {
        this.farmId = farmId;
    }

    public String getLandRentArea() {
        return landRentArea;
    }

    public void setLandRentArea(String landRentArea) {
        this.landRentArea = landRentArea;
    }

    public String getLandPrice() {
        return landPrice;
    }

    public void setLandPrice(String landPrice) {
        this.landPrice = landPrice;
    }

    public String getSeedMoney() {
        return seedMoney;
    }

    public void setSeedMoney(String seedMoney) {
        this.seedMoney = seedMoney;
    }

    public String getLandLayerCode() {
        return landLayerCode;
    }

    public void setLandLayerCode(String landLayerCode) {
        this.landLayerCode = landLayerCode;
    }

    public List<ImgInfo> getLandImages() {
        return landImages;
    }

    public void setLandImages(List<ImgInfo> landImages) {
        this.landImages = landImages;
    }

    public String getLandCode() {
        return landCode;
    }

    public void setLandCode(String landCode) {
        this.landCode = landCode;
    }

    public String getLandName() {
        return landName;
    }

    public void setLandName(String landName) {
        this.landName = landName;
    }

    public String getLandOtherPrice() {
        return landOtherPrice;
    }

    public void setLandOtherPrice(String landOtherPrice) {
        this.landOtherPrice = landOtherPrice;
    }

    public String getFeedMoney() {
        return feedMoney;
    }

    public void setFeedMoney(String feedMoney) {
        this.feedMoney = feedMoney;
    }

    public String getManageMoney() {
        return manageMoney;
    }

    public void setManageMoney(String manageMoney) {
        this.manageMoney = manageMoney;
    }

    public String getLandTags() {
        return landTags;
    }

    public void setLandTags(String landTags) {
        this.landTags = landTags;
    }

    public List<CommentInfo> getComment() {
        return comment;
    }

    public void setComment(List<CommentInfo> comment) {
        this.comment = comment;
    }

    public List<ImgInfo> getUserFarmImages() {
        return userFarmImages;
    }

    public void setUserFarmImages(List<ImgInfo> userFarmImages) {
        this.userFarmImages = userFarmImages;
    }

    public List<FarmCostInfo> getUserFarmCost() {
        return userFarmCost;
    }

    public void setUserFarmCost(List<FarmCostInfo> userFarmCost) {
        this.userFarmCost = userFarmCost;
    }

    public FarmLogInfo getFarmLog() {
        return farmLog;
    }

    public void setFarmLog(FarmLogInfo farmLog) {
        this.farmLog = farmLog;
    }



    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getSprayDay() {
        return sprayDay;
    }

    public void setSprayDay(String sprayDay) {
        this.sprayDay = sprayDay;
    }

    public String getLiveState() {
        return liveState;
    }

    public void setLiveState(String liveState) {
        this.liveState = liveState;
    }

//    public String getUserFarmImages() {
//        return userFarmImages;
//    }
//
//    public void setUserFarmImages(String userFarmImages) {
//        this.userFarmImages = userFarmImages;
//    }

    public String getDieDay() {
        return dieDay;
    }

    public void setDieDay(String dieDay) {
        this.dieDay = dieDay;
    }

    public String getProductionDay() {
        return productionDay;
    }

    public void setProductionDay(String productionDay) {
        this.productionDay = productionDay;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getUseArea() {
        return useArea;
    }

    public void setUseArea(int useArea) {
        this.useArea = useArea;
    }

    public String getDetailsId() {
        return detailsId;
    }

    public void setDetailsId(String detailsId) {
        this.detailsId = detailsId;
    }

    public String getLiveCycle() {
        return liveCycle;
    }

    public void setLiveCycle(String liveCycle) {
        this.liveCycle = liveCycle;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getUserFarmArea() {
        return userFarmArea;
    }

    public void setUserFarmArea(String userFarmArea) {
        this.userFarmArea = userFarmArea;
    }

    public String getRentArea() {
        return rentArea;
    }

    public void setRentArea(String rentArea) {
        this.rentArea = rentArea;
    }

    public String getUserFarmId() {
        return userFarmId;
    }

    public void setUserFarmId(String userFarmId) {
        this.userFarmId = userFarmId;
    }

    public int getLandType() {
        return landType;
    }

    public void setLandType(int landType) {
        this.landType = landType;
    }

    public int getLandState() {
        return landState;
    }

    public void setLandState(int landState) {
        this.landState = landState;
    }

    public String getLandId() {
        return landId;
    }

    public void setLandId(String landId) {
        this.landId = landId;
    }


    public String getUserHeadImg() {
        return userHeadImg;
    }

    public void setUserHeadImg(String userHeadImg) {
        this.userHeadImg = userHeadImg;
    }

    public String getUserDateTime() {
        return userDateTime;
    }

    public void setUserDateTime(String userDateTime) {
        this.userDateTime = userDateTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserGarde() {
        return userGarde;
    }

    public void setUserGarde(String userGarde) {
        this.userGarde = userGarde;
    }

    public String getUserNickName() {
        return userNickName;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName;
    }
}
