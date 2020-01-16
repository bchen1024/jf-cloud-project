package com.btsoft.jf.cloud.generate.entity;


import com.btsoft.jf.core.base.entity.impl.BaseEntity;

/**
 * 数据库实体
 * @author jeo_cb
 * @date 2020/1/12
 **/
public class DatabaseEntity extends BaseEntity<Long> {

    private static final long serialVersionUID = 4477559355032496845L;
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
}
