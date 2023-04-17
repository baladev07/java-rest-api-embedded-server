package com.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;

@Entity
@Table(name="FileTable")
public class FileObject {

    @Id
    @Column(name="FileId")
    int fileId;

    @Column(name="FileName")
    String fileName;

    @Column(name="FileStream")
    String fileReader;

    @Column(name="CreatedTime")
    long lastUpdatedTime;

    @Column(name="LastUpdatedTime")
    long createdTime;

    public long getLastUpdatedTime() {
        return lastUpdatedTime;
    }

    public void setLastUpdatedTime(long lastUpdatedTime) {
        this.lastUpdatedTime = lastUpdatedTime;
    }

    public long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(long createdTime) {
        this.createdTime = createdTime;
    }

    public String getFileReader() {
        return fileReader;
    }

    public void setFileReader(String fileReader) {
        this.fileReader = fileReader;
    }

    public int getFileId() {
        return fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

}

