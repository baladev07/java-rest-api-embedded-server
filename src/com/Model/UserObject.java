package com.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Users")
public class UserObject {

    @Id
    @Column(name="userid")
    protected int userId;
    @Column(name="username")
    protected String userName;
    @Column(name="useremail")
    protected String userEmail;
    @Column(name="role")
    protected int role;
    @Column(name="createdtime")
    protected Long createdTime;
    @Column(name="lastmodifiedby")
    protected Long lastModifiedBy;
    @Column(name="lastupdatedtime")
    protected Long lastUpdatedTime;
    @Column(name="createdby")
    protected Long createdBy;

    public UserObject()
    {

    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public Long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Long createdTime) {
        this.createdTime = createdTime;
    }

    public Long getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(Long lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Long getLastUpdatedTime() {
        return lastUpdatedTime;
    }

    public void setLastUpdatedTime(Long lastUpdatedTime) {
        this.lastUpdatedTime = lastUpdatedTime;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public String toString() {
        return "UserObject{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", role=" + role +
                ", createdTime=" + createdTime +
                ", lastModifiedBy=" + lastModifiedBy +
                ", lastUpdatedTime=" + lastUpdatedTime +
                ", createdBy=" + createdBy + '}';
    }
}
