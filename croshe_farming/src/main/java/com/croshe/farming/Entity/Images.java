package com.croshe.farming.Entity;

/**
 * Created by Administrator on 2017/6/30.
 */
public class Images {

    /**
     * fileName : 1499266719076.jpg
     * filePath : attachments/commentImage/b111e5fd63646c529cff0bf203a095a1.jpg
     * fileType : .jpeg
     * fileContent :
     * fileDateTime : 2017-07-25 11:58:12
     * duration : 0
     * fileSize : 135656
     * isLock : false
     */

    private String fileName;
    private String filePath;
    private String fileType;
    private String fileContent;
    private String fileDateTime;
    private String duration;
    private String fileSize;
    private boolean isLock;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFileContent() {
        return fileContent;
    }

    public void setFileContent(String fileContent) {
        this.fileContent = fileContent;
    }

    public String getFileDateTime() {
        return fileDateTime;
    }

    public void setFileDateTime(String fileDateTime) {
        this.fileDateTime = fileDateTime;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public boolean isIsLock() {
        return isLock;
    }

    public void setIsLock(boolean isLock) {
        this.isLock = isLock;
    }
}
