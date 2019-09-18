package com.btsoft.jf.cloud.admin.server;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Spring Cloud Admin启动类
 * @author jeo_cb
 * @date 2019/8/28
 **/
@EnableAdminServer
@EnableEurekaClient
@SpringBootApplication
public class JfCloudAdminServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(JfCloudAdminServerApplication.class, args);
	}

}
