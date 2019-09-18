package com.btsoft.jf.cloud.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 网关前置过滤器
 * @author jeo_cb
 * @date 2019/8/30
 **/
@Component
public class GatewayPreFilter extends ZuulFilter {

    private final static Logger logger= LoggerFactory.getLogger(GatewayPreFilter.class);

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest req = ctx.getRequest();
        if(logger.isDebugEnabled()){
            logger.debug("[{}][{}]",req.getMethod(),req.getRequestURI());
        }
        return null;
    }
}
