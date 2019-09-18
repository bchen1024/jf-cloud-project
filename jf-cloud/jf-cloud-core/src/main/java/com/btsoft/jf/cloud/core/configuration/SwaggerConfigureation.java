package com.btsoft.jf.cloud.core.configuration;

import com.google.common.base.Predicate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * Swagger配置类
 * @author chenbin
 * @date 2018-11-27 22:41
 */
@Configuration
@EnableSwagger2
public class SwaggerConfigureation {

	@Value("${jf.cloud.swagger.title:JF Cloud Swagger Title}")
	private String title;

	@Value("${jf.cloud.swagger.description:JF Cloud Swagger Description}")
	private String description;

	@Value("${jf.cloud.swagger.version:1.0}")
	private String version;

	@Value("${jf.cloud.swagger.name:userName}")
	private String name;

	@Value("${jf.cloud.swagger.url:}")
	private String url;

	@Value("${jf.cloud.swagger.email:}")
	private String email;

	@Value("${jf.cloud.swagger.basePackage:com.btsoft.jf.cloud}")
	private String basePackage;

	@Bean
	public Docket apiDocket() {
		ParameterBuilder pb = new ParameterBuilder();
		List<Parameter> pars = new ArrayList<Parameter>();
		pb.name("Authorization").description("Token令牌").parameterType("header").modelRef(new ModelRef("String"))
				.build();
		pars.add(pb.build());
		ApiInfo apiInfo = new ApiInfoBuilder().title(title).description(description).termsOfServiceUrl("")
				.version(version).contact(new Contact(name, url, email)).build();
		Predicate<RequestHandler> apiSelector = RequestHandlerSelectors.basePackage(basePackage);
		Predicate<String> pathSelecter = PathSelectors.any();
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo).select().apis(apiSelector).paths(pathSelecter)
				.build().globalOperationParameters(pars);
	}
}
