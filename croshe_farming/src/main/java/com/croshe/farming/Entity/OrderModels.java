package com.croshe.farming.Entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/6/28.
 */
//订单状态
public class OrderModels implements Serializable {
    /**
     * scoreDeducted : null
     * orderPayTime : null
     * orderCode : NC201707061147526025
     * orderPayType : 1
     * orderDeatils : [{"product":{"ifPublish":1,"productKeys":null,"productImages":null,"productBigImage":"attachments/merchantTBHYZGZ/0bc2bacc690454a1b81c7189423b9322.jpg","type":{"typeTop":1,"typeType":0,"typeName":"叶菜类","productTypeLayerCode":"0000000003","parentId":2,"typeSubtitle":null,"typeDateTime":"2017-05-31 16:03:08","typeImage":null,"typeClass":0,"typeId":3},"productSubtype":null,"matureImage":"attachments/matureImage/a40907d53994ea580e9131914f3ca718.jpg","shopId":0,"productDetails":null,"useArea":1,"productSubtitle":"有机种植芹菜种籽","productSmallImage":"attachments/merchantTBHYZGZ/dc83a9601a378f00cb9f994c376d8707.jpg","standardName":"[]","publishTime":"2017-07-05 19:11:42","productCode":"00000000058","productMonthCount":0,"typeClass":0,"productId":58,"productState":0,"productDateTime":"2017-07-04 16:33:11","typeType":0,"productAddress":null,"harvestType":0,"productOldPrice":0.3,"productPrice":0.3,"productName":"有机种植芹菜种籽","typeId":3,"productLayerCode":null},"standardId":16,"price":12,"count":1,"orderCode":"NC201707061147526025","orderDeatilsId":133,"shopId":0,"orderDeatilsDateTime":"2017-07-06 11:47:52","orderDeatilsType":1,"targetId":58,"totalPrice":0}]
     * orderDownTime : 2017-07-06 11:47:52
     * orderTruePrice : 36
     * backReason : null
     * backImages : null
     * city : null
     * billTitle : null
     * area : null
     * details : null
     * name : ftyfggy@qq.com
     * userId : 4
     * province : null
     * userName : null
     * userPhone : null
     * longitude : 125.288319
     * orderId : 131
     * orderDateTime : 2017-07-06 11:47:52
     * orderState : 0
     * backDetails : null
     * orderBill : null
     * orderType : 0
     * postal : null
     * orderParentId : 0
     * backMoney : null
     * orderSendTime : null
     * orderCostsPrice : null
     * billContent : null
     * orderDoPayType : null
     * orderManagePrice : null
     * orderPrice : 36
     * latitude : 43.833513
     */

    private String scoreDeducted;
    private String orderPayTime;
    private String orderCode;
    private int orderPayType;
    private String orderDownTime;
    private double orderTruePrice;
    private String backReason;
    private String backImages;
    private String city;
    private String billTitle;
    private String area;
    private String details;
    private String orderPhone;

    public String getOrderPhone() {
        return orderPhone;
    }

    public void setOrderPhone(String orderPhone) {
        this.orderPhone = orderPhone;
    }

    public String getBackReason() {
        return backReason;
    }

    public void setBackReason(String backReason) {
        this.backReason = backReason;
    }

    public String getBackImages() {
        return backImages;
    }

