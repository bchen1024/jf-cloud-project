package com.btsoft.jf.cloud.core.auth.impl;

import com.btsoft.jf.cloud.core.auth.IValidService;
import com.btsoft.jf.cloud.core.constant.RequestConstants;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 网关校验
 * @author jeo_cb
 * @date 2019/8/30
 **/
@Service
public class GatewayValidServiceImpl implements IValidService {

    @Override
    public boolean valid(HttpServletRequest req, HttpServletResponse resp) {
        boolean validFlag=true;
        //校验是否从网关访问
        String url=req.getRequestURI();
        String zuulCloudGateway=req.getHeader(RequestConstants.Header.X_CLOUD_GATEWAY);

        //1、请求url中必须包含网关上下文；2、请求头中必须包含网关标识
        if(!url.contains(RequestConstants.Url.GATEWAY_CONTEXT_PATH) &&
                Boolean.TRUE.toString().equals(zuulCloudGateway)){
            validFlag=false;
        }
        return validFlag;
    }

    @Override
    public int sort() {
        return 10;
    }
}
