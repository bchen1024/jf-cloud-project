package com.btsoft.jf.cloud.core.web.filter;

import com.alibaba.fastjson.JSON;
import com.btsoft.jf.cloud.core.constant.ErrorCodeConstants;
import com.btsoft.jf.cloud.core.constant.RequestConstants;
import com.btsoft.jf.cloud.core.constant.StringConstants;
import com.btsoft.jf.cloud.core.context.impl.JfCloud;
import com.btsoft.jf.cloud.core.context.impl.RequestContext;
import com.btsoft.jf.cloud.core.context.impl.RequestContextManagement;
import com.btsoft.jf.cloud.core.exception.AuthorizationException;
import com.btsoft.jf.cloud.core.util.ResponseUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

/**
 * 鉴权过滤器
 * @author jeo_cb
 * @date 2019/8/29
 **/
@Component
@WebFilter(
        filterName = "authorizationFilter",
        urlPatterns = "/*"
)
public class AuthorizationFilter extends HttpFilter {
    private final static Logger logger= LoggerFactory.getLogger(AuthorizationFilter.class);

    private final static Set<String> IGNORE_URL=new TreeSet<>();

    @Value("${jf.cloud.ignoreUrl}")
    private static String ignoreUrl;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        super.init(filterConfig);
        IGNORE_URL.addAll(Arrays.asList(RequestConstants.Url.IGNORE_URL.split(StringConstants.COMMA)));
        if (StringUtils.isNotEmpty(ignoreUrl)) {
            IGNORE_URL.addAll(Arrays.asList(ignoreUrl.split(StringConstants.COMMA)));
        }
        if(logger.isInfoEnabled()){
            logger.info("authorization ignore url:[{}]", JSON.toJSONString(IGNORE_URL));
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req=(HttpServletRequest)servletRequest;
        HttpServletResponse resp=(HttpServletResponse)servletResponse;
        if(logger.isDebugEnabled()){
            logger.debug("[{}][{}]",req.getMethod(),req.getRequestURI());
        }
        try{
            //构建请求上下文
            RequestContext rc=RequestContextManagement.buildRequestContext(req);
            JfCloud.setCurrent(rc);
            filterChain.doFilter(servletRequest,servletResponse);
        }catch (AuthorizationException ex){
            ResponseUtils.writeException(resp,ex.getCode(),ex);
        }catch (Exception e){
            ResponseUtils.writeException(resp, ErrorCodeConstants.Auth.AUTH_FAIL,e);
        }
        return;
    }

    private boolean ignoreRequest(HttpServletRequest req){
        String url = req.getRequestURI();
        for (String ignore : IGNORE_URL) {
            if (url.contains(ignore)) {
                return true;
            }
        }
        return false;
    }
}
