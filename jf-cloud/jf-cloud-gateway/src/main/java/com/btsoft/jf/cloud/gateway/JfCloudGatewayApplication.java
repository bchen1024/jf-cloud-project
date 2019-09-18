package com.btsoft.jf.cloud.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * Spring Cloud Gateway 网关启动类
 * @author jeo_cb
 * @date 2019/8/28
 **/
@SpringBootApplication
@EnableZuulProxy
public class JfCloudGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(JfCloudGatewayApplication.class, args);
	}

}
