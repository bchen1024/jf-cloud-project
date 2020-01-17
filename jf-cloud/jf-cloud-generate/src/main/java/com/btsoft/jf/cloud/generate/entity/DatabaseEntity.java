package com.btsoft.jf.cloud.generate.entity;


import com.btsoft.jf.core.mapper.annotation.Column;
import com.btsoft.jf.core.mapper.annotation.Table;
import com.btsoft.jf.core.mapper.entity.impl.BaseEntity;

/**
 * 数据库实体
 * @author jeo_cb
 * @date 2020/1/12
 **/
@Table(name = "db_database_t")
public class DatabaseEntity extends BaseEntity<Long> {

    private static final long serialVersionUID = 4477559355032496845L;
    @Column
    private String name;
    @Column
    private String url;
    @Column
    private String type;
    @Column(name = "user_name")
    private String userName;
    @Column
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
