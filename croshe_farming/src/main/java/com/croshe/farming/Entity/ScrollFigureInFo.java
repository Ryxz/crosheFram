package com.croshe.farming.Entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/6/1.
 */

public class ScrollFigureInFo implements Serializable {
//     "advertImage": "attachments/merchantTBHYZGZ/2f11762ad309919cb56419f9747b1bd4.jpg",
//             "advertTitle": "端午大优惠",
//             "advertIndex": 2,
//             "advertDateTime": "2017-05-27 10:14:05",
//             "advertId": 2,
//             "advertContent": "端午大优惠，小长假活动不断。"

    private String advertImage;
    private String advertTitle;
    private String advertIndex;
    private String advertDateTime;
    private String advertId;
    private String advertContent;

    public String getAdvertImage() {
        return advertImage;
    }

    public void setAdvertImage(String advertImage) {
        this.advertImage = advertImage;
    }

    public String getAdvertTitle() {
        return advertTitle;
    }

    public void setAdvertTitle(String advertTitle) {
        this.advertTitle = advertTitle;
    }

    public String getAdvertIndex() {
        return advertIndex;
    }

    public void setAdvertIndex(String advertIndex) {
        this.advertIndex = advertIndex;
    }

    public String getAdvertDateTime() {
        return advertDateTime;
    }

    public void setAdvertDateTime(String advertDateTime) {
        this.advertDateTime = advertDateTime;
    }

    public String getAdvertId() {
        return advertId;
    }

    public void setAdvertId(String advertId) {
        this.advertId = advertId;
    }

    public String getAdvertContent() {
        return advertContent;
    }

    public void setAdvertContent(String advertContent) {
        this.advertContent = advertContent;
    }
}
