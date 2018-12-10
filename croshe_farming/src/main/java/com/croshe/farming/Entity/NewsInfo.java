package com.croshe.farming.Entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/6/1.
 */

public class NewsInfo  implements Serializable {
//      "newsImage": null,
//              "newsId": 4,
//              "newsTop": 1,
//              "newsTopTime": "2017-05-27 10:37:12",
//              "newsDateTime": "2017-05-27 10:37:12",
//              "newsContent": "新闻4的内容",
//              "newsSource": "444444",
//              "newsTitle": "新闻4"
    private String newsImage;
    private String newsId;
    private String newsTop;
    private String newsTopTime;
    private String newsDateTime;
    private String newsContent;
    private String newsSource;
    private String newsTitle;
    private String newsUrl;

    public String getNewsUrl() {
        return newsUrl;
    }

    public void setNewsUrl(String newsUrl) {
        this.newsUrl = newsUrl;
    }

    public String getNewsImage() {
        return newsImage;
    }

    public void setNewsImage(String newsImage) {
        this.newsImage = newsImage;
    }

    public String getNewsId() {
        return newsId;
    }

    public void setNewsId(String newsId) {
        this.newsId = newsId;
    }

    public String getNewsTop() {
        return newsTop;
    }

    public void setNewsTop(String newsTop) {
        this.newsTop = newsTop;
    }

    public String getNewsTopTime() {
        return newsTopTime;
    }

    public void setNewsTopTime(String newsTopTime) {
        this.newsTopTime = newsTopTime;
    }

    public String getNewsDateTime() {
        return newsDateTime;
    }

    public void setNewsDateTime(String newsDateTime) {
        this.newsDateTime = newsDateTime;
    }

    public String getNewsContent() {
        return newsContent;
    }

    public void setNewsContent(String newsContent) {
        this.newsContent = newsContent;
    }

    public String getNewsSource() {
        return newsSource;
    }

    public void setNewsSource(String newsSource) {
        this.newsSource = newsSource;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }
}
