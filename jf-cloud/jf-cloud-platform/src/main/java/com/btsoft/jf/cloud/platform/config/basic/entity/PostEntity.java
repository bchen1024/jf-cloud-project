package com.btsoft.jf.cloud.platform.config.basic.entity;

import com.btsoft.jf.cloud.core.base.entity.impl.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 员工职位Entity
 * @author jeo_cb
 * @date 2019/10/7
 **/
public class PostEntity extends BaseEntity {

    private Long postId;
    private String postCn;
    private String postEn;
    private String postDesc;
    private Integer postLevel;

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getPostCn() {
        return postCn;
    }

    public void setPostCn(String postCn) {
        this.postCn = postCn;
    }

    public String getPostEn() {
        return postEn;
    }

    public void setPostEn(String postEn) {
        this.postEn = postEn;
    }

    public String getPostDesc() {
        return postDesc;
    }

    public void setPostDesc(String postDesc) {
        this.postDesc = postDesc;
    }

    public Integer getPostLevel() {
        return postLevel;
    }

    public void setPostLevel(Integer postLevel) {
        this.postLevel = postLevel;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
