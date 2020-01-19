package com.btsoft.jf.cloud.generate.dto;

import com.btsoft.jf.core.pojo.dto.impl.BaseDTO;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 数据源保存DTO
 * @author jeo_cb
 * @date 2020/1/19 11:20
 **/
public class DatabaseSaveDTO extends BaseDTO {
    private static final long serialVersionUID = -3934634316723700696L;

    private String name;
    private String url;
    private String type;
    private String userName;
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
