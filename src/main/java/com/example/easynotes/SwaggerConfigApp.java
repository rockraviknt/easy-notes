package com.example.easynotes;




import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.google.common.base.Predicate;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static springfox.documentation.builders.PathSelectors.regex;

import javax.sql.DataSource;

import static com.google.common.base.Predicates.or;

@Configuration
@EnableSwagger2
@EnableJpaAuditing
public class SwaggerConfigApp{
	

	@Bean
	public Docket postsApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("public-api")
				.apiInfo(apiInfo()).select().paths(postPaths()).build();
	}

	private Predicate<String> postPaths() {
		return or(regex("/api.*"), regex("/api/notes.*"));
	}
	
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("My First Application")
				.description("Ravi API reference for developers")
				.termsOfServiceUrl("http://ravikant.com")
				.contact("ravikant@gmail.com").license("My License")
				.licenseUrl("ravikant@gmail.com").version("1.0").build();
	}

}