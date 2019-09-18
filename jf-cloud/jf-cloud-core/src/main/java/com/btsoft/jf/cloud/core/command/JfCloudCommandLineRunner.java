package com.btsoft.jf.cloud.core.command;

import com.btsoft.jf.cloud.core.constant.EnvironmentConstants;
import com.btsoft.jf.cloud.core.constant.StringConstants;
import com.btsoft.jf.cloud.core.context.impl.JfCloud;
import com.btsoft.jf.cloud.core.exception.CommonException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * JF Cloud启动检查
 * @author jeo_cb
 * @date 2019/9/1
 **/
@Order(1)
@Component
public class JfCloudCommandLineRunner implements CommandLineRunner {

    private final static Logger logger= LoggerFactory.getLogger(JfCloudCommandLineRunner.class);

    @Override
    public void run(String... args) throws Exception {
        if(logger.isInfoEnabled()){
            logger.info("-------------------启动检查-------------------");

            logger.info("检查配置[{}]={},检查结果:{}", EnvironmentConstants.Spring.PROPERTY_KEY_PROFILES,
                    StringUtils.join(JfCloud.getProfiles(), StringConstants.COMMA),JfCloud.checkEnvironment());

            String appCode=JfCloud.getEnvironment().getProperty(EnvironmentConstants.Jf.JF_CLOUD_APPCODE);
            if(StringUtils.isEmpty(appCode)){
                throw new CommonException("",String.format("[%s]配置不能为空",
                        EnvironmentConstants.Jf.JF_CLOUD_APPCODE));
            }
            logger.info("检查配置[{}]={},检查结果:{}", EnvironmentConstants.Jf.JF_CLOUD_APPCODE,
                    appCode,StringUtils.isNotEmpty(appCode));
        }
    }
}
