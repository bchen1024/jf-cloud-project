package com.btsoft.jf.cloud.platform.config.entity;

import com.btsoft.jf.cloud.core.base.entity.impl.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 员工职位Entity
 * @author jeo_cb
 * @date 2019/10/7
 **/
public class EmployeeJobEntity extends BaseEntity {

    private Long jobId;
    private String jobCn;
    private String jobEn;
    private String jobDesc;

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public String getJobCn() {
        return jobCn;
    }

    public void setJobCn(String jobCn) {
        this.jobCn = jobCn;
    }

    public String getJobEn() {
        return jobEn;
    }

    public void setJobEn(String jobEn) {
        this.jobEn = jobEn;
    }

    public String getJobDesc() {
        return jobDesc;
    }

    public void setJobDesc(String jobDesc) {
        this.jobDesc = jobDesc;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
