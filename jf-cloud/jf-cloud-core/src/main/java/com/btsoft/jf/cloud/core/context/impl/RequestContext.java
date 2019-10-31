package com.btsoft.jf.cloud.core.context.impl;

import com.btsoft.jf.cloud.core.context.IRequestContext;
import com.btsoft.jf.cloud.core.enums.impl.LanguageEnum;
import com.btsoft.jf.cloud.core.util.DESEncryptUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 请求上下文
 * @author jeo_cb
 * @date 2019/8/29
 **/
public class RequestContext implements IRequestContext {

    private String token;
    private String requestApp;
    private String language;
    private String requestIp;
    private Long currentUserId;
    private String sessionId;
    private String appCode;

    @Override
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String getRequestApp() {
        return requestApp;
    }

    public void setRequestApp(String requestApp) {
        this.requestApp = requestApp;
    }

    @Override
    public String getLanguage() {
        if(language==null){
            language= LanguageEnum.CN.getKey();
        }
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public String getRequestIp() {
        return requestIp;
    }

    public void setRequestIp(String requestIp) {
        this.requestIp = requestIp;
    }

    @Override
    public Long getCurrentUserId() {
        if(token!=null){
            //TODO 解密token得到userId
            if(!token.startsWith("Basic")){
                currentUserId= Long.parseLong(DESEncryptUtils.dncrypt(token));
            }
        }
        if(currentUserId==null && JfCloud.isDev()){
            currentUserId=1000L;
        }
        return currentUserId;
    }

    public void setCurrentUserId(Long currentUserId) {
        this.currentUserId = currentUserId;
    }

    @Override
    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    @Override
    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
