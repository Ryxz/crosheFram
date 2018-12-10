package com.croshe.farming.Entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/6/5.
 */

public class FarmLogInfo implements Serializable {
//     "userFarmId": 1,
//             "landType": 0,
//             "logContent": "农场日志",
//             "logId": 1,
//             "logTime": "2017-06-02 13:26:02",
//             "userId": 1,
    private String userFarmId;
    private String landType;
    private String logId;
    private String logTime;
    private String logContent;
    private String userId;
    private List<ImgInfo> logImages;

    public String getLogContent() {
        return logContent;
    }

    public void setLogContent(String logContent) {
        this.logContent = logContent;
    }

    public String getUserFarmId() {
        return userFarmId;
    }

    public void setUserFarmId(String userFarmId) {
        this.userFarmId = userFarmId;
    }

    public String getLandType() {
        return landType;
    }

    public void setLandType(String landType) {
        this.landType = landType;
    }

    public String getLogId() {
        return logId;
    }

    public void setLogId(String logId) {
        this.logId = logId;
    }

    public String getLogTime() {
        return logTime;
    }

    public void setLogTime(String logTime) {
        this.logTime = logTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<ImgInfo> getLogImages() {
        return logImages;
    }

    public void setLogImages(List<ImgInfo> logImages) {
        this.logImages = logImages;
    }
}
