package com.btsoft.jf.cloud.monitor.dto;

import com.btsoft.jf.cloud.core.base.dto.impl.PageAppDTO;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;
import java.util.List;

/**
 * @author jeo_cb
 * @desc 审计日志查询DTo
 * @date 2019/10/12 11:54
 **/
public class AuditLogQueryDTO extends PageAppDTO {

    private List<Date> logTimes;

    private String userName;

    private String logIp;

    private String logModule;

    private String logMethod;

    private String logMessage;

    private List<Date> logStartTimes;

    private List<Date> logEndTimes;

    private String logType;

    public List<Date> getLogTimes() {
        return logTimes;
    }

    public void setLogTimes(List<Date> logTimes) {
        this.logTimes = logTimes;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public List<Date> getLogStartTimes() {
        return logStartTimes;
    }

    public void setLogStartTimes(List<Date> logStartTimes) {
        this.logStartTimes = logStartTimes;
    }

    public List<Date> getLogEndTimes() {
        return logEndTimes;
    }

    public void setLogEndTimes(List<Date> logEndTimes) {
        this.logEndTimes = logEndTimes;
    }

    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
