package com.btsoft.jf.cloud.monitor.dto;

import com.btsoft.jf.cloud.core.base.dto.impl.SearchKeywordAppDTO;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;
import java.util.List;

/**
 * @author jeo_cb
 * @desc 审计日志查询DTo
 * @date 2019/10/12 11:54
 **/
public class AuditLogQueryDTO extends SearchKeywordAppDTO {

    private static final long serialVersionUID = 7765900864175671508L;
    private List<Date> logTime;

    private Long userId;

    private String logIp;

    private String logModule;

    private String logMethod;

    private String logMessage;

    private List<Date> logStartTime;

    private List<Date> logEndTime;

    private String logType;

    public List<Date> getLogTime() {
        return logTime;
    }

    public void setLogTime(List<Date> logTime) {
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

    public List<Date> getLogStartTime() {
        return logStartTime;
    }

    public void setLogStartTime(List<Date> logStartTime) {
        this.logStartTime = logStartTime;
    }

    public List<Date> getLogEndTime() {
        return logEndTime;
    }

    public void setLogEndTime(List<Date> logEndTime) {
        this.logEndTime = logEndTime;
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
