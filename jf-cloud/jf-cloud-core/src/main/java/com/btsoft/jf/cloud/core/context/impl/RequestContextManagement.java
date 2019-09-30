package com.btsoft.jf.cloud.core.context.impl;

import com.btsoft.jf.cloud.core.constant.EnvironmentConstants;
import com.btsoft.jf.cloud.core.constant.RequestConstants;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * 请求上下文管理器
 * @author jeo_cb
 * @date 2019/8/29
 **/
public class RequestContextManagement {

    /**
     * 根据请求构建请求上下文
     * @author jeo_cb
     * @date 2019/8/29
     * @param req 请求
     * @return 请求上下文
     **/
    public static RequestContext buildRequestContext(HttpServletRequest req){
        RequestContext rc=new RequestContext();
        //Token
        String token=StringUtils.trim(req.getHeader(RequestConstants.Header.TOKEN));
        if(StringUtils.isEmpty(token)){
            token=StringUtils.trim(req.getHeader(RequestConstants.Header.AUTHORIZATION));
        }
        if(StringUtils.isEmpty(token)){
            token=StringUtils.trim(req.getParameter(RequestConstants.Url.TOKEN));
        }
        //TODO 校验token有效性
        rc.setToken(token);

        //App Code
        String appCode=StringUtils.trim(req.getHeader(RequestConstants.Header.X_CLOUD_APP));
        //TODO 校验app有效性
        rc.setRequestApp(appCode);

        //Language
        String language=StringUtils.trim(req.getHeader(RequestConstants.Header.LANGUAGE));
        if(StringUtils.isEmpty(language)){
            language=StringUtils.trim(req.getParameter(RequestConstants.Header.CURRENT_LANGUAGE));
        }
        //TODO 校验语言合法性
        rc.setLanguage(language);

        //Request IP
        String requestIp=JfCloud.getEnvironment().getProperty(EnvironmentConstants.Spring.SPRING_CLOUD_CLIENT_IP);
        rc.setRequestIp(requestIp);

        //SessionId
        rc.setSessionId(req.getRequestedSessionId());

        return rc;
    }
}
