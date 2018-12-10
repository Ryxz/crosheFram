package com.croshe.farming.Entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/6/30.
 */
public class CommentModel implements Serializable {
    /**
     * commentLevel : 3
     * userHeadImg : attachments/userHeadImg/ea16751b04405ce054d9a24d869c3a48.jpg
     * fuwu : 4
     * commentDateTime : 2017-07-25 11:58:12
     * targetId : 67
     * userGarde : 0
     * commentId : 5
     * userNickName : ZZZ
     * parentId : -1
     * peisong : 5
     * toUserId : -1
     * userId : 7
     * commentContent : 特别好
     * targetType : 1
     * orderId : 110
     */

    private String commentLevel;
    private String userHeadImg;
    private String fuwu;
    private String commentDateTime;
    private String targetId;
    private String userGarde;
    private String commentId;
    private String userNickName;
    private String parentId;
    private String peisong;
    private String toUserId;
    private String userId;
    private String commentContent;
    private int targetType;
    private String orderId;
    private List<Images> commentImages;

    public List<Images> getCommentImages() {
        return commentImages;
    }

    public void setCommentImages(List<Images> commentImages) {
        this.commentImages = commentImages;
    }

    public String getCommentLevel() {
        return commentLevel;
    }

    public void setCommentLevel(String commentLevel) {
        this.commentLevel = commentLevel;
    }

    public String getUserHeadImg() {
        return userHeadImg;
    }

    public void setUserHeadImg(String userHeadImg) {
        this.userHeadImg = userHeadImg;
    }

    public String getFuwu() {
        return fuwu;
    }

    public void setFuwu(String fuwu) {
        this.fuwu = fuwu;
    }

    public String getCommentDateTime() {
        return commentDateTime;
    }

    public void setCommentDateTime(String commentDateTime) {
        this.commentDateTime = commentDateTime;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public String getUserGarde() {
        return userGarde;
    }

    public void setUserGarde(String userGarde) {
        this.userGarde = userGarde;
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getUserNickName() {
        return userNickName;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getPeisong() {
        return peisong;
    }

    public void setPeisong(String peisong) {
        this.peisong = peisong;
    }

    public String getToUserId() {
        return toUserId;
    }

    public void setToUserId(String toUserId) {
        this.toUserId = toUserId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public int getTargetType() {
        return targetType;
    }

    public void setTargetType(int targetType) {
        this.targetType = targetType;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }


}
