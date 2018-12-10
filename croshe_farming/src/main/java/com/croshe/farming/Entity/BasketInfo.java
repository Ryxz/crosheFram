package com.croshe.farming.Entity;

/**
 * Created by Administrator on 2017/6/8.
 */

public class BasketInfo {
//    "product":String{...},
//            "foodCarLayerCode":null,
//            "harvestedArea":1221,
//            "harvestPrice":122,
//            "carDateTime":"2017-06-05 18:50:46",
//            "userId":1,
//            "harvestArea":122,
//            "foodCarId":1,
//            "productId":1
    private String standardId;
    private StandInfo standard;
    private String weight;
    private String weightUnit;
    private String storeTime;
    private int number;
    private String typeClass;
    private String typeType;
    private String price;
    private String storeId;
    private String numberUnit;
    private String storeType;
    private boolean isselecte;
    private String userId;
    private String productId;
    private ProductInfo product;
    private String name;

    /**
     * harvestedArea : 0
     * harvestPrice : 0
     * foodCarId : 18
     * productId : 28
     * foodCarLayerCode : null
     * detailsId : 1
     * harvestState : null
     * harvestType : null
     * userId : 4
     * carDateTime : 2017-07-12 11:23:40
     * harvestArea : 12
     * harvestedNumber : 0
     * harvestNumber : 10
     *//**
     * harvestedState : 0
     * harvestPrice : 12
     * productId : 51
     * foodCarLayerCode : null
     * harvestedTypeStr : 按面积收获
     * harvestState : null
     * harvestType : null
     * userId : 4
     */

    private int harvestedArea;
    private double harvestPrice;
    private int foodCarId;
    private String foodCarLayerCode;
    private int detailsId;
    private String harvestState;
    private int harvestType;
    private String carDateTime;
    private Double harvestArea;
    private int harvestedNumber;
    private int harvestNumber;
    private int harvestedState;
    private int harvestPriceX;
    private String foodCarLayerCodeX;
    private String harvestedTypeStr;
    /**
     * harvestedType : 1
     * harvestPrice : 12
     * productId : 51
     * foodCarLayerCode : null
     * harvestState : null
     * harvestType : null
     * userId : 4
     */

    private int harvestedType;
    private int num = -1;
    private Double allmoney;
    private String area ="-2";

    private String weightUnitStr;
    private String numberUnitStr;

    public String getWeightUnitStr() {
        return weightUnitStr;
    }

    public void setWeightUnitStr(String weightUnitStr) {
        this.weightUnitStr = weightUnitStr;
    }

    public String getNumberUnitStr() {
        return numberUnitStr;
    }

    public void setNumberUnitStr(String numberUnitStr) {
        this.numberUnitStr = numberUnitStr;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Double getAllmoney() {
        return allmoney;
    }

    public void setAllmoney(Double allmoney) {
        this.allmoney = allmoney;
    }

    public void setHarvestType(int harvestType) {
        this.harvestType = harvestType;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public StandInfo getStandard() {
        return standard;
    }

    public void setStandard(StandInfo standard) {
        this.standard = standard;
    }

    public String getStandardId() {
        return standardId;
    }

    public void setStandardId(String standardId) {
        this.standardId = standardId;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getWeightUnit() {
        return weightUnit;
    }

    public void setWeightUnit(String weightUnit) {
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

    public String getTypeClass() {
        return typeClass;
    }

    public void setTypeClass(String typeClass) {
        this.typeClass = typeClass;
    }

    public String getTypeType() {
        return typeType;
    }

    public void setTypeType(String typeType) {
        this.typeType = typeType;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getNumberUnit() {
        return numberUnit;
    }

    public void setNumberUnit(String numberUnit) {
        this.numberUnit = numberUnit;
    }

    public String getStoreType() {
        return storeType;
    }

    public void setStoreType(String storeType) {
        this.storeType = storeType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isselecte() {
        return isselecte;
    }

    public void setIsselecte(boolean isselecte) {
        this.isselecte = isselecte;
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public ProductInfo getProduct() {
        return product;
    }

    public void setProduct(ProductInfo product) {
        this.product = product;
    }

    public int getHarvestedArea() {
        return harvestedArea;
    }

    public double getHarvestPrice() {
        return harvestPrice;
    }

    public void setHarvestedArea(int harvestedArea) {
        this.harvestedArea = harvestedArea;
    }

    public void setHarvestPrice(int harvestPrice) {
        this.harvestPrice = harvestPrice;
    }

    public int getFoodCarId() {
        return foodCarId;
    }

    public void setFoodCarId(int foodCarId) {
        this.foodCarId = foodCarId;
    }

    public String getFoodCarLayerCode() {
        return foodCarLayerCode;
    }

    public void setFoodCarLayerCode(String foodCarLayerCode) {
        this.foodCarLayerCode = foodCarLayerCode;
    }

    public int getDetailsId() {
        return detailsId;
    }

    public void setDetailsId(int detailsId) {
        this.detailsId = detailsId;
    }

    public String getHarvestState() {
        return harvestState;
    }

    public void setHarvestState(String harvestState) {
        this.harvestState = harvestState;
    }

    public int getHarvestType() {
        return harvestType;
    }

    public void setHarvestPrice(double harvestPrice) {
        this.harvestPrice = harvestPrice;
    }

    public String getCarDateTime() {
        return carDateTime;
    }

    public void setCarDateTime(String carDateTime) {
        this.carDateTime = carDateTime;
    }

    public Double getHarvestArea() {
        return harvestArea;
    }

    public void setHarvestArea(Double harvestArea) {
        this.harvestArea = harvestArea;
    }

    public int getHarvestedNumber() {
        return harvestedNumber;
    }

    public void setHarvestedNumber(int harvestedNumber) {
        this.harvestedNumber = harvestedNumber;
    }

    public int getHarvestNumber() {
        return harvestNumber;
    }

    public void setHarvestNumber(int harvestNumber) {
        this.harvestNumber = harvestNumber;
    }

    public int getHarvestedState() {
        return harvestedState;
    }

    public void setHarvestedState(int harvestedState) {
        this.harvestedState = harvestedState;
    }

    public int getHarvestPriceX() {
        return harvestPriceX;
    }

    public void setHarvestPriceX(int harvestPriceX) {
        this.harvestPriceX = harvestPriceX;
    }

    public String getFoodCarLayerCodeX() {
        return foodCarLayerCodeX;
    }

    public void setFoodCarLayerCodeX(String foodCarLayerCodeX) {
        this.foodCarLayerCodeX = foodCarLayerCodeX;
    }

    public String getHarvestedTypeStr() {
        return harvestedTypeStr;
    }

    public void setHarvestedTypeStr(String harvestedTypeStr) {
        this.harvestedTypeStr = harvestedTypeStr;
    }


    public int getHarvestedType() {
        return harvestedType;
    }

    public void setHarvestedType(int harvestedType) {
        this.harvestedType = harvestedType;
    }
}
