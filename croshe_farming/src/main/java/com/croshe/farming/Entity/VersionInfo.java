package com.croshe.farming.Entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/7/25.
 */

public class VersionInfo implements Serializable {


    /**
     * versionDesc : 鏈€鏂扮増鏈細1.0
     * versionCode : 30
     * disable : 0
     * versionImportant : 0
     * versionName : 1.0
     * versionImportantDesc : 涓€鑸?     * downUrl : http://www.croshe.com:8085/app/down?id=8
     * success : true
     */

    private String versionDesc;
    private int versionCode;
    private int disable;
    private int versionImportant;
    private String versionName;
    private String versionImportantDesc;
    private String downUrl;
    private boolean success;

    public String getVersionDesc() {
        return versionDesc;
    }

    public void setVersionDesc(String versionDesc) {
        this.versionDesc = versionDesc;
    }

    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public int getDisable() {
        return disable;
    }

    public void setDisable(int disable) {
        this.disable = disable;
    }

    public int getVersionImportant() {
        return versionImportant;
    }

    public void setVersionImportant(int versionImportant) {
        this.versionImportant = versionImportant;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getVersionImportantDesc() {
        return versionImportantDesc;
    }

    public void setVersionImportantDesc(String versionImportantDesc) {
        this.versionImportantDesc = versionImportantDesc;
    }

    public String getDownUrl() {
        return downUrl;
    }

    public void setDownUrl(String downUrl) {
        this.downUrl = downUrl;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
