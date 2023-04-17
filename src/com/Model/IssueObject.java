package com.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="IssueTable")
public class IssueObject {

    @Id
    @Column(name="IssueId")
    protected int issueId;

    @Column(name="IssueDescription")
    protected String issueDescription;

    @Column(name="FileId")
    protected int fileId;

    @Column(name="AssignedTo")
    protected int assignedTo;

    @Column(name="IssueTitle")
    protected String issueTitle;

    @Column(name="CreatedTime")
    protected long createdTime;

    @Column(name="LastUpdatedTime")
    protected long lastUpdatedTime;

    @Column(name="IssueStatus")
    protected int issueStatus;

    @Column(name="IsNotified")
    protected boolean isNotified;

    @Column(name="CreatedBy")
    protected int createdBy;

    public int getIssueId() {
        return issueId;
    }

    public void setIssueId(int issueId) {
        this.issueId = issueId;
    }

    public String getIssueDescription() {
        return issueDescription;
    }

    public void setIssueDescription(String issueDescription) {
        this.issueDescription = issueDescription;
    }

    public int getFileId() {
        return fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    public int getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(int assignedTo) {
        this.assignedTo = assignedTo;
    }

    public String getIssueTitle() {
        return issueTitle;
    }

    public void setIssueTitle(String issueTitle) {
        this.issueTitle = issueTitle;
    }

    public long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(long createdTime) {
        this.createdTime = createdTime;
    }

    public long getLastUpdatedTime() {
        return lastUpdatedTime;
    }

    public void setLastUpdatedTime(long lastUpdatedTime) {
        this.lastUpdatedTime = lastUpdatedTime;
    }

    public int getIssueStatus() {
        return issueStatus;
    }

    public void setIssueStatus(int issueStatus) {
        this.issueStatus = issueStatus;
    }

    public boolean getIsNotified() {
        return isNotified;
    }

    public void setIsNotified(boolean notified) {
        isNotified = notified;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public String toString() {
        return "IssueObject{" +
                "issueId=" + issueId +
                ", issueDescription='" + issueDescription + '\'' +
                ", fileId=" + fileId +
                ", assignedTo=" + assignedTo +
                ", issueTitle='" + issueTitle + '\'' +
                ", createdTime=" + createdTime +
                ", lastUpdatedTime=" + lastUpdatedTime +
                ", issueStatus=" + issueStatus +
                ", isNotified=" + isNotified +
                ", createdBy=" + createdBy +
                '}';
    }
}
