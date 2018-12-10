package com.croshe.farming.Entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/6/3.
 */

public class ImgInfo implements Serializable {


    /**
     * fileType : .png
     * thumbPath : null
     * filePath : attachments/logImages/80c9a1fb0a62ff7059ce0a3756146de1.png
     * lock : false
     * lng : null
     * fileContent :
     * fileSize : 0
     * duration : 0
     * file : null
     * fileName : A157Y82ZJFA%6S~Z@1BZ_YG.png
     * fileDateTime : 2017-07-13 10:38:24
     * key : null
     * lat : null
     * id : extModel1788-1
     */

    private String fileType;
    private String thumbPath;
    private String filePath;
    private boolean lock;
    private String lng;
    private String fileContent;
    private int fileSize;
    private int duration;
    private String file;
    private String fileName;
    private String fileDateTime;
    private String key;
    private String lat;
    private String id;

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getThumbPath() {
        return thumbPath;
    }

    public void setThumbPath(String thumbPath) {
        this.thumbPath = thumbPath;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public boolean isLock() {
        return lock;
    }

    public void setLock(boolean lock) {
        this.lock = lock;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getFileContent() {
        return fileContent;
    }

    public void setFileContent(String fileContent) {
        this.fileContent = fileContent;
    }

    public int getFileSize() {
        return fileSize;
    }

    public void setFileSize(int fileSize) {
        this.fileSize = fileSize;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }


    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileDateTime() {
        return fileDateTime;
    }

    public void setFileDateTime(String fileDateTime) {
        this.fileDateTime = fileDateTime;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
