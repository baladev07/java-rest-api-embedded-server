package com.Model;

import net.bytebuddy.implementation.bind.annotation.Default;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TestCasesTable")
public class TestCasesObject {

    @Id
    @Column(name="TestId")
    int testCaseId;

    @Column(name="TestGroupId")
    int testGroupId;

    @Column(name="RequestApi")
    String reqApi;

    @Column(name="RequestHeader")
    String reqHeader;

    @Column(name="RequestPayload")
    String reqPayload;

    @Column(name="CreatedTime")
    long createdTime;

    @Column(name="LastUpdatedTime")
    long lastUpdatedTime;

    @Column(name="LastRunTime")
    long lastRunTime;

    @Column(name="ResultTime1")
    int resultTime1;

    @Column(name="ResultTime2")
    int resultTime2;

    @Column(name="ResultTime3")
    int resultTime3;

    @Column(name="ResultTime4")
    int resultTime4;

    @Column(name="ResultTime5")
    int resultTime5;

    @Column(name="RequestMethod")
    String  reqMethod;

    @Column(name="MinResponseTime")
    int minResTime;

    @Column(name="MaxResponseTime")
    int maxResTime;

    @Column(name="BuildLabel")
    String buildLabel;

    @Column(name="RunsCount")
    int runCount;

    public int getRunCount() {
        return runCount;
    }

    public void setRunCount(int runCount) {
        this.runCount = runCount;
    }

    public int getMinResTime() {
        return minResTime;
    }

    public void setMinResTime(int minResTime) {
        this.minResTime = minResTime;
    }

    public int getMaxResTime() {
        return maxResTime;
    }

    public void setMaxResTime(int maxResTime) {
        this.maxResTime = maxResTime;
    }

    public String getBuildLabel() {
        return buildLabel;
    }

    public void setBuildLabel(String buildLabel) {
        this.buildLabel = buildLabel;
    }

    public String getReqMethod() {
        return reqMethod;
    }

    public void setReqMethod(String reqMethod) {
        this.reqMethod = reqMethod;
    }

    public int getTestCaseId() {
        return testCaseId;
    }

    public void setTestCaseId(int testCaseId) {
        this.testCaseId = testCaseId;
    }

    public int getTestGroupId() {
        return testGroupId;
    }

    public void setTestGroupId(int testGroupId) {
        this.testGroupId = testGroupId;
    }

    public String getReqApi() {
        return reqApi;
    }

    public void setReqApi(String reqApi) {
        this.reqApi = reqApi;
    }

    public String getReqHeader() {
        return reqHeader;
    }

    public void setReqHeader(String reqHeader) {
        this.reqHeader = reqHeader;
    }

    public String getReqPayload() {
        return reqPayload;
    }

    public void setReqPayload(String reqPayload) {
        this.reqPayload = reqPayload;
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

    public long getLastRunTime() {
        return lastRunTime;
    }

    public void setLastRunTime(long lastRunTime) {
        this.lastRunTime = lastRunTime;
    }

    public int getResultTime1() {
        return resultTime1;
    }

    public void setResultTime1(int resultTime1) {
        this.resultTime1 = resultTime1;
    }

    public int getResultTime2() {
        return resultTime2;
    }

    public void setResultTime2(int resultTime2) {
        this.resultTime2 = resultTime2;
    }

    public int getResultTime3() {
        return resultTime3;
    }

    public void setResultTime3(int resultTime3) {
        this.resultTime3 = resultTime3;
    }

    public int getResultTime4() {
        return resultTime4;
    }

    public void setResultTime4(int resultTime4) {
        this.resultTime4 = resultTime4;
    }

    public int getResultTime5() {
        return resultTime5;
    }

    public void setResultTime5(int resultTime5) {
        this.resultTime5 = resultTime5;
    }
}
