package com.croshe.farming.Entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/6/5.
 */

public class CommentInfo implements Serializable {
//      "commentLevel": null,
//              "parentId": null,
//              "commentImages": null,
//              "toUserId": null,
//              "userId": 1,
//              "commentContent": "农场日志好",
//              "commentDateTime": "2017-06-02 16:16:22",
//              "targetId": 1,
//              "orderId": null,
//              "targetType": 7,
//              "commentId": 6
    private String commentLevel;
    private String parentId;
    private String commentImages;
    private String toUserId;
    private String userId;
    private String commentContent;
    private String targetId;
    private String orderId;
    private String targetType;
    private String commentId;
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCommentLevel() {
        return commentLevel;
    }

    public void setCommentLevel(String commentLevel) {
        this.commentLevel = commentLevel;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getCommentImages() {
        return commentImages;
    }

    public void setCommentImages(String commentImages) {
        this.commentImages = commentImages;
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

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getTargetType() {
        return targetType;
    }

    public void setTargetType(String targetType) {
        this.targetType = targetType;
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }
}