    public void setBackImages(String backImages) {
        this.backImages = backImages;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getBillTitle() {
        return billTitle;
    }

    public void setBillTitle(String billTitle) {
        this.billTitle = billTitle;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    private int userId;
    private String province;
    private String userPhone;
    private double longitude;
    private int orderId;
    private String orderDateTime;
    private int orderState;
    private Object backDetails;
    private Object orderBill;
    private int orderType;
    private String postal;
    private int orderParentId;
    private String backMoney;
    private String orderSendTime;
    private String orderCostsPrice;
    private String billContent;
    private String orderDoPayType;
    private String orderManagePrice;
    private double orderPrice;
    private double latitude;
    private List<OrderDeatils> orderDeatils;
    private String orderTypeStr;
    private List<OrderDeatils> deatils;
    private String userName;
    private String address;
    private String name;
    /**
     * scoreDeducted : null
     * orderPayTime : 2017-07-20 18:05:08
     * orderTruePrice : 2
     * backReason : null
     * addressId : 1
     * backImages : null
     * city : null
     * billTitle : null
     * area : null
     * details : null
     * orderStateStr : 待配送
     * province : null
     * userName : null
     * userPhone : null
     * longitude : null
     * backDetails : null
     * orderBill : null
     * postal : null
     * orderBillStr : 不需要发票
     * backMoney : null
     * orderSendTime : null
     * orderCostsPrice : null
     * billContent : null
     * orderDoPayType : 2
     * orderManagePrice : null
     * orderPrice : 2
     * orderDoPayTypeStr : 余额支付
     * latitude : null
     */

    private int addressId;
    private String orderStateStr;
    private String orderBillStr;
    private String orderDoPayTypeStr;
    /**
     * city : 合肥市
     * billTitle : null
     * area : 庐阳区
     */


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrderTypeStr() {
        return orderTypeStr;
    }

    public void setOrderTypeStr(String orderTypeStr) {
        this.orderTypeStr = orderTypeStr;
    }

    public List<OrderDeatils> getOrderDeatils() {
        return orderDeatils;
    }

    public void setOrderDeatils(List<OrderDeatils> orderDeatils) {
        this.orderDeatils = orderDeatils;
    }

    public String getScoreDeducted() {
        return scoreDeducted;
    }

    public void setScoreDeducted(String scoreDeducted) {
        this.scoreDeducted = scoreDeducted;
    }

    public String getOrderPayTime() {
        return orderPayTime;
    }

    public void setOrderPayTime(String orderPayTime) {
        this.orderPayTime = orderPayTime;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public int getOrderPayType() {
        return orderPayType;
    }

    public void setOrderPayType(int orderPayType) {
        this.orderPayType = orderPayType;
    }

    public String getOrderDownTime() {
        return orderDownTime;
    }

    public void setOrderDownTime(String orderDownTime) {
        this.orderDownTime = orderDownTime;
    }

    public double getOrderTruePrice() {
        return orderTruePrice;
    }

    public void setOrderTruePrice(double orderTruePrice) {
        this.orderTruePrice = orderTruePrice;
    }


    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }



    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getOrderDateTime() {
        return orderDateTime;
    }

    public void setOrderDateTime(String orderDateTime) {
        this.orderDateTime = orderDateTime;
    }

    public int getOrderState() {
        return orderState;
    }

    public void setOrderState(int orderState) {
        this.orderState = orderState;
    }

    public Object getBackDetails() {
        return backDetails;
    }

    public void setBackDetails(Object backDetails) {
        this.backDetails = backDetails;
    }

    public Object getOrderBill() {
        return orderBill;
    }

    public void setOrderBill(Object orderBill) {
        this.orderBill = orderBill;
    }

    public int getOrderType() {
        return orderType;
    }

    public void setOrderType(int orderType) {
        this.orderType = orderType;
    }

    public Object getPostal() {
        return postal;
    }


    public int getOrderParentId() {
        return orderParentId;
    }

    public void setOrderParentId(int orderParentId) {
        this.orderParentId = orderParentId;
    }

    public Object getBackMoney() {
        return backMoney;
    }


    public Object getOrderSendTime() {
        return orderSendTime;
    }


    public String getOrderCostsPrice() {
        return orderCostsPrice;
    }


    public Object getBillContent() {
        return billContent;
    }


    public String getOrderDoPayType() {
        return orderDoPayType;
    }

    public void setPostal(String postal) {
        this.postal = postal;
    }

    public void setBackMoney(String backMoney) {
        this.backMoney = backMoney;
    }

    public void setOrderSendTime(String orderSendTime) {
        this.orderSendTime = orderSendTime;
    }

    public void setOrderCostsPrice(String orderCostsPrice) {
        this.orderCostsPrice = orderCostsPrice;
    }

    public void setBillContent(String billContent) {
        this.billContent = billContent;
    }

    public void setOrderDoPayType(String orderDoPayType) {
        this.orderDoPayType = orderDoPayType;
    }

    public void setOrderManagePrice(String orderManagePrice) {
        this.orderManagePrice = orderManagePrice;
    }

    public String getOrderManagePrice() {
        return orderManagePrice;
    }

    public double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(double orderPrice) {
        this.orderPrice = orderPrice;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }


    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }


    public String getOrderStateStr() {
        return orderStateStr;
    }

    public void setOrderStateStr(String orderStateStr) {
        this.orderStateStr = orderStateStr;
    }


    public String getOrderBillStr() {
        return orderBillStr;
    }

    public void setOrderBillStr(String orderBillStr) {
        this.orderBillStr = orderBillStr;
    }

    public String getOrderDoPayTypeStr() {
        return orderDoPayTypeStr;
    }

    public void setOrderDoPayTypeStr(String orderDoPayTypeStr) {
        this.orderDoPayTypeStr = orderDoPayTypeStr;
    }

    public List<OrderDeatils> getDeatils() {
        return deatils;
    }

    public void setDeatils(List<OrderDeatils> deatils) {
        this.deatils = deatils;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}


