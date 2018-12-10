package com.croshe.farming.Entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/7/11.
 */

public class GrowEntity implements Serializable {

    /**
     * actionState : 1
     * actionTitle : 需要施肥，请您尽快施肥
     * targetId : 62
     * number : 12
     * actionType : 2
     * actionStateStr : 已操作
     * actionTime : null
     * actionTypeStr : 施肥
     * unit : 0
     * price : 1212
     * detailsId : 1
     * userId : 4
     * actionId : 1
     * targetType : 1
     */

    private int actionState;
    private String actionTitle;
    private int targetId;
    private int number;
    private int actionType;
    private String actionStateStr;
    private Object actionTime;
    private String actionTypeStr;
    private int unit;
    private Double price;
    private int detailsId;
    private int userId;
    private int actionId;
    private int targetType;
    private String unitStr;
    private ProductInfo product;

    public int getActionState() {
        return actionState;
    }

    public void setActionState(int actionState) {
        this.actionState = actionState;
    }

    public String getActionTitle() {
        return actionTitle;
    }

    public void setActionTitle(String actionTitle) {
        this.actionTitle = actionTitle;
    }

    public int getTargetId() {
        return targetId;
    }

    public void setTargetId(int targetId) {
        this.targetId = targetId;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getActionType() {
        return actionType;
    }

    public void setActionType(int actionType) {
        this.actionType = actionType;
    }

    public String getActionStateStr() {
        return actionStateStr;
    }

    public void setActionStateStr(String actionStateStr) {
        this.actionStateStr = actionStateStr;
    }

    public Object getActionTime() {
        return actionTime;
    }

    public void setActionTime(Object actionTime) {
        this.actionTime = actionTime;
    }

    public String getActionTypeStr() {
        return actionTypeStr;
    }

    public void setActionTypeStr(String actionTypeStr) {
        this.actionTypeStr = actionTypeStr;
    }

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getDetailsId() {
        return detailsId;
    }

    public void setDetailsId(int detailsId) {
        this.detailsId = detailsId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getActionId() {
        return actionId;
    }

    public void setActionId(int actionId) {
        this.actionId = actionId;
    }

    public int getTargetType() {
        return targetType;
    }

    public void setTargetType(int targetType) {
        this.targetType = targetType;
    }

    public String getUnitStr() {
        return unitStr;
    }

    public void setUnitStr(String unitStr) {
        this.unitStr = unitStr;
    }

    public ProductInfo getProduct() {
        return product;
    }

    public void setProduct(ProductInfo product) {
        this.product = product;
    }
}
