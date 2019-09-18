package com.btsoft.jf.cloud.admin.server.configuration;

import de.codecentric.boot.admin.server.config.AdminServerProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

/**
 * Spring Cloud Admin安全配置
 * @author jeo_cb
 * @date 2019/8/28
 **/
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final String adminContextPath;

    public WebSecurityConfig(AdminServerProperties adminServerProperties) {
        this.adminContextPath = adminServerProperties.getContextPath();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        SavedRequestAwareAuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
        successHandler.setTargetUrlParameter("redirectTo");
        http.authorizeRequests()
                //忽略静态资源
                .antMatchers(adminContextPath + "/assets/**").permitAll()
                //忽略登录页面
                .antMatchers(adminContextPath + "/login").permitAll()
                //其他请求需要登录
                .anyRequest().authenticated()
                .and()
                //替换默认的登录页面
                .formLogin().loginPage(adminContextPath + "/login").successHandler(successHandler).and()
                //替换默认的登出url
                .logout().logoutUrl(adminContextPath + "/logout").and()
                .httpBasic().and()
                //禁用csrf
                .csrf().disable();
        // @formatter:on
    }
}
