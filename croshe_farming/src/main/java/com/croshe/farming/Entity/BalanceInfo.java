package com.croshe.farming.Entity;

/**
 * Created by ZPA on 2017/6/28.
 */
/*
*  "recordId":5,
        "flowType":0,
        "recordCode":"NC201706131707477693",
        "payTypeStr":"支付宝",
        "recordDateTime":"2017-06-13 17:07:47",
        "balance":380,
        "flowTypeStr":"在线支付",
        "userId":6,
        "payType":0,
        "recordMoney":500,
        "recordDtails":"迪奥",
        "recordTypeStr":"支出",
        "recordType":1
* */
public class BalanceInfo {
    String recordCode;
    String payTypeStr;
    String recordDateTime;
    String balance;
    String recordMoney;
    String recordDtails;
    String recordTypeStr;
    String flowTypeStr;

    public String getFlowTypeStr() {
        return flowTypeStr;
    }

    public void setFlowTypeStr(String flowTypeStr) {
        this.flowTypeStr = flowTypeStr;
    }

    public String getRecordTypeStr() {
        return recordTypeStr;
    }

    public void setRecordTypeStr(String recordTypeStr) {
        this.recordTypeStr = recordTypeStr;
    }

    public String getBalance() {
        return balance;
    }

    public void setPayTypeStr(String payTypeStr) {
        this.payTypeStr = payTypeStr;
    }

    public String getPayTypeStr() {
        return payTypeStr;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getRecordCode() {
        return recordCode;
    }

    public void setRecordCode(String recordCode) {
        this.recordCode = recordCode;
    }

    public String getRecordDateTime() {
        return recordDateTime;
    }

    public void setRecordDateTime(String recordDateTime) {
        this.recordDateTime = recordDateTime;
    }

    public String getRecordDtails() {
        return recordDtails;
    }

    public void setRecordDtails(String recordDtails) {
        this.recordDtails = recordDtails;
    }

    public String getRecordMoney() {
        return recordMoney;
    }

    public void setRecordMoney(String recordMoney) {
        this.recordMoney = recordMoney;
    }
}
