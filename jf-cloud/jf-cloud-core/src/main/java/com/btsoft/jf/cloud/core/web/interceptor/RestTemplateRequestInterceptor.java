package com.btsoft.jf.cloud.core.web.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;

/**
 * RestTemplate请求拦截
 * @author jeo_cb
 * @date 2019/10/6
 **/
@Component
public class RestTemplateRequestInterceptor implements ClientHttpRequestInterceptor {
	
	private final static Logger logger=LoggerFactory.getLogger(RestTemplateRequestInterceptor.class);

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
			throws IOException {
		traceRequest(request,body);
		ClientHttpResponse response=execution.execute(request, body);
		return response;
	}
	
	private void traceRequest(HttpRequest request, byte[] body) throws IOException {
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		if(requestAttributes!=null){
			HttpServletRequest req = requestAttributes.getRequest();
			Enumeration<String> headers= req.getHeaderNames();
			while (headers.hasMoreElements()){
				String headerName=headers.nextElement();
				request.getHeaders().add(headerName,req.getHeader(headerName));
			}
			if(logger.isDebugEnabled()) {
				logger.debug("===========================restTemplate request begin================================================");
				logger.debug("URL:        : [{}][{}]", request.getMethod(),request.getURI());
				logger.debug("Headers     : [{}]", request.getHeaders());
				if(body!=null && body.length>0){
					logger.debug("Request Body: {}", new String(body, "UTF-8"));
				}
				logger.debug("==========================restTemplate request end================================================");
			}
		}
    }
}
