package com.btsoft.jf.cloud.core.configuration;

import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * JF Cloud自动配置
 * @author jeo_cb
 * @date 2019/8/29
 **/
@Configuration
@ServletComponentScan
@EnableTransactionManagement
@EnableAspectJAutoProxy
@EnableAsync
@EnableCaching
@ComponentScan({"com.btsoft.jf.cloud.core"})
public class JfCloudAutoConfiguration {
}
