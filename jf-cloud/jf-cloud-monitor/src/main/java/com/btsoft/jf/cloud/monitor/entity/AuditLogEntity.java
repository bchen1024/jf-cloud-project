package com.btsoft.jf.cloud.monitor.entity;

import com.btsoft.jf.cloud.core.base.entity.impl.BaseEntity;

import java.util.Date;

/**
 * 审计日志Entity
 * @author jeo_cb
 * @date 2019/10/12 10:32
 **/
public class AuditLogEntity extends BaseEntity {

    private Long logId;

    private Date logTime;

    private Long userId;

    private String logIp;

    private String logModule;

    private String logMethod;

    private String logMessage;

    private Date logStartTime;

    private Date logEndTime;

    private Long logCost;

    private String logType;

    private String appCode;

    public Long getLogId() {
        return logId;
    }

    public void setLogId(Long logId) {
        this.logId = logId;
    }

    public Date getLogTime() {
        return logTime;
    }

    public void setLogTime(Date logTime) {
        this.logTime = logTime;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getLogIp() {
        return logIp;
    }

    public void setLogIp(String logIp) {
        this.logIp = logIp;
    }

    public String getLogModule() {
        return logModule;
    }

    public void setLogModule(String logModule) {
        this.logModule = logModule;
    }

    public String getLogMethod() {
        return logMethod;
    }

    public void setLogMethod(String logMethod) {
        this.logMethod = logMethod;
    }

    public String getLogMessage() {
        return logMessage;
    }

    public void setLogMessage(String logMessage) {
        this.logMessage = logMessage;
    }

    public Date getLogStartTime() {
        return logStartTime;
    }

    public void setLogStartTime(Date logStartTime) {
        this.logStartTime = logStartTime;
    }

    public Date getLogEndTime() {
        return logEndTime;
    }

    public void setLogEndTime(Date logEndTime) {
        this.logEndTime = logEndTime;
    }

    public Long getLogCost() {
        return logCost;
    }

    public void setLogCost(Long logCost) {
        this.logCost = logCost;
    }

    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }
}
