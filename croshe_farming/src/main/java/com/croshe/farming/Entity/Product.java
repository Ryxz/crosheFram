package com.croshe.farming.Entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ZPA on 2017/6/28.
 */

public class Product implements Serializable {
    private String productSmallImage;
    private List<ImgInfo> productImages;
    private String productPrice;
    private String rate;
    private String productId;
    private CommentModel tCommentModels;
    private List<ProductStands> productStandardModels;
    private ProductType type;
    private String productSubtitle;
    private String productName;
    private String productBigImage;
    private List<StandardNameEntity> standardName;
    private List<String> productKeys;
    private Collocation collocation;
    private List<ProductInfo> productList;
    private List<CommentModel> commentlist;

    public List<CommentModel> getCommentlist() {
        return commentlist;
    }

    public void setCommentlist(List<CommentModel> commentlist) {
        this.commentlist = commentlist;
    }

    public List<ProductInfo> getProductList() {
        return productList;
    }

    public void setProductList(List<ProductInfo> productList) {
        this.productList = productList;
    }

    public Collocation getCollocation() {
        return collocation;
    }

    public void setCollocation(Collocation collocation) {
        this.collocation = collocation;
    }

    public String getProductBigImage() {
        return productBigImage;
    }

    public void setProductBigImage(String productBigImage) {
        this.productBigImage = productBigImage;
    }

    public List<StandardNameEntity> getStandardName() {
        return standardName;
    }

    public void setStandardName(List<StandardNameEntity> standardName) {
        this.standardName = standardName;
    }

    public String getProductSubtitle() {
        return productSubtitle;
    }

    public void setProductSubtitle(String productSubtitle) {
        this.productSubtitle = productSubtitle;
    }

    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }

    public List<ProductStands> getProductStandardModels() {
        return productStandardModels;
    }

    public void setProductStandardModels(List<ProductStands> productStandardModels) {
        this.productStandardModels = productStandardModels;
    }



    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public List<ImgInfo> getProductImages() {
        return productImages;
    }

    public void setProductImages(List<ImgInfo> productImages) {
        this.productImages = productImages;
    }

    public CommentModel gettCommentModels() {
        return tCommentModels;
    }

    public void settCommentModels(CommentModel tCommentModels) {
        this.tCommentModels = tCommentModels;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }


    public String getProductSmallImage() {
        return productSmallImage;
    }

    public void setProductSmallImage(String productSmallImage) {
        this.productSmallImage = productSmallImage;
    }


    public List<String> getProductKeys() {
        return productKeys;
    }

    public void setProductKeys(List<String> productKeys) {
        this.productKeys = productKeys;
    }
}
