package com.Model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TaskTable")
public class TaskObject {

    @Id
    @Column(name="TaskID")
    protected int taskId;

    @Column(name="TaskTitle")
    protected String taskTitle;

    @Column(name="TaskDescription")
    protected String taskDescription;

    @Column(name="CreatedTime")
    protected long createdTime;

    @Column(name="LastUpdatedTime")
    protected long lastUpdatedTime;

    @Column(name="CreatedBy")
    protected int createdBy;

    @Column(name="AssignedTo")
    protected int assignedTo;

    @Column(name="Status")
    protected int status;

    @Column(name="DueDate")
    protected long dueDate;

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
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

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public int getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(int assignedTo) {
        this.assignedTo = assignedTo;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getDueDate() {
        return dueDate;
    }

    public void setDueDate(long dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return "TaskObject{" +
                "taskId=" + taskId +
                ", taskTitle='" + taskTitle + '\'' +
                ", taskDescription='" + taskDescription + '\'' +
                ", createdTime=" + createdTime +
                ", lastUpdatedTime=" + lastUpdatedTime +
                ", createdBy=" + createdBy +
                ", assignedTo=" + assignedTo +
                ", status=" + status +
                ", dueDate=" + dueDate +
                '}';
    }
}
