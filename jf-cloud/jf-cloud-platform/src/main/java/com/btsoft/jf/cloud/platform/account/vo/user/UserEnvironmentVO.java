package com.btsoft.jf.cloud.platform.account.vo.user;

import com.btsoft.jf.cloud.platform.security.vo.app.AppBaseVO;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * 用户环境VO
 * @author jeo_cb
 * @date 2019/10/4
 **/
public class UserEnvironmentVO implements Serializable {

    private static final long serialVersionUID = 6212782306928397048L;
    private UserBaseVO user;

    private List<AppBaseVO> appList;

    private AppBaseVO appInfo;

    private List<String> permissionList;

    public UserBaseVO getUser() {
        return user;
    }

    public void setUser(UserBaseVO user) {
        this.user = user;
    }

    public List<AppBaseVO> getAppList() {
        return appList;
    }

    public void setAppList(List<AppBaseVO> appList) {
        this.appList = appList;
    }

    public AppBaseVO getAppInfo() {
        return appInfo;
    }

    public void setAppInfo(AppBaseVO appInfo) {
        this.appInfo = appInfo;
    }

    public List<String> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<String> permissionList) {
        this.permissionList = permissionList;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
