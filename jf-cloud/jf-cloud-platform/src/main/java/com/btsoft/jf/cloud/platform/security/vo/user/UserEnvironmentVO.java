package com.btsoft.jf.cloud.platform.security.vo.user;

import com.btsoft.jf.cloud.platform.security.vo.app.AppBaseVO;

import java.util.List;

/**
 * 用户环境VO
 * @author jeo_cb
 * @date 2019/10/4
 **/
public class UserEnvironmentVO {

    private UserBaseVO user;

    private List<AppBaseVO> appList;

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
}
