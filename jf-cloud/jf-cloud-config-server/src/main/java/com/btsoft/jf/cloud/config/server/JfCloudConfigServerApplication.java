package com.btsoft.jf.cloud.config.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * JF Cloud配置中心服务
 * @author jeo_cb
 * @date 2019/8/31
 **/
@SpringBootApplication
@EnableConfigServer
public class JfCloudConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(JfCloudConfigServerApplication.class, args);
	}

}
