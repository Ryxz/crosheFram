package com.croshe.farming.Entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/6/27.
 */
/*
* 收货地址的信息
*       "addressState":0,
        "addressDateTime":"2017-06-27 18:04:53",
        "postal":"230031",
        "street":"望江西路",
        "addressStateStr":"正常",
        "addressId":12,
        "city":"合肥市",
        "area":"庐阳区",
        "details":"555号",
        "shopAddressLayerCode":null,
        "userId":5,
        "province":"安徽省",
        "userName":"李四",
        "userPhone":"18856042333",
        "longitude":125.288319,
        "latitude":43.833513
    }
* */
public class ReceiveAddInfo implements Serializable{
    String userName;
    String userPhone;
    String province;
    String city;
    String area;
    String street;
    String details;
    String addressId;
    String postal;
    String addressState;
    /**
     * addressState : 1
     * addressDateTime : 2017-07-05 22:51:04
     * addressStateStr : 默认
     * addressType : 1
     * addressId : 1
     * shopAddressLayerCode : null
     * userId : 4
     * longitude : 125.288319
     * latitude : 43.833513
     * addressName : null
     */

    private int addressStateX;
    private String addressDateTime;
    private String addressStateStr;
    private int addressType;
    private int addressIdX;
    private Object shopAddressLayerCode;
    private int userId;
    private double longitude;
    private double latitude;
    private Object addressName;

    public String getAddressState() {
        return addressState;
    }

    public void setAddressState(String addressState) {
        this.addressState = addressState;
    }

    public String getPostal() {
        return postal;
    }

    public void setPostal(String postal) {
        this.postal = postal;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
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

    public int getAddressStateX() {
        return addressStateX;
    }

    public void setAddressStateX(int addressStateX) {
        this.addressStateX = addressStateX;
    }

    public String getAddressDateTime() {
        return addressDateTime;
    }

    public void setAddressDateTime(String addressDateTime) {
        this.addressDateTime = addressDateTime;
    }

    public String getAddressStateStr() {
        return addressStateStr;
    }

    public void setAddressStateStr(String addressStateStr) {
        this.addressStateStr = addressStateStr;
    }

    public int getAddressType() {
        return addressType;
    }

    public void setAddressType(int addressType) {
        this.addressType = addressType;
    }

    public int getAddressIdX() {
        return addressIdX;
    }

    public void setAddressIdX(int addressIdX) {
        this.addressIdX = addressIdX;
    }

    public Object getShopAddressLayerCode() {
        return shopAddressLayerCode;
    }

    public void setShopAddressLayerCode(Object shopAddressLayerCode) {
        this.shopAddressLayerCode = shopAddressLayerCode;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public Object getAddressName() {
        return addressName;
    }

    public void setAddressName(Object addressName) {
        this.addressName = addressName;
    }
}
