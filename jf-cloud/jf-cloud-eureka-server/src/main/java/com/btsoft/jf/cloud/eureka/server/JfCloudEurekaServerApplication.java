package com.btsoft.jf.cloud.eureka.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Spring Cloud Eureka Server启动类
 * @author jeo_cb
 * @date 2019/8/28
 **/
@SpringBootApplication
@EnableEurekaServer
public class JfCloudEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(JfCloudEurekaServerApplication.class, args);
	}

}
