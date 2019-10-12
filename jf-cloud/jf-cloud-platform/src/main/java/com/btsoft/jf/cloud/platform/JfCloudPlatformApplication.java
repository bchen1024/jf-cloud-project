package com.btsoft.jf.cloud.platform;

import com.btsoft.jf.cloud.core.web.interceptor.RestTemplateRequestInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * JF Cloud 平台启动类
 * @author jeo_cb
 * @date 2019/8/28
 **/
@SpringBootApplication
public class JfCloudPlatformApplication {

	public static void main(String[] args) {
		SpringApplication.run(JfCloudPlatformApplication.class, args);
	}

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		RestTemplate restTemplate=new RestTemplate();
		restTemplate.getInterceptors().add(new RestTemplateRequestInterceptor());
		return restTemplate;
	}
}
