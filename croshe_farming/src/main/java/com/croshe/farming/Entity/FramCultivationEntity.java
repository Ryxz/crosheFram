package com.croshe.farming.Entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/7/10.
 */

public class FramCultivationEntity implements Serializable {


    /**
     * standardId : 0
     * weight : 0
     * weightUnit : null
     * storeTime : 2017-07-06 21:41:46
     * number : 1
     * typeClass : 0
     * productId : 56
     * typeType : 0
     * price : 12
     * userId : 4
     * useArea : 12
     * storeType : 0
     * numberUnit : null
     * storeId : 6
     */
    /**
     * sprayDay : 12
     * userFarmId : 2
     * liveState : 1232
     * dieDay : 13
     * productionDay : 1
     * farmLog : null
     * productCount : 10
     * landType : 1
     * detailsId : 2
     * spreadDay : 12
     * useSumArea : 12
     * liveCycle : 13
     */
    private int standardId;
    private int weight;
    private Object weightUnit;
    private String storeTime;
    private int number;
    private int typeClass;
    private int productId;
    private int typeType;
    private int price;
    private int userId;
    private int useArea;
    private int storeType;
    private Object numberUnit;
    private int storeId;
    private List<ImgInfo> userFarmImages;
    private ProductInfo product;
    private ProductInfo poductModel;
    private String productName;
    private int detailsState;

    private int sprayDay;
    private int userFarmId;
    private String liveState;
    private int dieDay;
    private int productionDay;
    private Object farmLog;
    private int productCount;
    private int landType;
    private int detailsId;
    private int spreadDay;
    private String useSumArea;
    private String liveCycle;
    private List<CostEntity> costs;
    private List<FileEntity> detailsVideos;
    private List<MonitorsEntity> monitors;


    public int getDetailsState() {
        return detailsState;
    }

    public void setDetailsState(int detailsState) {
        this.detailsState = detailsState;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public List<ImgInfo> getUserFarmImages() {
        return userFarmImages;
    }

    public void setUserFarmImages(List<ImgInfo> userFarmImages) {
        this.userFarmImages = userFarmImages;
    }

    public int getStandardId() {
        return standardId;
    }

    public void setStandardId(int standardId) {
        this.standardId = standardId;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Object getWeightUnit() {
        return weightUnit;
    }

    public void setWeightUnit(Object weightUnit) {
        this.weightUnit = weightUnit;
    }

    public String getStoreTime() {
        return storeTime;
    }

    public void setStoreTime(String storeTime) {
        this.storeTime = storeTime;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getTypeClass() {
        return typeClass;
    }

    public void setTypeClass(int typeClass) {
        this.typeClass = typeClass;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getTypeType() {
        return typeType;
    }

    public void setTypeType(int typeType) {
        this.typeType = typeType;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
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

    public int getStoreType() {
        return storeType;
    }

    public void setStoreType(int storeType) {
        this.storeType = storeType;
    }

    public Object getNumberUnit() {
        return numberUnit;
    }

    public void setNumberUnit(Object numberUnit) {
        this.numberUnit = numberUnit;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public ProductInfo getProduct() {
        return product;
    }

    public void setProduct(ProductInfo product) {
        this.product = product;
    }

    public int getSprayDay() {
        return sprayDay;
    }

    public void setSprayDay(int sprayDay) {
        this.sprayDay = sprayDay;
    }

    public int getUserFarmId() {
        return userFarmId;
    }

    public void setUserFarmId(int userFarmId) {
        this.userFarmId = userFarmId;
    }

    public String getLiveState() {
        return liveState;
    }

    public void setLiveState(String liveState) {
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

    public Object getFarmLog() {
        return farmLog;
    }

    public void setFarmLog(Object farmLog) {
        this.farmLog = farmLog;
    }

    public int getProductCount() {
        return productCount;
    }

    public void setProductCount(int productCount) {
        this.productCount = productCount;
    }

    public int getLandType() {
        return landType;
    }

    public void setLandType(int landType) {
        this.landType = landType;
    }

    public int getDetailsId() {
        return detailsId;
    }

    public void setDetailsId(int detailsId) {
        this.detailsId = detailsId;
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

    public List<CostEntity> getCosts() {
        return costs;
    }

    public void setCosts(List<CostEntity> costs) {
        this.costs = costs;
    }

    public ProductInfo getPoductModel() {
        return poductModel;
    }

    public void setPoductModel(ProductInfo poductModel) {
        this.poductModel = poductModel;
    }

    public List<FileEntity> getDetailsVideos() {
        return detailsVideos;
    }

    public void setDetailsVideos(List<FileEntity> detailsVideos) {
        this.detailsVideos = detailsVideos;
    }

    public List<MonitorsEntity> getMonitors() {
        return monitors;
    }

    public void setMonitors(List<MonitorsEntity> monitors) {
        this.monitors = monitors;
    }
}
