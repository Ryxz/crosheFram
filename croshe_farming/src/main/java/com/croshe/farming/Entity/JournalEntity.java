package com.croshe.farming.Entity;

import java.util.List;

/**
 * Created by Administrator on 2017/7/13.
 */

public class JournalEntity {
    /**
     * userFarmId : 1
     * landType : 0
     * logContent : 今天浇了大粪，长的很好！！！！
     * detailsId : 1
     * logId : 2
     * logTime : 2017-07-13 10:38:42
     * userId : 4
     */

    private int userFarmId;
    private int landType;
    private String logContent;
    private int detailsId;
    private int logId;
    private String logTime;
    private int userId;
    private List<ImgInfo> logImages;

    public int getUserFarmId() {
        return userFarmId;
    }

    public void setUserFarmId(int userFarmId) {
        this.userFarmId = userFarmId;
    }

    public int getLandType() {
        return landType;
    }

    public void setLandType(int landType) {
        this.landType = landType;
    }

    public String getLogContent() {
        return logContent;
    }

    public void setLogContent(String logContent) {
        this.logContent = logContent;
    }

    public int getDetailsId() {
        return detailsId;
    }

    public void setDetailsId(int detailsId) {
        this.detailsId = detailsId;
    }

    public int getLogId() {
        return logId;
    }

    public void setLogId(int logId) {
        this.logId = logId;
    }

    public String getLogTime() {
        return logTime;
    }

    public void setLogTime(String logTime) {
        this.logTime = logTime;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<ImgInfo> getLogImages() {
        return logImages;
    }

    public void setLogImages(List<ImgInfo> logImages) {
        this.logImages = logImages;
    }
}
