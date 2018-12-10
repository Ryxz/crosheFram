package com.croshe.farming.Entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/6/7.
 */

public class EvaluationInfo implements Serializable {
//    "commentLevel": 4,
//            "parentId": null,
//            "commentImages": null,
//            "toUserId": null,
//            "userId": 2,
//            "commentContent": "套餐大减价",
//            "commentDateTime": "2017-06-06 16:46:26",
//            "targetId": 4,
//            "orderId": null,
//            "targetType": 6,
//            "commentId": 10
    private String commentLevel;
    private String parentId;
    private List<ImgInfo> commentImages;
    private String toUserId;
    private String userId;
    private String commentContent;
    private String commentDateTime;
    private String targetId;
    private String orderId;
    private String targetType;
    private String commentId;
    private String userHeadImg;
    private String userName;

    public String getUserHeadImg() {
        return userHeadImg;
    }

    public void setUserHeadImg(String userHeadImg) {
        this.userHeadImg = userHeadImg;
    }

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

    public List<ImgInfo> getCommentImages() {
        return commentImages;
    }

    public void setCommentImages(List<ImgInfo> commentImages) {
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
