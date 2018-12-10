package com.croshe.farming.Entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/7/12.
 */

public class FileEntity implements Serializable{

    /**
     * fileType : .mp4
     * thumbPath : null
     * filePath : attachments/detailsVideos/5905866674faa4c103b16b9cb36da8cc.mp4
     * lock : false
     * lng : null
     * fileContent :
     * fileSize : 0
     * duration : 0
     * file : null
     * fileName : ad35f481d6e90948c025d4aaa14ffd8e.mp4
     * fileDateTime : 2017-07-12 10:14:18
     * lat : null
     * key : null
     * id : extModel662-1
     */

    private String fileType;
    private Object thumbPath;
    private String filePath;
    private boolean lock;
    private Object lng;
    private String fileContent;
    private int fileSize;
    private int duration;
    private Object file;
    private String fileName;
    private String fileDateTime;
    private Object lat;
    private Object key;
    private String id;
    private List<ImgInfo> logImages;

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public Object getThumbPath() {
        return thumbPath;
    }

    public void setThumbPath(Object thumbPath) {
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

    public Object getLng() {
        return lng;
    }

    public void setLng(Object lng) {
        this.lng = lng;
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

    public Object getFile() {
        return file;
    }

    public void setFile(Object file) {
        this.file = file;
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

    public Object getLat() {
        return lat;
    }

    public void setLat(Object lat) {
        this.lat = lat;
    }

    public Object getKey() {
        return key;
    }

    public void setKey(Object key) {
        this.key = key;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<ImgInfo> getLogImages() {
        return logImages;
    }

    public void setLogImages(List<ImgInfo> logImages) {
        this.logImages = logImages;
    }
}
