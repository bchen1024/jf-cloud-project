package com.btsoft.jf.core.context.impl;

import com.btsoft.jf.core.constant.RequestConstants;
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
            token=StringUtils.trim(req.getParameter(RequestConstants.Url.TOKEN));
        }
        //TODO 校验token有效性
        rc.setToken(token);

        //Request App
        String requestApp=StringUtils.trim(req.getHeader(RequestConstants.Header.X_CLOUD_APP));
        //TODO 校验app有效性，判断app是否合法
        rc.setRequestApp(requestApp);

        //App Code
        String appCode=StringUtils.trim(req.getHeader(RequestConstants.Header.APP_CODE));
        //TODO 校验app有效性，判断app是否合法
        rc.setAppCode(appCode);

        //Language
        String language=StringUtils.trim(req.getHeader(RequestConstants.Header.LANGUAGE));
        //TODO 校验语言合法性
        rc.setLanguage(language);

        //Request IP


        //SessionId
        rc.setSessionId(req.getRequestedSessionId());

        return rc;
    }
}
